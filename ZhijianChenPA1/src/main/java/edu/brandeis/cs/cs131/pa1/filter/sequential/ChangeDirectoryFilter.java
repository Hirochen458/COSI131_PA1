/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the Cd function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.io.File;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;
import edu.brandeis.cs.cs131.pa1.filter.Message;


public class ChangeDirectoryFilter extends SequentialFilter{
	
	public static String parameter = new String("");
	
	/**
	 * This method is main function in this class that update the current working directory based on command or throw error messages.
	 */
	public void process() {
		
		
		String currentDir = WorkingDirectoryFilter.getDirectory();
		
		if(input!= null && !input.isEmpty()) {
			
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("cd " + parameter));
			
		}else {
			File file = new File(currentDir+CurrentWorkingDirectory.getPathSeparator()+parameter);
			
			if (!file.exists()) {
				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter("cd " + parameter));
			}else {
				if (parameter.equals(".")) {
					WorkingDirectoryFilter.setDirectory(currentDir);
				}else if(parameter.equals("..")) {
					String separater = CurrentWorkingDirectory.getPathSeparator();
					String newDir[] = currentDir.split(separater);
					String newDirectory = "";
					for (int i = 0; i<newDir.length-1;i++) {
						if(i != 0) {
							newDirectory += separater;
						}
						
						newDirectory += newDir[i];
					}
					WorkingDirectoryFilter.setDirectory(newDirectory);
					
				}else {
					String separater = CurrentWorkingDirectory.getPathSeparator();
					WorkingDirectoryFilter.setDirectory(currentDir+separater+parameter);
					
					output.write("error/"+Message.CANNOT_HAVE_OUTPUT.with_parameter("cd "+parameter));
				}
				
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
