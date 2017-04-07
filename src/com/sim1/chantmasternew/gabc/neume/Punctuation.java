package com.sim1.chantmasternew.gabc.neume;

import java.util.ArrayList;

import com.sim1.chantmasternew.gabc.GSubNeume;
import com.sim1.chantmasternew.gabc.GSubNeume.Name;

public class Punctuation extends GSubNeume {
	
	public char input;
	
	public Punctuation(char in){
		input = in;
		name = Name.PUNCTUATION;
		modifiers = new ArrayList<>();
	}
	
	public String getOutput(){
		out = "";
		switch (input) {
		case ';':  // centered half vertical bar
			out = ";";
			break;
		case ':':  // full vertical bar
			out = "|";
			break;
		case ',':  // small bar at top of staff
			out = "+";
			break;
		case '/':  // quarter space
			out = "'";
			break;
		case ' ':  // half space
			out = "=";
			break;
		default:
			break;
		}
		return out;
	}

}
