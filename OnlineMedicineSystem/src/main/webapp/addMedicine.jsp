<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.medicine.Medicine" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ADD MEDICINE DETAILS</title>
    
    <link rel="stylesheet" href="css/medicineStyle.css">

    <!-- validation -->
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
    <div class="form-container">
    
       			 <h1>ADD MEDICINE</h1>
        
			  
        <form action="addMedicineServlet" method="post" onsubmit="return validateForm()">
            <div class="form">
                <label>Medicine Name: </label>
                <input type="text" id="medname" name="medname"><br><br>

                <label>Quantity: </label>
                <input type="text" id="medqty" name="medqty"><br><br>

                <label>Price: </label>
                <input type="text" id="medprice" name="medprice"><br><br>

                <label>Category: </label>
                <select id="category" name="category">
                    <option value="Tablet">Tablet</option>
                    <option value="Syrup">Syrup</option>
                    <option value="Injection">Injection</option>
                </select>
                <br><br>

                <label>Expire Date: </label>
                <input type="date" id="medexpiredate" name="medexpiredate"><br>
            </div>

            <div class="button-container">
                <button type="reset">Clear</button>
                <button type="submit">Add Medicine</button>
            </div>
        </form><br>
        <form action="viewMedicineServlet" method="get">
			        <button type="submit" class="update-button">View Medicines</button>
			  </form>
    </div>
</body>
</html>