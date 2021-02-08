package beans;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ManagedBean
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "productName")
	int productID;
	@Column
	String productName;
	@Column
	String productDiscription;
	@Column
	int productAmount;
	@Column
	double productCost;
	@Column
	String imageUrl;
	
	public Product() {}
	
	
	public Product(int productID, String productName, String productDiscription, int productAmount, double productCost, String imageUrl) {
		this.productID = productID;
		this.productName = productName;
		this.productDiscription = productDiscription;
		this.productAmount = productAmount;
		this.productCost = productCost;
		this.imageUrl = imageUrl;
	}
	
	

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", productDiscription="
				+ productDiscription + ", productAmount=" + productAmount + ", productCost=" + productCost
				+ ", imageUrl=" + imageUrl + "]";
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDiscription() {
		return productDiscription;
	}

	public void setProductDiscription(String productDiscription) {
		this.productDiscription = productDiscription;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public double getProductCost() {
		return productCost;
	}

	public void setProductCost(double productCost) {
		this.productCost = productCost;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	

}
