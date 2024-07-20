package com.example.java_core9.ts2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "userServlet", value = "/user-servlet")
public class UserServlet extends HttpServlet {
    private DAO userDAO;
    public List<User> areas = new ArrayList<>();
    private int numberr;
    private String numberCookie;
    private String loginCookie = "testLogin";
    private String roleCookie = "testRole";
    private String dateCookie;


    public void init() {
        try {
            System.out.println("Initializing servlet...");
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
                System.out.println("Connection to DB successfully (func getConnection())");

            }catch (Exception ex){
                System.out.println(ex);
            }
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            String url = "jdbc:sqlserver://BLUEBOX-228;databaseName=Lab09_java;trustServerCertificate=true;encrypt=false;IntegratedSecurity=false";
            String username = "sa";
            String password = "1111";
            Database database = new Database(driver, url, username, password);

            this.userDAO = new DAO(database);



            System.out.println("Connection to DB successfully (func getConnection())");
        } catch(Exception ex) {
            System.out.println("Error connection to DB (func getConnection())");
            System.out.println(ex);
        }
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Date currentDate = new Date();
        boolean isFoundUser = false;
        numberr++;

        try {
            areas = userDAO.list();
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve areas", e);
        }

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        for (User user : areas) {
            if (user.UserName.equals(login) && user.UserPassword.equals(password)) {
//                out.println("<h1>Greetings!</h1>");
//                out.println("<h3>Name: " + user.getUserName() + "</h3>");
//                out.println("<h3>Role: " + user.getUserRole() + "</h3>");
//                out.println("<h3>Date: " + currentDate + "</h3>");
                request.setAttribute("name", user.getUserName());
                request.setAttribute("role", user.getUserRole());
                request.setAttribute("date", currentDate);
                isFoundUser = true;
                loginCookie = user.getUserName();
                roleCookie = user.getUserRole();
                dateCookie = currentDate.toString();
                numberCookie = "1";
            }
        }
    }
}
