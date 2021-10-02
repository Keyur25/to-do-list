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

@WebServlet("/addToList.html")
public class AddToList extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        String errorMessage  = model.validateNewItem(type, value);
        if (errorMessage.equals("")) model.addItemToCurrentList(type, value);
        else request.setAttribute("errorMessage", errorMessage);
        goBackToAddItems(request, response, model);
    }

    private void goBackToAddItems(HttpServletRequest request, HttpServletResponse response, Model model)
            throws ServletException, IOException
    {
        request.setAttribute("listName", model.getCurrentListName());
        request.setAttribute("list", model.getCurrentList());
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"addItems.jsp");
        dispatch.forward(request, response);
    }
}
