<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login | Sharath DevOps Institute</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <main class="card">
    <h1>Sharath DevOps Institute</h1>
    <p class="sub">Log in to see your courses</p>

    <c:if test="${not empty sessionScope.flash}">
      <div class="alert ok"><c:out value="${sessionScope.flash}"/></div>
      <c:remove var="flash" scope="session"/>
    </c:if>
    <c:if test="${not empty error}">
      <div class="alert err"><c:out value="${error}"/></div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/login">
      <label for="email">Email</label>
      <input id="email" type="email" name="email" value="<c:out value='${email}'/>" required autofocus>
      <label for="password">Password</label>
      <input id="password" type="password" name="password" required>
      <button type="submit">Log in</button>
    </form>
    <p class="foot">New here? <a href="${pageContext.request.contextPath}/register">Create an account</a></p>
  </main>
</body>
</html>
