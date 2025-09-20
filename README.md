# PG Management System

A platform to streamline **PG (Paying Guest) / Co-Living management** for both **owners** and **tenants**.  
This README serves as the project brief for developers and contributors.

---

## 📐 Architecture Decisions

- **Backend**:  
  - Framework: **Spring Boot 3.3.2**  
  - Language: **Java 21**  
  - API Style: **RESTful APIs** (documented with OpenAPI/Swagger)  
  - Persistence: **Couchbase** (JSON document store, flexible schema)  
  - Build Tool: **Maven**  
  - IDE: **IntelliJ IDEA (Community Edition is enough)**  

- **Frontend**:  
  - Framework: **React** (separate repo, consumes backend APIs)  
  - Target Platforms: **Web (initially)** → Android/iOS (later via React Native or wrapper app)  

- **Integration & Flow**:  
  - **Swagger / OpenAPI**: serves as the API contract between backend & frontend.  
  - **Postman**: for endpoint testing, using a shared collection + environment.  
  - **CORS Config**: uses `allowedOriginPatterns` to support frontend dev servers.  
  - **Profiles**: `application-local.yml` used for local dev with Couchbase.  

- **Deployment (later phases)**:  
  - Backend: Spring Boot JAR (can run in Docker, deploy to cloud/VPS)  
  - Database: Couchbase in Docker container (local) or managed service (production)  
  - Frontend: Static hosting (Vercel/Netlify) or packaged mobile apps  

---

## 📦 Versions / Dependencies
- **Spring Boot**: 3.3.2  
- **Java**: 21  
- **Springdoc OpenAPI**: 2.6.0  
- **Couchbase Java SDK**: 3.4.10  
- **Maven**: 3.9+  

---

## 🚀 Development Workflow
1. **Backend**:  
   - Run locally with `mvn spring-boot:run`.  
   - Health check: [http://localhost:8080/api/v1/health](http://localhost:8080/api/v1/health).  
   - Swagger docs: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).  

2. **Frontend**:  
   - React app will call backend APIs at `http://localhost:8080/api/v1`.  
   - Use the OpenAPI spec as the source of truth for request/response formats.  

3. **Testing APIs**:  
   - Import **Postman Collection** + **Environment** from `/docs/postman/`.  
   - Test endpoints before frontend integration.  

4. **Git Workflow**:  
   - Default branch: `main` (stable).  
   - Dev features: create branches (`feature/<name>`) → raise Pull Request → review & merge.  
   - `.gitignore` excludes build files (`target/`, `.class`, `.jar`, logs, `application-local.yml`).  

---

## ✅ Current Status
- Backend skeleton running (Spring Boot service + Swagger + health endpoint).  
- Couchbase integration configured (but optional for now; can be disabled if DB not running).  
- OpenAPI + Postman placeholders available for contract-first testing.  

---

👉 Next Steps:  
- Add in-memory CRUD APIs for Tenant & Room (so frontend dev can test flows).  
- Learn & set up Couchbase with Docker for persistence.  
- Expand Postman collection with frozen requirements (onboarding, billing, notices, referrals, dues registry, etc.).  
