import java.util.*;
import java.io.*;
public class ExNotEnoughSeats extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExNotEnoughSeats()
	{
		super("Not enough seats for the booking!");
	}
	public ExNotEnoughSeats(String message)
	{
		super(message);
	}
}
