package it.polito.tdp.nyc.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.nyc.db.NYCDao;

public class Model {
	
	private NYCDao dao;
	
	private Graph<String, DefaultWeightedEdge> grafo;
	private Map<Integer, Hotspot> hotspotIDMap;
	private List<InfoArco> archi;

	public Model() {
		super();
		dao = new NYCDao();
		this.hotspotIDMap = new HashMap<>();
	}
	
	
	public void creaGrafo(String provider) {
		
		this.grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.archi = new LinkedList<>();
		
		loadHotspotIDMap();
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertex(provider));
		
		List<Arco> archi = this.dao.getEdges(provider);
		
		for(Arco a: archi) {
			
			Hotspot h1 = this.hotspotIDMap.get(a.getH1());
			Hotspot h2 = this.hotspotIDMap.get(a.getH2());
			
			LatLng pos1 = new LatLng(h1.getLatitude(), h1.getLongitude());
			LatLng pos2 = new LatLng(h2.getLatitude(), h2.getLongitude());
			
			double peso = LatLngTool.distance(pos1, pos2, LengthUnit.KILOMETER);
			
			Graphs.addEdgeWithVertices(this.grafo, this.hotspotIDMap.get(a.getH1()).getCity(), this.hotspotIDMap.get(a.getH2()).getCity(), peso);	
			
			this.archi.add(new InfoArco(this.hotspotIDMap.get(a.getH1()).getCity(), this.hotspotIDMap.get(a.getH2()).getCity(), peso));
		}
	}
	
	private void loadHotspotIDMap() {
		
		List<Hotspot> hotspots = new LinkedList<>(this.dao.getAllHotspot());
		
		for(Hotspot h:hotspots)
			this.hotspotIDMap.put(h.getObjectId(), h);
	}


	public List<String> getProvider(){
		
		return this.dao.getProvider();
	}
	
	public List<String> getQuartieri(String provider){
		return this.dao.getVertex(provider);
	}
	
	public List<String> getQuartieriAdiacenti(String quartiere){
		
		return Graphs.neighborListOf(this.grafo, quartiere);
	}
	
//	public List<Vertice> getQuartieriAdiacenti2(String quartiere){
//		
//		List<String> quartieri = new LinkedList<>(getQuartieriAdiacenti(quartiere));
//		List<Vertice> vertici = new LinkedList<>();
//		
//		for(InfoArco a: archi) {
//			if(a.getCity1().compareTo(quartiere)==0 && a.getCity2().compareTo("Queens")==0) {
//				vertici.add(new Vertice(a.getCity2(), a.getDistanza()));
//			}	
//			}
//		
//		
//		return vertici;
//	}
//	
	
	
	public String infoGrafo() {
		return "Numero vertici: "+this.grafo.vertexSet().size() + "\nNumero archi: "+this.grafo.edgeSet().size();
	}
	
	
	
}
