package club.banyuan.shoppingstreet.servlet;

import club.banyuan.shoppingstreet.domain.User;
import club.banyuan.shoppingstreet.service.IUserService;
import club.banyuan.shoppingstreet.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/loginServlet.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String loginName = req.getParameter("loginName");
        String password = req.getParameter("password");
        //调用登录服务
        IUserService userService = new UserServiceImpl();
        User user = userService.login(loginName, password);

        if(user!=null){
            //user不为空，则成功登录，请求转发
            req.setAttribute("user",user);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else{
            //如果user为空登陆失败，刷新页面重新登录
            resp.sendRedirect("login.html");
        }
    }
}
