package club.banyuan.shoppingstreet.dao;


import club.banyuan.shoppingstreet.domain.Order;
import club.banyuan.shoppingstreet.domain.User;

import java.util.List;

public interface IOrderDao {


    //查询用户订单方法
    public List<Order> selectOrder(User user) throws Exception;
}
