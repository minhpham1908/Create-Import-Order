package model;

import java.time.LocalDateTime;

public class Site implements Comparable<Site> {
	private int id;
	private String SiteName;
	private String transportation;
	private int numberOfDayTransporting;
	private SellingMerchandise sellingMerchandise;

	public Site() {
		sellingMerchandise = new SellingMerchandise();
	}

	public int getId() {
		return id;
	}

	public String getSiteName() {
		return SiteName;
	}

	public String getTransportation() {
		return transportation;
	}

	public int getNumberOfDayTransporting() {
		return numberOfDayTransporting;
	}

	public void setid(int siteId) {
		this.id = siteId;
	}

	public void setSiteName(String siteName) {
		SiteName = siteName;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public void setNumberOfDayTransporting(int numberOfDayTransporting) {
		this.numberOfDayTransporting = numberOfDayTransporting;
	}

	public int getSellingMerchandiseStock() {
		return sellingMerchandise.getStock();
	}

	public void setSellingMerchandiseStock(int stock) {
		sellingMerchandise.setStock(stock);
	}

	public int getSellingMerchandiseId() {
		return sellingMerchandise.getId();
	}

	public void setSellingMerchandiseId(int id) {
		sellingMerchandise.setId(id);
	}

	@Override
	public String toString() {

		return "Thong tin site " + this.getSiteName() + "\n" + this.getId() + " | " + this.getSiteName() + " | "
				+ this.getTransportation() + " | " + this.getSellingMerchandiseId() + " | "
				+ this.getSellingMerchandiseStock() + " | " + this.getNumberOfDayTransporting() + "\n";
	}

	@Override
	public int compareTo(Site other) {
		return (this.getSellingMerchandiseStock() > other.getSellingMerchandiseStock()) ? -1
				: (this.getSellingMerchandiseStock() == other.getSellingMerchandiseStock()) ? 0 : 1;
	}
}
