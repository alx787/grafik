/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import ru.vrsp.grafik.dao.PostsFacadeLocal;
import ru.vrsp.grafik.entity.Posts;

/**
 *
 * @author Alex
 */
@Named(value = "postEditBean")
@ViewScoped
public class PostEditBean implements Serializable {
    @EJB
    private PostsFacadeLocal postDao;
    
    private Integer id;
    private String name;
    private Integer sorting;
    
    private Posts postSelect;

    @Inject
    private PostsBean postBean;
    
    /**
     * Creates a new instance of PostEditBean
     */
    public PostEditBean() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    @Override
    public String toString() {
        return "PostEditBean{" + "id=" + id + ", name=" + name + "}";
    }
    
    public String onActionOK() {
        onAction();
        return "postlist";
    }
    
    public String onActionSave() {
        onAction();
        return null;
    }
    public void onAction() {
        if (postSelect == null) {
            //создаем новый
            Posts postNew = new Posts();
            postNew.setName(name);
            postNew.setSorting(sorting);
            
            postDao.create(postNew);
            postSelect = postNew;
        } else {
            postSelect.setName(name);
            postSelect.setSorting(sorting);
            
            postDao.edit(postSelect);
        }
    }
    
    @PostConstruct
    private void init() {
        
        postSelect = null;
        
        if (postBean != null && postBean.getPostSelect() != null) {
            postSelect = postBean.getPostSelect();
        }
        
        if (postSelect == null) {
            this.name = "Новая должность";
        } else {
            this.id = postSelect.getId();
            this.name = postSelect.getName();
            this.sorting = postSelect.getSorting();
        }
    }
}
