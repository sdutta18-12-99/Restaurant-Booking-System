import java.util.*;
import java.io.*;
public class Table
{
	private String tableID;
	private int size;
	private int ticketNo;
	private HashMap<Day,Integer> bookings=new HashMap<>();
	//private Day dateDine;
	private int status;
	public int addBooking(Day obj,int ticket)
	{
		Integer ticketNo=bookings.get(obj);
		if(ticketNo==null)
		{
			bookings.put(obj.clone(),ticket);
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public int checkTableAlreadyAssigned(Day obj)
	{
		Integer ticketNo=bookings.get(obj);
		if(ticketNo!=null)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public void removeBooking(Day obj,int ticket)
	{
		bookings.remove(obj);
	}
	public int checkBooking(Day obj)
	{
		Integer ticketNo=bookings.get(obj);
		if(ticketNo==null)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public int printBooking(Day obj)
	{
		int c=0;
		Integer ticketNo1=bookings.get(obj);
		if(ticketNo1!=null)
		{
			c+=1;
			System.out.println(tableID+" (Ticket "+ticketNo1+")");
		}
		return c;
	}
	public Table(String tableID,int size)
	{
		this.tableID=tableID;
		this.size=size;
		this.status=0;
	}
	public String getTableID()
	{
		return tableID;
	}
	public int getStatus()
	{
		return status;
	}
	public int getTicketNo()
	{
		return ticketNo;
	}
	/*public Day getDateDine()
	{
		return dateDine;
	}*/
	public int getSize()
	{
		return size;
	}
	public void setStatus(int n)
	{
		this.status=n;
	}
	public void setTicketNo(int n)
	{
		this.ticketNo=n;
	}
	/*public void setDateDine(Day obj)
	{
		this.dateDine=obj;
	}*/
}
