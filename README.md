# WebAppInterviewExercise

## Descripcion
Aplicacion con Stack Java, Springboot y Thymeleaf que contiene una vista para consultar la nomina de escribanos a traves de la integracion con la API REST del Colegio de Escribanos de la Ciudad de Buenos Aires

## Setup
La aplicacion no utiliza bases de datos ni ningun otro medio de almacenamiento para su funcionamiento, por lo que se solo se necesita ejecutar o compilar la aplicacion con Maven (NOTA: Para el desarrollo del mismo se utilizo el IDE Intellij).
Antes de ejecutarse la aplicacion deben verificarse los datos de integracion (url y generacion de token) en el archivo properties ubicado dentro de la carpeta 'src/main/resources' con el nombre 'application.properties'
Para acceder a su unica vista solo se debe acceder a la url 'root' del aplicativo (EJ por default: http://127.0.0.1:8080).

## Configuracion
A continuacion se listan las configuraciones del aplicativo referentes a la integracion con la API:
- integration.url: Define la URL y endpoint del metodo de la API a consumir.
- integration.issuer: Define el valor que se utilizara en el Claim 'Issuer' para la generacion del Token.
- integration.secret: Define el valor del Secret utilizado para generar la firma en la generacion del Token.

## Changelog
- 0.0.2
 - Se completa test de integracion
 - FIX de comentarios de metodos.
- 0.0.1
 - Commit inicial con la funcionalidad base del aplicativo