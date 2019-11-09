package com.example.common.utils.security.pojo;

/**
 * @ClassName UserContext
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/11 8:39
 * @Version 1.0
 **/
public class UserContext implements AutoCloseable {
    static final ThreadLocal<LoginUser> current = new ThreadLocal<>();

    public UserContext(LoginUser user) {
        current.set(user);
    }

    public static LoginUser getCurrentUser() {
        return current.get();
    }

    public static void setCurrentUser(LoginUser user) {
        current.set(user);
    }

    public void close() {
        current.remove();
    }
}
