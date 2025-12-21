package service;

import dao.UserDAO;
import helper.PasswordUtil;
import model.User;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User authenticate(String username, String password) throws Exception {
        if (username == null || username.trim().isEmpty())
            throw new IllegalArgumentException("Username wajib diisi!");
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password wajib diisi!");

        User user = userDAO.findByUsername(username.trim());
        if (user == null) {
            System.out.println("DEBUG: user tidak ditemukan");
            return null;
        }

        String inputHash = PasswordUtil.sha256Hex(password.trim());

        // DEBUG (sementara, biar kamu lihat di Console Eclipse)
        System.out.println("DEBUG DB HASH     : " + user.getPasswordHash());
        System.out.println("DEBUG INPUT HASH  : " + inputHash);
        System.out.println("DEBUG DB LEN      : " + user.getPasswordHash().length());
        System.out.println("DEBUG INPUT LEN   : " + inputHash.length());

        if (inputHash.equalsIgnoreCase(user.getPasswordHash().trim())) {
            return user; // ✅ LOGIN BERHASIL
        }

        return null; // ❌ password salah
    }
}
