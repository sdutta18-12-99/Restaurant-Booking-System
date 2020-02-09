import java.util.*;
import java.io.*;
public class CmdSuggestTable implements Command
{
	BookingOffice instance=BookingOffice.getInstance();
	TableAllocation instance1=TableAllocation.getInstance();
	public void execute(String[] cmdParts)
	{
		try {
			if(cmdParts.length<3)
			{
				throw new ExInsufficientArguments();
			}
			Day dayObj=new Day(cmdParts[1]);
			int ticketNo=Integer.parseInt(cmdParts[2]);
			Reservation r=instance.search(dayObj,ticketNo);
			if(r==null)
			{
				throw new ExBookingNotFound();
			}
			int totPersons=r.getTotalPersons();
			String status=r.getStatus();
			if(!status.equals("Pending"))
			{
				throw new ExTablesAlreadyAssigned();
			}
			ArrayList<Table> availTables=instance1.getAvailableTables(dayObj);
			String suggest="Suggestion for "+totPersons+" persons: ";
			int temp=totPersons;
			while(temp>0)
			{
				String tableCode="";
				int size=0;
				Table p=null;
				if(temp>=7)
				{
					int result=0;
					for(Table t:availTables)
					{
						if(t.getSize()==8)
						{
							result=1;
							tableCode=t.getTableID();
							size=t.getSize();
							p=t;
							break;
						}
					}
					if(result==0)
					{
						for(Table t:availTables)
						{
							if(t.getSize()==4)
							{
								result=1;
								tableCode=t.getTableID();
								size=t.getSize();
								p=t;
								break;
							}
						}
					}
					if(result==0)
					{
						for(Table t:availTables)
						{
							if(t.getSize()==2)
							{
								result=1;
								tableCode=t.getTableID();
								size=t.getSize();
								p=t;
								break;
							}
						}
					}
					if(result==0)
					{
						throw new ExNotEnoughTables(totPersons);
					}
				}
				else if(temp>=3)
				{
					int result=0;
					for(Table t:availTables)
					{
						if(t.getSize()==4)
						{
							result=1;
							tableCode=t.getTableID();
							size=t.getSize();
							p=t;
							break;
						}
					}
					if(result==0)
					{
						for(Table t:availTables)
						{
							if(t.getSize()==2)
							{
								result=1;
								tableCode=t.getTableID();
								size=t.getSize();
								p=t;
								break;
							}
						}
					}
					if(result==0)
					{
						throw new ExNotEnoughTables(totPersons);
					}
				}
				else if(temp>=1)
				{
					int result=0;
					for(Table t:availTables)
					{
						if(t.getSize()==2)
						{
							result=1;
							tableCode=t.getTableID();
							size=t.getSize();
							p=t;
							break;
						}
					}
					if(result==0)
					{
						throw new ExNotEnoughTables(totPersons);
					}
				}
				for(Table t:availTables)
				{
					if(t.getTableID().equals(p.getTableID()))
					{
						availTables.remove(t);
						break;
					}
				}
				temp=temp-size;
				suggest+=tableCode+" ";
			}
			suggest.trim();
			System.out.println(suggest);
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		} catch (ExBookingNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExTablesAlreadyAssigned e) {
			System.out.println(e.getMessage());
		}
		catch(ExNotEnoughTables e)
		{
			System.out.println(e.getMessage());
		}
	}
}
