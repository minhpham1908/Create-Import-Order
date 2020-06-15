package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.MerchandiseToImport;
import model.Site;

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
		String sql = "SELECT * FROM MerchandiseToImport, Merchandise where MerchandiseToImport.merchId = Merchandise.merchId";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int timestampInSecond = rs.getInt("desired_delivery_date");
				LocalDateTime dayShipRequire = TimeProcessor.UnixToLocalDateTime(timestampInSecond);

				MerchandiseToImport merch = new MerchandiseToImport();
				merch.setId(rs.getInt(Tukhoa.MERCHANDISE_ID));
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
		String sql = "SELECT * from MerchandiseToImport, Merchandise where MerchandiseToImport.merchId = Merchandise.merchId and merchandiseToImportId = ?";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, merchandiseToOrderId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int timestampInSecond = rs.getInt("desired_delivery_date");
				LocalDateTime dayShipRequire = TimeProcessor.UnixToLocalDateTime(timestampInSecond);

				merch = new MerchandiseToImport();
				merch.setId(rs.getInt(Tukhoa.MERCHANDISE_ID));
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

	public List<Site> getSitesInfoThatSellTheMerchandie(int merchandiseId) {
		List<Site> sites = new ArrayList<Site>();
		String query = "SELECT * FROM Site, SellingMerchandise, Merchandise WHERE Site.siteId = SellingMerchandise.siteId and SellingMerchandise.merchId = Merchandise.merchId and  Merchandise.merchId = ?";
		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, merchandiseId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Site site = new Site();
				site.setSiteName(rs.getString(Tukhoa.SITE_NAME));
				site.setid(rs.getInt(Tukhoa.SITE_ID));
				site.setTransportation(rs.getString(Tukhoa.SITE_TRANSPORTATION));
				site.setNumberOfDayTransporting(rs.getInt(Tukhoa.SITE_TRANSPORTDAY));
				site.setSellingMerchandiseStock(rs.getInt(Tukhoa.MERCH_QUANTITY));
				site.setSellingMerchandiseId(rs.getInt(Tukhoa.MERCHANDISE_ID));
				sites.add(site);

			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return sites;
	}

	public static void PrintResList(List<MerchandiseToImport> lista) {
		for (MerchandiseToImport merch : lista) {
			PrintRes(merch);
		}
	}

	public static void PrintRes(MerchandiseToImport merch) {
		System.out.print(merch.getId() + " | " + merch.getCode() + " | " + merch.getName() + " | "
				+ merch.getNumberRequire() + " | " + merch.getUnit() + " | "
				+ TimeProcessor.getStringLocalDateTime(merch.getDayShipRequire(), "dd MMM uuuu HH:mm:ss") + "\n");
	}

	public static void PrintSites(List<Site> sites) {
		System.out.println("Thong tin cac sites");
		for (Site site : sites) {
			System.out.println(site);
		}
	}

}
