/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to print the final results in pipe into console or STDOUT.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

public class PrintFilter extends SequentialFilter {
	
	/**
	 * This method is main function in this class that print the final result to pipe or STUOUT.
	 */
	public void process() {
		while (input!=null && !input.isEmpty()) {
			String line = input.read();
			if(!line.contains("error")&&!line.contains("ERROR")) {
				System.out.println(line);
			}
			
		}
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
