/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the tail function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.cs131.pa1.filter.Message;

public class TailFilter extends SequentialFilter{
	
	public static int parameter = 10;
	List<String> lines = new ArrayList<>();
	
	/**
	 * This method is main function in this class that process the pipe input and output the result to pipe or throw error messages..
	 */
	public void process() {
		if(input == null) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("tail"));
			
		}else if(input.isEmpty()){
			return;
		}else {
				int counter = parameter;
				while(!input.isEmpty()) {
					String line = input.read();
					lines.add(line);
				}
				if(lines.size()<10) {
					for(int i = 0; i < lines.size();i++) {
						output.write(lines.get(i));
					}
				}else if(lines.size()==10) {
					for(int i = 0; i < lines.size();i++) {
						output.write(lines.get(i));
					}
				}else {
					for(int i = (lines.size()-10); i < lines.size();i++) {
						output.write(lines.get(i));
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
