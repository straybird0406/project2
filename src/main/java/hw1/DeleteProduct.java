package hw1;

import java.io.IOException;

import Dao.RedeemDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		try {
			// 從 HTML 獲取數據
			String product_id = request.getParameter("product_id");
			
			//調用delete方法
			RedeemDao redeemDao = new RedeemDao();
			boolean isSuccess =	redeemDao.delete(product_id);
			
			
			 if (isSuccess) {
					//刪除成功後，導向 查看全部商品的 Servlet
				  response.sendRedirect("GetAllProducts");
		        } else {
		        	// 跳轉到結果頁面
					request.getRequestDispatcher("Fail.jsp").forward(request, response);
					
		        }
		} catch (Exception e) {
			e.printStackTrace();
		
				
				}
			}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}