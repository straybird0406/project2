package bean;

public class AirportsBean {
	
	private int AirportsId; // 機場 ID
	private String IataCode; // 機場代碼
    private String AirportName; // 機場名稱
    private String CountryRegion; // 機場位置
    private String City; // 機場位置
    
    
    
    public AirportsBean() {
		super();
		
	}
    
    public int getAirportsId() {
		return AirportsId;
	}



	public void setAirportsId(int airportsId) {
		AirportsId = airportsId;
	}



	public String getIataCode() {
		return IataCode;
	}



	public void setIataCode(String iataCode) {
		IataCode = iataCode;
	}



	public String getAirportName() {
		return AirportName;
	}



	public void setAirportName(String airportName) {
		AirportName = airportName;
	}



	public String getCountryRegion() {
		return CountryRegion;
	}



	public void setCountryRegion(String countryRegion) {
		CountryRegion = countryRegion;
	}
	public String getCity() {
		return City;
	}



	public void setCity(String city) {
		this.City = city;
	}
	
	
}
