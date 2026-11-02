/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import static java.rmi.server.LogStream.log;

import pe.model.AccountDao;
import pe.model.AccountDto;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class MainController extends HttpServlet {

    private static final String LOGIN="login.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= LOGIN;
        try {
            String action= request.getParameter("action");
            //-----            your code here   --------------------------------
            if (action == null) {
                action = "";
            }
            HttpSession session = request.getSession();
            AccountDto account = (AccountDto) session.getAttribute("account");

            switch (action) {
                case "login": {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    if (username == null || username.trim().isEmpty()
                            || password == null || password.isEmpty()) {
                        request.setAttribute("error", "Username and password are required");
                        request.setAttribute("username", username);
                        url = LOGIN;
                    } else {
                        AccountDao accountDao = new AccountDao();
                        AccountDto loggedIn = accountDao.checkLogin(username.trim(), password);
                        if (loggedIn == null) {
                            request.setAttribute("error", "Invalid user or password");
                            request.setAttribute("username", username);
                            url = LOGIN;
                        } else {
                            session.setAttribute("account", loggedIn);
                            url = "welcome.jsp";
                        }
                    }
                    break;
                }
                case "logout":
                    session.invalidate();
                    url = LOGIN;
                    break;
                default:
                    url = (account == null) ? LOGIN : "welcome.jsp";
                    break;
            }
            //-----            your code here   --------------------------------
        } catch (Exception e) {
            log("error at MainController: "+ e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
