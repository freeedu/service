package org.personal.mason.feop.server.blog.mvc.utils;

public enum ViewMapper {

    Login("login"),
    Logout("logout"),
    Index("app.index"),

    Blog_List("app.blog.list"),
    Blog_New("app.blog.new"),
    Blog_View("app.blog.view"),
    Blog_Edit("app.blog.edit"),
    Blog_My_List("app.blog.my.list");


    private String viewName;

    private ViewMapper(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
