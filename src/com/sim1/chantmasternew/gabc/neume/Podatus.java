package com.sim1.chantmasternew.gabc.neume;

import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

// Neume with two positions: LOW then HIGH
public class Podatus extends GSubNeume {
	
	public Podatus(int staffpos1, int staffpos2){
		name = Name.PODATUS;
		modifiers = new ArrayList<>();
		pos = new int[2];
		pos[0] = staffpos1;
		pos[1] = staffpos2;
	}
	
public String getOutput(){
		if(modifiers.size() == 0) {
			if(pos[1] - pos[0] < 5) out = cStaff[pos[0]] + cStaff[pos[1]] + "P";
			else {
				out = cStaff[pos[0]] + "p";
				out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
				out += cStaff[pos[1]] + "P";
			}
		}
		else {
			// Get play order of GModifiers
			modifiers = GModifier.sortModifiers(this);
			
			out = "";
			int i = 0;
			GModifier mod = modifiers.get(i);
			
			// to fix wierd looking Podatus:
			boolean hasReplacingMods = false;
			for(int k = 0; k < modifiers.size(); k++) {
				if(modifiers.get(k).replacePunctum) hasReplacingMods = true;
			}
			if(!hasReplacingMods) {
				out += cStaff[pos[0]] + cStaff[pos[1]] + "P";
				for(int k = 0; k < modifiers.size(); k++) out += modifiers.get(k).getOutput();
			} else {
				// translate notation for first tone
				if(mod.index != 0 || !mod.replacePunctum) out += cStaff[pos[0]] + "p";
				while(i < modifiers.size() && mod.index == 0){
					mod = modifiers.get(i);
					out += mod.getOutput();
					i++;				
				}
				
				// translate notation for second tone
				if(mod.index != 1 || !mod.replacePunctum){
					if(pos[1] - pos[0] > 1) out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
					out += cStaff[pos[1]] + "P";
				}
				while(i < modifiers.size() && mod.index == 1){
					mod = modifiers.get(i);
					out += mod.getOutput();
					i++;
				}
			}
			
			
		}
		
		return out;
	}
}
