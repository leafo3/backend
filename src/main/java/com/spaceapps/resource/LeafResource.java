package com.spaceapps.resource;

import com.spaceapps.core.LeafRepository;
import com.spaceapps.core.UserRepository;
import com.spaceapps.core.facade.LeafInfoFacade;
import com.spaceapps.model.LeafInfoFileContent;
import com.spaceapps.util.BeanUtils;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.multipart.MultiPart;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
@Path("/leafs")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class LeafResource {

    private static final Logger logger = Logger.getLogger(LeafResource.class.getName());

    @Context
    private ApplicationContext context;

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createLeaf(@FormDataParam("file") InputStream leafStream,
                               @FormDataParam("file")FormDataContentDisposition leafDisposition,
                               @FormDataParam("email")String email,
                               @FormDataParam("location")String location,
                               @FormDataParam("title")String title,
                               @FormDataParam("comment")String comment){

        logger.info("Got new leaf! " + new Date().toString()); 
        LeafInfoFacade facade = BeanUtils.getLeafFacade(context);
        return facade.createNewLeaf(leafStream, leafDisposition.getName(), email, location, title, comment);
    }

    @Path("/leaf_list")
     @GET
     public Response getLeafInfo(){
        LeafInfoFacade facade = BeanUtils.getLeafFacade(context);
        LeafRepository repo = BeanUtils.getLeafRepository(context);
        return facade.findAll(null, repo);
     }
}
