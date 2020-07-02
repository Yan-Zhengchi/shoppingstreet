package club.banyuan.shoppingstreet.dao;


import club.banyuan.shoppingstreet.domain.User;

public interface IUserDao {

    //用户注册方法
    public int register(User user);
    //用户登录方法
    public User login(String loginName, String password) throws Exception;



}
