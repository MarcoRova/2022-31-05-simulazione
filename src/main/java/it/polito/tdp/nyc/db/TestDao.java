package it.polito.tdp.nyc.db;

import java.util.ArrayList;
import java.util.List;

public class TestDao {

	public static void main(String[] args) {
		NYCDao dao = new NYCDao();
//		System.out.println(dao.getAllHotspot().size());
		
		List<String> provider = new ArrayList<>(dao.getVertex("SPECTRUM"));
		
		for(String s:provider)
			System.out.println(s+"\n");
	}

}
