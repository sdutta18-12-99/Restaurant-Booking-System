import java.util.ArrayList;
import java.util.Collections;
public class BookingOffice
{
	private ArrayList<Reservation> allReservations;
	private static BookingOffice instance=new BookingOffice();
	private BookingOffice()
	{
		allReservations=new ArrayList<>();
	}
	public static BookingOffice getInstance()
	{
		return instance;
	}
	public Reservation search(Day obj,int ticket)
	{
		ArrayList<Reservation> subset=new ArrayList<>();
		for(Reservation r:allReservations)
		{
			if(r.getDateDine().getYear()==obj.getYear()&&r.getDateDine().getMonth()==obj.getMonth()&&r.getDateDine().getDay()==obj.getDay())
			{
				subset.add(r);
			}
		}
		for(Reservation r:subset)
		{
			if(r.getTicketNo()==ticket)
			{
				return r;
			}
		}
		return null;
	}
	public int noOfPending(Day obj)
	{
		int count=0;
		for(Reservation r:allReservations)
		{
			if(r.getStatus().equals("Pending")&&r.getDateDine().getYear()==obj.getYear()&&r.getDateDine().getMonth()==obj.getMonth()&&r.getDateDine().getDay()==obj.getDay())
			{
				count+=1;
			}
		}
		return count;
	}
	public int noOfPendingPersons(Day obj)
	{
		int num=0;
		for(Reservation r:allReservations)
		{
			if(r.getStatus().equals("Pending")&&r.getDateDine().getYear()==obj.getYear()&&r.getDateDine().getMonth()==obj.getMonth()&&r.getDateDine().getDay()==obj.getDay())
			{
				num+=r.getTotalPersons();
			}
		}
		return num;
	}
	public int check(String name,Day obj)
	{
		for(Reservation r1:allReservations)
		{
			if(r1.getName().equals(name))
			{
				if(r1.getDateDine().getYear()==obj.getYear()&&r1.getDateDine().getMonth()==obj.getMonth()&&r1.getDateDine().getDay()==obj.getDay())
				{
					return 1;
				}
			}
		}
		return 0;
	}
	public Reservation addReservation(String guestName,String phoneNumber,int totPersons,String dateDine)
	{
		Reservation r=new Reservation(guestName,phoneNumber,totPersons,dateDine);
		allReservations.add(r);
		Collections.sort(allReservations);
		return r;
	}
	public Reservation addReservation(Reservation r)
	{
		r=new Reservation(r.getName(),r.getPhoneNumber(),r.getTotalPersons(),r.getDateDine().toString());
		allReservations.add(r);
		Collections.sort(allReservations);
		return r;
	}
	public Reservation addReservationspe(Reservation r)
	{
		allReservations.add(r);
		Collections.sort(allReservations);
		return r;
	}
	public Reservation removeReservation(Reservation r)
	{	
		for(Reservation r1:allReservations)
		{
			if(r1.getName().equals(r.getName())&&r1.getDateDine().getYear()==r.getDateDine().getYear()&&r1.getDateDine().getMonth()==r.getDateDine().getMonth()&&r1.getDateDine().getDay()==r.getDateDine().getDay())
			{
				BookingTicketController.redTicket(r1.getDateDine());
				allReservations.remove(r1);
				break;
			}
		}
		return null;
	}
	public Reservation cancelReservation(Reservation r)
	{
		for(Reservation r1:allReservations)
		{
			if(r1.getName().equals(r.getName())&&r1.getDateDine().getYear()==r.getDateDine().getYear()&&r1.getDateDine().getMonth()==r.getDateDine().getMonth()&&r1.getDateDine().getDay()==r.getDateDine().getDay())
			{
				allReservations.remove(r1);
				break;
			}
		}
		return null;
	}
	public void listReservations()
	{
		System.out.println(Reservation.getListingHeader());
		for(Reservation r:allReservations)
		{
			System.out.println(r);
		}
	}
}
