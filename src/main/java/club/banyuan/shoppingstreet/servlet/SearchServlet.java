package club.banyuan.shoppingstreet.servlet;

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


@WebServlet("/searchServlet.do")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求参数,解决中文乱码问题
        String searchName = new String(req.getParameter("searchName").getBytes("iso-8859-1"), "utf-8");
        System.out.println(searchName);
        //调用搜索商品服务
        ISearchService searchService = new SearchServiceImpl();
        try {
            List<Product> products = searchService.selectProductByName(searchName);
            //设置Attribute
            req.setAttribute("products", products);
            //请求转发
            req.getRequestDispatcher("brandList.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
