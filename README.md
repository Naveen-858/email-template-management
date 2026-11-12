# Dynamic Email Template Management System

A Java Spring Boot application that allows HR teams to manage reusable email templates, maintain employee information, preview personalized emails, and send automated emails using dynamic placeholders.

## Project Overview

The Dynamic Email Template Management System helps HR teams send personalized emails to employees without manually modifying email content every time.

HR can create and update templates, store employee details, preview personalized messages, and send emails through Gmail SMTP.

## Key Features

- Employee Management – Create, retrieve, and update employee details.
- Dynamic Email Templates – Create, update, and manage reusable HTML email templates.
- Placeholder Processing – Automatically replace placeholders with employee-specific information.
- Email Preview – Preview personalized email content before sending.
- Email Sending – Send HTML emails using Gmail SMTP.
- Email Logging – Maintain SENT and FAILED email records.
- Exception Handling – Centralized exception handling and validation.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Programming language |
| Spring Boot 3.5.7 | Backend framework |
| Spring Web | REST API development |
| Spring Data JPA | Database operations |
| Hibernate | ORM |
| MySQL 8 | Database |
| Spring Boot Starter Mail | Email integration |
| Maven | Build management |
| Postman | API testing |
| Git & GitHub | Version control |

## Project Structure

```text
email-template-management
│
├── src/main/java/com/company/emailtemplate
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── exception
│   ├── repo
│   ├── service
│   └── EmailTemplateManagementApplication.java
│
├── src/main/resources
│   └── application.properties
│
├── src/test/java
├── pom.xml
└── README.md
```

## Database Configuration

Create the MySQL database:

```sql
CREATE DATABASE email_template_db;
```

Configure the following environment variables:

```text
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
MAIL_USERNAME=your_gmail_address
MAIL_PASSWORD=your_gmail_app_password
```

The application reads these values from environment variables rather than storing credentials in source code.

## Application Configuration

In `application.properties`:

```properties
spring.application.name=email-template-management
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/email_template_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME:}
spring.mail.password=${MAIL_PASSWORD:}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## REST API Endpoints

Base URL: `http://localhost:8080`

### Employee Management

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees` | Create employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| PUT | `/api/employees/{id}` | Update employee |

### Email Template Management

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/email-templates` | Create email template |
| GET | `/api/email-templates` | Get all templates |
| GET | `/api/email-templates/{id}` | Get template by ID |
| PUT | `/api/email-templates/{id}` | Update template |
| PATCH | `/api/email-templates/{id}/status` | Activate or deactivate template |
| GET | `/api/email-templates/active` | Get active templates |

### Email Preview and Sending

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/email-preview?employeeId=1&templateId=1` | Preview personalized email |
| POST | `/api/emails/send` | Send personalized email |

### Send Email Request

```json
{
  "employeeId": 1,
  "templateId": 1
}
```

## Dynamic Email Template Example

Templates support placeholders that are replaced with employee information at runtime.

```html
<html>
<body>
    <h2>Hello {{name}},</h2>

    <p>Congratulations on completing
       {{anniversaryYears}} years with our organization!</p>

    <p>Employee Code: {{employeeCode}}</p>
    <p>Designation: {{designation}}</p>
    <p>Total Experience: {{totalExperienceYears}} years</p>

    <p>We appreciate your contributions and wish you
       continued success.</p>

    <p>Regards,<br>HR Team</p>
</body>
</html>
```

The same template can generate different personalized emails for different employees.

## How to Run the Application

1. Install Java 17 and MySQL 8.
2. Create the `email_template_db` database.
3. Configure the required environment variables.
4. Clone the repository:

```bash
git clone https://github.com/Naveen-858/email-template-management.git
```

5. Navigate to the project directory:

```bash
cd email-template-management
```

6. Start the application:

```bash
mvn spring-boot:run
```

Alternatively, on Windows:

```cmd
mvnw.cmd spring-boot:run
```

The application starts at:

`http://localhost:8080`

## Email Integration

The application uses Spring Boot's `JavaMailSender` to send HTML emails through Gmail SMTP with STARTTLS authentication.

For Gmail, configure a Google App Password. Never commit your actual email credentials or database passwords to GitHub.

## Future Enhancements

- Automated anniversary email scheduling.
- Bulk email campaigns.
- Excel-based employee import.
- Email retry mechanism.
- Duplicate email prevention.
- Email campaign dashboard.
- Role-based access control for HR users.

## Author

**Singavarapu Naveen**

Java Backend Developer

GitHub: https://github.com/Naveen-858
