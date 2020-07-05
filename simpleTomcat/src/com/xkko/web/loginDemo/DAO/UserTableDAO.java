package com.xkko.web.loginDemo.DAO;

import com.xkko.web.loginDemo.DO.UserTableDO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserTableDAO {
    private JdbcTemplate jt = new JdbcTemplate(new DruidUtils().getDatasource());

    public UserTableDO query(UserTableDO userTableDO) {
        try {
            String sql = "select * from usertable where username = ? and password = ?";
            return jt.queryForObject(sql, new BeanPropertyRowMapper<>(UserTableDO.class),
                    userTableDO.getUserName(), userTableDO.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public UserTableDO queryName(UserTableDO userTableDO) {
        try {
            String sql = "select * from usertable where username = ?";
            return jt.queryForObject(sql, new BeanPropertyRowMapper<>(UserTableDO.class),
                    userTableDO.getUserName());
        } catch (DataAccessException e) {
            return null;
        }
    }

    public int insert(UserTableDO userTableDO) {
        String sql = "insert into usertable values(null, ?,?)";
        return jt.update(sql, userTableDO.getUserName(), userTableDO.getPassword());
    }
}
