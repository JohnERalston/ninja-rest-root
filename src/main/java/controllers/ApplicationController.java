package controllers;

import com.google.inject.Inject;
import ninja.Result;
import ninja.Results;
import com.google.inject.Singleton;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import filters.AuthenticationFilter;
import filters.CrossOriginAccessControlFilter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import models.FacebookAuthenticateRequest;
import models.Blog;
import ninja.FilterWith;
import ninja.params.PathParam;
import ninja.utils.NinjaProperties;
import util.AuthenticationUtil;

@Singleton
@FilterWith(CrossOriginAccessControlFilter.class)
public class ApplicationController {

    @Inject 
    private NinjaProperties ninjaProperties;

    private List<Blog> blogs = new LinkedList<Blog>();
    
    public Result cors() {
        return Results.json();
    }
    
    /*public Result authenticate(FacebookAuthenticateRequest request) {
        FacebookClient facebookClient = new DefaultFacebookClient(request.getToken(), ninjaProperties.get("facebook.secret"));
        User user = facebookClient.fetchObject("me", User.class);
        boolean authenticated = user.getEmail().equalsIgnoreCase(request.getEmail());
        if(authenticated) {
            String token = AuthenticationUtil.generateToken(user.getEmail(), ninjaProperties.get("application.secret"));
            HashMap<String, String> authorisationMap = new HashMap<String, String>();
            authorisationMap.put(AuthenticationUtil.tokenHeader, token);
            authorisationMap.put("userEmail", user.getEmail());
            return Results.json().render(authorisationMap);
        } else {
            return Results.unauthorized();
        }
    }
    
    @FilterWith(AuthenticationFilter.class)
    public Result saveBlog(Blog blog) {
        blog.setTitle(blog.getTitle()+ " - edited");
        blog.setContent(blog.getContent() + " - edited");
        return Results.json().render(blog);
    }
    
    //@FilterWith(AuthenticationFilter.class)
    public Result getBlog(@PathParam("id") int id) {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setTitle("CORS Stateless REST Service with Facebook Authentication");
        blog.setContent("How to authenticate a HTML5 client with a service using Facebook authentication");
        return Results.json().render(blog);
    }*/

    public Result listBlogs() {
        return Results.json().render(blogs);
    }

    public Result viewBlog(@PathParam("id") int id) {
        Blog blog = getBlog(id);
        return Results.json().render(blog);
    }

    public Result editBlog(Blog blog) {
        Blog forEdit = getBlog(blog.getId());
        forEdit.setTitle(blog.getTitle());
        forEdit.setContent(blog.getContent());
        return Results.json().render(forEdit);
    }

    public Result createBlog(Blog blog) {
        blog.setId(blogs.size()+1);
        blog.setTitle(blog.getTitle());
        blog.setContent(blog.getContent());
        blogs.add(blog);
        return Results.json().render(blog);
    }

    public Result deleteBlog(Blog blog) {
        Blog forDelete = getBlog(blog.getId());
        blogs.remove(forDelete);
        return Results.json().render(blog);
    }

    private Blog getBlog(int id) {
        Blog blog = new Blog();
        for(int i=0; i<blogs.size(); i++) {
            Blog b = blogs.get(i);
            if( b.getId() == id) {
                blog = b;
            }
        }
        return blog;
    }
    
}
