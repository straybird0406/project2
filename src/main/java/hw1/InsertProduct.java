package hw1;

import java.io.IOException;

import Dao.RedeemDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InsertProduct")
public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		try {
			
			// 從 HTML 獲取數據
			String category_id = request.getParameter("category_id");
			String product_name = request.getParameter("product_name");
			String product_desc = request.getParameter("product_desc");
			int needmiles = Integer.parseInt(request.getParameter("needmiles"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String product_image = request.getParameter("product_image");
			
			//存進Bean	
			 ProductsBean product = new ProductsBean();
		        product.setCategory_id(category_id);
		        product.setProduct_name(product_name);
		        product.setProduct_desc(product_desc);
		        product.setNeedmiles(needmiles);
		        product.setQuantity(quantity);
		        product.setProduct_image(product_image);
			

		    	RedeemDao redeemDao = new RedeemDao();
			boolean isSuccess =	redeemDao.insertone(product);
			
//			
			
			 if (isSuccess) {
					//新增成功後，導向 查看全部商品的 Servlet
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