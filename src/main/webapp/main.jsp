<%--
  Created by IntelliJ IDEA.
  User: maheng
  Date: 5/5/22
  Time: 11:47 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Admin</title>
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
        function changeMenu(type) {
            if (type == 1) {
                $('#PageMainContent').empty();
                $('#PageMainContent').load('/user/list');
            } else if (type == 2) {
                $('#PageMainContent').empty();
                $('#PageMainContent').load('/role/list');
            }
        }
    </script>
</head>

<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
    <!-- Sidebar -->
    <div id="left_navbar">
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <!-- Divider -->
            <hr class="sidebar-divider">
            <!-- Heading -Admin -->
            <div class="sidebar-heading">
                Admin
            </div>

            <!-- Nav Item: user -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" onclick="changeMenu(1)">
                    <span>User</span>
                </a>
            </li>
            <!-- Nav Item: role -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" onclick="changeMenu(2)">
                    <span>Role</span>
                </a>
            </li>
        </ul>
    </div>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column" style="max-height: 100vh;">

        <!-- Main Content -->
        <div id="content">
            <!-- Begin Page Content -->
            <div id="modal_wrapper">
                <div class="container-fluid" id="PageMainContent">
                    <!--default the user page-->
                    <jsp:include page="user_list.jsp"></jsp:include>
                </div>

            </div>

            <!-- End Page Content -->
        </div>
        <!-- End of Main Content -->
    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->


</body>

</html>
