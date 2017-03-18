package com.sim1.chantmasternew.gabc;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.*;

import com.sim1.chantmasternew.gabc.neume.GSubNeume;
import com.sim1.chantmasternew.gabc.neume.Podatus;



public class Regex {
	
	public String header, chantDataString, chantName;
	
	
	
	public static void main(String args[]){
		boolean atWork = false;
		
		String inputFolderFilePath;
		if(atWork) inputFolderFilePath = "C:\\Users\\smichalik\\Documents\\Eclipse\\GABC Test\\in\\";
		else inputFolderFilePath = "C:\\Users\\Simon\\Documents\\Project 1 Chant Master\\GABC Test\\in\\";
				
		String outputFolderFilePath;
		if(atWork) outputFolderFilePath = "C:\\Users\\smichalik\\Documents\\Eclipse\\GABC Test\\out\\";
		else outputFolderFilePath = "C:\\Users\\Simon\\Documents\\Project 1 Chant Master\\GABC Test\\outCae\\";
		
		File folder = new File(inputFolderFilePath);
		File[] listOfFiles = folder.listFiles();
		Regex regex = new Regex();
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
		
		// Read in files
		for(int kk = 0; kk < fileNames.size(); kk++){
			String path = inputFolderFilePath + fileNames.get(kk);
			String contents = "";
			try {
				contents = new Scanner(new File(path)).useDelimiter("\\Z").next();

			} catch (IOException e){
				System.out.print("File not found.");	
			}
			System.out.print(contents);
			String source = contents;
			
			//String source = "name:Judica me;\noffice-part:Introitus;\nmode:4;\nbook:Graduale Romanum, 1961, p. 151;\ntranscriber:Andrew Hinkley;\n%%\n(c4) JU(egf)di(f)ca(e!f'g) me(g) *() De(ixgiH'G )us,(gf..) (;) et(f) dis(fffd)cér(fg~)ne(g) cau(jj)sam(h_g) me(gh/ji)am(ih) (,) de(jj) gen(hi~)te(g_f) non(e!f'g) sanc(egff)ta:(fe..) (:) ab(fgf) hó(e!fwg'!hv)mi(g)ne(g) i(g)ní(ixhg/hig)quo(gf..) (,) et(hj) do(j)ló(ji/jhi)so(g.) (,) é(ii)ri(h)pe(h!iwj/kjj) me:(ji..) (:) qui(gh)a(h) tu(h./ijh) es(h.) De(giH'G)us(gh) me(ff//efd)us,(ed..) (;) et(fd~) for(ef)ti(g)tú(ixg./hig)do(g_fhvGF'g) me(egff)a.(fe..) <i>Ps.</i>(::)  E(hg)mít(gh)te(h) lu(h)cem(h) tu(h)am,(h) et(h) ve(h)ri(hg)tá(gi)tem(i) tu(hi)am:(h.) *(:) ip(hg)sa(gh) me(h) de(h)du(h)xé(h)runt,(h.) (,) et(h) ad(h)du(h)xé(h)runt(h) in(h) mon(h)tem(h) sanc(h)tum(h) tu(h)um,(h.) (,) et(h) in(h) ta(h)ber(h)ná(h)cu(gf)la(gh) tu(g)a.(e.) (::) Jú(egf)di(f)ca.(e!f'g) (::)";
			
			//source = "name:Hostis Herodes;office-part:Hymnus;mode:3;book:Antiphonale Monasticum, 1934, p. 288;transcriber:MarkM;\n%%\n(c4)HOs(d)tis(e')He(f)ró(gh)des(d) ím(e!fwg)pi(fe)e,(e.) (;)Chris(g)tum(hj) ve(j)ní(ji)re(hg) quid(hi) ti(i)mes?(i.) (:)Non(h) é(hj!kv)ri(j)pit(ji) mor(hg)tá(hih)li(gf)a,(e!fw!gh.)(;)Qui(d) reg(e')na(f) dat(h) cæ(hih)lé(gfg)sti(fe)a.(e.) (::)I(d)bant(e') ma(f)gi,(gh) quam(d) vi(e!fwg)de(fe)rant,(e.) (;)Stel(g)lam(hj) se(j)quen(ji)tes(hg) prae(hi)vi(i)am :(i.) (:)Lu(h)men(hj!kv) re(j)qui(ji)runt(hg) lu(hih)mi(gf)ne :(e!fw!gh.) (;)De(d)um(e') fa(f)ten(h)tur(hih) mu(gfg)ne(fe)re.(e.) (::)La(d)va(e')cra(f) pu(gh)ri(d) gur(e!fwg)gi(fe)tis(e.) (;)Cae(g)le(hj)stis(j) A(ji)gnus(hg) at(hi)ti(i)git :(i.) (:)Pec(h)ca(hj!kv)ta,(j) quae(ji) non(hg) de(hih)tu(gf)lit,(e!fw!gh.) (;)Nos(d) a(e')blu(f)en(h)do(hih) sus(gfg)tu(fe)lit.(e.) (::)No(d)vum(e') ge(f)nus(gh) po(d)ten(e!fwg)ti(fe)ae :(e.) (;)A(g)quae(hj) ru(j)be(ji)scunt(hg) hy(hi)dri(i)ae,(i.) (:)Vi(h)num(hj!kv)que(j) jus(ji)sa(hg) fun(hih)de(gf)re,(e!fw!gh.) (;)Mu(d)ta(e')vit(f) un(h)da(hih) o(gfg)ri(fe)gi(e.)nem.(d) (::)Glo(d)ri(e')a(f) ti(gh)bi(d) Do(e!fwg)mi(fe)ne,(e.) Qu<i>i</i> ap(g)pa(hj)ru(j)i(ji)sti(hg) ho(hi)di(i)e,(i.) Cum(h) Pa(hj!kv) tr<i>e</i> et(j) San(ji)cto(hg) Spi(hih)ri(gf)tu,(e!fw!gh.) In(d) sem(e')pi(f)tér(h)na(hih) sæ(gfg)cu(fe)la.(e.) (::) A(efe) men.(de..) (::)";
			 
			regex.parseHeader(source);
			//System.out.println(test);
				
			String pattern = "([^(]*)([(])([^)]*)([)])";
	                                                                     
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(source);

			int count = 0;
			ArrayList<GABCNeume> neumes = new ArrayList<>();
			ArrayList<String> lyrics = new ArrayList<String>();
			
			while(m.find()){
				
				//System.out.println("Match Number: " + count);
				//System.out.println("Found: " + m.group(1));
				lyrics.add(m.group(1));
				if(m.group(3) != null && !m.group(3).equals("")) {
					//converter.divideIntoSubNeumes(m.group(3));
					neumes.add(new GABCNeume(m.group(3)));
				}
				else neumes.add(new GABCNeume("!"));
				count++;
			}
			
			for(int k = 0; k < lyrics.size(); k++)
			{
				System.out.print(lyrics.get(k));
			}
			System.out.println("");
			GABCNeume temp;
			for(int k = 0; k < neumes.size(); k++)
			{
				System.out.print(lyrics.get(k) + "(");
				temp = neumes.get(k);
				System.out.print(temp.in + ") (" + temp.out + ")");
				/*
				for(int k2 = 0; k2 < temp.subNeumes.size(); k2++)
				{
					System.out.print(temp.subNeumes.get(k2).getInput() + " ");
					System.out.print("(" + temp.subNeumes.get(k2).getOutput() + ") ");
				}
				*/
				System.out.println("");
			}
			
			String converted = "";
			for(int k = 0; k < neumes.size(); k++)
			{
				converted += neumes.get(k).out;
				//converted += "_";
				System.out.print(neumes.get(k).out);
			}
			
			String outputName = regex.chantName; //.substring(0, regex.chantName.length()-4);
			
			//Write file to "out" folder
			 BufferedWriter writer = null;
		        try {
		        	String filepath = outputFolderFilePath + outputName + ".txt";
		            //create a temporary file
		            //String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		            //File logFile = new File(timeLog);

		            // This will output the full path where the file will be written to...
		            //System.out.println(logFile.getCanonicalPath());

		            writer = new BufferedWriter(new FileWriter(filepath));
		            writer.write(converted);
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                // Close the writer regardless of what happens...
		                writer.close();
		            } catch (Exception e) {
		            }
		        }
			
		}
		
		GSubNeume g = new Podatus(7,9);
		System.out.println("");
		System.out.println("");
		System.out.println(g.getOutput());
		
		//ModifierManager.test();
	}
	
	 public void parseHeader(String rawScore) {
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
