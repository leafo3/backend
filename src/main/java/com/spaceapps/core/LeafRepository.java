package com.spaceapps.core;

import com.spaceapps.model.LeafInfo;
import com.spaceapps.model.User;

import java.util.List;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public interface LeafRepository extends BaseRepository<LeafInfo> {
    public List<LeafInfo> findAll();
}
