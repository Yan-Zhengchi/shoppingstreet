package club.banyuan.shoppingstreet.dao.Impl;


import club.banyuan.shoppingstreet.dao.IBaseDao;
import club.banyuan.shoppingstreet.util.JDBCUtils;

import java.sql.*;

public abstract class BaseDaoImpl implements IBaseDao {
    //定义connection和statement的引用
    private Connection connection;
    private PreparedStatement preparedStatement;

    //构造方法从外部传入数据库对象
    public BaseDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet executeQuery(String sql, Object[] params) {
        ResultSet rs = null;
        try {

            //获取statement对象
            preparedStatement = connection.prepareStatement(sql);
            //遍历传来的数组给sql参数赋值
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            //调用查询方法
            rs = preparedStatement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public int executeUpdate(String sql, Object[] params) {
        int updateCount = 0;
        try {
            //获取预编译处理对象
            preparedStatement = connection.prepareStatement(sql);
            //遍历传来的数组给sql赋值
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            //调用查询方法
            updateCount = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        return updateCount;
    }

    @Override
    public int executeInsert(String sql, Object[] params) {
        int primaryKey = 0;
        //获取预编译处理对象
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //传入sql参数
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            //执行更新方法
            preparedStatement.executeUpdate();
            //获取新插入数据的主键
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                primaryKey = (int) generatedKeys.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return primaryKey;
    }

    @Override
    public boolean closeResource() {
        try {
            JDBCUtils.close(connection, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean closeResource(ResultSet reSet) {
        try {
            JDBCUtils.close(connection, preparedStatement, reSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public abstract Object tableToClass(ResultSet rs) throws Exception;
}
