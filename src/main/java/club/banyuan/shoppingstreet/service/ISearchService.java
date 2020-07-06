package club.banyuan.shoppingstreet.service;

import club.banyuan.shoppingstreet.domain.Product;

import java.sql.SQLException;
import java.util.List;

public interface ISearchService {

    //通过商品id查询的方法
    public List<Product> selectProductById(Integer id) throws SQLException, ClassNotFoundException;
    //通过商品名查询的方法
    public List<Product> selectProductByName(String proName) throws SQLException, ClassNotFoundException;
}
