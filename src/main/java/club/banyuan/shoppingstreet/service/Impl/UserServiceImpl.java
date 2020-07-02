package club.banyuan.shoppingstreet.service.Impl;

import club.banyuan.shoppingstreet.dao.Impl.UserDaoImpl;
import club.banyuan.shoppingstreet.domain.User;
import club.banyuan.shoppingstreet.service.IUserService;
import club.banyuan.shoppingstreet.util.JDBCUtils;

import java.sql.SQLException;

public class UserServiceImpl implements IUserService {
    @Override
    public int register(User user) {
        try {
            UserDaoImpl userDao = new UserDaoImpl(JDBCUtils.getConnection());
            int r = userDao.register(user);
            return r;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public User login(String name, String password) {
        try {
            UserDaoImpl userDao = new UserDaoImpl(JDBCUtils.getConnection());
            User user = userDao.login(name, password);
            return user;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
