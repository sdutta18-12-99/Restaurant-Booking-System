import java.util.*;
import java.io.*;
public class Reservation implements Comparable<Reservation>
{
	private String guestName;
	private String phoneNumber;
	private int totPersons;
	private Day dateDine;
	private Day dateRequest;
	private int ticketNo;
	private String status;
	public Reservation(String guestName,String phoneNumber,int totPersons,String sDateDine)
	{
		this.guestName=guestName;
		this.phoneNumber=phoneNumber;
		this.totPersons=totPersons;
		this.dateDine=new Day(sDateDine);
		this.dateRequest=SystemDate.getInstance().clone();
		this.ticketNo=BookingTicketController.takeTicket(this.dateDine);
		this.status="Pending";
	}
	public int getTicketNo()
	{
		return ticketNo;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status=status;
	}
	public int getTotalPersons()
	{
		return totPersons;
	}
	public Day getDateDine()
	{
		return dateDine;
	}
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	public String getName()
	{
		return guestName;
	}
	@Override
	public String toString()
	{
		return String.format("%-13s%-11s%-14s%-25s%4d       %s", this.guestName,this.phoneNumber,this.dateRequest,this.dateDine+String.format(" (Ticket %d)",this.ticketNo),this.totPersons,this.status);
	}
	public static String getListingHeader()
	{
		return String.format("%-13s%-11s%-14s%-25s%-11s%s","Guest Name","Phone","Request Date","Dining Date and Ticket","#Persons","Status");
	}
	@Override
	public int compareTo(Reservation another)
	{
		int result;
		if(this.guestName.equals(another.guestName))
		{
			result=0;
		}
		else if(this.guestName.compareTo(another.guestName)>0)
		{
			result=1;
		}
		else
		{
			result=-1;
		}
		if(result==0)
		{
			if(this.phoneNumber.equals(another.getPhoneNumber()))
			{
				result=0;
			}
			else if(this.phoneNumber.compareTo(another.getPhoneNumber())>0)
			{
				result=1;
			}
			else
			{
				result=-1;
			}
		}
		if(result==0)
		{
			if(this.dateDine.getYear()==another.dateDine.getYear()&&this.dateDine.getMonth()==another.dateDine.getMonth()&&this.dateDine.getDay()==another.dateDine.getDay())
			{
				result=0;
			}
			else if(this.dateDine.getYear()>another.dateDine.getYear())
			{
				result=1;
			}
			else if(this.dateDine.getYear()==another.dateDine.getYear()&&this.dateDine.getMonth()>another.dateDine.getMonth())
			{
				result=1;
			}
			else if(this.dateDine.getYear()==another.dateDine.getYear()&&this.dateDine.getMonth()==another.dateDine.getMonth()&&this.dateDine.getDay()>another.dateDine.getDay())
			{
				result=1;
			}
			else
			{
				result=-1;
			}
		}
		return result;
	}
}
