package model;

public class SiteOrdered {
    String name;
    int quantityOrdered;
    Transportation transportation;

    public SiteOrdered() {
        
    }

	public String getName() {
		return name;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public Transportation getTransportation() {
		return transportation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public void setTransportation(Transportation transportation) {
		this.transportation = transportation;
	}
}
