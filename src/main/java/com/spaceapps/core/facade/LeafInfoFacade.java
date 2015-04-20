package com.spaceapps.core.facade;

import com.spaceapps.core.BaseRepository;
import com.spaceapps.core.LeafRepository;
import com.spaceapps.core.UserRepository;
import com.spaceapps.model.LeafInfo;
import com.spaceapps.model.LeafInfoFileContent;

import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public interface LeafInfoFacade{
    public Response create(UriInfo uriInfo, LeafRepository repo, LeafInfoFileContent content);
    //public LeafInfo getLeaf(UriInfo uriInfo, UserRepository repo);
    public Response findAll(UriInfo uriInfo, LeafRepository repo);

    public Response createNewLeaf(InputStream stream, String fileName, String email, String location, String title, String comment);

}
