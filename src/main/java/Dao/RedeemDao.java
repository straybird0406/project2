package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hw1.ProductsBean;
import utils.Utils;

public class RedeemDao {

	// 用id查詢單筆商品
	public ProductsBean getOneProduct(String product_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT *FROM Products WHERE product_id=?";

		ProductsBean product = null;

		try {

			conn = Utils.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, product_id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				product = new ProductsBean();
				product.setProduct_id(rs.getString("product_id"));
				product.setCategory_id(rs.getString("category_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_desc(rs.getString("product_desc"));
				product.setNeedmiles(rs.getInt("needmiles"));
				product.setQuantity(rs.getInt("quantity"));
				product.setProduct_image(rs.getString("product_image"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closeResource(conn, stmt, rs);
			;
		}

		return product;
	}

	// 用名字模糊查詢多筆商品
	public List<ProductsBean> getByName(String product_name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT *FROM Products WHERE product_name like ?";
		ProductsBean product = null;
		List<ProductsBean> products = null;

		try {

			conn = Utils.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + product_name + "%");
			rs = stmt.executeQuery();
			products = new ArrayList<>();
			while (rs.next()) {
				product = new ProductsBean();
				product.setProduct_id(rs.getString("product_id"));
				product.setCategory_id(rs.getString("category_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_desc(rs.getString("product_desc"));
				product.setNeedmiles(rs.getInt("needmiles"));
				product.setQuantity(rs.getInt("quantity"));
				product.setProduct_image(rs.getString("product_image"));
				products.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closeResource(conn, stmt, rs);
			;
		}

		return products;
	}

	// 查詢全部商品
	public List<ProductsBean> getall() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT* from Products";
		ProductsBean product = null;
		List<ProductsBean> products = null;

		try {

			conn = Utils.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			products = new ArrayList<>();
			while (rs.next()) {
				product = new ProductsBean();
				product.setProduct_id(rs.getString("product_id"));
				product.setCategory_id(rs.getString("category_id"));
				product.setProduct_name(rs.getString("product_name"));
				product.setProduct_desc(rs.getString("product_desc"));
				product.setNeedmiles(rs.getInt("needmiles"));
				product.setQuantity(rs.getInt("quantity"));
				product.setProduct_image(rs.getString("product_image"));
				products.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closeResource(conn, stmt, rs);
			;
		}

		return products;
	}

	// 新增一筆商品資料
	@SuppressWarnings("unused")
	public boolean insertone(ProductsBean product) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "INSERT INTO Products (category_id, product_name, product_desc, needmiles, quantity, product_image) VALUES (?, ?, ?, ?, ?, ?)";
		int generatedId = -1;
		try {
			// 新增產品資料

			conn = Utils.getConnection();

			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, product.getCategory_id());
			stmt.setString(2, product.getProduct_name());
			stmt.setString(3, product.getProduct_desc());
			stmt.setInt(4, product.getNeedmiles());
			stmt.setInt(5, product.getQuantity());
			stmt.setString(6, product.getProduct_image());

			// 執行 INSERT
			int affectedRows = stmt.executeUpdate();

			// 確保有新增資料
			if (affectedRows > 0) {
				// 取得自動生成的 product_id
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						generatedId = generatedKeys.getInt(1);
					}
				}

				return affectedRows > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Utils.closeResource(conn, stmt);

		}
		return false;
	}

	// 刪除一個商品by id
	@SuppressWarnings("unused")
	public boolean delete(String product_id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "delete from Products where product_id=?";
		try {
			conn = Utils.getConnection();
			stmt = conn.prepareStatement(sql);

			// 執行 delete
			stmt.setString(1, product_id);
			int updaterows = stmt.executeUpdate();
			if (updaterows > 0) {
				return updaterows > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.closeResource(conn, stmt);

		}

		return false;
	}

	// 修改商品資料byId
	@SuppressWarnings("unused")
	public ProductsBean UpdateById(ProductsBean product) {
		Connection conn = null;
		PreparedStatement stmt = null;

		String sql = "UPDATE Products SET category_id=?,product_name=?,product_desc=?,"
				+ "needmiles=?,quantity=?,product_image=? WHERE product_id=?";

		try {
			conn = Utils.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getCategory_id());
			stmt.setString(2, product.getProduct_name());
			stmt.setString(3, product.getProduct_desc());
			stmt.setInt(4, product.getNeedmiles());
			stmt.setInt(5, product.getQuantity());
			stmt.setString(6, product.getProduct_image());
			stmt.setString(7, product.getProduct_id());

			// 執行update
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				// 如果沒有刪除任何資料
				return null; // 沒有刪除，返回 null
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.closeResource(conn, stmt);

		}
		return product;

	}
}
