/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the ">" function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.brandeis.cs.cs131.pa1.filter.Message;

public class RedirectFilter extends SequentialFilter {
	
	public static String parameter = new String("");
	
	/**
	 * This method is main function in this class that process the pipe input and output the result to a new txt file or throw error messages.
	 */
	public void process() {
		if(input == null || input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("> " + parameter));
		}else {
			String dir=WorkingDirectoryFilter.getDirectory();
			String newDir = dir+"/"+parameter;
			File files = new File(newDir);
				try {
					File myfile = new File(newDir);
					if(myfile.exists()) {
						myfile.delete();
					}
					myfile.createNewFile();
					FileWriter fw = new FileWriter(myfile);
			    	while(!input.isEmpty()) {
			    		fw.write(input.read()+"\n"); 
			    	}
			        fw.close();
//				    if (myfile.createNewFile()) {
//					if (!myfile.exists()) {
//				    	FileWriter fw = new FileWriter(myfile);
//				    	while(!input.isEmpty()) {
//				    		fw.write(input.read()); 
//				       }
//				       fw.close();
//				    } else {
//				        
//				    }
//				      myfile.close();
				    } catch (IOException e) {
				        System.out.print(Message.FILE_NOT_FOUND.with_parameter("cat " + parameter));
				        e.printStackTrace();
				    }
			//}
			String error = Message.CANNOT_HAVE_OUTPUT.with_parameter("> "+parameter);
			input.write("error"+error);
			
		}
		
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method is used to accept the parameter from another class.
	 * @param para para that will be used.
	 */
	public void setParameter(String para) {
//		System.out.println(para);
		if(para.isEmpty()) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(para));
			
		}else {
			parameter = para;
		}
	}
	
	

}
