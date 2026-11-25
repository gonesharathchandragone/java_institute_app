# Sharath DevOps Institute - Java Web App

Features: user registration, login (PBKDF2-hashed passwords), session-protected course list, MySQL storage.

Stack: Java 11, Servlets + JSP/JSTL, JDBC, MySQL 8, Maven, Tomcat 9.

## 1. Set up MySQL
    mysql -u root -p < db/schema.sql

## 2. Configure DB access (environment variables; defaults shown)
    DB_URL=jdbc:mysql://localhost:3306/sharath_institute?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    DB_USER=root
    DB_PASSWORD=root

## 3. Build
    mvn clean package
Produces `target/sharath-devops-institute.war`.

## 4. Run
Copy the WAR to Tomcat 9's `webapps/` (as `ROOT.war` to serve at `/`) and start Tomcat, then open
http://localhost:8080/ . Register an account, log in, and see the courses.

Note: Tomcat 9 is required (javax.* servlet API); Tomcat 10+ uses jakarta.*.

## Docker (app only; point DB_URL at your MySQL)
    docker build -t sharath-devops-institute .
    docker run -p 8080:8080 -e DB_URL="jdbc:mysql://host.docker.internal:3306/sharath_institute?useSSL=false&allowPublicKeyRetrieval=true" -e DB_USER=root -e DB_PASSWORD=root sharath-devops-institute

## Structure
    db/schema.sql                         tables + seed courses
    src/main/java/.../util                DBUtil, PasswordUtil
    src/main/java/.../dao                 UserDAO, CourseDAO
    src/main/java/.../servlet             Login, Register, Courses, Logout
    src/main/webapp/WEB-INF/views         login.jsp, register.jsp, courses.jsp
