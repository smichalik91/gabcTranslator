package com.sim1.chantmasternew.gabc.neume;

import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GModifier.Type;
import com.sim1.chantmasternew.gabc.GSubNeume;

// Neume with two positions: LOW then HIGH
public class Podatus extends GSubNeume {
	
	public Podatus(int staffpos1, int staffpos2){
		name = Name.PODATUS;
		modifiers = new ArrayList<>();
		pos = new int[2];
		pos[0] = staffpos1;
		pos[1] = staffpos2;
		containsShifter = new boolean[2];
	}
	
public String getOutput(){
		// With no modifiers
		if(modifiers.size() == 0) {
			if(pos[1] - pos[0] < 5) out = cStaff[pos[0]] + cStaff[pos[1]] + "P";
			else {
				out = cStaff[pos[0]] + "p";
				out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
				out += cStaff[pos[1]] + "P";
			}
		}
		
		// With modifiers
		else {
			// Get play order of GModifiers
			GModifier.sortModifiers(this);
			
			out = "";
			int i = 0;
			GModifier mod = modifiers.get(i);
			System.out.println("Podatus: containsShifter? " + containsShifter[0]);
			
			// to fix weird looking Podatus:
			// if none of the modifiers are 'replacePunctum' or SHIFTERs then follow the patter:
			// "[staffPos1][staffPos2]P" plus outputs of modifiers
			boolean hasReplacingMods = false;
			if(containsShifter[0]) hasReplacingMods = true;
			else for(int k = 0; k < modifiers.size(); k++) {
				if(modifiers.get(k).replacePunctum) hasReplacingMods = true;
			}
			if(!hasReplacingMods) {
				System.out.println("!hasReplacingMods");
				out += cStaff[pos[0]] + cStaff[pos[1]] + "P";
				for(int k = 0; k < modifiers.size(); k++) out += modifiers.get(k).getOutput();
			} 
			
			// if there is a modifier that is a SHIFTER or 'replacePunctum'
			else {
				// First Tone:
				// If the first modifier is assigned to tone #2 or is not a replacePunctum,
				// then add the "[staffPos]p" now, then add all modifiers' outputs
				if(mod.index != 0 || !mod.replacePunctum) out += cStaff[pos[0]] + "p";
				while(mod.index == 0){
					out += mod.getOutput();
					i++;
					if(i < modifiers.size()) mod = modifiers.get(i);
					else break;
				}
				
				// Second Tone:
				// if there are no modifiers on the second tone.
				//  add the "...p" and be done
				if(mod.index == 0 || mod.index > 1) {
					if(pos[1] - pos[0] > 1) out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
					out += cStaff[pos[1]];
					if(containsShifter[0]) out += "p";
					else out += "P";
				}
				// if there are modifiers on the second tone
				else {
					if(!mod.replacePunctum) {
						if(pos[1] - pos[0] > 1) out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
						out += cStaff[pos[1]];
						if(containsShifter[0]) out += "p";
						else out += "P";
					} else out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
					while(mod.index == 1){
						out += mod.getOutput();
						i++;
						if(i < modifiers.size()) mod = modifiers.get(i);
						else break;
					}
					
				}

			}		
			
		}
		
		return out;
	}
}
