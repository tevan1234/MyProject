package InsuranceSystem.servlet;

import java.io.IOException;
import java.util.List;

import InsuranceSystem.model.dto.ProdouctDto;
import InsuranceSystem.service.ProdouctService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/prodouct/*")
public class ProdouctServlet extends HttpServlet{

	private ProdouctService prodouctService = new ProdouctService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathinfo = req.getPathInfo();
		System.out.println(pathinfo);
		if(pathinfo == null || pathinfo.equals("/*")) {
			req.getRequestDispatcher("/WEB-INF/view/prodouct.jsp").forward(req, resp);
			return;
		}else if(pathinfo.equals("/list")) {
			List<ProdouctDto> prodouctDtos = prodouctService.findAll();
			req.setAttribute("prodouctDtos", prodouctDtos);
			req.getRequestDispatcher("/WEB-INF/view/prodouctlist.jsp").forward(req, resp);
			return;
		}else if(pathinfo.equals("/get")) {
			String ProdouctId = req.getParameter("prodouctId");
			ProdouctDto prodouctDto = prodouctService.getProdouct(ProdouctId);
			//resp.getWriter().print(prodouctDto.getPstatus());
			req.setAttribute("prodouctDto", prodouctDto);
			req.getRequestDispatcher("/WEB-INF/view/updateProdouct.jsp").forward(req, resp);
			return;
		}else if(pathinfo.equals("/delete")) {
			String ProdouctId = req.getParameter("prodouctId");
			prodouctService.deleteProdouct(ProdouctId);
			resp.sendRedirect("/InsuranceSystem/prodouct/list");
			return;
		}else if(pathinfo.equals("/rank")){
			req.setAttribute("ranking", prodouctService.Ranking());
			req.getRequestDispatcher("/WEB-INF/view/ranking.jsp").forward(req, resp);
		}
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathinfo = req.getPathInfo();
		
		String prodouctId = req.getParameter("prodouctId");
		String price = req.getParameter("price");
		String prodouctType = req.getParameter("prodouctType");
		String prodouctStatus = req.getParameter("prodouctStatus");
		
		switch(pathinfo) {
			case "/add" :
				prodouctService.addProdouct(prodouctType, price);
				break;
			case "/update":
				prodouctService.updateProdouct(prodouctId, price, prodouctStatus);
				break;
				
		}
		resp.sendRedirect("/InsuranceSystem/prodouct/list");
	}
	
	
	
	
}
