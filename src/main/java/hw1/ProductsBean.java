package hw1;

public class ProductsBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	//物件屬性
	private String product_id;
	private String category_id;
	private String product_name;
	private String product_desc;
	private int needmiles;    
	private int quantity;
	private String product_image;
	
	
	//getter setter
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}
	public int getNeedmiles() {
		return needmiles;
	}
	public void setNeedmiles(int needmiles) {
		this.needmiles = needmiles;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	
	//轉為字串方法
	@Override
	public String toString() {
		return "ProductsBean [product_id=" + product_id + ", category_id=" + category_id + ", product_name="
				+ product_name + ", product_desc=" + product_desc + ", needmiles=" + needmiles + ", quantity="
				+ quantity + ", product_image=" + product_image + ", getProduct_id()=" + getProduct_id()
				+ ", getCategory_id()=" + getCategory_id() + ", getProduct_name()=" + getProduct_name()
				+ ", getProduct_desc()=" + getProduct_desc() + ", getNeedmiles()=" + getNeedmiles() + ", getQuantity()="
				+ getQuantity() + ", getProduct_image()=" + getProduct_image() + "]";
	}
}
