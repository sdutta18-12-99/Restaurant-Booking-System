import java.util.*;
import java.io.*;
public class TableAllocation
{
	private static ArrayList<Table> allTables;
	private static TableAllocation instance=new TableAllocation();
	private TableAllocation()
	{
		allTables=new ArrayList<>();
		allTables.add(new Table("T01",2));
		allTables.add(new Table("T02",2));
		allTables.add(new Table("T03",2));
		allTables.add(new Table("T04",2));
		allTables.add(new Table("T05",2));
		allTables.add(new Table("T06",2));
		allTables.add(new Table("T07",2));
		allTables.add(new Table("T08",2));
		allTables.add(new Table("T09",2));
		allTables.add(new Table("T10",2));
		allTables.add(new Table("F01",4));
		allTables.add(new Table("F02",4));
		allTables.add(new Table("F03",4));
		allTables.add(new Table("F04",4));
		allTables.add(new Table("F05",4));
		allTables.add(new Table("F06",4));
		allTables.add(new Table("H01",8));
		allTables.add(new Table("H02",8));
		allTables.add(new Table("H03",8));
	}
	public static TableAllocation getInstance()
	{
		return instance;
	}
	public Table findTable(String tableID)
	{
		for(Table t:allTables)
		{
			if(t.getTableID().contentEquals(tableID))
			{
				return t;
			}
		}
		return null;
	}
	public ArrayList<Table> getAvailableTables(Day obj)
	{
		ArrayList<Table> availTables=new ArrayList<>();
		for(Table t:allTables)
		{
			if(t.checkTableAlreadyAssigned(obj)==0)
			{
				availTables.add(t);
			}
		}
		return availTables;
	}
	public void print(Day obj)
	{
		System.out.println("Allocated tables: ");
		int c=0;
		for(Table t:allTables)
		{
			int s=t.printBooking(obj);
			if(s==1)
			{
				c=s;
			}
		}
		if(c==0)
		{
			System.out.println("[None]");
		}
		System.out.println("Available tables: ");
		String s="";
		for(Table t:allTables)
		{
			if(t.checkBooking(obj)==1)
			{
				s+=t.getTableID()+" ";
			}
		}
		s.trim();
		System.out.println(s);
		BookingOffice instance=BookingOffice.getInstance();
		int pending=instance.noOfPending(obj);
		int numOfPersons=instance.noOfPendingPersons(obj);
		System.out.println("Total number of pending requests = "+pending+" (Total  number of persons = "+numOfPersons+")");
	}
}
