package pe.bsanchez.controller.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.bsanchez.dao.UserDao;
import pe.bsanchez.dao.entity.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
@SessionScoped
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	transient private UserDao userDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));

		response.setContentType("text/html");

		user = userDao.getAccess(user);
		if (user != null) {
			// se inserta el usuario logueado a la sesión
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("username", user.getUsername());

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/home.jsp");
			requestDispatcher.forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.print("<p style=\"color:red\">Username or password incorrect</p>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);

			out.close();
		}

	}

}
