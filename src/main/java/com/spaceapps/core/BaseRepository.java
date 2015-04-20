package com.spaceapps.core;

import com.spaceapps.model.LeafInfo;

import java.util.List;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public interface BaseRepository<T> {
    public void insert(T entity);
    public T find(String id);
    public void delete(String id);
}
