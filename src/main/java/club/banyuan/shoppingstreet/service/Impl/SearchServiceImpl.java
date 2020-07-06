package club.banyuan.shoppingstreet.service.Impl;

import club.banyuan.shoppingstreet.dao.IProductDao;
import club.banyuan.shoppingstreet.dao.Impl.ProductDaoImpl;
import club.banyuan.shoppingstreet.domain.Product;
import club.banyuan.shoppingstreet.service.ISearchService;
import club.banyuan.shoppingstreet.util.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class SearchServiceImpl implements ISearchService {
    @Override
    public List<Product> selectProductById(Integer id) throws SQLException, ClassNotFoundException {

        IProductDao productDao = new ProductDaoImpl(JDBCUtils.getConnection());
        List<Product> products = productDao.selectProductById(id);
        return products;
    }

    @Override
    public List<Product> selectProductByName(String proName) throws SQLException, ClassNotFoundException {
        //创建查询商品的dao，调用通过产品名查询的方法
        IProductDao productDao = new ProductDaoImpl(JDBCUtils.getConnection());
        List<Product> products = productDao.selectProductByName(proName);
        return products;
    }
}
