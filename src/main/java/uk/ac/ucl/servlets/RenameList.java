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

@WebServlet("/renameList.html")
public class RenameList extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        String newListName = request.getParameter("newListName");
        String errorMessage = model.validateNewListName(newListName);
        if (errorMessage.equals(""))
        {
            model.renameCurrentList(newListName);
            goToSuccessPage(request, response);
        }
        else
        {
            goBackToRenameListPage(request, response, errorMessage, model);
        }
    }

    private void goToSuccessPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setAttribute("message", "List Renamed!");
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"success.jsp");
        dispatch.forward(request, response);
    }

    private void goBackToRenameListPage(HttpServletRequest request, HttpServletResponse response, String errorMessage,
                                        Model model) throws ServletException, IOException
    {
        request.setAttribute("listName", model.getCurrentListName());
        request.setAttribute("errorMessage", errorMessage);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"renameList.jsp");
        dispatch.forward(request, response);
    }
}
