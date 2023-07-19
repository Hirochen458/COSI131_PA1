package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.brandeis.cs.cs131.pa1.filter.CurrentWorkingDirectory;
import edu.brandeis.cs.cs131.pa1.filter.Message;

/**
 * The main implementation of the REPL loop (read-eval-print loop). It reads
 * commands from the user, parses them, executes them and displays the result.
 */
public class SequentialREPL {

	/**
	 * The main method that will execute the REPL loop
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {

		Scanner consoleReader = new Scanner(System.in);
		System.out.print(Message.WELCOME);

		while (true) {
			System.out.print(Message.NEWCOMMAND);

			// read user command, if its just whitespace, skip to next command
			String cmd = consoleReader.nextLine();
			if (cmd.trim().isEmpty()) {
				continue;
			}

			// exit the REPL if user specifies it
			if (cmd.trim().equals("exit")) {
				break;
			}
			
			
			List<SequentialFilter> totalFilter;
			try {
				totalFilter = SequentialCommandBuilder.createFiltersFromCommand(cmd);
				if(totalFilter!=null && !totalFilter.isEmpty()) {
				for(int i = 0; i < totalFilter.size(); i++) {			
					totalFilter.get(i).process();
				}
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		System.out.print(Message.GOODBYE);
		consoleReader.close();

	}

}
