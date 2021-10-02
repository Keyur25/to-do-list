package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Item;
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
import java.util.Hashtable;

@WebServlet("/search.html")
public class Search extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Model model = ModelFactory.getModel();
        String searchString = request.getParameter("searchString");
        String isListOnly = request.getParameter("listNameOnly"); // if true then the search string is only looked for in list names
        if (isListOnly==null) isListOnly="";
        Hashtable<String, ArrayList<Item>> searchResults = model.searchFor(searchString, isListOnly);
        request.setAttribute("allLists", searchResults);
        request.setAttribute("maxLength", model.lengthOfLongestList(searchResults));
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher(File.separator+"viewLists.jsp");
        dispatch.forward(request, response);
    }
}
