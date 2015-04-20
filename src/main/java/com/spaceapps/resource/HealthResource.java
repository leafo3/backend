package com.spaceapps.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
@Path("/health")
@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HealthResource {

    @GET
    public String checkHealth(){
        return "SpaceApps Version 0.1 =)";
    }
}
