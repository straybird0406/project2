package hw1;

import java.io.IOException;
import java.util.List;

import Dao.RedeemDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllProducts")
public class GetAllProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			RedeemDao redeemDao = new RedeemDao();
			List<ProductsBean> products = redeemDao.getall();
			request.setAttribute("products", products);
			request.getRequestDispatcher("GetAllProducts.jsp").forward(request, response);

			if (products != null) {
//			跳轉到成功頁面
				request.getRequestDispatcher("GetAllProducts.jsp").forward(request, response);

			} else {
				// 跳轉到失敗頁面
				request.getRequestDispatcher("Fail.jsp").forward(request, response);

			}
		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}