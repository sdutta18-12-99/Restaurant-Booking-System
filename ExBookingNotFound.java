import java.util.*;
import java.io.*;
public class ExBookingNotFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExBookingNotFound()
	{
		super("Booking not found!");
	}
	public ExBookingNotFound(String message)
	{
		super(message);
	}
}
