package com.medicine;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/updateMedicineServlet")
public class updateMedicineServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get medicine details 
        int medid = Integer.parseInt(request.getParameter("medid"));
        String medname = request.getParameter("medname");
        int medqty = Integer.parseInt(request.getParameter("medqty"));
        double medprice = Double.parseDouble(request.getParameter("medprice"));
        String medexpiredate = request.getParameter("medexpiredate");

        // Call the updateMedicine method 
        boolean isSuccess = medicineDBUtil.updateMedicine(medid, medname, medqty, medprice, medexpiredate);

        if (isSuccess) {
            // Retrieve
            List<Medicine> medicineList = medicineDBUtil.getMedicineList();
            request.setAttribute("medicineList", medicineList);

            // Forward to viewMedicine.jsp 
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewMedicine.jsp");
            dispatcher.forward(request, response);
        } else {
            // If the update fail, display an error message 
            request.setAttribute("errorMessage", "Failed to update medicine.");
            List<Medicine> medicineList = medicineDBUtil.getMedicineList();
            request.setAttribute("medicineList", medicineList);

            
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewMedicine.jsp");
            dispatcher.forward(request, response);
        }
    }
}
