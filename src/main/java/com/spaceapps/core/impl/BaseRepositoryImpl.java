package com.spaceapps.core.impl;

import com.spaceapps.core.BaseRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public abstract class BaseRepositoryImpl<T> implements BaseRepository<T>{

    //constraints
    //user
    public static final String ID = "id";
    public static final String NICKNAME = "nickname";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LOCATION = "location";
    public static final String INTEREST = "interest";

    //leaf
    public static final String LEAF_IMAGE_TITLE = "title";
    public static final String LEAF_PIC_URL = "leaf_pic_url";
    public static final String COMMENTS = "comments";
    public static final String DAMAGE_PERCENT = "damage_percent";
    public static final String PUBLISH_DATE = "publish_date";
    public static final String TITLE = "title";
    public static final String DAMAGE_CLASS = "damage_class";

    protected DataSource dataSource;
    protected JdbcTemplate template;

    public BaseRepositoryImpl(DataSource dataSource){
        this.dataSource = dataSource;
        this.template = new JdbcTemplate(dataSource);
    }

}
