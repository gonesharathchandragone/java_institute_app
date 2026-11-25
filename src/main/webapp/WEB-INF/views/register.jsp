<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Create account | Sharath DevOps Institute</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <main class="card">
    <h1>Create your account</h1>
    <p class="sub">Sharath DevOps Institute</p>

    <c:if test="${not empty error}">
      <div class="alert err"><c:out value="${error}"/></div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/register">
      <label for="fullName">Full name</label>
      <input id="fullName" type="text" name="fullName" value="<c:out value='${fullName}'/>" maxlength="100" required autofocus>
      <label for="email">Email</label>
      <input id="email" type="email" name="email" value="<c:out value='${email}'/>" maxlength="150" required>
      <label for="password">Password (min 8 characters)</label>
      <input id="password" type="password" name="password" minlength="8" required>
      <label for="confirmPassword">Confirm password</label>
      <input id="confirmPassword" type="password" name="confirmPassword" minlength="8" required>
      <button type="submit">Create account</button>
    </form>
    <p class="foot">Already registered? <a href="${pageContext.request.contextPath}/login">Log in</a></p>
  </main>
</body>
</html>
