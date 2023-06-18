package it.polito.tdp.nyc.model;

public class InfoArco {

	private String city1;
	private String city2;
	private double distanza;
	
	public InfoArco(String city1, String city2, double distanza) {
		super();
		this.city1 = city1;
		this.city2 = city2;
		this.distanza = distanza;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public double getDistanza() {
		return distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	
}
