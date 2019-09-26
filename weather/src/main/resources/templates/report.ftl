<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>weather bordercast</title>
</head>
<body>
    <h3 th:text="${title}"></h3>
    <span th:text="${weatherRes.data.wendu}"></span>
    <select>
        <option th:each="city : ${cityList}"
        th:value="${city.id}" th:text="${city.cityZh}" th:selected="${city.id eq cityCode}">
        </option>
    </select>
</body>
</html>