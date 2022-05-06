<%--
  Created by IntelliJ IDEA.
  User: maheng
  Date: 4/5/22
  Time: 11:10 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add a user</title>
    <!-- fonts -->
    <link href="./css/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="./css/main.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/main.js"></script>

</head>

<body class="bg-gradient-primary">

<div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="card my-5">
                <!-- Nested Row within Card Body -->
                <form class="user" method="post" action="/login">
                    <div class="form-group">
                        <input type="text" id="username" name="username" class="form-control form-control-user"
                               placeholder="Enter User Name...">
                    </div>
                    <div class="form-group">
                        <input type="password" id="userPwd" name="userPwd" class="form-control form-control-user"
                               placeholder="Password">
                    </div>
                    <input type="submit" class="btn btn-primary btn-user btn-block" value="Login">
                    </input>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
