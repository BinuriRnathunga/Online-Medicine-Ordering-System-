package com.medicine;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewMedicineServlet")
public class viewMedicineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of medicine from the database 
        List<Medicine> medicineList = medicineDBUtil.getMedicineList();

        System.out.println("Retrieved medicines: " + medicineList);

        request.setAttribute("medicineList", medicineList);

        // Check if the medicine list is empty
        if (medicineList == null || medicineList.isEmpty()) {
            request.setAttribute("errorMessage", "No medicines found.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("onlyViewMedicine.jsp");
        dispatcher.forward(request, response);
    }
}


