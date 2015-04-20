package com.spaceapps.core.pagination;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class PaginationHelper<T> {
    @SuppressWarnings("unchecked")
    public Page<T> fetchPage(
            final JdbcTemplate template,
            final String sqlCountRows,
            final String sqlFetchRows,
            final Object[] args,
            final int pageNumber,
            final int pageSize,
            final ParameterizedRowMapper<T> mapper){
        //determine how many rows are available
        final int rowCount = template.queryForInt(sqlCountRows, args);

        //calculate number of pages
        int pageCount = rowCount / pageSize;
        if(rowCount > pageSize * pageCount)
            pageCount ++;

        //create page object
        final Page<T> page = new Page<T>();
        page.setPageNumber(pageNumber);
        page.setPagesAvailable(pageCount);

        //fetch a single page of results
        final int startRow = (pageNumber - 1) * pageSize;

        template.query(sqlFetchRows, args, new ResultSetExtractor(){
            public Object extractData(ResultSet rs)throws SQLException, DataAccessException {
                final List pageItems = page.getPageItems();
                int currentRow = 0 ;
                while(rs.next() && currentRow < startRow + pageSize){
                    if(currentRow >= startRow)
                        pageItems.add(mapper.mapRow(rs, currentRow));
                    currentRow ++;

                }
                return page;
            }
        });
        return page;
    }
}
