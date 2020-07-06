package club.banyuan.shoppingstreet.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import club.banyuan.shoppingstreet.domain.Order;
import club.banyuan.shoppingstreet.domain.Product;
import club.banyuan.shoppingstreet.domain.User;
import club.banyuan.shoppingstreet.service.IOrderService;
import club.banyuan.shoppingstreet.service.Impl.OrderServiceImpl;

/**
 * Servlet implementation class CreateOrderServlet
 */
@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//判断用户是否登录，如果没有登录则返回登录页面
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            //如果已经登录则在session中获取user对象
            User user = (User) session.getAttribute("user");
			//设置地址
            String address = "江苏省南京市";
            double totalPrice = Double.parseDouble(session.getAttribute("totalPrice").toString());
            //从session中获取购物车map
            Map<Product, Integer> car = (Map<Product, Integer>) session.getAttribute("cart");
            IOrderService orderService = new OrderServiceImpl();
            Order order = orderService.payShoppingCart(car, totalPrice, user, address);
            request.setAttribute("order", order);

            session.removeAttribute("car");
            session.removeAttribute("totalPrice");
            request.getRequestDispatcher("BuyCar3.jsp").forward(request, response);
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
