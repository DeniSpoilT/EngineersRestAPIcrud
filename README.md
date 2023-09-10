# REST API for the work of creating engineers and distributing repair requests.

---

> Author: *Komarov Denis*

---

### Endpoints:

| Endpoint               | Description             |
|------------------------|-------------------------|
| GET /engineers         | Find all engineers      |
| GET /engineers/{id}    | Find engineer by id     |
| POST /engineers        | Create engineer         |
| PUT /engineers/{id}    | Update engineer by id   |
| DELETE /engineers/{id} | Delete engineer by id   |
| GET /requests          | Find all requests       |
| GET /requests/{id}     | Find request by id      |
| POST /requests         | Create request          |
| PUT /requests/{id}     | Update request by id    |
| DELETE /requests/{id}  | Delete request by id    |

---

### Technologies used:

- Spring Boot 3.1.2
- Spring Data
- Spring MVC
- H2 database in memory
- Maven
- Swagger

> Basic configuration in application.properties:
```
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.connection-timeout=600000
server.port=8081
spring.mvc.validation.enabled=true
spring.datasource.generate-unique-name=false
spring.datasource.url=jdbc:h2:mem:default
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.show-sql=true
server.error.include-message=always
server.error.include-stacktrace=never
springdoc.swagger-ui.path=/swagger-ui.html
```

