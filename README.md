PatientDoctor Microservices System
Descripción General
PatientDoctor es un sistema distribuido basado en microservicios, diseñado para la gestión de pacientes y doctores en un entorno clínico. Utiliza Spring Boot y Spring Cloud para garantizar escalabilidad, mantenibilidad y facilidad de integración. El sistema sigue buenas prácticas de arquitectura de microservicios, permitiendo la incorporación de nuevos servicios de manera sencilla.

Arquitectura
El sistema está compuesto por los siguientes microservicios principales:

config-ms: Servidor de configuración centralizada, encargado de proveer la configuración a todos los microservicios desde un único punto.
eureka-ms: Servidor de descubrimiento de servicios, que permite que los microservicios se registren y descubran entre sí de manera dinámica.
gateway-ms: API Gateway que enruta las peticiones de los clientes a los microservicios correspondientes y puede aplicar filtros de seguridad, logging, entre otros.
patient-ms: Microservicio dedicado a la gestión de pacientes, encargado de operaciones CRUD y lógica relacionada.
doctor-ms: Microservicio dedicado a la gestión de doctores, encargado de operaciones CRUD y lógica relacionada.
Cada microservicio es independiente, tiene su propia configuración y puede escalarse de manera individual.

Estructura del Repositorio
El repositorio está organizado en carpetas, cada una correspondiente a un microservicio. Además, el microservicio de configuración contiene una carpeta específica para los archivos de configuración de cada servicio. La estructura general es la siguiente:

Carpeta para cada microservicio (config-ms, eureka-ms, gateway-ms, patient-ms, doctor-ms)
Carpeta de configuraciones centralizadas dentro de config-ms
Archivos de configuración específicos para cada microservicio
Configuración Centralizada
Toda la configuración de los microservicios se gestiona desde el microservicio de configuración centralizada. Cada microservicio tiene un archivo de configuración propio, donde se definen parámetros como el nombre del servicio, el puerto, la conexión a la base de datos, y la integración con Eureka y el gateway.

Esto permite modificar la configuración de cualquier microservicio sin necesidad de reconstruir o desplegar nuevamente el servicio, facilitando la administración y el mantenimiento.

Ejecución Local
Para ejecutar el sistema localmente, se recomienda seguir este orden de arranque:

Iniciar el microservicio de configuración centralizada.
Iniciar el servidor Eureka.
Iniciar el API Gateway.
Iniciar los microservicios de negocio (patient-ms y doctor-ms).
Cada microservicio puede ejecutarse de manera independiente y se conectará automáticamente a los servicios de configuración y descubrimiento.

Endpoints Principales
Cada microservicio de negocio expone una API RESTful para la gestión de sus entidades principales:

El microservicio de pacientes permite listar, crear, actualizar, obtener y eliminar pacientes.
El microservicio de doctores permite listar, crear, actualizar, obtener y eliminar doctores.
Las rutas de acceso a estos endpoints están definidas en el API Gateway, que se encarga de enrutar las peticiones al microservicio correspondiente.

Configuración del Gateway
El API Gateway enruta las peticiones de los clientes a los microservicios según el path de la URL. Además, puede aplicar filtros de seguridad, autenticación, autorización, logging, y otras funcionalidades transversales.

Seguridad
El sistema puede incorporar seguridad tanto en el gateway como en los microservicios individuales, utilizando mecanismos como autenticación básica, OAuth2, JWT, etc. Esto permite proteger los endpoints y controlar el acceso a los recursos.

Base de Datos
Cada microservicio de negocio puede tener su propia base de datos, lo que garantiza el principio de independencia y desacoplamiento. Esto facilita la escalabilidad y la resiliencia del sistema.

Pruebas
Cada microservicio incluye pruebas unitarias y de integración para garantizar la calidad y el correcto funcionamiento de la lógica de negocio y la integración entre servicios.

Extensión y Escalabilidad
El sistema está preparado para la incorporación de nuevos microservicios. Para agregar un nuevo servicio, solo es necesario:

Crear el nuevo microservicio siguiendo la estructura de los existentes.
Registrar el servicio en Eureka.
Añadir la configuración correspondiente en el microservicio de configuración centralizada.
Configurar el gateway para enrutar las nuevas rutas.
Tecnologías Utilizadas
Java (versión 17 o superior)
Spring Boot
Spring Cloud Config
Spring Cloud Eureka
Spring Cloud Gateway
Maven
MySQL u otro sistema de base de datos relacional
Docker (opcional, para despliegue y orquestación)
