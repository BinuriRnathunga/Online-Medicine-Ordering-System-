package com.medicine;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addMedicineServlet")
public class addMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String medname = request.getParameter("medname");
        int medqty = Integer.parseInt(request.getParameter("medqty"));
        double medprice = Double.parseDouble(request.getParameter("medprice"));
        String medexpiredate = request.getParameter("medexpiredate");

        boolean isAdded = medicineDBUtil.addMedicine(medname, medqty, medprice, medexpiredate);

        // Check
        if (isAdded) {
            
            List<Medicine> medicineList = medicineDBUtil.getMedicineList();

           
            request.setAttribute("medicineList", medicineList);

           
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewMedicine.jsp");
            dispatcher.forward(request, response);
        } else {
            
            request.setAttribute("errorMessage", "Failed to add new medicine.");
            
    
            List<Medicine> medicineList = medicineDBUtil.getMedicineList();
            request.setAttribute("medicineList", medicineList);

           
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewMedicine.jsp");
            dispatcher.forward(request, response);
        }
    }
}


