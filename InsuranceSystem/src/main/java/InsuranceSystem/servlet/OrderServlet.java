package InsuranceSystem.servlet;

import java.io.IOException;
import java.util.List;

import InsuranceSystem.model.dto.OrderDto;
import InsuranceSystem.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/order/*","/orders"})
public class OrderServlet extends HttpServlet{
	private OrderService orderService = new OrderService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathinfo = req.getPathInfo();
		
		if(pathinfo == null || pathinfo.equals("/*")) {
			List<OrderDto> orderDtos = orderService.findAll();
			req.setAttribute("orderDtos", orderDtos);
			req.getRequestDispatcher("/WEB-INF/view/order.jsp").forward(req, resp);
		}else if(pathinfo.equals("/get")) {
			String orderId = req.getParameter("orderId");
			OrderDto orderDto = orderService.getOrderbyId(orderId);
			req.setAttribute("orderDto", orderDto);
			req.getRequestDispatcher("/WEB-INF/view/updateOrder.jsp").forward(req, resp);
		}else if(pathinfo.equals("/info")) {
			String orderId = req.getParameter("orderId");
			OrderDto orderDto = orderService.getOrderbyId(orderId);
			req.setAttribute("orderDto", orderDto);
			req.getRequestDispatcher("/WEB-INF/view/orderInfo.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathinfo = req.getPathInfo();
		
		String holder = req.getParameter("holder");
		String holderCN = req.getParameter("holderCN");
		String insured = req.getParameter("insured");
		String insuredCN = req.getParameter("insuredCN");
		String orderType = req.getParameter("orderType");
		String orderDate = req.getParameter("orderDate");
		String payType = req.getParameter("payType");
		String charge = req.getParameter("charge");
		String contact = req.getParameter("contact");
		String comment = req.getParameter("comment");//以上是 /order/add 用
		String orderStatus = req.getParameter("orderStatus");
		String orderId = req.getParameter("orderId");
		
		switch (pathinfo) {
			case "/add": 
				orderService.addOrder(holder, holderCN, insured, insuredCN, orderType, payType, orderDate, charge, contact, comment);
				break;
			case "/update":
				orderService.updateOrder(orderId, orderStatus, charge, contact,comment);
				break;
		}
		
		resp.sendRedirect("/InsuranceSystem/order");
	}	
}
