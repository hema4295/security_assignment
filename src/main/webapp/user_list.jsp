<%--
  Created by IntelliJ IDEA.
  User: maheng
  Date: 5/5/22
  Time: 5:29 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list users</title>
    <!-- fonts -->
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
            // get all users
            var rst = ajaxGetRequest('/user/listUsers');
            if (rst == 'error') {
                alert("Fail to get users!")
            } else if (rst.resultCode != '0') {
                alert(rst.resultMsg)
            }

            var userObj = rst.resultData;
            var users = eval(userObj);
            var trStr = '';
            var successStr = '<a href=\"#\" class=\"btn btn-success btn-icon-split\"><span class=\"text\">Normal</span></a>';
            var deleteStr = '<a href=\"#\" class=\"btn btn-danger btn-icon-split\"><span class=\"text\">Deleted</span></a>';
            var addRoleStr1 = '<a href=\"#\" onclick=\"openRoles(\'';
            var addRoleStr2 = '\')\">Add Role</a>';
            if (users != null && users != undefined) {
                for (var i = 0; i < users.length; i++) {
                    var tdStr = '<tr><td>' + users[i].userName + '</td>' + '<td>';
                    if (users[i].isDelete == 1) {
                        tdStr = tdStr + deleteStr;
                    } else {
                        tdStr = tdStr + successStr;
                    }

                    var roles = users[i].roles;
                    if (roles != null && roles != undefined) {
                        var roleStr = '';
                        for (var j = 0; j < roles.length; j++) {
                            roleStr = roleStr + roles[j].roleName + ";"
                        }
                    }

                    tdStr = tdStr + '</td><td>' +roleStr +'</td>';

                    tdStr = tdStr + '<td><a href=\"#\" onclick=\"deleteUser(\'' + users[i].userName + '\')\">Delete the user</a> &nbsp;&nbsp;&nbsp;' + addRoleStr1 + users[i].userName + addRoleStr2 + '</td></tr>';
                    trStr = trStr + tdStr;
                }
            }
            $("#userTbody").append(trStr);

            // Call the dataTables jQuery plugin
            $('#userTable').DataTable({
                'ordering': false,
                'iDisplayLength': 7,
            });

            $('#addUser').click(function () {
                var userName = $('#userName').val();
                var password = $('#userPassword').val();
                if (userName == "") {
                    alert("User name cannot be blank!");
                    return;
                }
                if (password == "") {
                    alert("Password cannot be blank!");
                    return;
                }
                var rst = ajaxPostRequest('/user/add', {
                    'username': userName,
                    'userPwd': password
                });

                if (rst == 'error') {
                    alert("Fail to create a user!")
                } else if (rst.resultCode != '0') {
                    alert(rst.resultMsg)
                } else if (rst.resultCode == '0') {
                    alert("successfully create the user!");

                    setTimeout(function () {
                        // refresh
                        $('#PageMainContent').load('/user/list');
                    }, 500)
                }
            });


        });

        function deleteUser(userName) {
            if (userName == "") {
                alert("User name cannot be blank!");
                return;
            }

            var rst = ajaxPostRequest('/user/delete', {
                'username': userName
            });

            if (rst == 'error') {
                alert("Fail to delete a user!");
            } else if (rst.resultCode != '0') {
                alert(rst.resultMsg);
            } else if (rst.resultCode == '0') {
                alert("successfully delete the user!");
                // refresh
                $('#PageMainContent').load('/user/list');
            }
        }

        function openRoles(userName) {
            var win = window.open("/user_role.jsp?userName=" + userName, "child","width=500px,height=300px,resizable=no,scroll=no,status=no");
            win.blur();
            win.focus();
        }

    </script>

</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4" id="group_creating">
        <h1 class="h3 mb-0 text-gray-800">Users</h1>

        <button type="button" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" data-toggle="modal"
                data-target="#add_user">
            Add a User
        </button>
    </div>

    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="userTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>User Name</th>
                        <th>Status</th>
                        <th>Roles</th>
                        <th>Operation</th>
                    </tr>
                    </thead>

                    <tbody id="userTbody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- The Modal window for add a user-->
<div class="modal fade" id="add_user">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Add a User</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form class="user">
                    <div class="form-group">
                        <input type="text" id="userName" class="form-control form-control-user"
                               placeholder="Enter User Name...">
                    </div>
                    <div class="form-group">
                        <input type="password" id="userPassword" class="form-control form-control-user"
                               placeholder="Password">
                    </div>
                    <button id="addUser" type="button" class="btn btn-primary btn-user btn-block" data-dismiss="modal">
                        Add a user
                    </button>
                    <button type="button" class="btn btn-secondary btn-user btn-block" data-dismiss="modal">
                        Close
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
