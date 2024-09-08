	package dao;

	import java.sql.SQLException;
	import java.util.List;
	import model.Customer;

	public interface CustomerDAO {

	    public int insertCustomer(Customer c);
	    public Customer getCustomer(String email, String password);
	    public boolean editCustomer(Customer c) throws SQLException;
	    public Customer selectCustomer(int id);
	    public List<Customer> selectAllCustomers();
	    public boolean deleteCustomer(int id) throws SQLException;
	}


