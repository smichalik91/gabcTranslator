package com.sim1.chantmasternew.gabc;
import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.neume.GSubNeume;


public abstract class GModifier {

	//indicates index of tone within GSubNeume to which this modifier is applied
	public int index;
	
	// Type or Grouping helps indicate which modifiers are mutual exclusive
	public int type;
	
	//indicates whether this GModifier's output should replace the notation of that being modified
	//if replacePunctum is false, simply add modifier output to notation of that being modified
	public boolean replacePunctum;
	
	
	final public String[] cStaffPos = new String[]{"B","A","0","1","2","3","4","5","6","7","8","9","Z"};
	
	//Mutually Exclusive Group 1
	public final static int MEXGROUP1 = 1;
	//Mutually Exclusive Group 2
	public final static int MEXGROUP2 = 2;
	//Simply add on end with no restrictions
	public final static int ADDON = 10;
	
	
	public abstract String getOutput();
	
	
	public static ArrayList<GModifier> sortModifiers(GSubNeume in){
		ArrayList<GModifier> list = new ArrayList<>();
		GModifier temp = null;
		for(int k = 0; k < in.modifiers.size(); k++){
			temp = in.modifiers.get(k);
			if(k == 0) temp.replacePunctum = true;
			list.add(temp);
		}
		//GModifier[] array = (GModifier[]) list.toArray();
		return list;
	}
}
