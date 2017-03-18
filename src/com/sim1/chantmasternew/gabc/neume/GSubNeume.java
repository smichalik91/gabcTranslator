package com.sim1.chantmasternew.gabc.neume;
import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GModifier;

public abstract class GSubNeume {
	
	public String out;
	public ArrayList<GModifier> modifiers;
	
	final String[] cStaffPos = new String[]{"B","A","0","1","2","3","4","5","6","7","8","9","Z"};
		
	public void setModifier(GModifier in){
		modifiers.add(in);
	}
	
	public abstract String getOutput();

}
