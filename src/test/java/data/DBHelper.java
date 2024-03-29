package data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DBHelper {

    @SneakyThrows
    public static void clearDB() {
        val deletePayment = "DELETE FROM payment_entity;";
        val deleteCredit = "DELETE FROM credit_request_entity;";
        val deleteOrder = "DELETE FROM order_entity;";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.user"),
                        System.getProperty("db.password"))
        ) {
            runner.update(conn, deletePayment);
            runner.update(conn, deleteCredit);
            runner.update(conn, deleteOrder);
        }
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        val sql = "SELECT status FROM payment_entity;";
        val runner = new QueryRunner();
        String payStatus;

        try (
                val conn = DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.user"),
                        System.getProperty("db.password"))
        ) {
            payStatus = runner.query(conn, sql, new ScalarHandler<>());
        }
        return payStatus;
    }

    @SneakyThrows
    public static String getCreditStatus() {
        val status = "SELECT status FROM credit_request_entity;";
        val runner = new QueryRunner();
        String creditStatus;

        try (
                val conn = DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.user"),
                        System.getProperty("db.password"))
        ) {
            creditStatus = runner.query(conn, status, new ScalarHandler<>());
        }

        return creditStatus;
    }

    @SneakyThrows
    public static long getPaymentCount() {
        val sql = "SELECT COUNT(id) as count FROM payment_entity;";
        val runner = new QueryRunner();
        long payCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.user"),
                        System.getProperty("db.password"))
        ) {
            payCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return payCount;
    }

    @SneakyThrows
    public static long getCreditCount() {
        val sql = "SELECT COUNT(id) as count FROM credit_request_entity;";
        val runner = new QueryRunner();
        long creditCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.user"),
                        System.getProperty("db.password"))
        ) {
            creditCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return creditCount;
    }

    @SneakyThrows
    public static long getOrderCount() {
        val sql = "SELECT COUNT(id) as count FROM order_entity;";
        val runner = new QueryRunner();
        long orderCount;

        try (
                val conn = DriverManager.getConnection(System.getProperty("db.url"), System.getProperty("db.user"),
                        System.getProperty("db.password"))
        ) {
            orderCount = runner.query(conn, sql, new ScalarHandler<>());
        }
        return orderCount;
    }
}
