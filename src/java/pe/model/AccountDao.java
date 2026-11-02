/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class AccountDao {
    //-----            your code here   --------------------------------
    private static final String LOGIN_SQL
            = "SELECT username, password, fullName, phone, email, status, role "
            + "FROM Account WHERE username = ? AND password = ?";

    /**
     * Checks the username and password against the Account table.
     *
     * @param username the account username
     * @param password the account password
     * @return the matched account, or null when the credentials are invalid
     * @throws ClassNotFoundException when the JDBC driver is missing
     * @throws SQLException when the query fails
     */
    public AccountDto checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException {
        try (Connection conn = DbUtils.getConnection();
                PreparedStatement stm = conn.prepareStatement(LOGIN_SQL)) {
            stm.setString(1, username);
            stm.setString(2, password);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new AccountDto(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("fullName"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getBoolean("status"),
                            rs.getInt("role"));
                }
            }
        }
        return null;
    }
}
