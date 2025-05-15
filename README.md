# CRUD de Empleados con Auditoría 🧑‍💼📝

Este proyecto es un servicio RESTful desarrollado con **Spring Boot 3.x**, que permite gestionar empleados con operaciones CRUD completas, validaciones, documentación Swagger, manejo global de excepciones y una bitácora de eventos para auditoría.

---

## 🛠 Tecnologías Usadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Validation
- H2 Database (modo memoria)
- Lombok
- Swagger / OpenAPI 3
- JUnit 5 y Mockito
- SLF4J para logs

---

## 🚀 Endpoints principales

### Empleados

| Método | Endpoint              | Descripción                     |
|--------|-----------------------|---------------------------------|
| GET    | `/api/employees`      | Obtener todos los empleados     |
| GET    | `/api/employees/{id}` | Obtener empleado por ID         |
| POST   | `/api/employees`      | Crear nuevo empleado            |
| POST   | `/api/employees/batch`| Crear múltiples empleados       |
| PUT    | `/api/employees/{id}` | Actualizar empleado             |
| DELETE | `/api/employees/{id}` | Eliminar empleado               |

### Auditoría

| Método | Endpoint                               | Descripción                                      |
|--------|----------------------------------------|--------------------------------------------------|
| GET    | `/api/audit-logs`                      | Obtener todos los registros de auditoría         |
| GET    | `/api/audit-logs/{entityType}/{id}`    | Auditoría por tipo de entidad e ID               |

---

## 📋 Validaciones aplicadas

- Edad: **@Min(18)** y **@Max(100)**
- Fecha de nacimiento: patrón `"dd-MM-yyyy"`

---

## 🔐 Manejo de Errores

Todos los errores son gestionados por un **Handler Global**, devolviendo mensajes amigables y específicos con su respectivo código de estado HTTP.

---

## 🧪 Pruebas Unitarias

Se incluyen pruebas unitarias con JUnit y Mockito para:

- `EmployeeService`
- `EmployeeController`
- `AuditLogController`
- `AuditLogService`

Cobertura de pruebas básica para validar lógica y respuestas esperadas.

---

## 🗂 Estructura del proyecto

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
|   ├── enums
├── repository
├── exception
```

---

## ⚙️ Configuración rápida

1. Clona el repositorio:
   ```bash
   git clone [https://github.com/tu-usuario/CRUDEMPLOYEES.git](https://github.com/BautistaEspinosa/CRUD_EMPLOYEES.git)
   ```

2. Ejecuta la app desde tu IDE o:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Accede a Swagger UI:
   ```
   http://localhost:8080/swagger-ui.html
   ```

4. Accede a la consola H2:
   ```
   http://localhost:8080/h2-console
   ```

---


## 📌 Autor

**Román Bautista Espinosa**  
Desarrollador Java Backend  
[LinkedIn](https://www.linkedin.com/in/roman-bautista-espinosa-b04304170/)

---

## ✅ Estado del Proyecto

✅ CRUD funcional  
✅ Validaciones  
✅ Manejo de errores  
✅ Bitácora de auditoría  
✅ Documentación Swagger  
✅ Pruebas unitarias  

---
