<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Medicine</title>
    <link rel="stylesheet" href="css/medicineStyle.css">

    <!--validation -->
    <script>
        function validateForm() {
            var medname = document.getElementById("medname").value;
            var medqty = document.getElementById("medqty").value;
            var medprice = document.getElementById("medprice").value;
            var medexpiredate = document.getElementById("medexpiredate").value;

            if (medname === "" || medqty === "" || medprice === "" || medexpiredate === "") {
                alert("All fields are required");
                return false;
            }

            var currentDate = new Date();
            var currentDateString = currentDate.toISOString().split('T')[0];
            if (medexpiredate <= currentDateString) {
                alert("Please select a future date");
                return false;
            }

            if (isNaN(medqty) || parseInt(medqty) <= 0) {
                alert("Quantity must be a positive number");
                return false;
            }

            if (isNaN(medprice) || parseFloat(medprice) <= 0) {
                alert("Price must be a positive number");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
    <%
        String medid = request.getParameter("medid"); 
        String medname = request.getParameter("medname");
        String medqty = request.getParameter("medqty");
        String medprice = request.getParameter("medprice");
        String medexpiredate = request.getParameter("medexpiredate");
    %>

    <div class="form-container">
        <h1>UPDATE MEDICINE</h1>

        <form action="updateMedicineServlet" method="post" onsubmit="return validateForm()">
            <div class="form">
                <label>Medicine ID: </label>
                <input type="text" id="medid" name="medid" value="<%= medid %>" readonly><br><br>

                <label>Medicine Name: </label>
                <input type="text" id="medname" name="medname" value="<%= medname %>"><br><br>

                <label>Quantity: </label>
                <input type="number" id="medqty" name="medqty" value="<%= medqty %>"><br><br>

                <label>Price: </label>
                <input type="text" id="medprice" name="medprice" value="<%= medprice %>"><br><br>

                <label>Expire Date: </label>
                <input type="date" id="medexpiredate" name="medexpiredate" value="<%= medexpiredate %>"><br><br>
            </div>

            <div class="button-container">
                <button type="submit" name="update">Update</button>
            </div>
        </form>
    </div>
</body>
</html>