package club.banyuan.shoppingstreet.dao.Impl;

import club.banyuan.shoppingstreet.dao.IProductDao;
import club.banyuan.shoppingstreet.domain.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends BaseDaoImpl implements IProductDao {
    public ProductDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Product> selectProduct() throws SQLException, ClassNotFoundException {
        String sql = "select * from product where name like ? or description like ?";
        Object[] params = new Object[]{"%华为%","%华为%"};
        ResultSet resultSet = executeQuery(sql, params);
        List<Product> list = null;
        try {
            list = tableToClass(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> selectProductByName(String name) {
        String sql = "select * from product where name like ? ";
        Object[] params = new Object[]{"%"+name+"%"};
        ResultSet resultSet = executeQuery(sql, params);
        List<Product> list = null;
        try {
            list = tableToClass(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> tableToClass(ResultSet rs) throws Exception {
        ArrayList<Product> list = new ArrayList<>();
        while(rs.next()){
            Product product = new Product();
            product.setId(rs.getInt(1));
            product.setName(rs.getString(2));
            product.setDescription(rs.getString(3));
            product.setPrice(rs.getDouble(4));
            product.setStock(rs.getInt(5));
            product.setCategoryLevel1Id(rs.getInt(6));
            product.setCategoryLevel2Id(rs.getInt(7));
            product.setCategoryLevel3Id(rs.getInt(8));
            product.setFileName(rs.getString(9));
            product.setIsDelete(rs.getInt(10));
            list.add(product);
        }
        return list;
    }
}
