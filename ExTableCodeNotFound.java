import java.util.*;
import java.io.*;
public class ExTableCodeNotFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExTableCodeNotFound(String tableCode)
	{
		super("Table code "+tableCode+" not found!");
	}
}
