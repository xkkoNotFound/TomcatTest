package com.xkko.web.loginDemo.test;

import com.xkko.web.loginDemo.DAO.UserTableDAO;
import com.xkko.web.loginDemo.DO.UserTableDO;
import org.junit.Test;

public class UserTableDAOTest {
    @Test
    public void loginTest(){
        UserTableDO ut = new UserTableDO("admin","1");
        UserTableDAO utdao = new UserTableDAO();
        System.out.println(utdao.query(ut));
    }
}
