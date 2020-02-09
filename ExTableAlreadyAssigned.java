import java.util.*;
import java.io.*;
public class ExTablesAlreadyAssigned extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExTablesAlreadyAssigned()
	{
		super("Table(s) already assigned for this booking!");
	}
	public ExTablesAlreadyAssigned(String message)
	{
		super(message);
	}
}
