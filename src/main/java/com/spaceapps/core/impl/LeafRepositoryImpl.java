package com.spaceapps.core.impl;

import com.spaceapps.core.LeafRepository;
import com.spaceapps.model.LeafInfo;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class LeafRepositoryImpl extends BaseRepositoryImpl<LeafInfo> implements LeafRepository {

    private static final String INSERT = "insert into leafinfo(id, leaf_pic_url, comments, location, title, " +
            "damage_percent, email, damage_class, publish_date)" +
            " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND = "select * from leafinfo where id = ?";
    private static final String FIND_ALL = "select * from leafinfo";

    public LeafRepositoryImpl(DataSource dataSource){
        super(dataSource);
    }

    @Override
    public void insert(LeafInfo entity) {
        Object[] args = new Object[]{ entity.getId(), entity.getLeafPicUrl(),
                entity.getComment(), entity.getLocation(),
                entity.getTitle(), entity.getDamagePercent(), entity.getEmail(),
                entity.getDamageClass(), entity.getPublishDate()};
        template.update(INSERT, args);
    }

    @Override
    public LeafInfo find(String id) {
        Object[] args = new Object[]{ id };
        return template.queryForObject(FIND, args, leafInfoRowMapper);
    }

    @Override
    public List<LeafInfo> findAll() {

        List<LeafInfo> leafInfoList = new ArrayList<LeafInfo>();
        LeafInfo info;

        List<Map<String, Object>> rows = template.queryForList(FIND_ALL);
        for (Map row : rows) {
            info = new LeafInfo();

            info.setId((String) (row.get(ID)));
            info.setEmail((String) (row.get(EMAIL)));
            info.setLocation((String) (row.get(LOCATION)));
            info.setLeafPicUrl((String) (row.get(LEAF_PIC_URL)));
            info.setDamagePercent((String) (row.get(DAMAGE_PERCENT)));
            info.setPublishDate((String) (row.get(PUBLISH_DATE)));
            info.setComment((String) row.get(COMMENTS));
            info.setTitle((String) row.get(TITLE));
            info.setDamageClass((Integer)row.get(DAMAGE_CLASS));
            leafInfoList.add(info);
        }

        return leafInfoList;
    }

    @Override
    public void delete(String id) {

    }

    private static final RowMapper<List<LeafInfo>> leafInfoRowMapperList= new RowMapper<List<LeafInfo>>() {
        @Override
        public List<LeafInfo> mapRow(ResultSet resultSet, int i) throws SQLException {

            List<LeafInfo> leafInfoList = new ArrayList<LeafInfo>();
            LeafInfo info;
            while(resultSet.next()){
                info = new LeafInfo();
                info.setId(resultSet.getString(ID));
                info.setLeafPicUrl(resultSet.getString(LEAF_PIC_URL));
                info.setDamageClass(resultSet.getInt(DAMAGE_CLASS));
                info.setLocation(resultSet.getString(LOCATION));
                info.setTitle(resultSet.getString(TITLE));
                info.setComment(resultSet.getString(COMMENTS));
                info.setDamagePercent(resultSet.getString(DAMAGE_PERCENT));
                info.setEmail(resultSet.getString(EMAIL));
                info.setPublishDate(resultSet.getString(PUBLISH_DATE));
                leafInfoList.add(info);
            }
            return leafInfoList;
        }
    };

    private static final RowMapper<LeafInfo> leafInfoRowMapper = new RowMapper<LeafInfo>() {
        @Override
        public LeafInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            LeafInfo info = new LeafInfo();
            info.setId(resultSet.getString(ID));
            info.setLeafPicUrl(resultSet.getString(LEAF_PIC_URL));
            info.setDamageClass(resultSet.getInt(DAMAGE_CLASS));
            info.setLocation(resultSet.getString(LOCATION));
            info.setTitle(resultSet.getString(TITLE));
            info.setComment(resultSet.getString(COMMENTS));
            info.setDamagePercent(resultSet.getString(DAMAGE_PERCENT));
            info.setEmail(resultSet.getString(EMAIL));
            info.setPublishDate(resultSet.getString(PUBLISH_DATE));
            return info;
        }
    };
}
