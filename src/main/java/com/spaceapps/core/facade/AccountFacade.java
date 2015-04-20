package com.spaceapps.core.facade;

import com.spaceapps.core.UserRepository;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public interface AccountFacade {
    public Response createAccount(UriInfo uriInfo, UserRepository repo);
    public Response login(UriInfo uriInfo, UserRepository repo);
    public Response findUser(UriInfo uriInfo, UserRepository repo);
}
