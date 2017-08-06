<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr/>
<ul>
    <% for (int i=0;i<10;i++){%>
       <li> <%=i%> </li>
            <%}%>
</ul>
</body>
</html>
