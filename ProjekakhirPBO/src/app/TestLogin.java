package app;

import dao.UserDAO;
import service.AuthService;

public class TestLogin {
    public static void main(String[] args) throws Exception {
        AuthService auth = new AuthService(new UserDAO());
        System.out.println(auth.authenticate("admin", "123"));
    }
}

