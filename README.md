# PG Management System (fixed)

This build removes JPA (which caused a DataSource error) and uses **spring-boot-starter-data-couchbase** instead.
It also activates the **local** profile by default and configures CORS correctly.

## Run
```bash
cd backend
mvn spring-boot:run
# Health: http://localhost:8080/api/v1/health
# Swagger: http://localhost:8080/swagger-ui.html
```
