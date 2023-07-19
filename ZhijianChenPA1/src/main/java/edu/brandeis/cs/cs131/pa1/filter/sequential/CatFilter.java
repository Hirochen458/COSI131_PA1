

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import edu.brandeis.cs.cs131.pa1.filter.Message;

public class CatFilter extends SequentialFilter {
	
	public static String parameter = new String("");
	
	/**
	 * This method is main function in this class that access the given file and output the result to pipe or throw error messages..
	 */
	public void process(){

		if(input!= null && !input.isEmpty()) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("cat " + parameter));
			
			if(!input.isEmpty()) {
				input.read();
			}

		}else {
			String dir=WorkingDirectoryFilter.getDirectory();
			String newDir = dir+"/"+parameter;
			File files = new File(newDir);

				try {					
				    Scanner myReader = new Scanner(files);

				    while (myReader.hasNextLine()) {

				      String data = myReader.nextLine();

				      output.write(data);
				      
				    }
				      myReader.close();
				    } catch (FileNotFoundException e) {
				        System.out.print(Message.FILE_NOT_FOUND.with_parameter("cat " + parameter));
				        output.write("ERROR");
				        e.printStackTrace();
				    }
		}
	}

	@Override
	protected String processLine(String line) {

		return null;
	}
	
	
	/**
	 * This method is used to accept parameter that will be used.
	 * @param para parameter will be used
	 */
	public void setParameter(String para) {
		if(para == null) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(para));

			
		}else {
			parameter = para;
		}
	}
	
}
