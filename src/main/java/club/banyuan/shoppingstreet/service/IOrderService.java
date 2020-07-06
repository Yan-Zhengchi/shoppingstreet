package club.banyuan.shoppingstreet.service;

import java.util.Map;

import club.banyuan.shoppingstreet.domain.Order;
import club.banyuan.shoppingstreet.domain.Product;
import club.banyuan.shoppingstreet.domain.User;

public interface IOrderService {

	public Order payShoppingCart(Map<Product, Integer> car, double totalPrice, User user, String address);
}
