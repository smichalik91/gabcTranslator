package com.sim1.chantmasternew.gabc;

import java.util.ArrayList;

public class GNeume {

	public ArrayList<GSubNeume> subNeumes;
	public String input;
	public String output;
	
	public GNeume(String in){
		subNeumes = new ArrayList<>();
		input = in;
	}
	
	public static void replaceLastInSubNeumes(ArrayList<GSubNeume> subNeumesList, GSubNeume replacingSubNeume) {
		GSubNeume temp = subNeumesList.get(subNeumesList.size() - 1);
		for(int k = 0; k < temp.modifiers.size(); k++) {
			temp.modifiers.get(k).setSubNeume(replacingSubNeume);
		}
		subNeumesList.set(subNeumesList.size() - 1, replacingSubNeume);
	}

}
