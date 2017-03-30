package com.sim1.chantmasternew.gabc.neume;
import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

// Neume with one position
public class Punctum extends GSubNeume {
	

	public Punctum(int staffpos1){
		name = Name.PUNCTUM;
		modifiers = new ArrayList<>();
		pos = new int[1];
		pos[0] = staffpos1;		
		containsShifter = new boolean[1];
	}
	
	public String getOutput(){
		
		if(modifiers.size() == 0) out = cStaff[pos[0]] + "p";
		else {
			// Get play order of GModifiers
			GModifier.sortModifiers(this);
			
			out = "";
			GModifier mod;
			// if any modifiers are 'replacePunctum, the first modifier will be;
			// if it's not replacePunctum, add the "[staffPos]p" here
			if(!modifiers.get(0).replacePunctum) out += cStaff[pos[0]] + "p";
			
			// add all outputs of modifiers
			for(int k = 0; k < modifiers.size(); k++){
				mod = modifiers.get(k);				
				out += mod.getOutput();
			}
		}
				
		
		return out;
	}
	
}
