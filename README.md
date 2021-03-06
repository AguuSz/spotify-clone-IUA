# Spotify clone IUA

Proyecto realizado para el Instituto Universitario Aeronautico, especificamente para la materia Ingenieria Web 1.

Este proyecto consiste en la creacion de un backend lo mas parecido posible al de Spotify, donde podremos crear playlists, obtener sugerencias en base a nuestros gustos musicales y demas.

# Prerequisitos
Crear en la carpeta raiz una carpeta llamada "conf" y dentro de esta misma crear un archivo llamado "db.properties". En este archivo deberan rellenar con las credenciales para acceder a la base de datos. Aqui un ejemplo de como deberia quedar: 
```
# Mysql DB properties
MYSQL_DB_DRIVER_CLASS=com.mysql.jdbc.Driver
MYSQL_DB_URL=jdbc:mysql://localhost:3306/NombreDeLaBD
MYSQL_DB_USERNAME=IngreseAquiSuUsername
MYSQL_DB_PASSWORD=IngreseAquiSuPassword
```

## Estructura del proyecto
- **bd**: Dentro de esta carpeta encontraremos los archivos necesarios para crear la base de datos.
    * **datos**: Dentro de esta carpeta encontraremos la data a importar a la BD.
    * **modelo**: Dentro de esta carpeta encontraremos el modelo de la BD (Tablas y columnas).
    
- **DTOs**: Se encontraran los Data Transfer Objects encargados de mover informacion.
- **interfaces**: Se encontraran las interfaces que van a utilizar los modelos.
- **models**: Se encontraran los modelos (clases) con las que se trabajaran.
- **services**: Se encontraran los archivos de logica que trabajaran con la BD.
- **utils**: Se encontraran clases de utilidad para mejorar la legibilidad del codigo y hacerlo mas corto.

## Integrantes
- Camargo Tenaglia Gastón Matías.
- García Bidart Aurelio.
- Sangüesa Agustin.
- Sepúlveda San Martín Agustín Nicolas.

![IUA Logo](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5mW7FpJiD1VMh4ZMrrglBcTNTDyIfv8iGMw&usqp=CAU)
