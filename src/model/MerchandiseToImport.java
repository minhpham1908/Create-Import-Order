package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MerchandiseToImport extends Merchandise {
	private int merchandiseToImportCode;
		private int numberRequire;
	private LocalDateTime dayShipRequire;

	

	public MerchandiseToImport() {
	}


	public int getNumberRequire() {
		return numberRequire;
	}

	public LocalDateTime getDayShipRequire() {
		return dayShipRequire;
	}


	public void setDayShipRequire(LocalDateTime dayShipRequire) {
		this.dayShipRequire = dayShipRequire;
	}


	public void setNumberRequire(int numberRequire) {
		this.numberRequire = numberRequire;
	}

	public int getMerchandiseToImportCode() {
		return merchandiseToImportCode;
	}


	public void setMerchandiseToImportCode(int merchandiseToImportCode) {
		this.merchandiseToImportCode = merchandiseToImportCode;
	}

}
