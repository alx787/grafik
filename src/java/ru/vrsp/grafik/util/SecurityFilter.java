/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ru.vrsp.grafik.entity.Users;

/**
 *
 * @author user
 */
public class SecurityFilter implements Filter {
    private FilterConfig filterConfig;

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println(" ================== from servlet");
        
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession sess = req.getSession(false);
        
        if (sess == null || sess.getAttribute("user") == null) {
            req.getRequestDispatcher("/faces/login.xhtml").forward(request, response);    
        } else {
//            System.out.println(" ================== curr user: " + ((Users)sess.getAttribute("user")).toString());
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
    
}
