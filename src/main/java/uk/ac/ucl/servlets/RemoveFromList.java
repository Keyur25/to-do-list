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

@WebServlet("/removeFromList.html")
public class RemoveFromList extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        String itemToRemove = request.getParameter("itemToRemove");
        String type = itemToRemove.split(";" )[0];
        String value = itemToRemove.split(";")[1];
        String errorMessage = model.validateRemoveItem();
        if (errorMessage.equals("")) model.removeItemFromCurrentList(type, value);
        else request.setAttribute("errorMessage", errorMessage);
        goBackToRemoveItems(request, response, model);
    }

    private void goBackToRemoveItems(HttpServletRequest request, HttpServletResponse response, Model model)
            throws ServletException, IOException
    {
        request.setAttribute("listName", model.getCurrentListName());
        request.setAttribute("list", model.getCurrentList());
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"removeItems.jsp");
        dispatch.forward(request, response);
    }
}
