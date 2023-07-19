/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the ls function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;
import edu.brandeis.cs.cs131.pa1.filter.Message;

import java.io.File;


public class ListFilter extends SequentialFilter {
	
	
	
	/**
	 * This method is main function in this class that require current directory and output the result to pipe or throw error messages..
	 */
	public void process() {
		if (input != null && !input.isEmpty()) {
			while(!input.isEmpty()) {
				input.read();
			}
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("ls"));
			input.read();
		}else {
		String currentDir = CurrentWorkingDirectory.get();
		File temp = new File(currentDir);
		if (temp.exists() && temp.isDirectory()) {
			String[] files = temp.list();
			for(int i = 0; i < files.length;i++) {
				output.write(files[i]);
			}
		}else {
			Message.FILE_NOT_FOUND.with_parameter(currentDir);
		}
		}
		
		
	}

	@Override
	protected String processLine(String line) {
		
		return null;
	}

}
