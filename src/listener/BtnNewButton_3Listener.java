package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.SystemWindow;


public class BtnNewButton_3Listener implements ActionListener {

	private JFrame frame;
	public BtnNewButton_3Listener() {}
	public BtnNewButton_3Listener(JFrame frame)
	{
		this.frame = frame;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//设置主窗口不能使用
		frame.setEnabled(false);
		SystemWindow syswin = new SystemWindow(frame);
	}

}
