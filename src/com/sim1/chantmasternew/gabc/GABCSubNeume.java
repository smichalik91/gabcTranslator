package com.sim1.chantmasternew.gabc;
import java.util.ArrayList;


public class GABCSubNeume {

	private String in;
	private String out;
	public ArrayList<Integer> staffPos;

	final String[] cStaffPos = new String[]{"B","A","0","1","2","3","4","5","6","7","8","9","Z"};
	
	GABCSubNeume(String gabc_in)
	{
		
		in = gabc_in;
		out = "";
		staffPos = new ArrayList<>();
		String clefTest = isClef(in);		
		if(clefTest.equals("false")) // if the subneume is not a clef
		{
			int sp;
			int rh;
			int punctCount = 0;
			boolean beginStaffPos = false;
			char[] data = in.toCharArray();
			for(int k = 0; k < data.length; k++)
			{
				sp = staffPosToInt(data[k]);
				if(sp != -99) 
					{
					staffPos.add(sp);
					beginStaffPos = true;
					}
				if(isPunct(data[k]) && !beginStaffPos) punctCount++;
				
				rh = rhombus(data[k]);
				if(rh != -99)
				{
					out += cStaffPos[rh + 2];
					out += "n";
				}
				
				switch (data[k]) {
				case '/':
					out += "'";
					break;
				case ',':
					out += "+";
					break;
				case ';':
					out += ";";
					break;
				case ':':
					out += "|";
					break;
					
				}
			}
			
			// if only staff positions or punctuation and then staff positions
			if(staffPos.size() == in.length() || staffPos.size() + punctCount == in.length())
			{
				switch (staffPos.size()) {
				case 1:
					out = toCae1(staffPos.get(0));
					out += "-";
					break;
				case 2:
					out = toCae2(staffPos.get(0), staffPos.get(1));
					out += "-";
					break;
				case 3:
					out = toCae3(staffPos.get(0), staffPos.get(1), staffPos.get(2));
					out += "-";
					break;
				case 4:
					out = toCae4(staffPos.get(0), staffPos.get(1), staffPos.get(2), staffPos.get(3));
					out += "-";
					break;
				default:
					out = toCae5();
					out += "-";
					break;
				}
			}
		
		}
		else out = clefTest;
	}
	
	public String getOutput()
	{
		return out;
	}
	
	public String getInput()
	{
		return in;
	}
	
	private String isClef(String in)
	{
		String isClef = "false";
		switch (in) {
		case "c4":
			isClef = "7d";
			staffPos.add(7);
			break;
		case "c3":
			isClef = "5d";
			staffPos.add(5);
			break;
		case "c2":
			isClef = "3d";
			staffPos.add(3);
			break;
		case "c1":
			isClef = "1d";
			staffPos.add(1);
			break;
		case "f4":
			isClef = "7f";
			staffPos.add(7);
			break;
		case "f3":
			isClef = "5f";
			staffPos.add(5);
			break;
		case "cb3":
			isClef = "5d-4b";
			staffPos.add(5);
			staffPos.add(4);
			break;
		}
		return isClef;
	}
	
	private int staffPosToInt(char in)
	{
		int x = in;
		if(x > 96 && x < 110) x += -99;
		else x = -99;
		return x;
	}
	
	private int rhombus(char in)
	{
		int x = in;
		if(x > 64 && x < 78) x += -67;
		else x = -99;
		return x;
	}
	
	private boolean isPunct(char in)
	{
		boolean isPunct = false;
		switch (in) {
		case '/':
		case '!':
		case ' ':
			isPunct = true;
			break;
		}
		return isPunct;
	}
	
	private String toCae1(int pos1)
	{
		String s;
		
		s = punctum(pos1);
		
		return s;
	}
	
	private String toCae2(int pos1, int pos2)
	{
		String s = "";
		
		// if adjacent staff positions differ by more than 5, display as separated neumes
		if(Math.abs(pos1 - pos2) > 5)
		{
			s = punctum(pos1);
			s += "-";
			s += punctum(pos2);
		}
		else
		{
			//podatus
			if(pos1 < pos2)
			{
				s = podatus(pos1, pos2);
			}
			else //clivis
			{
				s = clivis(pos1, pos2);
			}
		}
		
		return s;
	}
	
