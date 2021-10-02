package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/editList.html")
public class EditList extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        String itemToEdit = request.getParameter("itemToEdit");
        String newValue = request.getParameter("newValue");
        String currentType = itemToEdit.split(";" )[0];
        String currentValue = itemToEdit.split(";")[1];
        String errorMessage  = model.validateNewItem(currentType, newValue);
        if (errorMessage.equals("")) model.editItemInCurrentList(currentType, currentValue, newValue);
        else request.setAttribute("errorMessage", errorMessage);
        goBackToEditItems(request, response, model);
    }

    private void goBackToEditItems(HttpServletRequest request, HttpServletResponse response, Model model)
            throws ServletException, IOException
    {
        request.setAttribute("listName", model.getCurrentListName());
        request.setAttribute("list", model.getCurrentList());
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"editItems.jsp");
        dispatch.forward(request, response);
    }
}
