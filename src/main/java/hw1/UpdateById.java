package hw1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Dao.RedeemDao;

@WebServlet("/UpdateById")
public class UpdateById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateById() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// 從html獲得數據
			String product_id = request.getParameter("product_id");
			String category_id = request.getParameter("category_id");
			String product_name = request.getParameter("product_name");
			String product_desc = request.getParameter("product_desc");
			int needmiles = Integer.parseInt(request.getParameter("needmiles"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String product_image = request.getParameter("product_image");

			
//			將數據存入 Bean
			ProductsBean product = new ProductsBean();
			product.setProduct_id(product_id);
			product.setCategory_id(category_id);
			product.setProduct_name(product_name);
			product.setProduct_desc(product_desc);
			product.setNeedmiles(needmiles);
			product.setQuantity(quantity);
			product.setProduct_image(product_image);
			
			RedeemDao redeemDao = new RedeemDao();
			ProductsBean success = redeemDao.UpdateById(product);
			
			if (success!=null) {
				response.sendRedirect("GetAllProducts");
			} else {
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
