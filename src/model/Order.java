package model;

import java.util.List;

public class Order {
	Merchandise merchandise;
	int quantityNeedToOrder;
	List<SiteOrdered> siteOrdereds;

	public Order() {
	}

	public static void createOrder(Merchandise merchandise, int quantityNeedToOrder, List<SiteOrdered> siteOrdereds) {

	}
}
