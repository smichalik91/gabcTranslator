package com.sim1.chantmasternew.gabc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import com.sim1.chantmasternew.gabc.GSubNeume.Name;


public abstract class GModifier {

	//MEXGROUP1: Mutually Exclusive Group 1 - single notes (e.g. can't have virgo and quilisma
	//MEXGROUP2: Mutually Exclusive Group 2 - end modifiers (e.g. can't have mora and epizema)
	//ADDON: Simply add on end with no restrictions
	public enum Type {
		MEXGROUP1, MEXGROUP2, ADDON, SHIFTER
	}
	
	// Type or Grouping helps indicate which modifiers are mutual exclusive
	public Type type;
	
	//  This determines order of outputting modifiers
	public int priority; 
	
	// subNeume to modify
	public GSubNeume subNeume;
	
	//indicates index of tone within GSubNeume to which this modifier is applied
	public int index;
	
	//indicates whether this GModifier's output should replace the notation of that being modified
	//if replacePunctum is false, simply add modifier output to notation of that being modified
	public boolean replacePunctum;

	
	public abstract String getOutput();
	
	protected void setPriority(){
		switch (type) {
			case SHIFTER:
				priority = 99999;
				break;
			case MEXGROUP1:
				priority = 1;
				break;
			case MEXGROUP2:
				priority = 10;
				break;
			case ADDON:
				priority = 100;
				break;
			default:
				priority = 1000;
				break;
					
		}
	}
	
	public void setSubNeume(GSubNeume in) {
		subNeume = in;
	}
	
	
	//                      Assumptions for "sortModifiers": 
	//   mutually exclusive (conflicting) modifiers will be eliminated (keep first instance)
	//   there will be no more than one modifier with (replacePunctum = true) per tone
	//   the above mentioned modifier will be first of those of that tone
	//   modifiers will be ordered first by tone, then: MEXGROUP1, MEXGROUP2, ADDON, SHIFTER
	public static void sortModifiers(GSubNeume in){
		ArrayList<GModifier> list = new ArrayList<>();
		GModifier temp = null;
		
		System.out.println();
		System.out.println("SubNeume: " + in.getClass().toString());
		System.out.println("# of Modifiers pre-sort: " + in.modifiers.size());
		
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
		boolean hasGroup3 = (in.name == Name.PUNCTUM); // Remove all shifters if it's a punctum, they don't apply.
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
						System.out.println("Shifter found!");
						if(!hasGroup3) hasGroup3 = true;
						else modsToRemove.add(k);
					}
				}
			}
			hasGroup1 = false;
			hasGroup2 = false;
			hasGroup3 = (in.name == Name.PUNCTUM);
			p2++;
		}
		
		// remove conflicting modifiers from list
		for(int k = modsToRemove.size() - 1; k >= 0 ; k--){
			int indexToRemove = modsToRemove.get(k);
			GModifier removed = list.remove(indexToRemove);
			System.out.println("Modifier Removed: " + removed.getClass().toString());
		}
		
		// sort by index then priority
		Collections.sort(list, new GModifierComparator());
		
		System.out.println("# of Modifiers post sort: " + list.size());
		
		Iterator<GModifier> itr = list.iterator();
		while(itr.hasNext()){
			GModifier m = (GModifier)itr.next();
			
			// populate the containsShifter array
			if(m.type == Type.SHIFTER) in.containsShifter[m.index] = true;
			
			System.out.println("Modifier: index: " + m.index + " Type: " + m.getClass().toString());
		}
		
		in.modifiers = list;
	}
}
