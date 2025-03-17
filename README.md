# Reto 1 - Hospitales 🔥🔥🔥

En Oracle, registro de tablas 🚀
(Gerente, Condición, Distrito, Sede, Provincia) menos en la tabla Hospital. 
## Grafico de las relaciones

![image](https://github.com/user-attachments/assets/e824b0b7-d15b-44b6-9530-c9cfbd224af7)

### Detalle de los PROCEDIMIENTOS ALMACENADOS 🚀

### SP_HOSPITAL_REGISTRAR 📌
Se encarga de registrar un nuevo hospital en la base de datos, asegurando que los datos sean válidos antes de la inserción.
- No se permite insertar valores NULL en ningún parámetro.
- Si algún parámetro es NULL, se lanza el error -20010.
- p_Antiguedad debe ser un número positivo (>= 0).
- p_Area debe ser mayor que 0.
  
### Insercion 

Si no se cumplen estas condiciones, se lanza el error -20011.
El procedimiento inserta un nuevo registro en la tabla Hospital con los datos proporcionados.
Se usa seq_Hospital.NEXTVAL para generar un idHospital único.
Se registra automáticamente la fecha de creación con SYSDATE.

```sql
BEGIN
    SP_HOSPITAL_REGISTRAR(
        p_idDistrito => 3,
        p_Nombre => 'Hospital Central',
        p_Antiguedad => 15,
        p_Area => 2500,
        p_idSede => 1,
        p_idGerente => 5,
        p_idCondicion => 2
    );
END;

```

### Manejo de Errores

Si ocurre un error durante la inserción, el procedimiento:
Realiza un ROLLBACK para evitar datos inconsistentes.
Muestra un mensaje en la consola con DBMS_OUTPUT.PUT_LINE.


### SP_HOSPITAL_ACTUALIZAR 📌
permite actualizar la información de un hospital en la base de datos. Se asegura de que el hospital exista y
que los datos ingresados sean válidos antes de aplicar los cambios.

#### Validaciones Implementadas
El ID del hospital es obligatorio:
Si p_idHospital es NULL, se lanza el error -20012.

Verificación de existencia del hospital:
Si el UPDATE no afecta ninguna fila (SQL%ROWCOUNT = 0), significa que el idHospital no existe en la base de datos y se lanza el error -20014.

 #### Manejo de Errores
Si el hospital no existe (SQL%ROWCOUNT = 0), se lanza una excepción (-20014).
Si ocurre un error durante la actualización, el procedimiento:
Realiza un ROLLBACK para evitar datos inconsistentes.
Muestra un mensaje en la consola con DBMS_OUTPUT.PUT_LINE.

```sql
BEGIN
    SP_HOSPITAL_ACTUALIZAR(
        p_idHospital => 101,
        p_idDistrito => 8,
        p_idSede => 4,
        p_idGerente => 10,
        p_idCondicion => 2
    );
END;
```
### SP_HOSPITAL_ELIMINAR 📌

Permite eliminar un hospital de la base de datos mediante su idHospital. Antes de eliminar, se realizan validaciones para garantizar 
que el ID proporcionado sea válido.

#### Validaciones Implementadas
El ID del hospital es obligatorio:

Si p_idHospital es NULL, se lanza el error -20015.
Verificación de existencia del hospital:

Si el DELETE no afecta ninguna fila (SQL%ROWCOUNT = 0), significa que el idHospital no existe en la base de datos y se lanza el error -20016.

#### Manejo de Errores
Si el idHospital no existe en la tabla, se lanza una excepción con el código -20016.
Si ocurre un error durante la eliminación, el procedimiento:
Realiza un ROLLBACK para evitar datos inconsistentes.
Muestra un mensaje de error en la consola con DBMS_OUTPUT.PUT_LINE.

```sql
BEGIN
    SP_HOSPITAL_ELIMINAR(p_idHospital => 101);
END;
```

### SP_HOSPITAL_LISTAR 📌 dinamico

Permite obtener una lista de hospitales, incluyendo detalles sobre su ubicación, gerente, sede y condición. 
Se puede filtrar por descCondicion para listar hospitales con una condición específica.

Funcionamiento
Consulta principal:

Se obtiene información del hospital junto con la descripción de su distrito, gerente, sede y condición.
Se utilizan JOINs para unir las tablas Hospital, Distrito, Gerente, Sede y Condicion.

Filtrado dinámico:
Si se proporciona el parámetro p_condicion, la consulta incluirá un WHERE para listar solo los hospitales con esa condición.

Recorrido del cursor:
Se abre un SYS_REFCURSOR para iterar sobre los resultados y mostrarlos con DBMS_OUTPUT.PUT_LINE.

```sql
BEGIN
    SP_HOSPITAL_LISTAR('Activo');
END;

```
## auhor George Chinchayan Martinez 🔥🔥🔥
