package model;

public class wine_b2 {
	  private String id;
	  private String enName;
	  private String chName;
	  private String type;
	  private String percent;
	  private int ml;
	  private int price;
	  private String unit;
	  private String place;
	  private String grape;
	  private String feature;
	  private String imgPath;
	
	  public wine_b2(){}
	  public wine_b2(String id, String enName, String chName, String type, String percent, int ml, int price, String unit,
			String place, String grape, String feature, String imgPath) {
		this.id = id;
		this.enName = enName;
		this.chName = chName;
		this.type = type;
		this.percent = percent;
		this.ml = ml;
		this.price = price;
		this.unit = unit;
		this.place = place;
		this.grape = grape;
		this.feature = feature;
		this.imgPath = imgPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public int getMl() {
		return ml;
	}
	public void setMl(int ml) {
		this.ml = ml;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getGrape() {
		return grape;
	}
	public void setGrape(String grape) {
		this.grape = grape;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	  
	 
	  

}
