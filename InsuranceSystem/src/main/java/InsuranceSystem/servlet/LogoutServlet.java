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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		String message = "登出成功";
		//req.setAttribute("message", message);
		//req.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(req, resp);//之後改成導到首頁
		resp.sendRedirect("/InsuranceSystem/home");
	}

	
}
