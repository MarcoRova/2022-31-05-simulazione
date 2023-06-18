package it.polito.tdp.nyc.model;

public class Vertice {

	private String quartiere;
	private double distanza;
	
	public Vertice(String quartiere, double distanza) {
		super();
		this.quartiere = quartiere;
		this.distanza = distanza;
	}

	public String getQuartiere() {
		return quartiere;
	}

	public void setQuartiere(String quartiere) {
		this.quartiere = quartiere;
	}

	public double getDistanza() {
		return distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	
	
	
}
