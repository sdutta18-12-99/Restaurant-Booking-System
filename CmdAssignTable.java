import java.util.*;
import java.io.*;
public class CmdAssignTable extends RecordedCommand
{
	TableAllocation instance=TableAllocation.getInstance();
	BookingOffice instance1=BookingOffice.getInstance();
	SystemDate instance2=SystemDate.getInstance();
	Reservation r;
	Day dayObj;
	int ticketNo;
	String s;
	String[] s1;
	public void execute(String[] cmdParts)
	{
		try {
			s1=cmdParts;
			dayObj=new Day(cmdParts[1]);
			ticketNo=Integer.parseInt(cmdParts[2]);
			if(instance2.getYear()>dayObj.getYear())
			{
				throw new ExDateHasAlreadyPassed();
			}
			if(instance2.getMonth()>dayObj.getMonth()&&instance2.getYear()==dayObj.getYear())
			{
				throw new ExDateHasAlreadyPassed();
			}
			if(instance2.getDay()>dayObj.getDay()&&instance2.getMonth()==dayObj.getMonth()&&instance2.getYear()==dayObj.getYear())
			{
				throw new ExDateHasAlreadyPassed();
			}
			r=instance1.search(dayObj,ticketNo);
			if(cmdParts.length<4)
			{
				throw new ExInsufficientArguments();
			}
			if(r==null)
			{
				throw new ExBookingNotFound();
			}
			if(!r.getStatus().equals("Pending"))
			{
				throw new ExTablesAlreadyAssigned();
			}
			for(int i=3;i<cmdParts.length;i++)
			{
				Table t2=instance.findTable(cmdParts[i]);
				if(t2!=null)
				{
					if(t2.checkTableAlreadyAssigned(dayObj)==1)
						{
							throw new ExTableAlreadyReserved(cmdParts[i]);
						}
				}
			}
			int sum=0;
			for(int i=3;i<cmdParts.length;i++)
			{
				Table t1=instance.findTable(cmdParts[i]);
				if(t1==null)
				{
					throw new ExTableCodeNotFound(cmdParts[i]);
				}
				else
				{
					sum+=t1.getSize();
				}
			}
			Reservation r1=instance1.search(dayObj,ticketNo);
			if(r1!=null&&sum<r1.getTotalPersons())
			{
				throw new ExNotEnoughSeats();
			}
			s="Table assigned: ";
			for(int i=3;i<cmdParts.length;i++)
			{
				Table t=instance.findTable(cmdParts[i]);
				t.setStatus(1);
				t.setTicketNo(r.getTicketNo());
				//t.setDateDine(r.getDateDine());
				t.addBooking(dayObj, ticketNo);
				s+=cmdParts[i]+" ";
			}
			s.trim();
			r.setStatus(s);
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		} catch (ExBookingNotFound e) {
			System.out.println(e.getMessage());
		}
		catch(ExTablesAlreadyAssigned e)
		{
			System.out.println(e.getMessage());
		}
		catch(ExInsufficientArguments e)
		{
			System.out.println(e.getMessage());
		}
		catch(ExTableCodeNotFound e)
		{
			System.out.println(e.getMessage());
		}
		catch(ExNotEnoughSeats e)
		{
			System.out.println(e.getMessage());
		}
		catch(ExTableAlreadyReserved e)
		{
			System.out.println(e.getMessage());
		}
		catch(ExDateHasAlreadyPassed e)
		{
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void undoMe()
	{
		String[] tables=r.getStatus().split(" ");
		for(int i=0;i<tables.length;i++)
		{
			Table t=instance.findTable(tables[i]);
			if(t!=null)
			{
			t.removeBooking(dayObj, ticketNo);
			}
		}
		r.setStatus("Pending");
		addRedoCommand(this);
	}
	@Override
	public void redoMe()
	{
		for(int i=3;i<s1.length;i++)
		{
			Table t=instance.findTable(s1[i]);
			t.setStatus(1);
			t.setTicketNo(r.getTicketNo());
			//t.setDateDine(r.getDateDine());
			t.addBooking(dayObj, ticketNo);
		}
		r.setStatus(s);
		addUndoCommand(this);
	}
}
