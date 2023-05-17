

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserDAO userDAO;
    
    public void init() {
    	this.userDAO = new UserDAO();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		UserModel userModel = new UserModel();
		userModel.setUsername(request.getParameter("uname"));
		userModel.setPassword(request.getParameter("pwd"));
		
		try {
			
			boolean result = userDAO.validateUser(userModel);
			
			// test use
//			System.out.println(result);
			
			if (result) {
				// redirect to success page
				RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
				rd.forward(request, response);
			} 
			else {
				// redirect to error page
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
