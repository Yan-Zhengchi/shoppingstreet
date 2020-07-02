package club.banyuan.shoppingstreet.dao;


import club.banyuan.shoppingstreet.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {


    //通过商品名和描述查询商品的方法
    public List<Product> selectProduct() throws SQLException, ClassNotFoundException;

    //通过商品名查询商品的方法
    public List<Product> selectProductByName(String name);
}