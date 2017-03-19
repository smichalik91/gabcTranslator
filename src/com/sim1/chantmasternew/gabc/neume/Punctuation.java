package com.sim1.chantmasternew.gabc.neume;

import com.sim1.chantmasternew.gabc.GSubNeume;

public class Punctuation extends GSubNeume {
	
	public char input;
	
	public Punctuation(char in){
		input = in;
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
		}
		return out;
	}

}
