import java.util.*;
import java.io.*;
public class CmdRequest extends RecordedCommand
{
	Reservation r,r1;
	BookingOffice instance=BookingOffice.getInstance();
	SystemDate instance1=SystemDate.getInstance();
	public void execute(String[] cmdParts)
	{
		try {
			if(cmdParts.length<5)
			{
				throw new ExInsufficientArguments();
			}
			//r=new Reservation(cmdParts[1], cmdParts[2], Integer.parseInt(cmdParts[3]), cmdParts[4]);
			Day obj=new Day(cmdParts[4]);
			if(instance1.getYear()>obj.getYear())
			{
				throw new ExDateHasAlreadyPassed();
			}
			if(instance1.getMonth()>obj.getMonth()&&instance1.getYear()==obj.getYear())
			{
				throw new ExDateHasAlreadyPassed();
			}
			if(instance1.getDay()>obj.getDay()&&instance1.getMonth()==obj.getMonth()&&instance1.getYear()==obj.getYear())
			{
				throw new ExDateHasAlreadyPassed();
			}
			if(instance.check(cmdParts[1],obj)==1)
			{
				throw new ExBookingSamePerson();
			}
			r=instance.addReservation(cmdParts[1], cmdParts[2], Integer.parseInt(cmdParts[3]), cmdParts[4]);
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done. Ticket code for "+cmdParts[4]+": "+r.getTicketNo());
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		}
		catch(ExInsufficientArguments e)
		{
			System.out.println(e.getMessage());
		}
		catch(ExBookingSamePerson e)
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
		instance.removeReservation(r);
		addRedoCommand(this);
	}
	@Override
	public void redoMe()
	{
		instance.addReservation(r);
		addUndoCommand(this);
	}
}
