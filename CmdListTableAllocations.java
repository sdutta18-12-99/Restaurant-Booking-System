import java.util.*;
import java.io.*;
public class CmdListTableAllocations implements Command
{
	public void execute(String[] cmdParts)
	{
		TableAllocation instance=TableAllocation.getInstance();
		Day obj=new Day(cmdParts[1]);
		instance.print(obj);
	}
}
