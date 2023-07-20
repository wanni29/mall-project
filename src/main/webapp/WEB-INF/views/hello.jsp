<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Mall</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container mt-3">
    <h1>Hello jsp</h1>
    <hr>

    <%
        String name = (String) request.getAttribute("price"); //이거 다운 캐스팅을 하는 이유가 request.getAttribute("price"); 이부분이 object 타입이며 상위에 있기 때문이다.
        System.out.println("name : " + name); //  항상 확인해야 돼!
    %>
    <%=name%>

    ${price}
    ${banana}
    ${ddalgi}
    ${chanwei}

    <h1>Lost렌더링</h1>
    ${lost[0]}


</div>

</body>
</html>
