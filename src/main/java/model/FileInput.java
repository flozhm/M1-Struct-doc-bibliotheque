package model;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInput {

	
	
   public static void main(String args[]) throws IOException {
	  
	   
	  boolean bName;
	  File repertoire = new File("./import");
	  File[] files=repertoire.listFiles();
	  System.out.println(files.length);
	  
	  for(int i = 0; i < files.length ; i++){ //On boucle sur les Set

		  String fileName = files[i].getName();
		  Pattern uName = Pattern.compile("[a-zA-Z0-9.+-]+.pdf");
	      Matcher mUname = uName.matcher(fileName);
	      bName = mUname.matches();
          if (bName) {
        	  System.out.println(mUname.group());
          }
	  }
	  
   }
   
  // public void 
}
