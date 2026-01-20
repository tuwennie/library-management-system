# Library Management System Backend

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?style=for-the-badge&logo=mysql)
![Docker](https://img.shields.io/badge/Docker-Container-blue?style=for-the-badge&logo=docker)
![Swagger](https://img.shields.io/badge/Swagger-API_Docs-green?style=for-the-badge&logo=swagger)


A robust, scalable, and secure RESTful API for managing library operations. This project demonstrates a complete backend architecture using **Java Spring Boot**, featuring advanced capabilities like containerization, API documentation, and secure authentication.

## Technologies & Tools

* **Language:** Java 17
* **Framework:** Spring Boot 3.2.2
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Security:** Spring Security (Basic Auth)
* **Documentation:** OpenAPI (Swagger UI)
* **Containerization:** Docker
* **Build Tool:** Maven

## Key Features

* **Secure Authentication:** Role-based access control (Admin/User) using Spring Security.
* **CRUD Operations:** Complete management for Books and Users.
* **Business Logic:** Borrowing and returning book flows with validation (e.g., preventing borrowing of already borrowed books).
* **Pagination & Sorting:** Optimized API endpoints to handle large datasets efficiently.
* **Global Exception Handling:** Centralized error management returning standardized JSON responses.
* **Dockerized:** Fully containerized application for consistent deployment across environments.

## Project Structure

A modular "Layered Architecture" is used to ensure separation of concerns.

```text
src/main/java/com/tuwennie/library_management
├── config/             # Security and App Configurations
├── controller/         # REST Controllers (API Layer)
├── dto/                # Data Transfer Objects (Request/Response)
├── entity/             # JPA Entities (Database Tables)
├── exception/          # Global Exception Handling
├── repository/         # Data Access Layer (Interfaces)
└── service/            # Business Logic Layer
```

## Installation & Running

### Prerequisites
* Java 17 SDK
* Docker Desktop
* MySQL Server

### Option 1: Run with Docker (Recommended)
You can run the application without installing Java or Maven dependencies using the Docker image.

1.  **Build the Image:**
    ```bash
    docker build -t library-app .
    ```

2.  **Run the Container:**
    (Ensure your local MySQL is running)
    ```bash
    docker run -p 8080:8080 -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/library_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" library-app
    ```

### Option 2: Run Locally 
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/tuwennie/library-management.git](https://github.com/tuwennie/library-management.git)
    ```
2.  **Configure Database:** Update `src/main/resources/application.properties` with your MySQL credentials.
3.  **Run the App:**
    ```bash
    ./mvnw spring-boot:run
    ```

## API Documentation (Swagger UI)

Once the application is running, you can explore and test all API endpoints via the interactive Swagger interface:

👉 **URL:** `http://localhost:8080/swagger-ui/index.html`

<img width="100%" src="https://github.com/user-attachments/assets/cda70502-fc76-425f-9b60-f91d70eb466b" />
<img width="100%" src="https://github.com/user-attachments/assets/01222502-d3c2-4e6e-98fc-441a6f066597" />

## API Endpoints Overview

| Method | Endpoint | Description | Roles |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register new user | All |
| `GET` | `/api/books` | Get all books (Paged) | User, Admin |
| `POST` | `/api/books` | Add a new book | Admin |
| `POST` | `/api/transactions/borrow` | Borrow a book | User, Admin |
| `POST` | `/api/transactions/return` | Return a book | User, Admin |

## Future Improvements (Roadmap)

* [ ] **Unit Tests:** Adding JUnit and Mockito tests for higher code coverage.
* [ ] **JWT Authentication:** Upgrading from Basic Auth to JWT (JSON Web Tokens) for stateless security.
* [ ] **Frontend Client:** Building a React or Angular interface to consume these APIs.
* [ ] **Email Notifications:** Sending email alerts for overdue books.

---
**Author:** Tuba Rukiye D.
*Built as a comprehensive backend engineering project.*
