/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the Head function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import edu.brandeis.cs.cs131.pa1.filter.Message;

public class HeadFilter extends SequentialFilter {
	
	public static int parameter = 10;
	
	
	/**
	 * This method is main function in this class that process the pipe input and output the result to pipe or throw error messages.
	 */
	public void process() {
		if(input == null) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("head"));
			
		}else if(input.isEmpty()){
			return;
		}else {
				int counter = parameter;
				while(counter != 0) {
					String line = input.read();
					if(line == null) {
						counter = 0;
					}else {
						output.write(line);
						counter--;
					}
					
					
				}
			
			
			
		}
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
