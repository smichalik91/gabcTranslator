package com.sim1.chantmasternew.gabc;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

import com.sim1.chantmasternew.gabc.modifier.Clef;
import com.sim1.chantmasternew.gabc.modifier.Mora;
import com.sim1.chantmasternew.gabc.modifier.Shifter;
import com.sim1.chantmasternew.gabc.modifier.Virgo;
import com.sim1.chantmasternew.gabc.neume.Clivis;
import com.sim1.chantmasternew.gabc.neume.Empty;
import com.sim1.chantmasternew.gabc.neume.Podatus;
import com.sim1.chantmasternew.gabc.neume.Punctuation;
import com.sim1.chantmasternew.gabc.neume.Punctum;



public class GabcTranslator {
	
	public static String header, chantDataString, chantName;
	
	
	
	public static void main(String args[]){
		boolean atWork = false;
		
		String inputFolderFilePath;
		if(atWork) inputFolderFilePath = "C:\\Users\\smichalik\\Documents\\Eclipse\\gabcTranslator\\in\\";
		else inputFolderFilePath = "C:\\Users\\Simon\\Documents\\Project 1 Chant Master\\gabcTranslator\\in\\";
				
		String outputFolderFilePath;
		if(atWork) outputFolderFilePath = "C:\\Users\\smichalik\\Documents\\Eclipse\\gabcTranslator\\outCae\\";
		else outputFolderFilePath = "C:\\Users\\Simon\\Documents\\Project 1 Chant Master\\gabcTranslator\\outCae\\";
		
		String outputFullFolderFilePath;
		if(atWork) outputFullFolderFilePath = "C:\\Users\\smichalik\\Documents\\Eclipse\\gabcTranslator\\out\\";
		else outputFullFolderFilePath = "C:\\Users\\Simon\\Documents\\Project 1 Chant Master\\gabcTranslator\\out\\";
		
		File folder = new File(inputFolderFilePath);
		File[] listOfFiles = folder.listFiles();
		GabcTranslator regex = new GabcTranslator();
		GABCConverter converter = new GABCConverter();
		ArrayList<String> fileNames = new ArrayList();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		        fileNames.add(listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }
		    }
		
