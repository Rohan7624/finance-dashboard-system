📌 1. Project Title
# Finance Dashboard System
📌 2. Description
A backend system built using Spring Boot for managing financial records, user roles, and dashboard analytics.

The system supports role-based access control, financial transaction management, and summary insights like total income, expenses, and trends.
📌 3. 🚀 Features
## 🚀 Features

- User Management (Admin, Analyst, Viewer)
- Role-Based Access Control (RBAC)
- Financial Records CRUD (Income / Expense)
- Dashboard APIs (Summary, Category, Monthly trends)
- Input Validation & Error Handling
- Swagger API Documentation
- Spring Security (Basic Auth)
📌 4. 🛠 Tech Stack
## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL (Railway Cloud DB)
- Swagger (OpenAPI)
📌 5. ⚙️ Setup Instructions
## ⚙️ Setup Instructions

### 1. Clone the repository
git clone https://github.com/rohan7624/finance-dashboard-system.git

### 2. Navigate to project
cd finance-dashboard-system

### 3. Configure database
Update application.properties:

spring.datasource.url=YOUR_DB_URL
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

### 4. Run the application
mvn spring-boot:run
📌 6. 🔗 API Documentation
## 🔗 API Documentation

Swagger UI:
http://localhost:9091/swagger-ui/index.html
📌 7. 📊 API Overview
## 📊 API Overview

### Authentication
- Basic Auth (username & password)

### Key Endpoints

#### Users
- POST /users → Create user (Admin)

#### Records
- POST /records → Create record
- GET /records → Get all records
- PUT /records/{id} → Update record
- DELETE /records/{id} → Delete record

#### Dashboard
- GET /dashboard/summary → Income, Expense, Balance
- GET /dashboard/category → Category-wise summary
- GET /dashboard/monthly → Monthly trends
📌 8. 🔐 Role-Based Access
## 🔐 Role-Based Access

- Viewer → Read-only access
- Analyst → Read + analytics
- Admin → Full access (CRUD + user management)
📌 9. ⚠️ Assumptions
## ⚠️ Assumptions

- Users are pre-created or managed by Admin
- Financial records are simple transactions (no currency conversion)
- Basic authentication is used for simplicity
📌 10. ⚖️ Trade-offs
## ⚖️ Trade-offs

- Used Basic Auth instead of JWT for simplicity
- No pagination implemented for records (can be added)
- Limited validation rules for faster development
📌 11. 🚀 Future Improvements
## 🚀 Future Improvements

- JWT Authentication
- Pagination & Sorting
- Advanced analytics (charts)
- Frontend integration (React)
