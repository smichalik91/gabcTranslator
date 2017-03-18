package com.sim1.chantmasternew.gabc;


import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.*;

public class WordMaster {

	WordMaster()
    {

    }

    public static int getIndexOfVowel(String in)
    {
        in = removeDiacriticalMarks(in);
        System.out.println(in);
    	String pattern = "(?i)[aeiouæœÆŒ]";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(in);

        int index = 0;

        if(m.find()) index = m.start();
        else
        {
            pattern = "[y]";
            r = Pattern.compile(pattern);
            m = r.matcher(in);
            if(m.find()) index = m.start();
            else System.out.println("No Vowel found");
        }

        return index;
    }
    
    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
