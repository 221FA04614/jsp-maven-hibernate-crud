package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

    static Connection con;
    static PreparedStatement ps;

    private static final String SELECT_CUSTOMER_BY_ID = "SELECT id,email,password,firstName,lastName FROM customer WHERE id =?";
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
    private static final String UPDATE_CUSTOMERS_SQL = "UPDATE customer SET email = ?, password = ?, firstName = ?, lastName = ? WHERE id = ?;";
    private static final String DELETE_CUSTOMERS_SQL = "DELETE FROM customer WHERE id = ?;";

    @Override
    public int insertCustomer(Customer c) {
        int status = 0;

        try {
            con = ConnectionProvider.getConnection();

            ps = con.prepareStatement("INSERT INTO customer" +
                    "(email, password, firstName, lastName) VALUES" +
                    "(?, ?, ?, ?)");

            ps.setString(1, c.getEmail());
            ps.setString(2, c.getPassword());
            ps.setString(3, c.getFirstName());
            ps.setString(4, c.getLastName());

            status = ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    @Override
    public Customer getCustomer(String email, String password) {

        Customer c = new Customer(email, password);
        try {
            con = ConnectionProvider.getConnection();

            ps = con.prepareStatement("SELECT * FROM customer WHERE email = ? AND password = ?");

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                c.setEmail(rs.getString(2));
                c.setPassword(rs.getString(3));
                c.setFirstName(rs.getString(4));
                c.setLastName(rs.getString(5));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }

    @Override
    public boolean editCustomer(Customer c) throws SQLException {

        boolean rowUpdated;
        try {
            con = ConnectionProvider.getConnection();
            ps = con.prepareStatement(UPDATE_CUSTOMERS_SQL);

            ps.setString(1, c.getEmail());
            ps.setString(2, c.getPassword());
            ps.setString(3, c.getFirstName());
            ps.setString(4, c.getLastName());
            ps.setInt(5, c.getId());

            rowUpdated = ps.executeUpdate() > 0;
            con.close();

        } finally {

        }
        return rowUpdated;
    }

    @Override
    public Customer selectCustomer(int id) {

        Customer customer = null;
        try {
            con = ConnectionProvider.getConnection();
            ps = con.prepareStatement(SELECT_CUSTOMER_BY_ID);

            ps.setInt(1, id);
            System.out.println(ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                customer = new Customer(id, email, password, firstName, lastName);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> selectAllCustomers() {

        List<Customer> customers = new ArrayList<>();
        try {
            con = ConnectionProvider.getConnection();
            ps = con.prepareStatement(SELECT_ALL_CUSTOMERS);

            System.out.println(ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                customers.add(new Customer(id, email, password, firstName, lastName));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return customers;
    }

    @Override
    public boolean deleteCustomer(int id) throws SQLException {

        boolean rowDeleted;
        try {
            con = ConnectionProvider.getConnection();
            ps = con.prepareStatement(DELETE_CUSTOMERS_SQL);

            ps.setInt(1, id);

            rowDeleted = ps.executeUpdate() > 0;
            con.close();
        } finally {

        }
        return rowDeleted;
    }

}
