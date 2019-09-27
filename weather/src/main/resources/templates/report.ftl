<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>weather bordercast</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h3 th:text="${title}"></h3>
    <span th:text="${weatherRes.data.wendu}"></span>
    <select id="cityP">
        <option th:each="city : ${cityList}"
                th:value="${city.id}" th:text="${city.cityZh}" th:selected="${city.id eq cityCode}">
        </option>
    </select>
    <h1 th:text="${weatherRes.data.city}">城市名称</h1>
    <p>
        温度：<span th:text="${weatherRes.data.wendu}"></span>
    </p>
    <p>
        温馨提示：<span th:text="${weatherRes.data.ganmao}"></span>
    </p>
    <div th:each="forecast : ${weatherRes.data.forecast}">
        <div>
            <p th:text="${forecast.date}"></p>
            <p th:text="${forecast.high}"></p>
            <p th:text="${forecast.fengli}"></p>
            <p th:text="${forecast.low}"></p>
            <p th:text="${forecast.fengxiang}"></p>
            <p th:text="${forecast.type}"></p>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script type="text/JavaScript">
    $(function () {
       $("#cityP").change(function () {
            var cityCode = $("#cityP").val();
            var url = "/weather/report/"+cityCode;
            window.location.href = url;
       });
    });
</script>
</html>