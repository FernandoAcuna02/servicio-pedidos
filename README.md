# Microservicio: Servicio de Pedidos

Este repositorio contiene el código fuente del `servicio-pedidos`, que forma parte de un sistema de E-Commerce basado en una arquitectura de microservicios.

## Descripción

Este microservicio actúa como el **orquestador** del sistema. Su principal responsabilidad es gestionar el ciclo de vida de los pedidos de los clientes. Para crear un pedido, este servicio se comunica con otros microservicios (como el `servicio-productos`) para validar información y obtener datos necesarios, como el precio y el stock de los productos.

## Arquitectura y Diseño

-   **Arquitectura de Microservicios:** Este servicio es un componente central que demuestra la comunicación inter-servicio, un pilar fundamental de esta arquitectura.
-   **Arquitectura Hexagonal:** Se ha implementado para aislar la lógica de negocio de la tecnología. Notablemente, cuenta con dos tipos de adaptadores de salida: uno para la persistencia en base de datos (JPA) y otro para la comunicación con APIs externas (`ProductoRestClientAdapter` usando `WebClient`).
-   **Principios SOLID:** El código sigue los principios SOLID para crear un software robusto y fácil de mantener.

## Tecnologías Utilizadas

-   **Lenguaje:** Java 17
-   **Framework:** Spring Boot 3.x
-   **Comunicación:** Spring WebFlux (WebClient)
-   **Persistencia:** Spring Data JPA / Hibernate
-   **Base de Datos:** PostgreSQL
-   **Gestión de Dependencias:** Maven

## Endpoints de la API

La API se expone en la ruta base `/api/pedidos`.

---

### Crear un nuevo pedido

-   **Método:** `POST`
-   **URL:** `/api/pedidos`
-   **Descripción:** Crea un nuevo pedido para un usuario, consultando la información de los productos en el `servicio-productos`.
-   **Request Body (Ejemplo):**
    ```json
    {
        "usuarioId": 1,
        "items": [
            {
                "productoId": 1,
                "cantidad": 2
            }
        ]
    }
    ```
-   **Respuesta Exitosa (201 Created):**
    ```json
    {
        "id": 1,
        "usuarioId": 1,
        "fecha": "2025-06-14T20:05:00",
        "estado": "CREADO",
        "items": [
            {
                "id": 1,
                "productoId": 1,
                "cantidad": 2,
                "precioUnitario": 1250000.00
            }
        ],
        "total": 2500000.00
    }
    ```

## Cómo Ejecutar el Proyecto

1.  **Pre-requisitos:**
    * Tener los servicios `servicio-productos` y `servicio-usuarios` ejecutándose.
    * Tener una instancia de PostgreSQL corriendo.

2.  **Base de Datos:**
    * Cree una nueva base de datos en PostgreSQL llamada `pedidos_db`.
    * Configure las credenciales en el archivo `src/main/resources/application.properties`.

3.  **Ejecución:**
    * Clone este repositorio.
    * Ejecute el comando Maven: `mvn spring-boot:run`.
    * La aplicación se iniciará en el puerto `8083` por defecto.

## Autor

-   [CIPALOSINGENIEROS]
