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
	}
	
	public String getOutput(){
		if(modifiers.size() == 0){
			if(pos[0] - pos[1] < 5) out = cStaff[pos[0]] + cStaff[pos[1]] + "C";
			else {
				out = cStaff[pos[0]] + cStaff[pos[1]] + "X";
				out += cStaff[pos[0]] + "p";
				out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
				out += cStaff[pos[1]] + "p";
			}
		}
		else {
			// Get play order of GModifiers
			modifiers = GModifier.sortModifiers(this);
			
			out = "";
			int i = 0;
			GModifier mod = modifiers.get(i);
			
			// translate notation for first tone
			if(mod.index != 0 || !mod.replacePunctum){
				out += cStaff[pos[0]] + cStaff[pos[1]] + "X"; 
				out += cStaff[pos[0]] + "p";
			}
			while(i < modifiers.size() && (mod.index == 0)){
				mod = modifiers.get(i);
				out += mod.getOutput();
				i++;				
			}
			
			// translate notation for second tone
			if(mod.index != 1 || !mod.replacePunctum){
				out += cStaff[pos[0]] + cStaff[pos[1]] + "X";
				out += cStaff[pos[1]] + "p";
			}
			while(i < modifiers.size() && mod.index == 1){
				mod = modifiers.get(i);
				out += mod.getOutput();
				i++;				
			}
			
		}
		
		return out;
	}
}

