import java.util.*;
import java.io.*;
public class CmdListReservations implements Command
{
	public void execute(String[] cmdParts)
	{
		BookingOffice instance=BookingOffice.getInstance();
		instance.listReservations();
	}
}
