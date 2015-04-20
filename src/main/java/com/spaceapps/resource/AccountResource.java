package com.spaceapps.resource;

import com.spaceapps.core.UserRepository;
import com.spaceapps.core.facade.AccountFacade;
import com.spaceapps.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
@Path("/account")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    private Logger logger = Logger.getLogger("AccountResource");

    @Context
    private ApplicationContext context;

    @Context
    private UriInfo uriInfo;

    @Path("/create")
    @POST
    public Response createAccount(){
        //get facade
        AccountFacade facade = BeanUtils.getAccountFacade(context);
        UserRepository repo = BeanUtils.getUserRepository(context);
        return facade.createAccount(uriInfo, repo);
    }

    @Path("/login")
    @GET
    public Response login(){
        logger.info("Login request arrive jejejejej");
        AccountFacade facade = BeanUtils.getAccountFacade(context);
        UserRepository repo = BeanUtils.getUserRepository(context);
        return facade.login(uriInfo, repo);
    }

    @Path("/find_user")
    @GET
    public Response findUser(){
        AccountFacade facade = BeanUtils.getAccountFacade(context);
        UserRepository repo = BeanUtils.getUserRepository(context);
        return facade.findUser(uriInfo, repo);
    }

    @Path("/email_availability")
    @POST
    public Response isEmailAvailable(){
        return null;
    }
}
