package com.done.dao;

import com.done.interceptor.support.UserLog;
import com.done.model.persist.UserOperationLog;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Done Lin on 2018/1/6.
 */
@Repository
@Log4j
public class UserOprationLogDao {

    @Autowired
    private JdbcTemplate jt;

    @Autowired
    private NamedParameterJdbcTemplate njt;

    public void save(final UserOperationLog log){
        String  sql = "INSERT INTO USER_OPERATION_LOG VALUES(?,?,?)";
        jt.update(sql,new PreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,log.getId());
                ps.setString(2,log.getUserId());
                ps.setString(3,log.getType().toString());
            }
        });
    }

    public void saveByParameterName(UserOperationLog log){
        String  sql = "INSERT INTO USER_OPERATION_LOG VALUES(:id,:userId,:userType)";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id",log.getId());
        sqlParameterSource.addValue("userId",log.getUserId());
        sqlParameterSource.addValue("userType",log.getType().toString());
        njt.update(sql,sqlParameterSource);
    }


    public void batchSave(final List<UserOperationLog> logs){
        String  sql = "INSERT INTO USER_OPERATION_LOG VALUES(?,?,?)";
        jt.batchUpdate(sql,new BatchPreparedStatementSetter(){
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                UserOperationLog log = logs.get(i);
                ps.setString(1,log.getId());
                ps.setString(2,log.getUserId());
                ps.setString(3,log.getType().toString());
            }
            @Override
            public int getBatchSize() {
                return logs.size();
            }
        });
    }


    public UserOperationLog getUserOperationById(String id){
        log.info("--------从数据库拿数据---------");
        String  sql = "SELECT T.* FROM USER_OPERATION_LOG T WHERE T.ID=?";
        final UserOperationLog result = new UserOperationLog();
        jt.query(sql,new Object[]{id},new RowCallbackHandler(){
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.setId(rs.getString("id"));
                result.setUserId(rs.getString("user_id"));
                result.setType(UserLog.OperationType.valueOf(rs.getString("type")));
            }
        });
        return result.getId()==null?null:result;
    }
}
