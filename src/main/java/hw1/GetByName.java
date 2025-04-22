package hw1;

import java.io.IOException;
import java.util.List;

import Dao.RedeemDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/GetByName")
public class GetByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//用商品名稱查詢多筆商品資料
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String product_name = request.getParameter("product_name");
			RedeemDao redeemDao = new RedeemDao();
			List<ProductsBean> products = redeemDao.getByName(product_name);
			request.setAttribute("products", products);
			request.getRequestDispatcher("GetAllProducts.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}