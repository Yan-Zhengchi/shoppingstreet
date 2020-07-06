package club.banyuan.shoppingstreet.servlet;

import club.banyuan.shoppingstreet.domain.User;
import club.banyuan.shoppingstreet.service.IUserService;
import club.banyuan.shoppingstreet.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
            //将用户登录成功的信息存放在session中
            //手动创建session依赖的cookie：JESSIONID
            HttpSession session = req.getSession();
            Cookie cookie = new Cookie("JSESSIONID",session.getId());
            //设置cookie保存到浏览器本地内存时间
            cookie.setMaxAge(60*60);
            resp.addCookie(cookie);
            cookie.setMaxAge(3000);

            session.setAttribute("user",user);
            req.getRequestDispatcher("index.jsp").forward(req,resp);

        }else{
            //如果user为空登陆失败，刷新页面重新登录
            resp.sendRedirect("login.html");
        }
    }
}
