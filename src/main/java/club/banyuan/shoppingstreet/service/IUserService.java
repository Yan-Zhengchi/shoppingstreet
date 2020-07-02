package club.banyuan.shoppingstreet.service;

import club.banyuan.shoppingstreet.domain.User;

public interface IUserService {

    //用户注册的方法
    public int register(User user);

    //用户登录的方法
    public User login(String name ,String password);


}
