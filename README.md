# User Access Management System (UAMS)

A simple web-based system to manage user roles and software access within an organization. Built using **Java Servlets**, **JSP**, **PostgreSQL**, and **Maven**.

---

## 🔧 Technologies Used

* **Java Servlets & JSP**: For dynamic web content and server-side logic.
* **PostgreSQL**: A powerful, open-source relational database for data storage.
* **Apache Tomcat**: The web server and servlet container.
* **Maven**: For project build automation and dependency management.
* **HTML/CSS**: For the front-end user interface.
* **JDBC**: Java Database Connectivity for interacting with PostgreSQL.

---

## 🧑‍💻 User Roles

* **Employee**: Can sign up, log in, and request access to software.
* **Manager**: Can view and approve/reject pending access requests.
* **Admin**: Has full control, including creating new software, and possesses all Employee and Manager privileges.

---

## 📁 Project Structure
```
uams/
├── src/
│ └── main/
│ ├── java/
│ │ └── com/project/uams/
│ │ ├── DBUtil.java            // Database utility class for connection management
│ │ ├── LoginServlet.java      // Handles user login
│ │ ├── SignUpServlet.java     // Handles new user registration
│ │ ├── SoftwareServlet.java   // Manages software creation (Admin)
│ │ ├── RequestServlet.java    // Handles software access requests (Employee)
│ │ └── ApprovalServlet.java   // Manages request approvals/rejections (Manager)
│ └── webapp/
│ ├── signup.jsp             // User registration page
│ ├── login.jsp              // User login page
│ ├── createSoftware.jsp     // Admin page to add new software
│ ├── requestAccess.jsp      // Employee page to request software access
│ ├── pendingRequests.jsp    // Manager page to view and act on requests
│ └── WEB-INF/
│ └── web.xml              // Deployment descriptor for servlets
├── pom.xml                  // Maven project object model file

```

## 🛠️ Setup Instructions

### 📌 Prerequisites

Before you begin, ensure you have the following installed:

* **Java 8+**
* **Apache Tomcat 8.5+**
* **PostgreSQL**
* **Eclipse IDE with Maven** integration (recommended)

### ⚙️ Steps

1.  **Create PostgreSQL Database and Tables**:

    Open your PostgreSQL client (e.g., `psql` or pgAdmin) and execute the following SQL commands to create the `uams` database and necessary tables:

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
    ```

2.  **Configure Database Credentials**:

    Navigate to `src/main/java/com/project/uams/DBUtil.java` and update the database connection details with your PostgreSQL username and password:

    ```java
    private static final String URL = "jdbc:postgresql://localhost:5432/uams";
    private static final String USER = "your_username"; // <-- Update your PostgreSQL username
    private static final String PASSWORD = "your_password"; // <-- Update your PostgreSQL password
    ```

3.  **Build with Maven**:

    Open your terminal or command prompt, navigate to the root directory of the `uams` project (where `pom.xml` is located), and run the following Maven command to build the project:

    ```bash
    mvn clean install
    ```
    This command will compile the Java source files, run tests (if any), and package the application into a `WAR` file.

4.  **Deploy WAR to Apache Tomcat**:

    After a successful Maven build, you'll find the generated WAR file at `target/uams.war`.

    * **Manual Deployment**: Copy `uams.war` to the `webapps` directory of your Apache Tomcat 8.5+ installation. Tomcat will automatically deploy the application.
    * **Eclipse Deployment**: If you're using Eclipse, you can configure Tomcat as a server and deploy the `uams` project directly from within Eclipse.

---

## 🌐 Access Application

Once deployed successfully, you can access the application through your web browser:

[http://localhost:8080/uams/signup.jsp](http://localhost:8080/uams/signup.jsp)

Start by signing up as a new user!

---

## 📦 Deliverables

This project includes the following components:

* ✅ **Java Source Files (Servlets)**: All server-side logic for handling user requests and interactions.
* ✅ **JSP Pages**: The front-end views for user interfaces.
* ✅ **Database Scripts (PostgreSQL)**: SQL commands to set up the necessary database tables.
* ✅ **pom.xml (Maven)**: The project's build configuration and dependency management file.
* ✅ **README file**: This comprehensive guide for setting up and running the application.
