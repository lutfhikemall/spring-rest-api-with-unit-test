package com.test.indivara.test_indivara.constnats;

public class Query {
    public static class Product {
        public static String CREATE = "INSERT INTO tb_product (name) VALUES (?)";
        public static String UPDATE = "UPDATE tb_product SET name = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        public static String VIEW = "SELECT * FROM tb_product";
        public static String DELETE = "DELETE FROM tb_product WHERE id = ?";
    }

    public static class User {
        public static String CREATE = "INSERT INTO tb_users (full_name,email,password) VALUES (?,?,?)";
        public static String FIND_BY_EMAIL = "SELECT id, full_name, email, password, created_at, updated_at FROM tb_users WHERE email = ?";
        public static String IS_EXIST = "SELECT COUNT(*) FROM tb_users WHERE email = ?";
    }
}
