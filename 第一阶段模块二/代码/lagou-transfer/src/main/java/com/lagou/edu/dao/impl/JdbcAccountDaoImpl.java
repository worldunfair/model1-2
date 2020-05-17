package com.lagou.edu.dao.impl;

import com.lagou.edu.pojo.Account;
import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.utils.ConnectionUtils;
import com.lagou.edu.utils.DruidUtils;
import org.litespring.beans.factory.annotation.Autowired;
import org.litespring.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 应癫
 */
@Component("jdbcAccountDaoImpl")
public class JdbcAccountDaoImpl implements AccountDao {
    @Autowired
    private ConnectionUtils connectionUtils;


    public void init() {
        System.out.println("初始化方法.....");
    }

    public void destory() {
        System.out.println("销毁方法......");
    }

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        //从连接池获取连接
        // Connection con = DruidUtils.getInstance().getConnection();
        System.out.println("开始");
        Connection con = connectionUtils.getCurrentThreadConn();
        System.out.println("结束");
        String sql = "select * from account where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        System.out.println("结束");
        preparedStatement.setString(1,cardNo);
        System.out.println("结束");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("结束");


        Account account = new Account();
        while(resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }

        resultSet.close();
        preparedStatement.close();
        //con.close();
        System.out.println("结束");
        return account;
    }

    @Override
    public int updateAccountByCardNo(Account account) throws Exception {

        // 从连接池获取连接
        // 改造为：从当前线程当中获取绑定的connection连接
        //Connection con = DruidUtils.getInstance().getConnection();
        Connection con = connectionUtils.getCurrentThreadConn();
        String sql = "update account set money=? where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1,account.getMoney());
        preparedStatement.setString(2,account.getCardNo());
        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
        //con.close();
        return i;
    }
}
