package com.spaceapps.core.facade.impl;

import com.spaceapps.core.BaseRepository;
import com.spaceapps.core.ImageUtils;
import com.spaceapps.core.LeafRepository;
import com.spaceapps.core.UserRepository;
import com.spaceapps.core.facade.LeafInfoFacade;
import com.spaceapps.core.impl.BaseRepositoryImpl;
import com.spaceapps.model.LeafInfo;
import com.spaceapps.model.LeafInfoFileContent;
import com.spaceapps.model.User;
import com.spaceapps.model.ValidationError;
import com.spaceapps.model.dto.FindResourceResponse;
import com.spaceapps.model.dto.NewLeafResponse;
import com.spaceapps.model.dto.NewResourceResponse;
import com.spaceapps.model.dto.ValidationResponse;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger; 

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class LeafInfoFacadeImpl implements LeafInfoFacade{

    private static final Logger logger = Logger.getLogger(LeafInfoFacadeImpl.class.getName());

    private LeafRepository leafRepository;
    public LeafInfoFacadeImpl(LeafRepository repository){
        this.leafRepository = repository;
    }


    @Override
    public Response create(UriInfo uriInfo, LeafRepository repo, LeafInfoFileContent content) {


        return null; //Response.ok().entity(new NewResourceResponse(id, true, "Success")).build();
    }

    private String saveImage(InputStream content, String fileName, String email){
        try{
            //create a directory with email name
            File emailDir = new File(email);
            emailDir.mkdir();
            if(! fileName.endsWith(".jpg"))
                //add jpg extension
                fileName += ".jpg";
            //set the name to the date
            String newFileName = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss").format(new Date());
            File destinationFile = new File(emailDir, newFileName + ".jpg");
            byte[] bytes = IOUtils.toByteArray(content);

            if(! destinationFile.exists())
                destinationFile.createNewFile();

            FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
            fileOutputStream.write(bytes);

            fileOutputStream.flush();
            fileOutputStream.close();
            return destinationFile.getAbsolutePath();
        }catch(IOException ex){
            return null;
        }
    }

    @Override
    public Response findAll(UriInfo uriInfo, LeafRepository repo){
        Response response = null;
        boolean hasError = false;
        NewResourceResponse newResourceResponse = new NewResourceResponse();

        try{
            List<LeafInfo> list = repo.findAll();
            response = Response.ok().entity(new FindResourceResponse<List<LeafInfo>>(list, true, "al publishes were...")).build();
            return response;
        }catch(Exception ex){
            hasError = true;
        }

        if(hasError){
            //return a bad response
            newResourceResponse.setId("");
            newResourceResponse.setMessage("Failure");
            newResourceResponse.setSuccess(false);
        }else{
            newResourceResponse.setId("");
            newResourceResponse.setSuccess(true);
            newResourceResponse.setMessage("Success");
        }
        response = Response.ok().entity(newResourceResponse).build();
        return response;
    }

    @Override
    public Response createNewLeaf(InputStream stream, String fileName, String email, String location, String title, String comment) {
        //save input stream to file
        logger.info("Saving image to: " + email); 
        String path = saveImage(stream, fileName, email);

        double percentage = 0.0;
        try{
            logger.info("Starting image process"); 
            percentage = ImageUtils.processImage(path);
        }catch(IOException ex){
            logger.info("Just got an error :C =" + ex.getMessage()); 
            ex.getMessage();
            return Response.ok().entity(new NewResourceResponse(null, false, "Could not process your leaf image :(")).build();
        }
        logger.info("Creating new Leaf"); 
        LeafInfo info = new LeafInfo(title, comment, path, String.valueOf(percentage), location,
                new SimpleDateFormat("MM/dd/yyyy").format(new Date()),email, getDamageClass(percentage));
        logger.info("Inserting to database.."); 
        leafRepository.insert(info);
        logger.info("Returning response"); 
        return Response.ok().entity(new NewLeafResponse(info.getId(), true, info.getDamageClass())).build();
    }

    private int getDamageClass(double percentage){
        if(percentage == 0.0)
            return 1;
        else if(percentage >= 1 && percentage <= 6)
            return 2;
        else if(percentage >= 7 && percentage <= 25)
            return 3;
        else if(percentage >= 26 && percentage <= 50)
            return 4;
        else if(percentage >= 51 && percentage <= 75)
            return 5;
        else//(percentage >= 76 && percentage <= 100)
            return 6;
    }
}
