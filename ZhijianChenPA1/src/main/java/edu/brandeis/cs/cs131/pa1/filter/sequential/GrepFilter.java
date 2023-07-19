/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the Grep function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.cs131.pa1.filter.Message;

public class GrepFilter extends SequentialFilter {
	
	public static String parameter = new String("");
	
	boolean noOutPut = false;
	
 	
	@Override
	public void process() {
		List<String> lines = new ArrayList<>();
		if(input==null || input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("grep " + parameter));
		}else {
			while(!input.isEmpty()) {
				String line = input.read();
				String processedLine = processLine(line);
				if (processedLine != null && noOutPut == false) {
					output.write(processedLine);
				}
			}
		}
	}
	
	@Override
	protected String processLine(String line) {
		if(line.contains("ERROR")) {
			output.write(line);
			noOutPut = true;
			
			while(!input.isEmpty()) {
				input.read();
			}
		}
		if(noOutPut == false && line.contains(parameter)) {
			return line;
		}
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
