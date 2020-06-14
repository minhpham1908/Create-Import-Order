package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.MerchandiseToImport;

public class SQLConnect {
	public Connection connect() {
		Connection connect = null;
		try {
			String url = "jdbc:sqlite:PTTKXDPM.db";
			// create a connection to the database
			connect = DriverManager.getConnection(url);

//			System.out.println("Connection to SQLite has been established.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connect;
	}

	public List<MerchandiseToImport> getMerchandiseToImportList() {
		List<MerchandiseToImport> merchandiseToImports = new ArrayList<MerchandiseToImport>();
		String sql = "SELECT * FROM MerchandiseToImport, Merchandise where MerchandiseToImport.merchCode = Merchandise.merchCode";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int timestampInSecond = rs.getInt("desired_delivery_date");
				LocalDateTime dayShipRequire = TimeProcessor.UnixToLocalDateTime(timestampInSecond);

				MerchandiseToImport merch = new MerchandiseToImport();
				merch.setMerchandiseToImportCode(rs.getInt(Tukhoa.MERCHANDISE_TO_IMPORT_ID));
				merch.setCode(rs.getString(Tukhoa.MERCHANDISE_CODE));
				merch.setName(rs.getString(Tukhoa.MERCHANDISE_NAME));
				merch.setNumberRequire(rs.getInt(Tukhoa.MERCHANDISE_QUANTITY));
				merch.setUnit(rs.getString(Tukhoa.MERCHANDISE_UNIT));
				merch.setDayShipRequire(dayShipRequire);
				merchandiseToImports.add(merch);
			}
		} catch (SQLException e) {
			System.err.println("Loi");
			System.out.println(e.getMessage());

		}
		return merchandiseToImports;
	}

	public MerchandiseToImport getMerchandiseToImport(int merchandiseToOrderId) {
		MerchandiseToImport merch = null;
		String sql = "SELECT * from MerchandiseToImport, Merchandise where MerchandiseToImport.merchCode = Merchandise.merchCode and merchandiseToImportId =\""
				+ merchandiseToOrderId + "\"";
		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int timestampInSecond = rs.getInt("desired_delivery_date");
				LocalDateTime dayShipRequire = TimeProcessor.UnixToLocalDateTime(timestampInSecond);

				merch = new MerchandiseToImport();
				merch.setMerchandiseToImportCode(rs.getInt(Tukhoa.MERCHANDISE_TO_IMPORT_ID));
				merch.setCode(rs.getString(Tukhoa.MERCHANDISE_CODE));
				merch.setName(rs.getString(Tukhoa.MERCHANDISE_NAME));
				merch.setNumberRequire(rs.getInt(Tukhoa.MERCHANDISE_QUANTITY));
				merch.setUnit(rs.getString(Tukhoa.MERCHANDISE_UNIT));
				merch.setDayShipRequire(dayShipRequire);
				return merch;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return merch;

	}

//	public static void main(String[] args) {
//		System.out.println("Hello");
//		SQLConnect sqlConnect = new SQLConnect();
//		MerchandiseToImport merch = sqlConnect.GetMerchandiseToImport();
//		sqlConnect.PrintRes(merch);
//
//		System.out.println();
//	}

	public static void PrintResList(List<MerchandiseToImport> lista) {
		for (int i = 0; i < lista.size(); i++) {
			MerchandiseToImport merch = lista.get(i);
			PrintRes(merch);
		}
	}

	public static void PrintRes(MerchandiseToImport merch) {

		System.out.print(merch.getCode() + " | " + merch.getName() + " | " + merch.getNumberRequire() + " | "
				+ merch.getUnit() + " | "
				+ TimeProcessor.getStringLocalDateTime(merch.getDayShipRequire(), "dd MMM uuuu HH:mm:ss") + "\n");

	}
}
