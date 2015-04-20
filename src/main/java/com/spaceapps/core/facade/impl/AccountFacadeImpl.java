package com.spaceapps.core.facade.impl;

import com.spaceapps.core.UserRepository;
import com.spaceapps.core.facade.AccountFacade;
import com.spaceapps.core.impl.BaseRepositoryImpl;
import com.spaceapps.core.impl.UserRepositoryImpl;
import com.spaceapps.model.User;
import com.spaceapps.model.ValidationError;
import com.spaceapps.model.dto.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class AccountFacadeImpl implements AccountFacade {

    public AccountFacadeImpl(){

    }

    @Override
    public Response createAccount(UriInfo uriInfo, UserRepository repo) {
        //get user
        boolean hasError = false;
        NewResourceResponse newResourceResponse = new NewResourceResponse();

        User user = getUser(uriInfo);
        try{
            repo.insert(user);
        }catch(Exception ex){
            hasError = true;
        }

        if(hasError){
            //return a bad response
            newResourceResponse.setId("");
            newResourceResponse.setMessage("Failure");
            newResourceResponse.setSuccess(false);
        }else{
            newResourceResponse.setId(user.getId());
            newResourceResponse.setSuccess(true);
            newResourceResponse.setMessage("Success");
        }

        //create a response
        Response response = Response.ok().entity(newResourceResponse).build();

        return response;
    }

    @Override
    public Response login(UriInfo uriInfo, UserRepository repo) {
        Response response = null;
        List<ValidationError> errors = new ArrayList<ValidationError>();
        ValidationResponse validationResponse = null;
        //get params
        boolean validationError = false;
        boolean hasError = false;
        String id = null;

        String nickname = uriInfo.getQueryParameters().getFirst(UserRepositoryImpl.NICKNAME);
        String password = uriInfo.getQueryParameters().getFirst(UserRepositoryImpl.PASSWORD);

        if(nickname == null || nickname.isEmpty()){
            errors.add(new ValidationError("No nickname provided", "Validation error"));
            validationError = true;
        }else if(password == null || password.isEmpty()){
            validationError = true;
            errors.add(new ValidationError("No password provided", "Validation error"));
        }

        if(validationError){
            //return a response
            validationResponse = new ValidationResponse(errors);
            response = Response.ok().entity(validationResponse).build();
            return response;
        }else{
            //request a login
            try{
                id = repo.login(nickname, password);
            } catch(Exception ex){
                hasError = true;
            }
        }
        if(hasError){
            //return error
            response = Response.ok().entity(new ErrorResponse("Could not complete your request, try again in a few minutes", "Server Error")).build();
        }else{
            response = Response.ok().entity(new LoginResponse(id, "Welcome")).build();
        }
        return response;
    }

    @Override
    public Response findUser(UriInfo uriInfo, UserRepository repo) {
        Response response = null;
        List<ValidationError> errors = new ArrayList<ValidationError>();
        ValidationResponse validationResponse = null;
        //get params
        boolean validationError = false;
        boolean hasError = false;
        User user = null;

        String userId = uriInfo.getQueryParameters().getFirst(UserRepositoryImpl.USER_ID);

        if(userId == null || userId.isEmpty()){
            errors.add(new ValidationError("No id user provided", "Validation error"));
            validationError = true;
        }

        if(validationError){
            //return a response
            validationResponse = new ValidationResponse(errors);
            response = Response.ok().entity(validationResponse).build();
            return response;
        }else{
            //request a find user by id
            try{
                user = repo.find(userId);
            } catch(Exception ex){
                hasError = true;
            }
        }
        if(hasError){
            //return error
            response = Response.ok().entity(new ErrorResponse("Could not complete your request, try again in a few minutes", "Server Error")).build();
        }else{
            response = Response.ok().entity(new FindResourceResponse<User>(user, true,"loged")).build();
        }
        return response;
    }

    public User getUser(UriInfo uriInfo){
        User user = new User();
        String nickname = uriInfo.getQueryParameters().getFirst(BaseRepositoryImpl.NICKNAME);
        String email = uriInfo.getQueryParameters().getFirst(BaseRepositoryImpl.EMAIL);
        String password = uriInfo.getQueryParameters().getFirst(BaseRepositoryImpl.PASSWORD);
        String location = uriInfo.getQueryParameters().getFirst(BaseRepositoryImpl.LOCATION);
        String interest = uriInfo.getQueryParameters().getFirst(BaseRepositoryImpl.INTEREST);

        user.setNickname(nickname);
        user.setEmail(email);
        user.setPassword(password);
        user.setLocation(location);
        user.setInterest(interest);
        return user;
    }
}