		for(int kk = 0; kk < fileNames.size(); kk++){
			// Read in files
			String path = inputFolderFilePath + fileNames.get(kk);
			String contents = "";
			try { contents = new Scanner(new File(path)).useDelimiter("\\Z").next();
			} catch (IOException e){
				System.out.print("File not found.");	
			}
			System.out.print(contents);
			String source = contents;
			
			parseHeader(source);
			//System.out.println(test);
				
			String pattern = "([^(]*)([(])([^)]*)([)])";
	                                                                     
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(source);

			int count = 0;
			ArrayList<GNeume> neumes = new ArrayList<>();
			ArrayList<String> lyrics = new ArrayList<String>();
			
			// build arraylists of lyrics and neumes
			while(m.find()){
				lyrics.add(m.group(1));
				if(m.group(3) != null && !m.group(3).equals("")) neumes.add(new GNeume(m.group(3)));
				else neumes.add(new GNeume("!"));
				count++;
			}
			
			// Print all lyrics
			for(int k = 0; k < lyrics.size(); k++)
			{
				System.out.print(lyrics.get(k));
			}
			System.out.println("");
			
			// Create SubNeumes
			GSubNeume sn;
			GModifier mod;
			for(int i = 0; i < neumes.size(); i++){
					System.out.println(neumes.get(i).input);
					sn = new Empty();
					mod = null;
					ArrayList<GModifier> tempMods = new ArrayList<GModifier>();
					char[] data = neumes.get(i).input.toCharArray();
					char cur, prev = '!';
					boolean couldBeClef = false;
					int k = 0;
					// assume the GSubNeume has been added. If adding modifier, recall from arraylist, modify, and put back
					while(k < data.length) {
						cur = data[k];
						if(k > 0) prev = data[k - 1];
						if(prev == 'c' || prev == 'f') couldBeClef = true;
						else couldBeClef = false;
						if(rhombus(cur) == -99 && staffPosToInt(cur) == -99) // if it's not a rhombus or a staffPos
						{
							switch (cur) {
							case '!':
								sn = new Empty();
								break;
							case ';':
							case '\'':
							case '/':
							case ':':
								sn = new Punctuation(cur);
								neumes.get(i).subNeumes.add(sn);
								sn = new Empty();
								break;
							case '.': // mora
								mod = new Mora(sn, sn.pos.length - 1);
								sn.addModifier(mod);
								GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
								break;
							case ' ':
								break;
							case 'v': // virgo
								mod = new Virgo(sn, sn.pos.length - 1);
								sn.addModifier(mod);
								GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
								break;
							case 'q': // virgo
								mod = new Shifter(sn, sn.pos.length - 1);
								sn.addModifier(mod);
								GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
								break;
							case 'w':  // quilisma
								//mod = new Virgo(sn, sn.pos.length - 1);
								//sn.setModifier(mod);
								//GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
								break;
							case 'x': // flat
								break;
							case 'o':
								break;
							case '<':
								break;
							case '~':
								break;
							case '>':  // pes
								//mod = new Virgo(sn, sn.pos.length - 1);
								//sn.setModifier(mod);
								//GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
								break;
							case 's':  // rhombus
								//mod = new Virgo(sn, sn.pos.length - 1);
								//sn.setModifier(mod);
								//GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
							case '1':
							case '2':
							case '3':
							case '4':
								if(couldBeClef) { // clef
									mod = new Clef(sn, sn.pos.length - 1, cur);
									sn.addModifier(mod);
									GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
								}
								break;
							default:
								break;
							}

						} else if(rhombus(cur) != -99) { // rhombus
							//mod = new Virgo(sn, sn.pos.length - 1);
							//sn.setModifier(mod);
							//GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
							
						} else {
							int pos = staffPosToInt(cur);
							if(sn.endSubNeumeHere){
								sn = new Punctuation('/');
								neumes.get(i).subNeumes.add(sn);
								neumes.get(i).subNeumes.add(sn);
							}
							switch (sn.name) {
							case PUNCTUATION:
							case EMPTY:
								sn = new Punctum(pos);
								neumes.get(i).subNeumes.add(sn);
								break;
							case PUNCTUM:
								if(pos > sn.pos[0]) {
									tempMods = sn.modifiers;
									sn = new Podatus(sn.pos[0], pos);
									sn.modifiers = tempMods;
									GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
								} else if(pos < sn.pos[0]) {
									tempMods = sn.modifiers;
									sn = new Clivis(sn.pos[0], pos);
									sn.modifiers = tempMods;
									GNeume.replaceLastInSubNeumes(neumes.get(i).subNeumes, sn);
								} else {
									// if staffPos same as prev, add spaces then punctum
									sn = new Punctuation('/');
									neumes.get(i).subNeumes.add(sn);
									neumes.get(i).subNeumes.add(sn);
									sn = new Punctum(pos);
									neumes.get(i).subNeumes.add(sn);
								}
								break;
							case CLIVIS:
								break;
							case PODATUS:
								break;
							default:
								break;
								
							}
						}
						k++;
					}			


			}
			
			// Get outputs from SubNeumes
			String str;
			for(int k = 0; k < neumes.size(); k++) {
				str = "";
				System.out.print(neumes.get(k).input);
				for(int k2 = 0; k2 < neumes.get(k).subNeumes.size(); k2++) str += neumes.get(k).subNeumes.get(k2).getOutput();
				neumes.get(k).output = "_" + str;
				System.out.print("(" + neumes.get(k).output + ")\n");
			}
					
			String converted = "";
			String convertedFull = ""; //header;
			//convertedFull += "\n%%\n";
			for(int k = 0; k < neumes.size(); k++){
				converted += neumes.get(k).output;
				converted += "_";
				System.out.print(neumes.get(k).output);
				
				convertedFull += lyrics.get(k) + "(";
				convertedFull += neumes.get(k).output.substring(1) + ")";
			}
			
			
						
			String outputName = regex.chantName; //.substring(0, regex.chantName.length()-4);
			
			//Write file to "outCae" folder
			 BufferedWriter writer = null;
			 BufferedWriter writer2 = null;
		        try {
		        	String filepath1 = outputFolderFilePath + outputName + ".txt";
		        	String filepath2 = outputFullFolderFilePath + outputName + ".txt";
		            //create a temporary file
		            //String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		            //File logFile = new File(timeLog);

		            // This will output the full path where the file will be written to...
		            //System.out.println(logFile.getCanonicalPath());

		            writer = new BufferedWriter(new FileWriter(filepath1));
		            writer.write(converted);
		            writer2 = new BufferedWriter(new FileWriter(filepath2));
		            writer2.write(convertedFull);
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                // Close the writer regardless of what happens...
		                writer.close();
		                writer2.close();
		            } catch (Exception e) {
		            }
		        }
		}
		
		// Test
		//System.out.print(header);
		
		String test = "";
		
	}
	
	private static int staffPosToInt(char in){
		int x = in;
		if(x > 96 && x < 110) x += -97;
		else x = -99;
		return x;
	}	
	
	private static int rhombus(char in){
		int x = in;
		if(x > 64 && x < 78) x += -65;
		else x = -99;
		return x;
	}
	
	
	public static void parseHeader(String rawScore) {
	        header = "";
	        chantDataString = "";
	        String pattern;
	        Pattern r;
	        Matcher m;

	        // Replace all CR/LF
	        rawScore = rawScore.replaceAll("\\n","");

	        //  Determine if there is a header in the file (AKA %%)
	        String[] splitString = rawScore.split("%{2}");
	        System.out.println("splitString.length = " + splitString.length);
	        switch (splitString.length) {
	            case 2:  // Contains header
	                header = splitString[0];
	                System.out.println("Header:  " + header);
	                pattern = "((?i)name:) *([^;]*);";
	                r = Pattern.compile(pattern);
	                m = r.matcher(header);
	                if (m.find()) chantName = m.group(2);
	                else System.out.println("No name found in header");
	                System.out.println("chantName:" + chantName);
	                chantDataString = splitString[1];
	                System.out.println("Body:  " + chantDataString);
	                break;
	            case 1:   // No header
	            	System.out.println("No Header Found");
	                chantDataString = rawScore;
	                chantName ="";
	                break;
	            case 0:  //  This should never happen
	            	System.out.println("splitString.length = 0 !!!");
	                break;
	            default:  //  Multiple headers found
	            	System.out.println("Error: Multiple headers found, ignore duplicates");
	                chantDataString = splitString[1];
	                chantName ="";
	                break;
	        }
	        
	    }
}
