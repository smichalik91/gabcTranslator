package com.sim1.chantmasternew.gabc;
import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GSubNeume.Name;


public abstract class GModifier {

	//MEXGROUP1: Mutually Exclusive Group 1 - single notes (can't have virgo and quilisma
	//MEXGROUP2: Mutually Exclusive Group 2 - end modifiers (can't have mora and epizema)
	//ADDON: Simply add on end with no restrictions
	public enum Type {
		MEXGROUP1, MEXGROUP2, ADDON
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
	
	
	//  Assumptions for "sortModifiers": 
	//   mutually exclusive (conflicting) modifiers will be eliminated (keep last instance)
	//   there will be no more than one modifier with (replacePunctum = true) per tone
	//   the above mentioned modifier will be first of those of that tone
	//   modifiers will be ordered as such, MEXGROUP1, MEXGROUP2, ADDON then by tone
	public static ArrayList<GModifier> sortModifiers(GSubNeume in){
		ArrayList<GModifier> list = new ArrayList<>();
		GModifier temp = null;
		for(int k = 0; k < in.modifiers.size(); k++){
			temp = in.modifiers.get(k);
			list.add(temp);
		}
		return list;
	}
}
