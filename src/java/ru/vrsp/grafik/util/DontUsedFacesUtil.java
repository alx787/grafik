/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.util;

import javax.faces.context.FacesContext;

/**
 *
 * @author Alex
 */
public class DontUsedFacesUtil {
   // Getters -----------------------------------------------------------------------------------
    public static Object getSessionMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    // Setters -----------------------------------------------------------------------------------
    public static void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    // Remove -----------------------------------------------------------------------------------
    public static void removeSessionMapValue(String key) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
        } catch (Exception e) {
            
        }
    }
}
