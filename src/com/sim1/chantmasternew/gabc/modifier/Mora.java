package com.sim1.chantmasternew.gabc.modifier;

import com.sim1.chantmasternew.gabc.GModifier;

public class Mora extends GModifier{

	Mora(int posInSubNeume){
		super.index = posInSubNeume;
		super.type = GModifier.MEXGROUP2;
		super.replacePunctum = false;
	}
	
	
	public String getOutput(){
		String out = "";
		if(super.replacePunctum) out = super.cStaffPos[super.index] + "p";
		out += super.cStaffPos[super.index] + ".";
		return out;
	}
}
