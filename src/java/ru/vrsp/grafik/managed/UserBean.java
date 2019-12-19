/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ru.vrsp.grafik.dao.DepartFacadeLocal;
import ru.vrsp.grafik.dao.UsersFacadeLocal;
import ru.vrsp.grafik.entity.Depart;
import ru.vrsp.grafik.entity.Users;

/**
 *
 * @author Alex
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    @EJB
    private DepartFacadeLocal depDao;
    @EJB
    private UsersFacadeLocal usersDao;
    
    private Integer id;
    private Boolean admin;
    private String name;
    private String password;
    // доступные 
    private List<Depart> departs;
    private Users users;
    
    // параметры настройки
    private Date periodBegin;
    private Date periodEnd;
    private boolean showAll;
    

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Depart> getDeparts() {
        return departs;
    }

    public void setDeparts(List<Depart> departs) {
        this.departs = departs;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPeriodBegin() {
        return periodBegin;
    }

    public void setPeriodBegin(Date periodBegin) {
        this.periodBegin = periodBegin;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public boolean getShowAll() {
        return showAll;
    }

    public void setShowAll(boolean showAll) {
        this.showAll = showAll;
    }
    
    //////////////////////////////////////////////////////
    // methods
    
    //попытка залогиниться
    public String tryLogin() {
        
        id = null;
        admin = null;
        departs = null;
        users = null;

        // создаем сессию
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        
        try {
            users = usersDao.userLogin(name, MD5(password));
        }
        catch (NoSuchAlgorithmException e) {
            users = null;
        }
        
        if (users != null) {
            id = users.getId();
            admin = users.getAdm();
            name = users.getFullname();
            
            if (users.getAdm()) {
                departs = depDao.findAllDeparts();
            } else {
                departs = depDao.findByUser(users);
            }
            
            
            sess.setAttribute("user", users);
            
            return "pages/grafiklist.xhtml";
        } else {
            id = null;
            admin = null;
            name = null;
            departs = null;

            sess.invalidate();
            
            return null;
        }
    }

    //попытка разлогиниться
    public String tryLogout() {
        // создаем сессию
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (sess != null) {
            sess.setAttribute("user", null);
            sess.removeAttribute("user");
            sess.invalidate();
        }
        
        return "/faces/login.xhtml";
    }
    
    public static String MD5(String input) throws NoSuchAlgorithmException{
        String res = "";
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        algorithm.reset();
        algorithm.update(input.getBytes());
        byte[] md5 = algorithm.digest();
        String tmp = "";
        for (int i = 0; i < md5.length; i++) {
             tmp = (Integer.toHexString(0xFF & md5[i]));
             if (tmp.length() == 1) {
                 res += "0" + tmp;
             } else {
                 res += tmp;
             }
        }
        return res;
    }    
    
    @PostConstruct
    private void init() {
        //System.out.println(" ==================== from user bean ");
        showAll = false;

        // ============== установка времени начало
        Date tekDate = new Date();
        GregorianCalendar gregCalendar = new GregorianCalendar();
        gregCalendar.setTime(tekDate);
        
        if (periodBegin == null) {
            //gregCalendar.set(GregorianCalendar.DAY_OF_MONTH, gregCalendar.getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
            gregCalendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
            periodBegin = gregCalendar.getTime();
        }
        
        if (periodEnd == null) {
            gregCalendar.set(GregorianCalendar.DAY_OF_MONTH, gregCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
            periodEnd = gregCalendar.getTime();
        }
        // ============== установка времени конец

    }
    
}
