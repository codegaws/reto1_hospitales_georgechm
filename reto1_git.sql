-- CREACIÓN DE SECUENCIA PARA idHospital
CREATE SEQUENCE seq_Hospital START WITH 1 INCREMENT BY 1 NOCACHE;

-- CREACIÓN DE TABLAS
CREATE TABLE Provincia (
    idProvincia INT PRIMARY KEY,
    descProvincia VARCHAR2(100),
    fechaRegistro DATE
);

CREATE TABLE Distrito (
    idDistrito INT PRIMARY KEY,
    idProvincia INT,
    descDistrito VARCHAR2(100),
    fechaRegistro DATE,
    FOREIGN KEY (idProvincia) REFERENCES Provincia(idProvincia)
);

CREATE TABLE Sede (
    idSede INT PRIMARY KEY,
    descSede VARCHAR2(100),
    fechaRegistro DATE
);

CREATE TABLE Gerente (
    idGerente INT PRIMARY KEY,
    descGerente VARCHAR2(100),
    fechaRegistro DATE
);

CREATE TABLE Condicion (
    idCondicion INT PRIMARY KEY,
    descCondicion VARCHAR2(100),
    fechaRegistro DATE
);

CREATE TABLE Hospital (
    idHospital INT PRIMARY KEY,
    idDistrito INT,
    Nombre VARCHAR2(100),
    Antiguedad INT,
    Area DECIMAL(5,2),
    idSede INT,
    idGerente INT,
    idCondicion INT,
    fechaRegistro DATE,
    FOREIGN KEY (idDistrito) REFERENCES Distrito(idDistrito),
    FOREIGN KEY (idSede) REFERENCES Sede(idSede),
    FOREIGN KEY (idGerente) REFERENCES Gerente(idGerente),
    FOREIGN KEY (idCondicion) REFERENCES Condicion(idCondicion)
);

-- PROCEDIMIENTOS ALMACENADOS

-- Registrar hospital con validaciones
CREATE OR REPLACE PROCEDURE SP_HOSPITAL_REGISTRAR (
    p_idDistrito INT,
    p_Nombre VARCHAR2,
    p_Antiguedad INT,
    p_Area NUMBER,
    p_idSede INT,
    p_idGerente INT,
    p_idCondicion INT
) AS
BEGIN
    BEGIN
        -- Validaciones
        IF p_idDistrito IS NULL OR p_Nombre IS NULL OR p_Antiguedad IS NULL OR p_Area IS NULL 
            OR p_idSede IS NULL OR p_idGerente IS NULL OR p_idCondicion IS NULL THEN
            RAISE_APPLICATION_ERROR(-20010, 'Error: Ningún campo puede ser NULL.');
        END IF;
        
        IF p_Antiguedad < 0 OR p_Area <= 0 THEN
            RAISE_APPLICATION_ERROR(-20011, 'Error: Antigüedad y Área deben ser valores positivos.');
        END IF;
        
        INSERT INTO Hospital (idHospital, idDistrito, Nombre, Antiguedad, Area, idSede, idGerente, idCondicion, fechaRegistro)
        VALUES (seq_Hospital.NEXTVAL, p_idDistrito, p_Nombre, p_Antiguedad, p_Area, p_idSede, p_idGerente, p_idCondicion, SYSDATE);
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error al registrar hospital: ' || SQLERRM);
    END;
END;
/

-- Actualizar hospital con validaciones
CREATE OR REPLACE PROCEDURE SP_HOSPITAL_ACTUALIZAR (
    p_idHospital INT,
    p_idDistrito INT,
    p_idSede INT,
    p_idGerente INT,
    p_idCondicion INT
) AS
BEGIN
    BEGIN
        -- Validaciones
        IF p_idHospital IS NULL THEN
            RAISE_APPLICATION_ERROR(-20012, 'Error: ID de hospital no puede ser NULL.');
        END IF;
        
        UPDATE Hospital
        SET idDistrito = p_idDistrito, 
            idSede = p_idSede, 
            idGerente = p_idGerente, 
            idCondicion = p_idCondicion
        WHERE idHospital = p_idHospital;
        
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20014, 'Error: No se encontró el hospital con el ID especificado.');
        END IF;
        
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error al actualizar hospital: ' || SQLERRM);
    END;
END;
/

-- Eliminar hospital con validación
CREATE OR REPLACE PROCEDURE SP_HOSPITAL_ELIMINAR (
    p_idHospital INT
) AS
BEGIN
    BEGIN
        IF p_idHospital IS NULL THEN
            RAISE_APPLICATION_ERROR(-20015, 'Error: ID de hospital no puede ser NULL.');
        END IF;
        
        DELETE FROM Hospital WHERE idHospital = p_idHospital;
        
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20016, 'Error: No se encontró el hospital con el ID especificado.');
        END IF;
        
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error al eliminar hospital: ' || SQLERRM);
    END;
END;
/

-- Listar hospitales dinámicamente
CREATE OR REPLACE PROCEDURE SP_HOSPITAL_LISTAR (p_condicion VARCHAR2 DEFAULT NULL) AS
    v_sql VARCHAR2(1000);
    v_cursor SYS_REFCURSOR;
    v_idHospital Hospital.idHospital%TYPE;
    v_Nombre Hospital.Nombre%TYPE;
    v_Antiguedad Hospital.Antiguedad%TYPE;
    v_Area Hospital.Area%TYPE;
    v_descDistrito Distrito.descDistrito%TYPE;
    v_descGerente Gerente.descGerente%TYPE;
    v_descSede Sede.descSede%TYPE;
    v_descCondicion Condicion.descCondicion%TYPE;
BEGIN
    v_sql := 'SELECT H.idHospital, H.Nombre, H.Antiguedad, H.Area, D.descDistrito, G.descGerente, S.descSede, C.descCondicion
              FROM Hospital H
              JOIN Distrito D ON H.idDistrito = D.idDistrito
              JOIN Gerente G ON H.idGerente = G.idGerente
              JOIN Sede S ON H.idSede = S.idSede
              JOIN Condicion C ON H.idCondicion = C.idCondicion';
    
    IF p_condicion IS NOT NULL THEN
        v_sql := v_sql || ' WHERE C.descCondicion = ''' || p_condicion || '''';
    END IF;
    
    OPEN v_cursor FOR v_sql;
    
    LOOP
        FETCH v_cursor INTO v_idHospital, v_Nombre, v_Antiguedad, v_Area, v_descDistrito, v_descGerente, v_descSede, v_descCondicion;
        EXIT WHEN v_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(v_idHospital || ' - ' || v_Nombre || ' | ' || v_Antiguedad || ' años | ' || v_Area || 'm2 | ' || v_descDistrito || ' | ' || v_descGerente || ' | ' || v_descSede || ' | ' || v_descCondicion);
    END LOOP;
    
    CLOSE v_cursor;
END;