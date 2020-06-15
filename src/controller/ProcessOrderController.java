package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import model.MerchandiseToImport;
import model.Order;
import model.Site;
import util.SQLConnect;
import util.SiteSorter;
import view.OrderView;

public class ProcessOrderController {
	Order order;
	Site site;
	List<MerchandiseToImport> merchandise;

	public ProcessOrderController() {

	}

	public List<MerchandiseToImport> getMerchandiseToImportList() {
		SQLConnect sqlConnect = new SQLConnect();
		List<MerchandiseToImport> merchList = sqlConnect.getMerchandiseToImportList();
		return merchList;
	}

	public void createOrder(int merchandiseToImportId) {
		// TODO Auto-generated method stub
		System.out.println("Mặt hàng được chọn. Chuyẩn bị tạo đơn cho mã yêu cầu nhập khẩu: " + merchandiseToImportId);
		SQLConnect sqlConnect = new SQLConnect();
		MerchandiseToImport merch = sqlConnect.getMerchandiseToImport(merchandiseToImportId);
		SQLConnect.PrintRes(merch);
		int quantityRequire = merch.getNumberRequire();
		System.out.println(quantityRequire);

		// get site info
		try {
			List<Site> sites = sqlConnect.getSitesInfoThatSellTheMerchandie(merch.getId());
			SQLConnect.PrintSites(sites);

			// choose site to order
			SiteSorter sorter = new SiteSorter((ArrayList<Site>) sites);
			sorter.getSortedSiteByStock();
			List<Site> chosenSites = new ArrayList<Site>();
			for (Site site : sites) {
				if (site.getTransportation().equals("Train")) {
					chosenSites.add(site);
				}
			}
			int quantityOrdered = 0;
			int i = 0;
			while (quantityOrdered <= quantityRequire && i < chosenSites.size()) {
				quantityOrdered += chosenSites.get(i).getSellingMerchandiseStock();
				i++;
			}
			chosenSites.subList(i, chosenSites.size()).clear();

			OrderView orderView = new OrderView((ArrayList<Site>) chosenSites, merch.getUnit());
			orderView.setOrderLabel(merch.getName(), merch.getCode());
			orderView.setVisible(true);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void onCreateOrderBtnClick(int merchandiseToImportCode) {
		createOrder(merchandiseToImportCode);

	}

}
