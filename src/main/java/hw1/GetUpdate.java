package hw1;

import java.io.IOException;

import Dao.RedeemDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetUpdate")
public class GetUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			// 從 HTML 獲取數據
			String product_id = request.getParameter("product_id"); 
			ProductsBean product  = new ProductsBean();
			
			RedeemDao redeemDao = new RedeemDao();
			product=redeemDao.getOneProduct(product_id);
		        
			request.setAttribute("product", product);
			request.getRequestDispatcher("GetUpdate.jsp").forward(request, response);
				
		     
		} catch (Exception e) {
			e.printStackTrace();
		
				
				}
			}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}