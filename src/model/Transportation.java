package model;

public class Transportation {
    String type;
    int numberOfDaysTransporting;

    public Transportation() {
        
    }

	public String getType() {
		return type;
	}

	public int getNumberOfDaysTransporting() {
		return numberOfDaysTransporting;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNumberOfDaysTransporting(int numberOfDaysTransporting) {
		this.numberOfDaysTransporting = numberOfDaysTransporting;
	}
    
}
