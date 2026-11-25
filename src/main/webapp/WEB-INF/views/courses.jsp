<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Courses | Sharath DevOps Institute</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="wide">
  <header class="bar">
    <strong>Sharath DevOps Institute</strong>
    <span>Welcome, <c:out value="${sessionScope.user.fullName}"/>
      <a class="btn" href="${pageContext.request.contextPath}/logout">Logout</a>
    </span>
  </header>
  <main class="grid-wrap">
    <h2>Our Courses</h2>
    <div class="grid">
      <c:forEach var="course" items="${courses}">
        <article class="course">
          <h3><c:out value="${course.title}"/></h3>
          <p><c:out value="${course.description}"/></p>
          <span class="tag"><c:out value="${course.duration}"/></span>
        </article>
      </c:forEach>
    </div>
  </main>
</body>
</html>
