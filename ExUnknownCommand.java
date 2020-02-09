import java.util.*;
import java.io.*;
public class ExUnknownCommand extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExUnknownCommand()
	{
		super("Unknown command");
	}
	public ExUnknownCommand(String message)
	{
		super(message);
	}
}
