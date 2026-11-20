# AgriPrice — Java Full Stack Cloud Project

AgriPrice helps farmers compare sample crop prices across markets and estimate net earnings after transport and other expenses. This starter project includes a Spring Boot MVC application, Thymeleaf pages, secured registration/login, REST endpoints, JPA entities/repositories, profit calculation, MySQL schema, and demo seed data.

> **Important:** Seed prices and market names are illustrative demo data only. They are not live agricultural market quotations. Never make a travel or selling decision from demo data. Replace them with verified data from a reliable source before production.

## Stack
- Java 21
- Spring Boot 3.5.7 / Maven
- Spring MVC, Thymeleaf, Spring Data JPA, Hibernate, Spring Security
- MySQL 8+
- Bootstrap 5.3 (CDN)
- Git

## Requirements
- JDK 21 (`java -version`, `javac -version`)
- Maven 3.9+ (`mvn -version`)
- MySQL Server 8+ and MySQL Workbench
- Git

## Run locally
1. Create a MySQL schema using Workbench: open and execute `database/agriprice_schema.sql`. Alternatively, the default JDBC URL creates the schema if the MySQL user has permission.
2. Set environment variables in the terminal (PowerShell example):
   ```powershell
   $env:DB_URL="jdbc:mysql://localhost:3306/agriprice?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
   $env:DB_USERNAME="root"
   $env:DB_PASSWORD="YOUR_LOCAL_MYSQL_PASSWORD"
   $env:APP_ADMIN_EMAIL="admin@agriprice.local"
   $env:APP_ADMIN_PASSWORD="Set-A-Strong-Local-Admin-Password"
   ```
3. From the project root:
   ```powershell
   mvn clean spring-boot:run
   ```
4. Open `http://localhost:8080`.
5. Register a farmer account. Admin credentials are the environment values above. Change the default admin password before use.

## Build executable JAR
```powershell
mvn clean package
java -jar target/agriprice-1.0.0.jar
```

## Main pages
- `/` landing page
- `/register` farmer registration
- `/login` login
- `/dashboard` authenticated farmer dashboard
- `/admin` ADMIN-only overview

## REST API
- `GET /api/public/crops`
- `GET /api/public/markets`
- `GET /api/public/crops/{cropId}/prices?date=YYYY-MM-DD` (date defaults to today)
- `POST /api/profit/compare` (authenticated)
- `POST /api/admin/crops` (ADMIN)
- `PUT /api/admin/crops/{id}` (ADMIN)
- `DELETE /api/admin/crops/{id}` (ADMIN; soft deactivation)
- `POST /api/admin/markets` (ADMIN)
- `POST /api/admin/prices?cropId=1&marketId=1&price=20.50&date=YYYY-MM-DD` (ADMIN)

Profit request example:
```json
{
  "cropId": 1,
  "quantityKg": 1000,
  "transportExpense": 3000,
  "otherExpense": 500
}
```
Net earnings = quantityKg × pricePerKg − transportExpense − otherExpense. Results are sorted highest net earnings first. The current comparison uses prices for today's date.

## Database design
- `users`: farmer/admin accounts; email unique; password stored as BCrypt hash.
- `crops`: crop master data.
- `markets`: market master data.
- `crop_prices`: price observations; each crop/market/date combination is unique.
- `price_alerts`: farmer target-price rules (storage model; notification delivery is not yet implemented).
- `search_history`: schema and entity foundation for tracking searches (write workflow is not yet wired into UI).

Relationships: one crop has many price rows; one market has many price rows; one user can own many alerts and search-history rows; each alert refers to one crop. Foreign keys protect references.

## Security notes / current scope
- BCrypt password hashing, form login, logout, and role checks are configured.
- Public endpoints are limited to crop/market read APIs and public pages. Admin write APIs require ADMIN.
- CSRF protection remains enabled. For browser forms this is handled by Thymeleaf/Spring Security; for Postman POST/PUT/DELETE calls, obtain and send a CSRF token or use a properly designed API security configuration.
- Do not expose MySQL publicly. Use environment variables/secrets manager; never commit `.env` or credentials.
- The app currently uses `spring.jpa.hibernate.ddl-auto=update` for convenient local development. For production, use Flyway/Liquibase migrations and `validate`.
- Before public deployment, add integration tests, audit logging, rate limiting, secure admin bootstrap, robust DTOs for all API responses (avoid exposing entities), CSRF-aware API design, and production secrets management.

## Roadmap / not yet implemented
This is a runnable foundation, not every item in the original aspirational scope. Price alerts are persisted but notification delivery is not implemented. Search history entity/table exists but automatic logging is not wired. Price-history page/chart, full CRUD UI forms, distance-based transport estimator, S3 uploads, CloudWatch integration, Docker deployment, and AWS infrastructure are documented next milestones. The architecture is structured for these extensions.

## AWS deployment outline
1. Create an RDS MySQL instance in a private subnet; allow inbound 3306 only from the EC2 security group.
2. Launch EC2 with a supported Linux image and Java 21 runtime.
3. Supply `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `APP_ADMIN_EMAIL`, and `APP_ADMIN_PASSWORD` through a secure deployment mechanism (prefer AWS Systems Manager Parameter Store/Secrets Manager and an EC2 IAM role).
4. Deploy the JAR and run it as a systemd service behind HTTPS reverse proxy/load balancer.
5. Attach least-privilege IAM role only if S3/CloudWatch integration is enabled.
6. Configure CloudWatch logs/alarms and backups.
7. Review AWS regional pricing before provisioning. EC2, RDS, storage, snapshots, data transfer, and monitoring can incur charges even when idle.

## Git quick start
```powershell
git init
git add .
git commit -m "Initial AgriPrice project"
```
Create an empty GitHub repository, then add its remote URL and push. Never commit passwords or production credentials.
