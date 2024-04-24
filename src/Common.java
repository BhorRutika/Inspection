
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Common {
	public String URL="http://it.mh-hsc.ac.in/inspection/";
	public String  Key="ONLINEEXAM_M2022";
	public String  FolderName="March_2024";
	 
	public void ShowMessage(String MSG)
	{
		
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "" + MSG);
	}
	
	public String Year()
	{
		String year="2024";
		return year;
	}
	
	public String OsName_Version()
	{
		String Os_name=System.getProperty("os.name");
		String Os_Version=System.getProperty("os.version");
		//System.out.println("Os Name:"+Os_name+"\nOs Version"+Os_Version);
		String Os_Info=Os_name+"_"+Os_Version;
		return Os_Info;
	}
	

	

}


