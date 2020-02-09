import java.util.*;
public class ExDateHasAlreadyPassed extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExDateHasAlreadyPassed()
	{
		super("Date has already passed!");
	}
	public ExDateHasAlreadyPassed(String message)
	{
		super(message);
	}
}
