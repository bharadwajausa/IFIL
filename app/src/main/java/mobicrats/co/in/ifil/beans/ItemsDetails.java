package mobicrats.co.in.ifil.beans;

import java.sql.Blob;

public class ItemsDetails {
	public String ProductID;
    public String ItemName;
    public String UserID;
    public String StateID;
    public String CityID;
    public String AreaId;
    public String Landmark;
    public String Address;
    public String Desc;
    public String date;

	public byte[] getProfilePic() {
		return ProfilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		ProfilePic = profilePic;
	}

	public byte[] ProfilePic;



	public String s ;//----It represents two types{ Ifound or Ilost} based on this value it takes whether it is lost or found item

	public int getCountt() {
		return countt;
	}

	public void setCountt(int countt) {
		this.countt = countt;
	}

	public int countt;


    public String getProductID() {
		return ProductID;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getStateID() {
		return StateID;
	}
	public void setStateID(String stateID) {
		StateID = stateID;
	}
	public String getCityID() {
		return CityID;
	}
	public void setCityID(String cityID) {
		CityID = cityID;
	}
	public String getAreaId() {
		return AreaId;
	}
	public void setAreaId(String areaId) {
		AreaId = areaId;
	}
	public String getLandmark() {
		return Landmark;
	}
	public void setLandmark(String landmark) {
		Landmark = landmark;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	

}
