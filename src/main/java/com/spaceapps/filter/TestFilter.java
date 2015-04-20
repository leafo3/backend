package com.spaceapps.filter;

import com.sun.jersey.api.representation.Form;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import org.springframework.util.MultiValueMap;

/**
 * Created by Alberto Rubalcaba on 4/18/2015.
 */
public class TestFilter implements ContainerRequestFilter{

    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) {
        if(containerRequest.getHeaderValue("Content-Type").equalsIgnoreCase("multipart/form-data")){
            FormDataMultiPart multiPart = null;
            try{
                //containerRequest.getEntity(FormDataMultiPart.class);
            }   catch(Exception ex){
                //try with paramters
                Form form = containerRequest.getFormParameters();
                //TODO: LETS CHECK IT OUT
                form.getFirst("");
            }

        }
        return containerRequest;
    }
}
