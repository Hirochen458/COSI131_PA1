/**
 * Zhijian Chen
 * Chen5340@brandeis.edu
 * Feb 17th 2023
 * PA1
 * This class is used to implement how to divide the command the recognize the commands after split. \
 * Known bugs: None.
 */

package edu.brandeis.cs.cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import edu.brandeis.cs.cs131.pa1.filter.Filter;
import edu.brandeis.cs.cs131.pa1.filter.Message;

/**
 * This class manages the parsing and execution of a command. It splits the raw
 * input into separated subcommands, creates subcommand filters, and links them
 * into a list.
 */
public class SequentialCommandBuilder {

	public static List<SequentialFilter> createFiltersFromCommand(String command) throws Exception {
		List<SequentialFilter> sequFilter = new ArrayList<>();
		SequentialFilter filter;
		if(command.contains("|")) {
			String commands[] = command.split("\\|");
			if(commands[0].contains(">")) {
				String wrongCommands[] = commands[0].split(">");
				System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(">"+wrongCommands[1]));
				throw new Exception();
			}
			if(commands[commands.length-1].contains(">")) {
				for(int i=0; i<commands.length-1; i++) {
					
					try {
						filter = constructFilterFromSubCommand(commands[i]);
						if(filter != null) {
							sequFilter.add(filter);
						}
					} catch (Exception e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				
				}
				String last = commands[commands.length-1];
				String lastCommand[] = last.split(">");
				if(lastCommand.length != 1) {
					try {
						filter = constructFilterFromSubCommand(lastCommand[0]);
						if(filter != null) {
							sequFilter.add(filter);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
					try {
						filter = constructFilterFromSubCommand("> " + lastCommand[1]);
						if(filter != null) {
							sequFilter.add(filter);
						}
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}else {
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter(">"));
					throw new Exception();
				}
			}else {
				
				for(int i=0; i<commands.length; i++) {
				
					try {
						filter = constructFilterFromSubCommand(commands[i]);
						if(filter != null) {
							sequFilter.add(filter);
						}
					} catch (Exception e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				
				}
			PrintFilter print = new PrintFilter();
			sequFilter.add(print);
			
			}
			
			linkFilters(sequFilter);
		//without "|"
		}else {
			if(Character.toString(command.charAt(0)).equals(">")) {
				try {
					filter = constructFilterFromSubCommand(command);
					if(filter != null) {
						sequFilter.add(filter);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}else {
				if(command.contains(">")) {
					String original = command;
					String lastCommand[] = original.split(">");
					if(lastCommand.length != 1) {
						
						try {
							filter = constructFilterFromSubCommand(lastCommand[0]);
							if(filter != null) {
								sequFilter.add(filter);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
						
						try {
							String input = "> " + lastCommand[1];
							filter = constructFilterFromSubCommand(input);
							
							if(filter != null) {
								sequFilter.add(filter);
							}
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
					}else if(lastCommand.length == 1) {
						System.out.print(Message.REQUIRES_PARAMETER.with_parameter(">"));
						throw new Exception();
					}
					PrintFilter print = new PrintFilter();
					sequFilter.add(print);
					linkFilters(sequFilter);
					
					
				}else {
					try {
						filter = constructFilterFromSubCommand(command);
						if(filter != null) {
							sequFilter.add(filter);
							PrintFilter print = new PrintFilter();
							sequFilter.add(print);
							filter.setNextFilter(print);
							print.setPrevFilter(filter);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}
			}
			
			
		}

			
			
		return sequFilter;
	}
	
	/**
	 * This method is used to recognized the divided single command and return the instance of that filter.
	 * @param subCommand the command the needed to be recognized
	 * @return return the corresponded the filter instance.
	 * @throws Exception throw exception when there is error occurs.
	 */
	private static SequentialFilter constructFilterFromSubCommand(String subCommand) throws Exception {
		String subCommandCleanTotal = subCommand.trim();
		String subCommandClean = subCommand.stripLeading();
		if (subCommandCleanTotal.equals("head")){
			HeadFilter head = new HeadFilter();
			return head;
		}else if(subCommandCleanTotal.equals("tail")) {
			TailFilter tail = new TailFilter();
			return tail;
		}else if(subCommandCleanTotal.equals("wc")) {
			WordCountFilter wc = new WordCountFilter();
			return wc;
		}else if(subCommandCleanTotal.equals("pwd")) {
			WorkingDirectoryFilter pwd = new WorkingDirectoryFilter();
			return pwd;
			
		}else if(subCommandCleanTotal.equals("ls")) {
			ListFilter lS = new ListFilter();
			return lS;
			
		}else if(subCommandCleanTotal.equals("cd")) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cd"));
			throw new Exception();
		
		}else if(subCommandCleanTotal.equals("cat")) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cat"));
			throw new Exception();
		}else if(subCommandCleanTotal.equals("uniq")) {
			UniqFilter uniq = new UniqFilter();
			return uniq;
		}else if(subCommandCleanTotal.equals("grep")) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter("grep"));
			throw new Exception();
		}else if(subCommandCleanTotal.equals("cc")) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cc"));
			throw new Exception();
		
		
		
		
		
		}else if(!subCommandClean.contains(" ")) {
			System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(subCommandClean));
			throw new Exception();
		
		
			
		//To separate the sub-command with parameters
		}else {
			String[] subCommandsplited = subCommandClean.split(" ");
			
			if (subCommandsplited[0].equals("cd")){
				ChangeDirectoryFilter cd = new ChangeDirectoryFilter();
				cd.setParameter(subCommandsplited[1]);
				return cd;
				
			}else if (subCommandsplited[0].equals("cat")){
				CatFilter cat = new CatFilter();
				cat.setParameter(subCommandsplited[1]);
				return cat;
				
			}else if (subCommandsplited[0].equals("grep")){
				GrepFilter grep = new GrepFilter();
				grep.setParameter(subCommandsplited[1]);
				return grep;
			}else if (subCommandsplited[0].equals("cc")){
				CaesarCipherFilter cc = new CaesarCipherFilter();
				cc.setParameter(subCommandsplited[1]);;
				cc.addTime();
				return cc;
			}else if (subCommandsplited[0].equals(">")) {
				RedirectFilter rd = new RedirectFilter();
				if(subCommandsplited.length == 3) {
					rd.setParameter(subCommandsplited[2]);
				}else {
					rd.setParameter(subCommandsplited[1]);
				}
				
				return rd;
			}else if(subCommandsplited[0].equals("ls")) {
				ListFilter lS = new ListFilter();
				return lS;
			
			}else if(subCommandsplited[1].equals(">")) {
				String line = subCommandsplited[1];
				if(line.contains(" ")) {
					String reDir[] = line.split(" ");
					RedirectFilter rd = new RedirectFilter();
					rd.setParameter(reDir[1]);
				}else {
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter(">"));
					throw new Exception();
				}
					
			}else {				
				System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(subCommandClean));

				throw new Exception();
			}
		}
		return null;
			
		
			
		
	}
	
	/**
	 * This method used to link two separate filter instance.
	 * @param filters filters instance.
	 */
	private static void linkFilters(List<SequentialFilter> filters) {
		int f = 0;
		int s = 1;
		while(s<filters.size()) {
			filters.get(f).setNextFilter(filters.get(s));
			filters.get(s).setPrevFilter(filters.get(f));
			f++;
			s++;
		}
		
	}
}
