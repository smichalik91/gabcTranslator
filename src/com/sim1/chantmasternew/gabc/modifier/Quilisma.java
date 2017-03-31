package com.sim1.chantmasternew.gabc.modifier;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

public class Quilisma extends GModifier {
	
	public Quilisma(GSubNeume subNeumeToModify, int posInSubNeume){
		subNeume = subNeumeToModify;
		index = posInSubNeume;
		type = Type.MEXGROUP1;
		setPriority();
		replacePunctum = true;
		subNeume.endSubNeumeHere = true;
	}
	
	
	public String getOutput(){
		String out = GSubNeume.cStaff[subNeume.pos[index]] + "q";
		return out;
	}

}