package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import Dao.LoginDao;
import Model.Credentials;


@Path("/login")
public class Login {

    @Context
    private HttpServletRequest request;

    LoginDao dao = new LoginDao();


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean create(final Credentials credentials) {
        System.out.println("creating login");
        this.request.getSession().setAttribute("user", credentials.getUserName());
        return this.dao.login(credentials);


    }



}
