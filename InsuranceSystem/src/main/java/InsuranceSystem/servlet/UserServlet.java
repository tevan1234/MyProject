package InsuranceSystem.servlet;

import java.io.IOException;
import java.util.List;

import InsuranceSystem.exception.PasswordInvalidException;
import InsuranceSystem.exception.UserNotFoundException;
import InsuranceSystem.model.dto.OrderDto;
import InsuranceSystem.model.dto.UserCert;
import InsuranceSystem.model.dto.UserDto;
import InsuranceSystem.service.OrderService;
import InsuranceSystem.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/user/*","/users"})
public class UserServlet extends HttpServlet{
	
	private UserService userService = new UserService();
	private OrderService orderService = new OrderService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathinfo = req.getPathInfo();
		if(pathinfo == null || pathinfo.equals("/*")) {
			List<UserDto> userDtos = userService.findAll();
			//resp.getWriter().print(userDtos);
			req.setAttribute("userDtos", userDtos);
			req.getRequestDispatcher("/WEB-INF/view/user.jsp").forward(req, resp);
			return;
		}else if(pathinfo.equals("/get")) {
			String username = req.getParameter("username");
			UserDto userDto = userService.getUser(username);
			req.setAttribute("userDto", userDto);
			req.getRequestDispatcher("/WEB-INF/view/updateUser.jsp").forward(req, resp);
			
			return;
		}else if(pathinfo.equals("/delete")) {
			String userId = req.getParameter("userId");
			userService.deleteUser(userId);
			resp.sendRedirect("/InsuranceSystem/user");
			return;
		}else if(pathinfo.equals("/update/password")) {
			req.getRequestDispatcher("/WEB-INF/view/updatePassword.jsp").forward(req, resp);
			return;
		}else if(pathinfo.equals("/info")) {
			String cardNumber = req.getParameter("cardNumber");
			List<OrderDto>orderDtos = orderService.filterAll(cardNumber);
			req.setAttribute("orderDtos", orderDtos);
			req.getRequestDispatcher("/WEB-INF/view/userinfo.jsp").forward(req, resp);
			return;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathinfo = req.getPathInfo();
		
		String cardNumber = req.getParameter("cardNumber");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String mail = req.getParameter("mail");
		String role = req.getParameter("role");//以上六個屬性主要用於 user/add
		String userId = req.getParameter("userId");// user/update 專用
		String active = req.getParameter("active");// user/update 專用
		String oldPassword = req.getParameter("oldPassword"); // /update/password專用
		String newPassword = req.getParameter("newPassword"); // /update/password專用
		
		
		switch(pathinfo) {
			case "/add":
				userService.appendUser(cardNumber, username, password, phone, mail, role);
				break;
			case "/update":
				userService.updateUser(userId, active, role);
				break;
			case "/update/password":
				HttpSession session = req.getSession();				
				try {
					UserCert userCert = (UserCert)session.getAttribute("userCert");
					userService.updatePassword(userCert.getUserId(), userCert.getUsername(), oldPassword, newPassword);
					req.setAttribute("message","密碼更新成功");
					req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);
					
				}catch (Exception e) {
					req.setAttribute("message", e.getMessage());
					req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
				}			
				return;
		}
		
		resp.sendRedirect("/InsuranceSystem/user");
	}
	
}
