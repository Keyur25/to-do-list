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
import java.util.ArrayList;

@WebServlet("/newListFrame.html")
public class NewListFrame extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String errorMessage = model.validateNewListName(listName);
        if (errorMessage.equals(""))
        {
            updateModel(model, listName);
            goToAddItemsPage(request, response, model);
        }
        else
        {
            goBackToInitialiseNewListName(request, response, errorMessage);
        }
    }

    private void updateModel(Model model, String listName)
    {
        model.setCurrentListName(listName);
        model.setCurrentList(new ArrayList<>());
    }

    private void goToAddItemsPage(HttpServletRequest request, HttpServletResponse response, Model model)
            throws ServletException, IOException
    {
        request.setAttribute("listName", model.getCurrentListName());
        request.setAttribute("list", model.getCurrentList());
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"addItems.jsp");
        dispatch.forward(request, response);
    }

    private void goBackToInitialiseNewListName(HttpServletRequest request, HttpServletResponse response,
                                               String errorMessage) throws ServletException, IOException
    {
        request.setAttribute("errorMessage", errorMessage);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"newListName.jsp");
        dispatch.forward(request, response);
    }
}