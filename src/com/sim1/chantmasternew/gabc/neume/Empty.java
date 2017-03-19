package com.sim1.chantmasternew.gabc.neume;
import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GModifier;
import com.sim1.chantmasternew.gabc.GSubNeume;

// Neume with no position
public class Empty extends GSubNeume {

	public Empty(){
		modifiers = new ArrayList<>();
		name = Name.EMPTY;
	}
	
	public String getOutput(){
		out = "";
		return out;
	}
	
}
