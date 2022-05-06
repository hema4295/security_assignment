<%--
  Created by IntelliJ IDEA.
  User: maheng
  Date: 6/5/22
  Time: 10:36 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add role for user</title>
    <link href="./css/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="./css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="./css/main.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/main.js"></script>
    <script src="./js/bootstrap.js"></script>
    <!-- Page level plugins -->
    <script src="./js/datatables/jquery.dataTables.min.js"></script>
    <script src="./js/datatables/dataTables.bootstrap4.min.js"></script>
    <!-- Page level css -->
    <link href="./css/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    <script>
        $(document).ready(function () {
            // get all roles
            var rst = ajaxGetRequest('/role/listRoles');
            if (rst == 'error') {
                alert("Fail to get roles!")
            } else if (rst.resultCode != '0') {
                alert(rst.resultMsg)
            }

            var roleObj = rst.resultData;
            var roles = eval(roleObj);
            console.log(roles);

            var queryURL = window.location.search.substring(1);
            var paras = queryURL.split("&");
            var username = paras[0].split("=")[1];
            console.log("=====" + username);

            var divStr = '<input type=\"hidden\" id=\"username\" name=\"username\" value=\"' + username + '\">';
            var divStr1 = '<div class=\"form-check\"><input type=\"radio\" class=\"form-check-input\" id=\"roleRadio\" name=\"roleRadio\" value=\"';
            var divStr2 = '\"><label class=\"form-check-label\">';
            var divStr3 = '</label></div>';

            if (roles != null && roles != undefined) {
                for (var i = 0; i < roles.length; i++) {
                    var str = divStr1 + roles[i].roleName + divStr2 + roles[i].roleName + divStr3;
                    divStr = divStr + str;
                }
            }
            divStr = divStr + '<button type=\"submit\" class=\"btn btn-primary mt-3\">Add</button>';
            console.log(divStr);
            $("#roleRadio").append(divStr);
        });

    </script>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Add a Role for User</h1>
    </div>

    <div class="card shadow mb-4">
        <div class="card-body">
            <form action="/user/addRole" id="roleRadio">

            </form>
        </div>
    </div>
</div>
</body>
</html>
