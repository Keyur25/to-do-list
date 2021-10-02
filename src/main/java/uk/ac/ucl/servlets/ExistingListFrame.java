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

@WebServlet("/existingListFrame.html")
public class ExistingListFrame extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        String listName = request.getParameter("listName");
        String errorMessage = model.validateExistingListName(listName);
        if (errorMessage.equals(""))
        {
            setAttributes(request, model, listName);
            goToActionPage(request, response, model);
        }
        else
        {
            goBackToExistingListNamePage(request, response, errorMessage);
        }
    }

    private void setAttributes(HttpServletRequest request, Model model, String listName)
    {
        model.setCurrentListName(listName);
        model.setCurrentList(model.getListWithName(listName));
        request.setAttribute("listName", model.getCurrentListName());
        request.setAttribute("list", model.getCurrentList());
    }

    private void goToActionPage(HttpServletRequest request, HttpServletResponse response, Model model)
            throws ServletException, IOException
    {
        switch (model.getModifyAction())
        {
            case "add" -> goToAddItemsPage(request, response);
            case "edit" -> goToEditItemsPage(request, response);
            case "remove" -> goToRemoveItemsPage(request, response);

            case "rename" -> goToRenameListsPage(request, response);

            case "delete" -> goToDeleteListPage(request, response);
        }
    }

    private void goToAddItemsPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"addItems.jsp");
        dispatch.forward(request, response);
    }

    private void goToEditItemsPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"editItems.jsp");
        dispatch.forward(request, response);
    }

    private void goToRemoveItemsPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"removeItems.jsp");
        dispatch.forward(request, response);
    }

    private void goToRenameListsPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"renameList.jsp");
        dispatch.forward(request, response);
    }

    private void goToDeleteListPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"deleteList.jsp");
        dispatch.forward(request, response);
    }

    private void goBackToExistingListNamePage(HttpServletRequest request, HttpServletResponse response,
                                              String errorMessage) throws ServletException, IOException
    {
        request.setAttribute("errorMessage", errorMessage);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"existingListName.jsp");
        dispatch.forward(request, response);
    }
}