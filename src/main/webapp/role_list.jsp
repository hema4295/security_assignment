<%--
  Created by IntelliJ IDEA.
  User: maheng
  Date: 6/5/22
  Time: 12:20 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list users</title>
    <!-- fonts -->
    <link href="./css/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="./css/main.css" rel="stylesheet">
    <script src="./js/jquery.min.js"></script>
    <script src="./js/main.js"></script>
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
            var trStr = '';
            var successStr = '<a href=\"#\" class=\"btn btn-success btn-icon-split\"><span class=\"text\">Normal</span></a>';
            var deleteStr = '<a href=\"#\" class=\"btn btn-danger btn-icon-split\"><span class=\"text\">Deleted</span></a>';

            if (roles != null && roles != undefined) {
                for (var i = 0; i < roles.length; i++) {
                    var tdStr = '<tr><td>' + roles[i].roleName + '</td>' + '<td>';
                    if (roles[i].isDelete == 1) {
                        tdStr = tdStr + deleteStr;
                    } else {
                        tdStr = tdStr + successStr;
                    }

                    tdStr = tdStr + '</td>' + '<td><a href=\"#\" onclick=\"deleteRole(\'' + roles[i].roleName + '\')\">Delete the role</a></td></tr>';
                    trStr = trStr + tdStr;
                }
            }
            $("#roleTbody").append(trStr);

            // Call the dataTables jQuery plugin
            $('#roleTable').DataTable({
                'ordering': false,
                'iDisplayLength': 7,
            });

            $('#addRole').click(function () {
                var roleName = $('#roleName').val();
                if (roleName == "") {
                    alert("Role name cannot be blank!");
                    return;
                }
                var rst = ajaxPostRequest('/role/add', {
                    'roleName': roleName
                });

                if (rst == 'error') {
                    alert("Fail to create a role!")
                } else if (rst.resultCode != '0') {
                    alert(rst.resultMsg)
                } else if (rst.resultCode == '0') {
                    alert("successfully create the role!");

                    setTimeout(function () {
                        // refresh
                        $('#PageMainContent').load('/role/list');
                    }, 500)
                }
            });
        });

        function deleteRole(roleName) {
            if (roleName == "") {
                alert("Role name cannot be blank!");
                return;
            }

            var rst = ajaxPostRequest('/role/delete', {
                'rolename': roleName
            });

            if (rst == 'error') {
                alert("Fail to delete a role!");
            } else if (rst.resultCode != '0') {
                alert(rst.resultMsg);
            } else if (rst.resultCode == '0') {
                alert("successfully delete the role!");
                // refresh
                $('#PageMainContent').load('/role/list');
            }
        }
    </script>

</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="d-sm-flex align-items-center justify-content-between mb-4" id="group_creating">
        <h1 class="h3 mb-0 text-gray-800">Roles</h1>

        <button type="button" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" data-toggle="modal"
                data-target="#add_role">
            Add a Role
        </button>
    </div>

    <div class="card shadow mb-4">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="roleTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Role Name</th>
                        <th>Status</th>
                        <th>Operation</th>
                    </tr>
                    </thead>

                    <tbody id="roleTbody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- The Modal window for add a role-->
<div class="modal fade" id="add_role">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Add a Role</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form class="user">
                    <div class="form-group">
                        <input type="text" id="roleName" class="form-control form-control-user"
                               placeholder="Enter Role Name...">
                    </div>
                    <button id="addRole" type="button" class="btn btn-primary btn-user btn-block" data-dismiss="modal">
                        Add a Role
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
