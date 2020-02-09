import java.util.*;
public class ExBookingSamePerson extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExBookingSamePerson()
	{
		super("Booking by the same person for the dining date already exists!");
	}
	public ExBookingSamePerson(String message)
	{
		super(message);
	}
}
