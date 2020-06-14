package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import model.Site;

public class SiteSorter {
	ArrayList<Site> sites = new ArrayList<Site>();
	public SiteSorter(ArrayList<Site> sites) {
		this.sites = sites;
	}
	
	public ArrayList<Site> getSortedSiteByStock(){
		Collections.sort(sites);
		return sites;
	}
	
}
