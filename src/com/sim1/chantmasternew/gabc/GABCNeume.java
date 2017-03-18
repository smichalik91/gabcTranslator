package com.sim1.chantmasternew.gabc;
import java.util.ArrayList;


public class GABCNeume {

	public ArrayList<GABCSubNeume> subNeumes;
	public ArrayList<Integer> staffPos;
	public String in;
	public String out;
	public Test ref2;

	
	GABCNeume(String gabc_in)
	{
		in = gabc_in;
		out = "";
		staffPos = new ArrayList<>();
		subNeumes = gABCToSubNeumes(in);
		for(int k = 0; k < subNeumes.size(); k++)
		{
			//if(k > 0) out += "-"; // add a small space in between each subneume of a neume
			out += subNeumes.get(k).getOutput();
			
			for(int k2 = 0; k2 < subNeumes.get(k).staffPos.size(); k2++)
			{
				staffPos.add(subNeumes.get(k).staffPos.get(k2));
			}
			
		}
		out += "_"; //Add space after each neume

	}
	
	private ArrayList<GABCSubNeume> gABCToSubNeumes(String in)
	{
		ArrayList<GABCSubNeume> subNeumesTemp = new ArrayList<>();
		String[] subData = new String[in.length()];
		char[] data = in.toCharArray();
		char cur, prev = '!';
		boolean startNew = false;
		boolean doublePunctuation = false;
		int index = 0;
		int k = 0;
		while(k < data.length)
		{
			cur = data[k];
			if(cur == 'w') // escapes liquescents for now
			{
				k++;
				cur = data[k];
			}
			if(k > 0) prev = data[k - 1];
			if(!rhombus(cur) && staffPosToInt(cur) == -99) // if it's not a rhombus or a staffPos
			{
				switch (cur) {
				case '!':
				case '\'':
				case '/':
					if(prev == '!'||prev == '\''||prev == '/') doublePunctuation = true;
				case '.':
				case ' ':
				case 'v':
				case 'x':
					startNew = true;
					break;
				default:
					break;
				}

			} else if(rhombus(cur))
			{
				startNew = true;
				
			} else if(staffPosToInt(cur) == staffPosToInt(prev))
			{
				startNew = true;	
			}

			if(startNew)
			{
				index++;
				startNew = false;
				if(doublePunctuation)
				{
					index--;
					doublePunctuation = false;
				}
			}
			if(subData[index] != null) subData[index] += cur;
			else subData[index] = Character.toString(cur);
			k++;
		}

		Test temp4 = new Test("Dummy");
		
		//GABCSubNeumes[] subNeumes = new GABCSubNeumes[index + 1];
		for(k = 0; k < index + 1; k++)
		{
			subNeumesTemp.add(new GABCSubNeume(subData[k],temp4));
		}

		return subNeumesTemp;	
	}
	

	private int staffPosToInt(char in)
	{
		int x = in;
		if(x > 96 && x < 110) x += -99;
		else x = -99;
		return x;
	}

	private boolean rhombus(char in)
	{
		boolean rhombus;
		int x = in;
		if(x > 64 && x < 78) rhombus = true;
		else rhombus = false;
		return rhombus;
	}

	
}
