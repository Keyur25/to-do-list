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

@WebServlet("/modifyActionType.html")
public class ModifyActionType extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        String action = request.getParameter("typeOfAction");
        model.setModifyAction(action);
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"existingListName.jsp");
        dispatch.forward(request, response);
    }
}
