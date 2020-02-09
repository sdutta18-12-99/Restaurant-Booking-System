import java.util.HashMap;
public class BookingTicketController
{
	private static HashMap<Day,Integer> tControllers=new HashMap<>();
	public static int takeTicket(Day d)
	{
		Integer ticket=tControllers.get(d);
		if(ticket==null)
		{
			tControllers.put(d.clone(),2);
			return 1;
		}
		else
		{
			tControllers.put(d, ticket+1);
			return ticket;
		}
	}
	public static void redTicket(Day d)
	{
		Integer ticket=tControllers.get(d);
		if(ticket!=null)
		{
			tControllers.put(d,ticket-1);
		}
	}
}
