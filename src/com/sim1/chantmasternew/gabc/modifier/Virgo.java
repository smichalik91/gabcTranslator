package com.sim1.chantmasternew.gabc.modifier;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

public class Virgo extends GModifier {
	
	public Virgo(GSubNeume subNeumeToModify, int posInSubNeume){
		subNeume = subNeumeToModify;
		index = posInSubNeume;
		type = Type.MEXGROUP1;
		super.setPriority();
		replacePunctum = true;
		subNeume.endSubNeumeHere = true;
	}
	
	
	public String getOutput(){
		String out = GSubNeume.cStaff[subNeume.pos[index]] + "v";
		return out;
	}

}
