# CRUD de Empleados con Auditoría 🧑‍💼📝

## 📝 Descripción General

Este proyecto es un servicio RESTful desarrollado con Spring Boot 3.x que permite gestionar empleados con operaciones CRUD completas. Además, implementa una bitácora de auditoría para registrar eventos importantes relacionados con las entidades, manejo global de excepciones, validaciones rigurosas y documentación interactiva Swagger/OpenAPI.

El proyecto utiliza una base de datos en memoria H2 para facilitar el desarrollo y pruebas, y cuenta con pruebas unitarias para validar la lógica de negocio.

---

## 🛠 Tecnologías Usadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Validation
- H2 Database (en memoria)
- Lombok
- Swagger / OpenAPI 3
- JUnit 5 y Mockito
- SLF4J para logging

---

## 📋 Requisitos Previos

- Java 17 instalado y configurado
- Maven (opcional si no usas el wrapper)
- Git para clonar el repositorio

---

## ⚙️ Configuración y Ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/tu-usuario/CRUDEMPLOYEES.git
cd CRUDEMPLOYEES
```
2. Ejecutar la aplicación:

Desde la terminal con Maven Wrapper:

```bash
./mvnw spring-boot:run
```
O desde tu IDE favorito (Eclipse, IntelliJ, VSCode) ejecutando la clase principal.

3. Acceder a Swagger UI para explorar y probar los endpoints:
```bash
http://localhost:8080/swagger-ui.html
```
4. Acceder a la consola H2 para ver la base de datos en memoria:
```bash
http://localhost:8080/h2-console
```
Parámetros para la conexión:
```bash
JDBC URL: jdbc:h2:mem:testdb
Usuario: sa
Contraseña: (dejar vacío)
```
---
## 🚀 Endpoints Principales
### Empleados

| Método | Endpoint              | Descripción                     |
|--------|-----------------------|---------------------------------|
| GET    | `/api/employees`      | Obtener todos los empleados     |
| GET    | `/api/employees/{id}` | Obtener empleado por ID         |
| POST   | `/api/employees`      | Crear nuevo empleado            |
| POST   | `/api/employees/batch`| Crear múltiples empleados       |
| PUT    | `/api/employees/{id}` | Actualizar empleado             |
| DELETE | `/api/employees/{id}` | Eliminar empleado               |

### Ejemplo curl para empleados

 - Crear nuevo empleado:

```bash
curl -X POST "http://localhost:8080/api/employees" \
-H "Content-Type: application/json" \
-d '{
  "firstName": "Juan",
  "middleName": "Carlos",
  "lastName": "Pérez",
  "secondLastName": "Gómez",
  "age": 30,
  "gender": "MALE",
  "birthday": "1993-05-15",
  "position": "Developer"
}'
```
 
### Auditoría

|Método | Endpoint                               | Descripción                                      |
|-------|----------------------------------------|--------------------------------------------------|
| GET   | `/api/audit-logs`                      | Obtener todos los registros de auditoría         |
| GET   | `/api/audit-logs/{entityType}/{id}`    | Auditoría por tipo de entidad e ID               |

### Ejemplo curl para auditoría
- Obtener todos los registros de auditoría:
```bash  
curl -X GET "http://localhost:8080/api/audit-logs"
```
---

## 📋 Validaciones
- Edad: debe ser un valor entre 18 y 100 (@Min(18), @Max(100))
- Fecha de nacimiento: formato "yyyy-MM-dd" (ejemplo: "1993-05-15")

---

## 🔐 Manejo de Errores
Todos los errores son manejados globalmente con respuestas JSON claras que incluyen:
- Mensaje amigable
- Código de estado HTTP adecuado
- Detalles específicos del error para facilitar el debug y la experiencia del usuario.

---

## 🧪 Pruebas Unitarias

Se incluyen pruebas unitarias usando JUnit 5 y Mockito para:

- Servicios (EmployeeService, AuditLogService)
- Controladores (EmployeeController, AuditLogController)

Estas pruebas aseguran que la lógica principal y los endpoints respondan correctamente ante distintos escenarios.

---

## 🗂 Estructura del Proyecto

```
src/main/java/com/example/CRUDEMPLOYEES/
├── apidoc
├── config
├── controller
├── service
│   ├── impl
├── model
│   ├── dto
│   │   ├── request
│   │   └── response
│   └── entities
│       ├── enums
├── repository
├── exception
```
---

## 📌 Autor
***Román Bautista Espinosa***

Desarrollador Java Backend

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/roman-bautista-espinosa-b04304170/)

Estado del Proyecto
- ✅ CRUD funcional
- ✅ Validaciones robustas
- ✅ Manejo global de errores
- ✅ Auditoría completa
- ✅ Documentación Swagger
- ✅ Pruebas unitarias

