/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the Caesar Cipher function.
 * Known bugs: None.
 */
package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.cs131.pa1.filter.Message;

public class CaesarCipherFilter extends SequentialFilter{
	
	List<String> timeParameter = new ArrayList<>();
	int times = -1;
	
	/**
	 * This method is main function in this class that process the pipe input and output the result to pipe.
	 */
	public void process() {
		List<String> newLines = new ArrayList<>();
		if(input == null || input.isEmpty()) {
			String parameterString = timeParameter.get(times);  
			System.out.print(Message.REQUIRES_INPUT.with_parameter("cc "+ parameterString));
		}else {
			while(!input.isEmpty()) {
				String line = input.read();
				String processedLine = processLine(line);
				if (processedLine != null) {
					newLines.add(processedLine);
				}
			}
			for(int i = 0; i < newLines.size();i++) {
				output.write(newLines.get(i));
			}
		}
	}
	
	/**
	 * This method is mainly implement how to do the Caeser Cipher to the input line.
	 * @param line line input that needed to by processed
	 * @return return the processed line
	 */
	@Override
	protected String processLine(String line) {
		int parameter = Integer.parseInt(timeParameter.get(times)); 
		String newLine = "";
		int position = 0;
		while(position<line.length()) {
			char character = line.charAt(position); 
			int ascii = (int) character;
			if(65<=ascii && ascii<=90) {
				if(ascii+parameter>90) {
					int remind = 90 - ascii;
					int forword = parameter - remind;
					ascii = 65 + forword - 1;
				}else {
					ascii+=parameter;
				}
			}else if(97<=ascii && ascii<=122) {
				if(ascii+parameter>122) {
					int remind = 122 - ascii;
					int forword = parameter - remind;
					ascii = 97 + forword - 1;
				}else {
					ascii+=parameter;
				}
			}
			char asciiToChar = (char) ascii;
			newLine+=Character.toString(asciiToChar);
			position++;
		}
		return newLine;
	}
	
	/**
	 * This method is used to accept the parameter from another class.
	 * @param para para that used to set the parameter.
	 */
	public void setParameter(String para) {
		timeParameter.add(para) ;
	}
	
	/**
	 * This class is used to track the times that doing the Caeser Cipher
	 */
	public void addTime() {
		times++;
	}

}
