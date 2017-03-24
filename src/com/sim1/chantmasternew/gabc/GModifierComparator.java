package com.sim1.chantmasternew.gabc;

import java.util.Comparator;

public class GModifierComparator implements Comparator<Object> {

	@Override
	public int compare(Object mod1, Object mod2) {
		GModifier m1 = (GModifier)mod1;
		GModifier m2 = (GModifier)mod2;
		
		if(m1.index < m2.index) return -1;
		else if(m1.index > m2.index) return 1;
		else if(m1.priority < m2.priority) return -1;
		else if(m1.priority > m2.priority) return 1;
		else return 0;
	}
	
}
