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
		case ';':
			out = ";";
			break;
		case ':':
			out = "|";
			break;
		case ',':
			out = "+";
			break;
		case '/':
			out = "-";
			break;
		}
		return out;
	}

}
