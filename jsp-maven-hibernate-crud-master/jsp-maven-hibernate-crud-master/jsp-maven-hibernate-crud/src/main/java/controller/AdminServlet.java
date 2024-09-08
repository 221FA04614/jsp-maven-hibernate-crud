package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClientDAOImpl;
import dao.ProductDAO;
import model.Product;
import model.Client;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDAOImpl clientDAOImpl;
	private ProductDAO productDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
    	this.clientDAOImpl = new ClientDAOImpl();
    	this.productDao = new ProductDAO();
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getServletPath();
		
		switch(url) {
		case "/login":
			try {
				loginClient(request, response);
			}catch(ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/logout":
			try {
				logoutClient(request, response);
			}catch(ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/home":
			try {
				accessHome(request, response);
			}catch(ServletException | IOException e) {
				e.printStackTrace();
				}
			break;
		case "/admin/delete-client":
			try {
				deleteClient(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/admin/edit-client":
			try {
				showEditForm(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/admin/update-client":
			try {
				updateClient(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/admin/list-client":
			try {
				listClients(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "/admin/insert-product":
            try {
				insertProduct(request, response);
			} catch (SQLException | IOException | ServletException e3) {
				e3.printStackTrace();
			}
            break;
        case "/admin/delete-product":
            try {
				deleteProduct(request, response);
			} catch (SQLException | IOException | ServletException e2) {
				e2.printStackTrace();
			}
            break;
        case "/admin/edit-product":
            try {
				showEditProduct(request, response);
			} catch (SQLException | IOException | ServletException e1) {
				e1.printStackTrace();
			}
            break;
        case "/admin/update-product":
            updateProduct(request, response);
            break;
        case "/admin/list-products":
        	listProducts(request, response);
        	break;
		default:
			try {
				redirectHome(request, response);
			} catch (SQLException | ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List <Product> productList = productDao.getAllProducts();
		request.setAttribute("productList", productList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
		dispatcher.forward(request, response);
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String imageLink = request.getParameter("imageLink");
		
		Product product = new Product(id, title, description, price, imageLink);
		productDao.updateProduct(product);
		response.sendRedirect("list-products");
	}

	private void showEditProduct(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingProduct = productDao.getProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit-product.jsp");
		
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException{
		
		int id = Integer.parseInt(request.getParameter("id"));
		productDao.deleteProduct(id);
		response.sendRedirect("list-products");
	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String imageLink = request.getParameter("imageLink");
		
		Product newProduct = new Product(title, description, price, imageLink);
		productDao.saveProduct(newProduct);
		response.sendRedirect("list-products");
	}

	private void accessHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = (String) request.getSession().getAttribute("name");
		
		if(name != null) {
			List <Product> productList = productDao.getAllProducts();
			request.setAttribute("productList", productList);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/home.jsp");
			dispatcher.forward(request, response); 
			
		}else {
			request.setAttribute("sessionMessage",
					"<div class=\"alert alert-danger col-6 mx-auto mt-3\" role=\"alert\">"
							+ " 	Not authenticated! Please log in first."
							+ "	</div>");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	
	}

	private void loginClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String submitType = request.getParameter("submit");

		Client c = clientDAOImpl.getClient(email, password);

		if (submitType.equals("Login") && c != null && c.getName() != null) {
			HttpSession session=request.getSession(); 
			session.setAttribute("name", c.getName());
			response.sendRedirect("home");

		} else if (submitType.equals("Register")) {
			request.setCharacterEncoding("UTF-8");
			
			c.setEmail(email);
			c.setPassword(password);
			c.setName(request.getParameter("name"));
			c.setLastName(request.getParameter("lastName"));
			clientDAOImpl.insertClient(c);

			request.setAttribute("successMessage", "<div class=\"alert alert-success my-2 col-12\" role=\"alert\">"
					+ " 	Registration successful! Please log in to continue." + "	</div>");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			request.setAttribute("failureMessage",
					"<div class=\"alert alert-danger col-6 mx-auto mt-3\" role=\"alert\">"
							+ " 	Incorrect email or password. If you are not registered, click 'Sign up'."
							+ "	</div>");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

	private void logoutClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();  
        session.invalidate();
        
        request.setAttribute("logoutMessage", "<div id=\"logout\" class=\"alert alert-success my-2 col-12\" role=\"alert\">"
				+ " 	Logout successful!" + "	</div>");
		request.getRequestDispatcher("index.jsp").include(request, response);
	}

	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientDAOImpl.deleteClient(id);
		response.sendRedirect("list-client");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		Client existingClient = clientDAOImpl.selectClient(id);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-form.jsp");
		request.setAttribute("client", existingClient);
		
		dispatcher.forward(request, response);
	}
	
	private void updateClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		
		Client client = new Client(id, email, password, name, lastName);
		clientDAOImpl.editClient(client);
		
		response.sendRedirect("list-client");
	}
	
	private void listClients(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		List<Client> clientList = clientDAOImpl.selectAllClients();
		
		request.setAttribute("clientList", clientList);
		request.getRequestDispatcher("clients.jsp").forward(request, response);
	}
	
	private void redirectHome(HttpServletRequest request, HttpServletResponse response)throws SQLException, ServletException, IOException{
		request.getRequestDispatcher("home").forward(request, response);
	}
	
}
