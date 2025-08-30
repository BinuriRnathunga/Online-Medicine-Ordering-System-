package com.medicine;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/deleteMedicineServlet")
public class deleteMedicineServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String medid = request.getParameter("medid");

       
        boolean isSuccess = medicineDBUtil.deleteMedicine(medid);

        if (isSuccess) {
           
            List<Medicine> medicineList = medicineDBUtil.getMedicineList();
            request.setAttribute("medicineList", medicineList);

           
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewMedicine.jsp");
            dispatcher.forward(request, response);
        } else {
            
            request.setAttribute("errorMessage", "Failed to delete medicine.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}




