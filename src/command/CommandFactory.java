package command;

import java.io.FileInputStream;
import java.util.Properties;

import context.RequestContext;

public abstract class CommandFactory {

	public static AbstractCommand getCommand(RequestContext rc){

		AbstractCommand command = null;
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("D:/Lessons/JE22/workspace2/J2EE-4/src/commands.properties"));
			String name = prop.getProperty(rc.getCommandPath());
			Class c = Class.forName(name);
			command = (AbstractCommand) c.newInstance();
		}
		catch(Exception e){
			e.getMessage();
		}
		return command;
	}
}
