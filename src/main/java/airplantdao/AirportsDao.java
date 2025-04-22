package airplantdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.JDBCutil;
import bean.AirportsBean;

public class AirportsDao {
	//新增機場
	public void insertAirport(AirportsBean airport) throws SQLException {
	    String sql = "INSERT INTO Airports (AirportName, IataCode, CountryRegion,City) VALUES (?, ?, ?,?)";
	    Connection connection = JDBCutil.getConnection();
	    PreparedStatement preparedStatement = null;

	    try {
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, airport.getAirportName());
	        preparedStatement.setString(2, airport.getIataCode());
	        preparedStatement.setString(3, airport.getCountryRegion());
	        preparedStatement.setString(4, airport.getCity());
	        preparedStatement.executeUpdate(); // 執行更新
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        JDBCutil.closeResources(connection, preparedStatement, null); // 確保關閉所有資源
	    }
	}
	//刪除機場
	public  void deleteAirport(int airportsId) throws SQLException {
	    String sql = "DELETE FROM Airports WHERE AirportsId = ?";
	    Connection connection = JDBCutil.getConnection();
	    PreparedStatement preparedStatement = null;

	    try {
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setInt(1, airportsId);
	        preparedStatement.executeUpdate(); // 執行更新
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        JDBCutil.closeResources(connection, preparedStatement, null); // 確保關閉所有資源
	    }
	}
	// 修改機場
	public void updateAirports(List<AirportsBean> airports) throws SQLException {
	    String sql = "UPDATE Airports SET AirportName = ?, IataCode = ?, CountryRegion = ?, City = ? WHERE AirportsId = ?";
	    Connection connection = JDBCutil.getConnection();
	    PreparedStatement preparedStatement = null;

	    try {
	        preparedStatement = connection.prepareStatement(sql);

	        for (AirportsBean airport : airports) {
	            preparedStatement.setString(1, airport.getAirportName());
	            preparedStatement.setString(2, airport.getIataCode());
	            preparedStatement.setString(3, airport.getCountryRegion());
	            preparedStatement.setString(4, airport.getCity());
	            preparedStatement.setInt(5, airport.getAirportsId()); // 設置要更新的機場 ID
	            preparedStatement.addBatch(); // 將更新添加到批次中
	        }

	        preparedStatement.executeBatch(); // 執行批次更新
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    } finally {
	        JDBCutil.closeResources(connection, preparedStatement, null); // 確保關閉所有資源
	    }
	}
	//搜尋機場
	public ArrayList<AirportsBean> searchAirports(String airportName, String iataCode, String countryRegion) throws SQLException {
	    ArrayList<AirportsBean> airportsList = new ArrayList<>();
	    StringBuilder sql = new StringBuilder("SELECT * FROM Airports WHERE 1=1"); // 基本查詢

	    // 用於存儲查詢參數的列表
	    List<String> parameters = new ArrayList<>();

	    // 根據條件動態添加查詢
	    if (airportName != null && !airportName.trim().isEmpty()) {
	        sql.append(" AND AirportName LIKE ?");
	        parameters.add("%" + airportName + "%");
	    }
	    if (iataCode != null && !iataCode.trim().isEmpty()) {
	        sql.append(" AND IataCode LIKE ?");
	        parameters.add("%" + iataCode + "%");
	    }
	    if (countryRegion != null && !countryRegion.trim().isEmpty()) {
	        sql.append(" AND CountryRegion LIKE ?");
	        parameters.add("%" + countryRegion + "%");
	    }

	    // 使用 try-with-resources 自動關閉資源
	    try (Connection connection = JDBCutil.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

	        // 設置查詢參數
	        for (int i = 0; i < parameters.size(); i++) {
	            preparedStatement.setString(i + 1, parameters.get(i));
	        }

	        try (ResultSet resultSet = preparedStatement.executeQuery()) { // 獲取結果集
	            while (resultSet.next()) {
	                AirportsBean airport = new AirportsBean();
	                airport.setAirportsId(resultSet.getInt("AirportsId"));
	                airport.setAirportName(resultSet.getString("AirportName"));
	                airport.setIataCode(resultSet.getString("IataCode"));
	                airport.setCountryRegion(resultSet.getString("CountryRegion"));
	                airport.setCity(resultSet.getString("City"));
	                airportsList.add(airport); // 將每個案例添加到列表中
	            }
	        }
	    } catch (SQLException e) {
	        // 使用日誌記錄異常
	        // logger.error("SQL error while searching airports", e);
	        throw e; // 重新拋出異常以供調用者處理
	    }

	    return airportsList; // 返回包含搜尋結果的列表
	}
	//搜尋全部
	public ArrayList<AirportsBean> SELECTallinfo() throws SQLException{
		String sql = "SELECT * FROM Airports";
		Connection connection = JDBCutil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<AirportsBean> AirportsList = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(); // 使用 executeQuery() 來獲取結果集
			while (resultSet.next()) {

				AirportsBean Airports = new AirportsBean();
				Airports.setAirportsId(resultSet.getInt("AirportsId"));
				Airports.setAirportName(resultSet.getString("AirportName"));
				Airports.setIataCode(resultSet.getString("IataCode"));
				Airports.setCountryRegion(resultSet.getString("CountryRegion"));
				Airports.setCity(resultSet.getString("City"));
			
				AirportsList.add(Airports); // 將每個案例添加到列表中
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCutil.closeResources(connection, preparedStatement, resultSet); // 確保關閉所有資源
		}
		return AirportsList; // 返回包含所有案例的列表
	}
}
