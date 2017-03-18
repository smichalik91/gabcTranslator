package com.sim1.chantmasternew.gabc.neume;

import java.util.ArrayList;

public class Clivis extends GSubNeume{

	public int pos1, pos2;
	
	public Clivis(int staffpos1, int staffpos2){
		super.modifiers = new ArrayList<>();
		pos1 = staffpos1;
		pos2 = staffpos2;
	}
	
public String getOutput(){		
		super.out = super.cStaffPos[pos1] + super.cStaffPos[pos2] + "C";
		return super.out;
	}
}

