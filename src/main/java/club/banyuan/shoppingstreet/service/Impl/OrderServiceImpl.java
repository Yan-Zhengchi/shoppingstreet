package club.banyuan.shoppingstreet.service.Impl;

import club.banyuan.shoppingstreet.dao.IOrderDao;
import club.banyuan.shoppingstreet.dao.IOrderDetailDao;
import club.banyuan.shoppingstreet.dao.Impl.OrderDaoImpl;
import club.banyuan.shoppingstreet.dao.Impl.OrderDetailDaoImpl;
import club.banyuan.shoppingstreet.domain.Order;
import club.banyuan.shoppingstreet.domain.OrderDetail;
import club.banyuan.shoppingstreet.domain.Product;
import club.banyuan.shoppingstreet.domain.User;
import club.banyuan.shoppingstreet.service.IOrderService;
import club.banyuan.shoppingstreet.util.JDBCUtils;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;




public class OrderServiceImpl  implements IOrderService {


    @Override
    public Order payShoppingCart(Map<Product, Integer> car, double totalPrice, User user, String address) {

        Connection connection = null;
        Order order = new Order();
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            IOrderDao orderDao = new OrderDaoImpl(connection);
            IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl(connection);
            //
            order.setUserId(user.getId());
            order.setLoginName(user.getLoginName());
            order.setUserAddress(address);
            order.setCreateTime(new Date());
            order.setCost(totalPrice);
            order.setSerialNumber(null);
            orderDao.add(order);
            //
            for (Product product : car.keySet()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(order.getId());
                orderDetail.setCost(product.getPrice());
                orderDetail.setProductId(product.getId());
                orderDetail.setQuantity(car.get(product));
                orderDetailDao.add(orderDetail);
                connection.commit();
            }
        } catch (Exception e) {

            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            order = null;
        } finally {
            try {
                connection.setAutoCommit(true);
              JDBCUtils.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }
}
