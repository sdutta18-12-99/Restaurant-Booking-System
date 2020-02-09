import java.util.*;
import java.io.*;
public class ExTableAlreadyReserved extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExTableAlreadyReserved(String tableCode)
	{
		super("Table "+tableCode+" is already reserved by another booking!");
	}
}
