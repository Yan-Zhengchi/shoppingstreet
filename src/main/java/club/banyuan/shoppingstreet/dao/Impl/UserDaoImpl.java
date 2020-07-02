package club.banyuan.shoppingstreet.dao.Impl;



import club.banyuan.shoppingstreet.dao.IUserDao;
import club.banyuan.shoppingstreet.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;


public class UserDaoImpl extends BaseDaoImpl implements IUserDao {
    public UserDaoImpl(Connection connection){
        super(connection);
    }
    @Override
    public int register(User user) {
        String sql = "insert into user(id,loginName,userName,password,sex,email,mobile) values(null,?,?,?,?,?,?)";
        Object[] params = new Object[]{user.getLoginName()
                ,user.getUserName(),user.getPassword(),user.getSex(),user.getEmail(),user.getMobile()};
        int i= executeInsert(sql,params);
        user.setId(i);
        closeResource();
        return i;
    }

    @Override
    public User login(String loginName, String password) throws Exception {
        String sql = "select * from user where loginName = ? and password = ?;";
        Object[] objects = {loginName, password};
        ResultSet resultSet = executeQuery(sql, objects);
        User user = tableToClass(resultSet);
        return user;
    }



    @Override
    public User tableToClass(ResultSet rs) throws Exception {
        User user = new User();
        if (rs.next()){
            user.setId(rs.getInt(1));
            user.setLoginName(rs.getString(2));
            user.setUserName(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setSex(rs.getInt(5));
            user.setIdentityCode(rs.getString(6));
            user.setEmail(rs.getString(7));
            user.setMobile(rs.getString(8));
            user.setType(rs.getInt(9));
        }
        return user;
    }
}
