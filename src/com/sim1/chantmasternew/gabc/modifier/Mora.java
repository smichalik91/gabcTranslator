package com.sim1.chantmasternew.gabc.modifier;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

public class Mora extends GModifier{

	public Mora(GSubNeume subNeumeToModify, int posInSubNeume){
		subNeume = subNeumeToModify;
		index = posInSubNeume;
		type = Type.MEXGROUP2;
		replacePunctum = false;
	}
	
	
	public String getOutput(){
		String out = "";
		out += GSubNeume.cStaff[subNeume.pos[index]] + ".";
		return out;
	}
}
