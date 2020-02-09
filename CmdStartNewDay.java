import java.util.*;
import java.io.*;
public class CmdStartNewDay extends RecordedCommand
{
	String s1,s2;
	SystemDate instance=SystemDate.getInstance();
	public void execute(String[] cmdParts)
	{
		try {
			if(cmdParts.length<2)
			{
				throw new ExInsufficientArguments();
			}
			s1=instance.toString();
			s2=cmdParts[1];
			instance.set(cmdParts[1]);
			addUndoCommand(this);
			clearRedoList();
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void undoMe()
	{
		instance.set(s1);
		addRedoCommand(this);
	}
	@Override
	public void redoMe()
	{
		instance.set(s2);
		addUndoCommand(this);
	}
}
