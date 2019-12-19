/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.managed;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import ru.vrsp.grafik.dao.PostsFacadeLocal;
import ru.vrsp.grafik.entity.Posts;

/**
 *
 * @author Alex
 */
@Named(value = "postsBean")
@RequestScoped
public class PostsBean implements Serializable {
    @EJB
    private PostsFacadeLocal postDao;
    
    private List<Posts> postList = null;
    private Collection<Object> postCollectSelect = null;
    private Posts postSelect = null;
    
    /**
     * Creates a new instance of PostsBean
     */
    public PostsBean() {
    }

    public List<Posts> getPostList() {
        return postList;
    }

    public void setPostList(List<Posts> postList) {
        this.postList = postList;
    }

    public Collection<Object> getPostCollectSelect() {
        return postCollectSelect;
    }

    public void setPostCollectSelect(Collection<Object> postCollectSelect) {
        this.postCollectSelect = postCollectSelect;
    }

    public Posts getPostSelect() {
        return postSelect;
    }
    
    ////////////////////////////////////////////
    // methods
    public String addPost() {
        postSelect = null;
        return "postedit";
    }

    public String editPost() {
        String postIdStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("postId");
        //параметр действия
        Integer postIdInt = 0;
        
        try {
            postIdInt = Integer.parseInt(postIdStr);
        } catch (NumberFormatException numberFormatException) {
            return null;
        }
        
        postSelect = postDao.find(postIdInt);
        return "postedit";
    }
     
    @PostConstruct
    private void init() {
        postList = postDao.findAll();
    }
}
