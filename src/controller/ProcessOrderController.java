package controller;
import java.util.List;

import model.MerchandiseToImport;
import model.Order;
import model.Site;
import util.SQLConnect;
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
		
		
		//get site info
		Site site = new Site();
		
		OrderView view = new OrderView();
		view.setVisible(true);
	}
	public void onCreateOrderBtnClick(int merchandiseToImportCode) {
		createOrder(merchandiseToImportCode);
		
	}
}
