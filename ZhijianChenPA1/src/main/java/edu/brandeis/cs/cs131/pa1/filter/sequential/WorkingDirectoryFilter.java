/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement pwd function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;
import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;
import edu.brandeis.cs.cs131.pa1.filter.Filter;
import edu.brandeis.cs.cs131.pa1.filter.Message;

public class WorkingDirectoryFilter extends SequentialFilter {
	
	public static boolean shouldPrint = true;
	
	public static String getDirectory() {
		return CurrentWorkingDirectory.get();
	}
	
	public static void setDirectory(String dir) {
		CurrentWorkingDirectory.setTo(dir);
	}
	
	
	
	/**
	 * This method is main function in this class that require current directory and output the result to pipe or throw error messages.
	 */
	public void process() {
		if (input != null && !input.isEmpty()) {
			while(!input.isEmpty()) {
				input.read();
			}
			
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("pwd"));
			input.read();
		}else {
			String directory = getDirectory();
			output.write(directory);
		}
		;
	}
			
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