	private String toCae3(int pos1, int pos2, int pos3)
	{
		String s = "";
		
		// if adjacent staff positions differ by more than 5, display as separated neumes
		if(Math.abs(pos1 - pos2) > 5 && Math.abs(pos2 - pos3) > 5)
		{
			s = punctum(pos3);
			s += "-";
			s += punctum(pos2);
			s += "-";
			s += punctum(pos3);
		}
		else if(Math.abs(pos1 - pos2) > 5)
		{
			s = punctum(pos1);
			s += "-";
			s += toCae2(pos2, pos3);
		}
		else if(Math.abs(pos2 - pos3) > 5)
		{
			s = toCae2(pos1, pos2);
			s += "-";
			s += punctum(pos3);
		}
		else
		{
			// up up = all connected
			// up dn = all connected
			// dn up = porrectus
			// dn dn = clivis - single
			int trans1 = pos2 - pos1;
			int trans2 = pos3 - pos2;
			if(trans1 > 0)
			{
				if(trans2 > 0)
				{
					s = punctum(pos1);
					s += connector(pos1, pos2);
					s += podatus(pos2, pos3);
				} else {
					s = punctum(pos1);
					s += connector(pos1, pos2);
					s += punctum(pos2);
					s += connector(pos2, pos3);
					s += punctum(pos3);
				}
			} else {
				if(trans2 > 0)
				{
					s = porrectus(pos1, pos2, pos3);				
				} else {
					s = clivis(pos1, pos2);
					s += "-";
					s += punctum(pos3);
				}
			}
			
		}
		
		return s;
	}
	
	private String toCae4(int pos1, int pos2, int pos3, int pos4)
	{
		String s = "";
		// up up up  cae3 - punctum
		// up up dn  cae3 - puntum
		// up dn up  mighty porrectus
		// up dn dn  cae3 - punctum
		// dn up up  porrectus - punctum
		// dn up dn  mighty porrectus/clivis
		// dn dn up  four punctum
		// dn dn dn  four punctum
		int trans1 = pos2 - pos1;
		int trans2 = pos3 - pos2;
		int trans3 = pos4 - pos3;
		
		if(trans1 > 0)
		{
			if(trans2 < 0 && trans3 > 0)
			{
				s = punctum(pos1);
				s += connector(pos1, pos2);
				s += porrectus(pos2, pos3, pos4);
			}
			else
			{
				s = toCae3(pos1, pos2, pos3);
				s += "-";
				s += punctum(pos4);
			}
		}
		else
		{
			if(trans2 > 0)
			{
				if(trans3 > 0)
				{
					s = porrectus(pos1, pos2, pos3);
					s += "-";
					s += punctum(pos4);
				}
				else
				{
					s = cStaffPos[pos1 - 1];
					s += cStaffPos[pos1 + 2];
					s += "X";
					s += cStaffPos[pos1 + 2];
					s += cStaffPos[pos2 + 2];
					s += "R";
					s += cStaffPos[pos2 + 2];
					s += cStaffPos[pos3 + 2];
					s += "X";
					s += clivis(pos3, pos4);
				}		
			}
			else
			{
				s = punctum(pos1);
				s += "-";
				s += punctum(pos2);
				s += "-";
				s += punctum(pos3);
				s += "-";
				s += punctum(pos4);
			}
		}
		
		return s;
	}
	
	private String toCae5()
	{
		String s = "";
		
		return s;
	}
	
	private String podatus(int pos1, int pos2)
	{
		String s = cStaffPos[pos1 + 2];
		s += cStaffPos[pos2 + 2];
		s += "P";
		return s;
	}
	
	private String connector(int pos1, int pos2)
	{
		String s = cStaffPos[pos1 + 2];
		s += cStaffPos[pos2 + 2];
		s += "X";
		return s;
	}
	
	private String porrectus(int pos1, int pos2, int pos3)
	{
		String s = cStaffPos[pos1 + 2];
		s += cStaffPos[pos2 + 2];
		s += "X";
		s += cStaffPos[pos1 + 2];
		s += cStaffPos[pos2 + 2];
		s += "R";
		s += cStaffPos[pos2 + 2];
		s += cStaffPos[pos3 + 2];
		s += "X";
		s += cStaffPos[pos3 + 2];
		s += "P";
		return s;
	}
	
	private String punctum(int pos1)
	{
		String s = cStaffPos[pos1 + 2];
		s += "p";
		return s;
	}
	
	private String clivis(int pos1, int pos2)
	{
		String s = cStaffPos[pos1 + 2];
		s += cStaffPos[pos2 + 2];
		s += "C";
		return s;
	}
}

