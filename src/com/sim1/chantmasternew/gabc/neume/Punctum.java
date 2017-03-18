package com.sim1.chantmasternew.gabc.neume;
import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GModifier;


public class Punctum extends GSubNeume {
	
	//staff position of tone
	public int pos1;

	public Punctum(int staffpos1){
		modifiers = new ArrayList<>();
		pos1 = staffpos1;
	}
	
	public String getOutput(){
		
		if(modifiers.size() == 0) out = cStaffPos[pos1] + "p";
		else {
			// Get play order of GModifiers
			modifiers = GModifier.sortModifiers(this);
			
			out = "";
			GModifier mod;
			if(!modifiers.get(0).replacePunctum) out += cStaffPos[pos1] + "p";
			for(int k = 0; k < modifiers.size(); k++){
				mod = modifiers.get(k);				
				out += mod.getOutput();
			}
		}
				
		
		return out;
	}
	
}
