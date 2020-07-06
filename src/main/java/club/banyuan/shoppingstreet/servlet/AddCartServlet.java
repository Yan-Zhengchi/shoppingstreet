package club.banyuan.shoppingstreet.servlet;



import club.banyuan.shoppingstreet.domain.Product;
import club.banyuan.shoppingstreet.service.ISearchService;
import club.banyuan.shoppingstreet.service.Impl.SearchServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "AddCartServlet",urlPatterns = "/addCart.do")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品id
        int id = Integer.valueOf(request.getParameter("productId"));
        //获取商品数量
        int num = Integer.valueOf(request.getParameter("num"));
        //调用搜索服务，通过id查询到产品
        ISearchService productService = new SearchServiceImpl();
        Product product = null;
        try {
            List<Product> list = productService.selectProductById(id);
            product = list.get(0);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //如果商品不为空
        if(product!=null) {
            HttpSession session = request.getSession();
            //如果session中不存在购物车map，则创建一个新的map
            Map<Product, Integer> cart = null;
            if(session.getAttribute("cart")==null){
                 cart = new HashMap<>();
            }
            else{
                //如果购物车中已经存在map，则获取到已存在的map
                cart = (Map<Product,Integer>)session.getAttribute("cart");
            }
            //向购物车map中添加商品和购物数量
            cart.put(product,num);
            //更新session中的map
            session.setAttribute("cart",cart);

            StringBuilder productsNames = new StringBuilder();
            Set<Product> products = cart.keySet();
            for (Product product1 : products) {
                productsNames.append(product1.getName());
                productsNames.append("  ");
            }
            session.setAttribute("productsNames",productsNames);
        }
        //将页面跳转到购物车页面
        request.getRequestDispatcher("buyCart.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
