package club.banyuan.shoppingstreet.dao.Impl;


import club.banyuan.shoppingstreet.dao.IOrderDetailDao;
import club.banyuan.shoppingstreet.domain.OrderDetail;
import club.banyuan.shoppingstreet.domain.Product;
import club.banyuan.shoppingstreet.domain.ShoppingCart;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl extends BaseDaoImpl implements IOrderDetailDao {
    public OrderDetailDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<OrderDetail> selectOrderDetailByPids(List<Product> products) throws Exception {
        String sql = "select * from order_detail where productId = ? or productId = ?";
        ArrayList<Object> params = new ArrayList<>();
        for (Product product : products) {
            params.add(product.getId());
        }
        Object[] objects = params.toArray();
        ResultSet resultSet = executeQuery(sql, objects);
        List<OrderDetail> orderDetails = tableToClass(resultSet);
        return orderDetails;
    }

    @Override
    public List<ShoppingCart> selectCostByPids(List<Integer> productId) throws SQLException {
        String sql = "select productId,sum(quantity),sum(cost) from order_detail where productId = ? or productId = ? group by productId;";
        ArrayList<Object> params = new ArrayList<>();
        for (Integer product : productId) {
            params.add(product);
        }
        Object[] objects = params.toArray();
        ResultSet resultSet = executeQuery(sql, objects);
        ArrayList<ShoppingCart> shoppingCart = new ArrayList<>();
        while(resultSet.next()){
            ShoppingCart shoppingCart1 = new ShoppingCart();
            shoppingCart1.setProductId(resultSet.getInt(1));
            shoppingCart1.setCount(resultSet.getInt(2));
            shoppingCart1.setCost(resultSet.getDouble(3));
            shoppingCart.add(shoppingCart1);
        }
        return shoppingCart;
    }

    @Override
    public List<OrderDetail> selectOrderDetail(Integer orderId) throws Exception {
        String sql = "select * from order_detail where orderId = ?;";
        Object[] objects = {orderId};
        ResultSet resultSet = executeQuery(sql, objects);
        List<OrderDetail> orderDetails = tableToClass(resultSet);
        return orderDetails;
    }

    @Override
    public List<OrderDetail> tableToClass(ResultSet rs) throws Exception {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        while(rs.next()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(rs.getInt(1));
            orderDetail.setOrderId(rs.getInt(2));
            orderDetail.setProductId(rs.getInt(3));
            orderDetail.setQuantity(rs.getInt(4));
            orderDetail.setCost(rs.getDouble(5));
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }
}
