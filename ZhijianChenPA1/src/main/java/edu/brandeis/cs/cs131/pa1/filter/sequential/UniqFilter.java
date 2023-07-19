/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the uniq function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import edu.brandeis.cs.cs131.pa1.filter.Message;

public class UniqFilter extends SequentialFilter{

public static String parameter = new String("");
	


/**
 * This method is main function in this class that process the pipe input and output the result to pipe or throw error messages.
 */
	public void process() {
		List<String> lines = new ArrayList<>();
		if(input==null || input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("uniq"));
		}else {
			
			if(input.size()==1) {
				String line = input.read();
				output.write(line);
			}else {
				
				while(!input.isEmpty()) {
					String line = input.read();
					if(lines.size()==0) {
						lines.add(line);
					}else {
						if(!line.equals(lines.get(lines.size()-1)) ) {
							lines.add(line);
						}
						
					}
				}
			}
			for(int i = 0; i < lines.size();i++) {
				output.write(lines.get(i));
			}
			
	        
		}
		
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
