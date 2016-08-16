package mobicrats.co.in.ifil.beans;

public class Ifound_Get_FoundItemDetails_Result {
	public String ItemID;
	public String ItemName;
	public String ItemDesc;
	public String StateName;
	public String AreaName;
	public String CityName;
	public String Landmark;
	public String FoundDate;
	public String Address;
	public String ImagePath;
	public String FoundUserID;
	public String StatusName;
	public String ProdCategory;


	public Ifound_Get_FoundItemDetails_Result(){

	}

	public Ifound_Get_FoundItemDetails_Result(String _ItemID,String _ItemName,String _ItemDesc,
			String _StateName,String _AreaName,String _CityName,String _Landmark,String _FoundDate,
			String _Address,String _ImagePath){
		this.ItemID=_ItemID;
		this.ItemDesc=_ItemDesc;
		this.StateName=_StateName;
		this.AreaName=_AreaName;
		this.CityName=_CityName;
		this.Landmark=_Landmark;
		this.FoundDate=_FoundDate;
		this.Address=_Address;
		this.ImagePath=_ImagePath;
		/*this.FoundUserID=_FoundUserID;
		this.StatusName=_StatusName;
		this.ProdCategory=_ProdCategory;*/

	}

	public String getItemID() {
		return ItemID;
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getItemDesc() {
		return ItemDesc;
	}
	public void setItemDesc(String itemDesc) {
		ItemDesc = itemDesc;
	}
	public String getStateName() {
		return StateName;
	}
	public void setStateName(String stateName) {
		StateName = stateName;
	}
	public String getAreaName() {
		return AreaName;
	}
	public void setAreaName(String areaName) {
		AreaName = areaName;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getLandmark() {
		return Landmark;
	}
	public void setLandmark(String landmark) {
		Landmark = landmark;
	}
	public String getFoundDate() {
		return FoundDate;
	}
	public void setFoundDate(String foundDate) {
		FoundDate = foundDate;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	public String getFoundUserID() {
		return FoundUserID;
	}
	public void setFoundUserID(String foundUserID) {
		FoundUserID = foundUserID;
	}
	public String getStatusName() {
		return StatusName;
	}
	public void setStatusName(String statusName) {
		StatusName = statusName;
	}
	public String getProdCategory() {
		return ProdCategory;
	}
	public void setProdCategory(String prodCategory) {
		ProdCategory = prodCategory;
	}


}
