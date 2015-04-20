package com.spaceapps.core.impl;

import com.spaceapps.core.UserRepository;
import com.spaceapps.model.User;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository{

    public static final String USER_ID = "user_id";

    private static final String INSERT = "insert into user(id, nickname, email, password, location, interest)" +
            "values(?, ?, ?, ?, ?, ?)";
    private static final String FIND = "select * from user where id = ?";
    private static final String LOGIN = "select id from user where nickname = ? and password = ?";

    public UserRepositoryImpl(DataSource dataSource){
        super(dataSource);
    }

    @Override
    public void insert(User entity) {
        //insert a user
        Object[] args = new Object[]{ entity.getId(), entity.getNickname(), entity.getEmail(),
                entity.getPassword(), entity.getLocation(), entity.getInterest() };
        try {
            template.update(INSERT, args);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User find(String id) {
        Object[] args = new Object[]{ id };
        User user = template.queryForObject(FIND, args, userRowMapper);
        return user;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public String login(String nickname, String pass) {
        Object[] args = new Object[]{ nickname, pass };
        String id = template.queryForObject(LOGIN, args, loginRowMapper);
        return id;
    }

    private static final RowMapper<User> userRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setNickname(resultSet.getString(NICKNAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setLocation(resultSet.getString(LOCATION));
            user.setInterest(resultSet.getString(INTEREST));

            return user;
        }
    };

    private static final RowMapper<String> loginRowMapper = new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getString(UserRepositoryImpl.ID);
        }
    };


}
