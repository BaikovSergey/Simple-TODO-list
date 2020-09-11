<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Simple TODO list</title>

    <script>
        function validate() {
            var result = true;
            var description = document.getElementById("description");

            if (description.value === "") {
                result = false;
            }

            if (!result) {
                if (description.value === "") {
                    alert("Please fill this field: Description");
                }

            }
            return result;
        }

        function displayAllTasks() {
            $("#allTasks tbody tr").empty();
            $.getJSON('http://localhost:8080/index/getAllTasks.do', function (data) {
                for (var i = 0; i < data.length; i++) {
                    $('#allTasks > tbody:last-child').append('<tr class="task">'
                        + '<td class="id" style="display: none">' + data[i].id + '</td>'
                        + '<td class="description">' + data[i].description + '</td>'
                        + '<td class="created">' + data[i].created + '</td>'
                        + '<td class="status">' + data[i].status + '</td>'
                        + '<td><div class="btn-group btn-group-sm"><button type="button" '
                        + 'class="btn btn-success">Complete</button><button type="button" '
                        + 'class="btn btn-primary">Change</button><button type="button" '
                        + 'class="btn btn-danger">Delete</button></div></td></tr>');
                }
            });
        }

        $(document).ready(function () {
            displayAllTasks();
        });

        $(document).ready(function () {
            $("#allTasks").on("click", ".btn-danger", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find(".id").text();
                $.post("http://localhost:8080/index/deleteTask.do",
                    "id=" + $id,
                    function () {
                   displayAllTasks();
                });
            });
        });

        $(document).ready(function () {
            $("#allTasks").on("click", ".btn-success", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find(".id").text();
                $.post("http://localhost:8080/index/completeTask.do",
                    "id=" + $id,
                    function () {
                        displayAllTasks();
                    });
            });
        });

        $(document).ready(function () {
            $("#allTasks").on("click", ".btn-primary", function () {
                var $row = $(this).closest("tr");
                var $id = $row.find(".id").text();
                window.location.replace("http://localhost:8080/index/editTaskDescription.jsp?id=" +
                    $id);
            });
        });

        $(document).ready(function () {
            $("#toggleList").click(function () {
                $('#allTasks tbody > tr .status').filter(function () {
                    return $(this).text() === "true";
                }).each(function () {
                    $(this).closest("tr").toggle();
                });
            });
        });
    </script>
</head>
<body>
<div class="container text-right">
        <a class="nav-link" href="<%=request.getContextPath()%>/login.do"> <c:out
                value="${user.name}"/> | Sign in</a>
</div>
<div class="container">
    <form action="<%=request.getContextPath()%>/postTask.do" method="post">
        <div class="form-group">
            <label for="description">Task description:</label>
            <input type="text" class="form-control" placeholder="Task description"
                   name="description" id="description">
        </div>
        <button type="submit" class="btn btn-primary" onclick="return validate();">Create new
            task</button>
    </form>
</div>
<div class="container pt-5">
    <div>
        <button  type="button" class="btn btn-primary btn-sm" id="toggleList">Show all/ hide
            complete
        </button>
    </div>
</div>

<div class="container">
    <table class="table" id="allTasks" style="table-layout: fixed">
        <thead class="thead-light">
        <tr>
            <th style="display: none">Id</th>
            <th style="width: 40%">Description</th>
            <th style="width: 20%">Date</th>
            <th style="width: 20%">Status</th>
            <th style="width: 20%">Action</th>
        </tr>
        </thead>
        <tbody id="allTasksBody">

        </tbody>
    </table>
</div>
</body>
</html>