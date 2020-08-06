package test;


import gnu.io.SerialPort;
import gui.MainWindow;
import util.SerialPortUtil;

public class Test01 {

	
	public void recieve(MainWindow window) {		
		SerialPort switchPort = window.getSwitchPort();
		while(true)
		{			
			byte[] msg = SerialPortUtil.readFromPort(switchPort);
			String str = new String(msg);
			System.out.println(str);
		}
	}
	
	
}
