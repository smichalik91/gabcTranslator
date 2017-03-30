package com.sim1.chantmasternew.gabc.neume;

import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

//Neume with two positions: HIGH then LOW
public class Clivis extends GSubNeume{

	public int pos1, pos2;
	
	public Clivis(int staffpos1, int staffpos2){
		name = Name.CLIVIS;
		modifiers = new ArrayList<>();
		pos = new int[2];
		pos[0] = staffpos1;
		pos[1] = staffpos2;
		containsShifter = new boolean[2];
	}
	
	public String getOutput(){
		
		// Notes: Shifters do nothing to a clivis.
		
// --------------------- With NO Modifiers -------------------------
		if(modifiers.size() == 0){
			if(pos[0] - pos[1] < 5) out = cStaff[pos[0]] + cStaff[pos[1]] + "C";
			else {
				out = cStaff[pos[0]] + cStaff[pos[1]] + "X";
				out += cStaff[pos[0]] + "p";
				out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
				out += cStaff[pos[1]] + "p";
			}
		}
		
// --------------------- WITH Modifiers -------------------------
		else {
			// Get play order of GModifiers
			GModifier.sortModifiers(this);
			
			out = "";
			int i = 0;
			GModifier mod = modifiers.get(i);
			
// --------------------- First Tone -------------------------
			// If the first modifier is assigned to tone #2 or is not a replacePunctum,
			// then add the punctum glyph now...
			if(mod.index != 0 || !mod.replacePunctum){
				out += cStaff[pos[0]] + cStaff[pos[1]] + "X"; 
				out += cStaff[pos[0]] + "p";
			}
			// then add all modifiers' outputs
			while(mod.index == 0){
				out += mod.getOutput();
				i++;
				if(i < modifiers.size()) mod = modifiers.get(i);
				else break;
			}
			
// --------------------- Second Tone -------------------------
			// if mod is not a replacePunctum, add the punctum now...
			if(mod.index != 1 || !mod.replacePunctum){
				out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
				out += cStaff[pos[1]] + "p";
			}
			// then add all modifiers' outputs
			while(mod.index == 1){
				out += mod.getOutput();
				i++;
				if(i < modifiers.size()) mod = modifiers.get(i);
				else break;
			}
			
		}
		
		return out;
	}
}

