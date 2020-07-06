package club.banyuan.shoppingstreet.servlet;

import club.banyuan.shoppingstreet.dao.IOrderDetailDao;
import club.banyuan.shoppingstreet.domain.Product;
import club.banyuan.shoppingstreet.service.ISearchService;
import club.banyuan.shoppingstreet.service.Impl.SearchServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/detail.do")
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String productId = req.getParameter("productId");
        Integer integer = Integer.valueOf(productId);
        //查询商品详情
        ISearchService searchService = new SearchServiceImpl();
        List<Product> productList = null;
        try {
            productList = searchService.selectProductById(integer);
            //添加productList到req域
            req.setAttribute("productList",productList);
            //请求转发
            req.getRequestDispatcher("product.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }
}
