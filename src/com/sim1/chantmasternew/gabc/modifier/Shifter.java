package com.sim1.chantmasternew.gabc.modifier;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

public class Shifter extends GModifier{

	public Shifter(GSubNeume subNeumeToModify, int posInSubNeume){
		subNeume = subNeumeToModify;
		index = posInSubNeume;
		type = Type.SHIFTER;
		setPriority();
		replacePunctum = false;
	}
	
	
	public String getOutput(){
		String out = "";
		return out;
	}
}
