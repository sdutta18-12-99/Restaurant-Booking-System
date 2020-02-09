import java.util.*;
import java.io.*;
public class ExNotEnoughTables extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExNotEnoughTables()
	{
		super("Not enough tables");
	}
	public ExNotEnoughTables(int totalPersons)
	{
		super("Suggestion for "+totalPersons+" persons: Not enough tables");
	}
} 
