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

@WebServlet("/RegisterServlet.do")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求参数，封装到user中
        User user = new User();
        user.setUserName(req.getParameter("loginName"));
        user.setLoginName(req.getParameter("loginName"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setMobile(req.getParameter("mobile"));
        //调用注册服务
        IUserService userService = new UserServiceImpl();
        int i = userService.register(user);
        if(i>0){
            //如果返回值>0说明注册成功，则跳转到登录界面
           resp.sendRedirect("login.html");
        }else{
            //如果注册失败，则刷新页面重新注册
            resp.sendRedirect("register.html");
        }

    }
}
