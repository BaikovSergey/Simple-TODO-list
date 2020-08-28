<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
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

        $.getJSON('http://localhost:8080/index/getAllTasks.do', function (data) {
            for (var i = 0; i < data.length; i++) {
                $('#tasksTable tr:last').after('<tr>' +
                    '<td>' + data[i].description + '</td>' +
                    '<td>' + data[i].created + '</td>' +
                    '<td>' + data[i].status + '</td></tr>');
            }
        });
    </script>
</head>
<body>
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
    <div class="form-check">
        <label class="form-check-label">
            <input type="checkbox" class="form-check-input" value="">Show all tasks
        </label>
    </div>
</div>
<div class="container">
    <table class="table" id="tasksTable">
        <thead class="thead-light">
        <tr>
            <th>Description</th>
            <th>Date</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
</body>
</html>