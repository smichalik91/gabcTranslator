package com.sim1.chantmasternew.gabc;
import java.util.ArrayList;

public abstract class GSubNeume {
	
	public enum Name {
		EMPTY, PUNCTUATION, PUNCTUM, PODATUS, CLIVIS
	}
	
	public Name name;
	
	// staffPos of each tone
	public int[] pos;
	// output in Caeciliae
	public String out;
	// modifiers: mora, epizema, virgo, rhombus, etc.
	public ArrayList<GModifier> modifiers;
	
	// array for converting integer staff position 0-12 to those used in Caeciliae
	public static final String[] cStaff = new String[]{"B","A","0","1","2","3","4","5","6","7","8","9","Z"};
		
	public void addModifier(GModifier in){
		modifiers.add(in);
		System.out.println("Modifier added! - index: " + in.index + " Type: " + in.getClass().toString());
	}
	
	public abstract String getOutput();

}
