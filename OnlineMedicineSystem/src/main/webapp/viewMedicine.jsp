<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.medicine.Medicine" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Medicine Details</title>
    <link rel="stylesheet" href="css/viewMedicineStyle.css">
</head>
<body>
    <div class="table-container">
        <h1>Medicine Details</h1>

        <a href="#">
            <input type="button" class="update-button" name="homePage" value="Home Page">
        </a>
        <a href="addMedicine.jsp">
            <input type="button" class="update-button" name="addNewMedicine" value="Add New Medicine">
        </a> <br><br>
		    <form action="viewMedicineServlet" method="get">
		        <button type="submit" class="update-button">View Medicines</button>
		    </form>

        <!-- Display  error message -->
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <div class="table">
            <%
                Object medicineListObj = request.getAttribute("medicineList");
           		List<Medicine> medicineList = new ArrayList<>();

                // Check the Medicine objects valid or not
                if (medicineListObj instanceof List<?>) {
                    for (Object obj : (List<?>) medicineListObj) {
                        if (obj instanceof Medicine) {
                            medicineList.add((Medicine) obj);
                        }
                    }
                }
            %>

            <table class="styled-table">
                <thead>
                    <tr>
                        <th>Medicine ID</th>
                        <th>Medicine Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Expire Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        // if medicineList is not empty and displaying medicines
                        if (medicineList != null && !medicineList.isEmpty()) {
                            for (Medicine med : medicineList) { 
                    %>
                    <tr>
                        <td><%= med.getMedid() %></td>
                        <td><%= med.getMedname() %></td>
                        <td><%= med.getMedqty() %></td>
                        <td><%= med.getMedprice() %></td>
                        <td><%= med.getMedexpiredate() %></td>
                        <td>
                        
                        		<!-- Update Button-->
                        
                            <a href="updateMedicine.jsp?medid=<%= med.getMedid() %>&medname=<%= med.getMedname() %>&medqty=<%= med.getMedqty() %>&medprice=<%= med.getMedprice() %>&medexpiredate=<%= med.getMedexpiredate() %>">
                                <input type="button" class="update-button" name="update" value="Update">
                            </a>
                            
                            	<!-- Delete Button -->
                            <form action="deleteMedicineServlet" method="post" style="display:inline;">
                                <input type="hidden" name="medid" value="<%= med.getMedid() %>">
                                <button type="submit" class="delete-button" onclick="return confirm('Are you sure you want to delete this medicine?');">Delete</button>
                            </form>
                        </td>
                    </tr>
                    <% 
                            } 
                        } else { 
                    %>
                    <tr>
                        <td colspan="6">No Medicines Available</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>

