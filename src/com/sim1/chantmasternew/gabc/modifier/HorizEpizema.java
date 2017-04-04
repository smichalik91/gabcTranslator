package com.sim1.chantmasternew.gabc.modifier;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

public class HorizEpizema extends GModifier{
	
	public boolean aboveNeume;

	public HorizEpizema(GSubNeume subNeumeToModify, int posInSubNeume){
		subNeume = subNeumeToModify;
		index = posInSubNeume;
		type = Type.MEXGROUP2;
		setPriority();
		replacePunctum = false;
		aboveNeume = true;
	}
	
	public void setAboveNeume(boolean in){
		aboveNeume = in;
		System.out.println("setAboveNeume");
	}
	
	
	public String getOutput(){
		String out = "";
		out += GSubNeume.cStaff[subNeume.pos[index]];
		if(aboveNeume) out += "H";
		else out += "h";
		return out;
	}
}