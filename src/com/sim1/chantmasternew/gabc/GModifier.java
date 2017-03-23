package com.sim1.chantmasternew.gabc;
import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GSubNeume.Name;


public abstract class GModifier {

	//MEXGROUP1: Mutually Exclusive Group 1 - single notes (can't have virgo and quilisma
	//MEXGROUP2: Mutually Exclusive Group 2 - end modifiers (can't have mora and epizema)
	//ADDON: Simply add on end with no restrictions
	public enum Type {
		MEXGROUP1, MEXGROUP2, ADDON, SHIFTER
	}
	// Type or Grouping helps indicate which modifiers are mutual exclusive
	public Type type;
	
	// subNeume to modify
	public GSubNeume subNeume;	
	//indicates index of tone within GSubNeume to which this modifier is applied
	public int index;	
	//indicates whether this GModifier's output should replace the notation of that being modified
	//if replacePunctum is false, simply add modifier output to notation of that being modified
	public boolean replacePunctum;

	
	public abstract String getOutput();
	
	public void setSubNeume(GSubNeume in) {
		subNeume = in;
	}
	
	
	//                      Assumptions for "sortModifiers": 
	//   mutually exclusive (conflicting) modifiers will be eliminated (keep first instance)
	//   there will be no more than one modifier with (replacePunctum = true) per tone
	//   the above mentioned modifier will be first of those of that tone (after a shifter if there is one)
	//   modifiers will be ordered as such, MEXGROUP1, MEXGROUP2, ADDON then by tone
	public static ArrayList<GModifier> sortModifiers(GSubNeume in){
		ArrayList<GModifier> list = new ArrayList<>();
		GModifier temp = null;
		
		// reorder modifiers by index
		int p = 0;
		while(list.size() < in.modifiers.size()){
			for(int k = 0; k < in.modifiers.size(); k++){
				temp = in.modifiers.get(k);
				if(temp.index == p) list.add(temp);
			}
			p++;
		}
		
		//mark conflicting mutually exclusive modifiers to remove
		ArrayList<Integer> modsToRemove = new ArrayList<>();
		int p2 = 0;
		boolean hasGroup1 = false;
		boolean hasGroup2 = false;
		boolean hasGroup3 = false;
		while(p2 < p){
			for(int k = 0; k < list.size(); k++){
				temp = list.get(k);
				if(temp.index == p2){
					if(temp.type == Type.MEXGROUP1){
						if(!hasGroup1) hasGroup1 = true;
						else modsToRemove.add(k);
					} else if(temp.type == Type.MEXGROUP2){
						if(!hasGroup2) hasGroup2 = true;
						else modsToRemove.add(k);
					} else if(temp.type == Type.SHIFTER){
						if(!hasGroup3) hasGroup3 = true;
						else modsToRemove.add(k);
					}
				}
			}
			hasGroup1 = false;
			hasGroup2 = false;
			p2++;
		}
		
		// remove conflicting modifiers from list
		for(int k = modsToRemove.size() - 1; k >= 0 ; k--){
			int indexToRemove = modsToRemove.get(k);
			GModifier removed = list.remove(indexToRemove);
			System.out.println("Modifier Removed: " + removed.getClass().toString());
		}
		
		return list;
	}
}
