/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.vrsp.grafik.dao.EmployeesFacadeLocal;
//import ru.vrsp.grafik.dao.DepartFacadeLocal;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Employees;
import static ru.vrsp.grafik.managed.UserBean.MD5;
//import ru.vrsp.grafik.dao.EmployeesFacade;

/**
 *
 * @author Alex
 */
@WebServlet(name = "DaoTestServlet", urlPatterns = {"/DaoTestServlet"})
public class DaoTestServlet extends HttpServlet {
    @EJB
    private EmployeesFacadeLocal daoEmpl;
    //@PersistenceContext(name = "GrafikPU")
    //private EntityManager emf;

    
    
    
    
    
    
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
        
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DaoTestServlet</title>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DaoTestServlet at " + request.getContextPath() + "</h1>");
            out.println("<br/>");
            out.println("using GET parameter \"pass\"  to get md5 hash");
            out.println("<br/>");
            
            String parPass = request.getParameter("pass");
            if (parPass != null) {
                try {
                    out.println("md5 hash for " + parPass + " is " + MD5(parPass));
                } catch (NoSuchAlgorithmException ex) {
                    out.println("ошибка NoSuchAlgorithmException");
                }
            }

            
//            try {
//            List<Employees> resEmp = daoEmpl.findAll();
//            for (Employees sss : resEmp) {
//                out.println("<br/>" + sss);
//            }
//            
//            
//            } catch(Exception e) {
//                out.println("<br/>" + e.toString());
//            }
            
            out.println("</body>");
            out.println("</html>");
                
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
