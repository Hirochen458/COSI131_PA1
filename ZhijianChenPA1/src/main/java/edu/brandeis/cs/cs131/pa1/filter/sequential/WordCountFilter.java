/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement the wc function.
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.cs131.pa1.filter.Message;

public class WordCountFilter extends SequentialFilter{
	
	
	@Override
	public void process() {
		
		int sentence = 0;
		int word = 0;
		int characters = 0;
		boolean noOutPut = false;
		
		if(input == null) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("wc"));
		}else if(input.isEmpty()){
			output.write("0 0 0");
		}else {
			while(!input.isEmpty()) {
				String line = input.read();
				if(line.contains("error")) {
					noOutPut = true;
					String errorMessage[] = line.split("/");
					System.out.print(errorMessage[1]);
					while(!input.isEmpty()) {
						input.read();
					}
				}
				if(line.contains("ERROR")) {
					output.write(line);
					noOutPut = true;
					
					while(!input.isEmpty()) {
						input.read();
					}
				}
				sentence +=1;
				characters +=line.length();
				String subLine[] = line.split(" ");
				word+=subLine.length;
			}
			
			String result = "";
			result+=String.valueOf(sentence);
			result+=" ";
			result+=String.valueOf(word);
			result+=" ";
			result+=String.valueOf(characters);
			if(noOutPut==false) {
				output.write(result);
			}
			
			
		}
		
	}
	
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
