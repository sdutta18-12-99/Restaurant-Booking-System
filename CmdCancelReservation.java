import java.util.*;
import java.io.*;
public class CmdCancelReservation extends RecordedCommand
{
	BookingOffice instance=BookingOffice.getInstance();
	TableAllocation instance1=TableAllocation.getInstance();
	SystemDate instance2=SystemDate.getInstance();
	Day dayObj;
	int ticketNo;
	Reservation r;
	public void execute(String[] cmdParts)
	{
		try {
			if(cmdParts.length<3)
			{
				throw new ExInsufficientArguments();
			}
			dayObj=new Day(cmdParts[1]);
			ticketNo=Integer.parseInt(cmdParts[2]);
			r=instance.search(dayObj, ticketNo);
			if(r==null)
			{
				throw new ExBookingNotFound();
			}
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
			String tables=r.getStatus();
			if(tables.equals("Pending"))
			{
				instance.cancelReservation(r);
			}
			else
			{
				String[] set=tables.split(" ");
				for(int i=0;i<set.length;i++)
				{
					Table t=instance1.findTable(set[i]);
					if(t!=null)
					{
						t.removeBooking(dayObj, ticketNo);
					}
				}
				instance.cancelReservation(r);
			}
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		} catch (ExBookingNotFound e) {
			System.out.println(e.getMessage());
		}
		catch(ExInsufficientArguments e)
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
		String s=r.getStatus();
		if(s.equals("Pending"))
		{
			instance.addReservationspe(r);
		}
		else
		{
			instance.addReservationspe(r);
			Day obj=r.getDateDine();
			int ticket=r.getTicketNo();
			String[] set=s.split(" ");
			for(int i=0;i<set.length;i++)
			{
				Table t=instance1.findTable(set[i]);
				if(t!=null)
				{
					t.addBooking(obj, ticket);
				}
			}
		}
		addRedoCommand(this);
	}
	public void redoMe()
	{
		String tables=r.getStatus();
		if(tables.equals("Pending"))
		{
			instance.cancelReservation(r);
		}
		else
		{
			String[] set=tables.split(" ");
			for(int i=0;i<set.length;i++)
			{
				Table t=instance1.findTable(set[i]);
				if(t!=null)
				{
					t.removeBooking(dayObj, ticketNo);
				}
			}
			instance.cancelReservation(r);
		}
		addUndoCommand(this);
	}
}
