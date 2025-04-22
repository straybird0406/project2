package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import JDBC.JDBCutil;
import airplantdao.AirportsDao;
import bean.AirportsBean;


@WebServlet("/airports")
public class airports extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 Map<String, Object> jsonResponse = new HashMap<>();
	boolean status=true;
    
    public airports() {
        super();
       
        
    }

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 設定回應內容類型
	    response.setContentType("application/json");
	    jsonResponse.clear();
	 // 使用自定義函式解析 JSON 數據
        Map<String, String> params = parseJsonRequest(request);
	    
	    // 獲取操作類型
	    String action = params.get("action");
	    AirportsDao aDao=new AirportsDao();
		  ArrayList<AirportsBean> AirportsList = null;
		  
		  try {
				
			  if (action.equalsIgnoreCase("getall")){
				  	
					
					
						AirportsList=aDao.SELECTallinfo();
						jsonResponse.put("result",AirportsList);
						
						
					
		        } else if (action.equalsIgnoreCase("select")){
		        	
		        	
		        	//irportsList=aDao.searchAirports();
					jsonResponse.put("result",AirportsList);
		           
		        } else if (action.equalsIgnoreCase("update")){
		           
		        } else if (action.equalsIgnoreCase("insert")){
		            
		        }else if (action.equalsIgnoreCase("edit")){
		        	aDao.updateAirports(AirportsList);
		        }
		        else if (action.equalsIgnoreCase("delete")){
		        	
		        	   int delectid = Integer.parseInt(request.getParameter("airportsId"));
						//aDao.deleteAirport(delectid);
						
		            
		        }
		  } catch (Exception e) {
			    e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 設置 HTTP 錯誤狀態碼
	            
	            return;
		}
		  
		
		
		 jsonResponse.put("stuts", status);
		// 使用 Gson 將列表轉換為 JSON
        Gson gson = new Gson();
        String json = gson.toJson(jsonResponse);

        // 設置回應類型為 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json); // 寫入 JSON 到回應
		
	}
	
	// 函式：從 HttpServletRequest 中讀取 JSON 數據並解析為 Map
    public static Map<String, String> parseJsonRequest(HttpServletRequest request) throws IOException {
    	
        // 讀取請求體中的 JSON 數據
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        
        String jsonString = jsonBuilder.toString();
        
        // 使用 Gson 解析 JSON 數據
        Gson gson = new Gson();
        return gson.fromJson(jsonString, new TypeToken<Map<String, String>>(){}.getType());
    }

}
