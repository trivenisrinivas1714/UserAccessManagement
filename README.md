# User Access Management System (UAMS)

A simple web-based system to manage user roles and software access within an organization. Built using **Java Servlets**, **JSP**, **PostgreSQL**, and **Maven**.

## 🔧 Technologies Used

- Java Servlets & JSP
- PostgreSQL
- Apache Tomcat
- Maven
- HTML/CSS
- JDBC

## 🧑‍💻 User Roles

- **Employee**: Can sign up, log in, and request access to software.
- **Manager**: Can view and approve/reject pending access requests.
- **Admin**: Can create new software and has all Employee and Manager privileges.

## 📁 Project Structure

uams/
├── src/
│ └── main/
│ ├── java/
│ │ └── com/project/uams/
│ │ ├── DBUtil.java
│ │ ├── LoginServlet.java
│ │ ├── SignUpServlet.java
│ │ ├── SoftwareServlet.java
│ │ ├── RequestServlet.java
│ │ └── ApprovalServlet.java
│ └── webapp/
│ ├── signup.jsp
│ ├── login.jsp
│ ├── createSoftware.jsp
│ ├── requestAccess.jsp
│ ├── pendingRequests.jsp
│ └── WEB-INF/
│ └── web.xml
├── pom.xml



## 🛠️ Setup Instructions

### 📌 Prerequisites

- Java 8+
- Apache Tomcat 8.5+
- PostgreSQL
- Eclipse IDE with Maven

### ⚙️ Steps

1. **Create PostgreSQL database**:

```sql
CREATE DATABASE uams;

-- users table
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(20) NOT NULL DEFAULT 'Employee'
);

-- software table
CREATE TABLE software (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  access_levels TEXT
);

-- requests table
CREATE TABLE requests (
  id SERIAL PRIMARY KEY,
  user_id INT REFERENCES users(id),
  software_id INT REFERENCES software(id),
  access_type VARCHAR(50),
  reason TEXT,
  status VARCHAR(20) DEFAULT 'Pending'
);


⚙️ Configuration
1. Configure DB Credentials
Update DBUtil.java:

private static final String URL = "jdbc:postgresql://localhost:5432/uams";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";

2. Build With Maven

mvn clean install

3. Deploy WAR

Output WAR: target/uams.war

Deploy to Apache Tomcat 8.5

OR run directly from Eclipse if configured

🌐 Access Application
http://localhost:8080/uams/signup.jsp

📦 Deliverables
✅ Java Source Files (Servlets)

✅ JSP Pages

✅ Database Scripts (PostgreSQL)

✅ pom.xml (Maven)

✅ README file
