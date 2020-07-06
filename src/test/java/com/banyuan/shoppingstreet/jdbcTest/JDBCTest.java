package com.banyuan.shoppingstreet.jdbcTest;


import club.banyuan.shoppingstreet.dao.IOrderDetailDao;
import club.banyuan.shoppingstreet.dao.IProductDao;
import club.banyuan.shoppingstreet.dao.Impl.OrderDetailDaoImpl;
import club.banyuan.shoppingstreet.dao.Impl.ProductDaoImpl;
import club.banyuan.shoppingstreet.dao.Impl.UserDaoImpl;
import club.banyuan.shoppingstreet.domain.OrderDetail;
import club.banyuan.shoppingstreet.domain.Product;
import club.banyuan.shoppingstreet.domain.ShoppingCart;
import club.banyuan.shoppingstreet.domain.User;
import club.banyuan.shoppingstreet.util.JDBCUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class JDBCTest {
    private Connection connection;

    @Before
    public void Init() {
        try {
            connection = JDBCUtils.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        try {
            JDBCUtils.close(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestConn() {
        System.out.println(connection);
    }

    @Test
    public void TestShoppingCart() {

        //创建产品dao对象
        ProductDaoImpl productDao = new ProductDaoImpl(connection);
        //创建订单dao对象
        OrderDetailDaoImpl orderDetailDao = new OrderDetailDaoImpl(connection);
        try {
            //创建一个list，存储所有从数据库中查出来的华为商品
            List<Product> list = productDao.selectProduct();
            //创建一个list，存储所有华为商品的订单详情
            List<OrderDetail> orderDetails = orderDetailDao.selectOrderDetailByPids(list);
            //遍历华为商品list
            HashSet<Integer> productIds = new HashSet<>();
            for (Product product : list) {
                productIds.add(product.getId());
            }
            //遍历set到数组里
            ArrayList<Integer> integers = new ArrayList<>();
            for (Integer productId : productIds) {
                integers.add(productId);
            }
            List<ShoppingCart> shoppingCart = orderDetailDao.selectCostByPids(integers);
            System.out.println(shoppingCart);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void TestLogin(){
        UserDaoImpl userDao = new UserDaoImpl(connection);
        try {
            User aaa = userDao.login("aaa", "123");
            System.out.println(aaa);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void TestSelectOrderDetail(){
        IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl(connection);
        try {
            List<OrderDetail> orderDetails = orderDetailDao.selectOrderDetail(1);
            System.out.println(orderDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectProductByName(){
        ProductDaoImpl productDao = new ProductDaoImpl(connection);
        List<Product> products = productDao.selectProductByName("华为");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    public void testSelectProductById(){
        ProductDaoImpl productDao = new ProductDaoImpl(connection);
        List<Product> products = productDao.selectProductById(744);
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
