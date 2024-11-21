package InsuranceSystem.servlet;

import java.io.IOException;

import InsuranceSystem.exception.CertException;
import InsuranceSystem.model.dto.UserCert;
import InsuranceSystem.service.CertService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private CertService certService = new CertService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req, resp);		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserCert userCert = null;
		
		try {
			userCert = certService.getCert(username, password);
		}catch (CertException e) {
			req.setAttribute("message", e.getMessage());
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
			return;
		}
		
		HttpSession session = req.getSession();
		session.setAttribute("userCert", userCert);
		//req.setAttribute("message", "登入成功");
		//req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);//之後導到首頁
		resp.sendRedirect("/InsuranceSystem/home");
	}

}
