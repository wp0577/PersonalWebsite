package dao;

import Domain.LoginInfo;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.DBUtil;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @program: personalInfo
 * @description: manager loginInfo database
 * @author: Pan wu
 * @create: 2018-09-05 01:23
 **/
public class LoginInfoDao {
    public static LoginInfo getLoginByIp(String remoteAddr) throws SQLException {
        BeanHandler<LoginInfo> beanHandler = new BeanHandler<LoginInfo>(LoginInfo.class);
        DataSource ds = DBUtil.getDataSource();
        //建立连接
        QueryRunner queryRunner = new QueryRunner(ds);
        String sql = "select * from loginInfo where ipString = ?";
        LoginInfo loginInfo = queryRunner.query(sql, beanHandler, remoteAddr);
        return loginInfo;
    }

    public static void save(LoginInfo l) {
        DataSource ds = DBUtil.getDataSource();
        //建立连接
        QueryRunner queryRunner = new QueryRunner(ds);
        String sql = "insert into loginInfo (ipString, totalCount, lastTimeString, firstTimeString) values(?,?,?,?)";
        try {
            queryRunner.update(sql, l.getIpString(), l.getTotalCount(), l.getLastTimeString(), l.getFirstTimeString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(LoginInfo l) {
        DataSource ds = DBUtil.getDataSource();
        //建立连接
        QueryRunner queryRunner = new QueryRunner(ds);
        String sql = "update loginInfo set ipString = ?, totalCount = ?, lastTimeString = ?, firstTimeString = ? where id = ?";
        try {
            queryRunner.update(sql, l.getIpString(), l.getTotalCount(), l.getLastTimeString(), l.getFirstTimeString(), l.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTotal() throws SQLException {
        DataSource dataSource = DBUtil.getDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        String sql = "select sum(totalCount) from loginInfo";
        BeanHandler stringBeanHandler = new BeanHandler<>(String.class);
        Integer query = queryRunner.query(sql, resultSet -> {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
        );
        return String.valueOf(query);
    }
}
