package com.sim1.chantmasternew.gabc.modifier;

import com.sim1.chantmasternew.gabc.GModifier;

public class Virgo extends GModifier {
	
	Virgo(int posInSubNeume){
		super.index = posInSubNeume;
		super.type = GModifier.MEXGROUP1;
		super.replacePunctum = true;
	}
	
	
	public String getOutput(){
		String out = super.cStaffPos[super.index] + "v";
		return out;
	}
}
