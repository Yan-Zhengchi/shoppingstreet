package club.banyuan.shoppingstreet.servlet;

import club.banyuan.shoppingstreet.domain.User;
import club.banyuan.shoppingstreet.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ajax.do")
public class RegisterAjax extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImpl userService = new UserServiceImpl();
        String userName = req.getParameter("userName");
        User user = userService.selectByLoginName(userName);
        if(user!=null){
            resp.getWriter().write("<td></td><td style=\"color:#ff4e00 \">用户名已经被注册</td>");
        }
    }
}
