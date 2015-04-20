package com.spaceapps.core;

import com.spaceapps.core.impl.BaseRepositoryImpl;
import com.spaceapps.model.User;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public interface UserRepository extends BaseRepository<User> {
    public String login(String nickname, String pass);
    public User find(String id);
}
