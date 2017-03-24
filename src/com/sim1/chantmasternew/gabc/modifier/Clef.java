package com.sim1.chantmasternew.gabc.modifier;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

public class Clef extends GModifier {
	
	public char clefNum;
	
	public Clef(GSubNeume subNeumeToModify, int posInSubNeume, char clefNumber){
		subNeume = subNeumeToModify;
		index = posInSubNeume;
		type = Type.MEXGROUP1;
		super.setPriority();
		replacePunctum = true;
		clefNum = clefNumber;
	}
	
	
	public String getOutput(){
		String out = "";
		System.out.print("Is Clef, clefNum = " + clefNum + ", subNeume.pos[0] = " + subNeume.pos[0]);
		//  Is it a C-clef?
		if(subNeume.pos[0] == 2) {
			switch (clefNum) {
			case '1':
				out = "1d";
				break;
			case '2':
				out = "3d";
				break;
			case '3':
				out = "5d";
				break;
			case '4':
				out = "7d";
				break;
			default:
				out = "7d";
				break;				
			}
		} else if(subNeume.pos[0] == 5) {
			switch (clefNum) {
			case '2':
				out = "3f";
				break;
			case '3':
				out = "5f";
				break;
			case '4':
				out = "7f";
				break;
			default:
				out = "7f";
				break;				
			}
		}
		return out;
	}

}