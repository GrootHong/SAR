package listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gui.MainWindow;
import util.SerialPortUtil;
import util.ShowUtils;

/**
 * 步进电机串口连接监听器
 * @author Vito Hong
 *
 */
public class MotorConnectButtonListener implements ActionListener {

	private MainWindow window;
	private SerialPort motorPort = null;
	public MotorConnectButtonListener() {
		super();
	}
	public MotorConnectButtonListener(MainWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = window.getMotorConnectButton().getText();
		String selected = (String)window.getComboBox_1().getSelectedItem();
		
		if(text.equals("连接")&&!selected.equals("选择串口号"))
		{
			try {
				if(!SerialPortUtil.isInUse(selected))
				{					
					motorPort = SerialPortUtil.openPort(selected, 9600);
					window.setMotorPort(motorPort);
					window.getMotorConnectButton().setText("断开");
					window.getLblNewLabel_13().setText("已连接");
					window.getLblNewLabel_13().setForeground(Color.green);
				}
				else
				{
					//弹出提醒，串口已被占用
					ShowUtils.message("对不起，所选串口正在被其他程序占用， 请重新选择！");
					window.getComboBox_1().setSelectedIndex(0);
				}
			} catch (PortInUseException e1) {
				e1.printStackTrace();
			}
		}
		else if(text.equals("断开"))
		{
			SerialPortUtil.closePort(motorPort);
			window.setMotorPort(null);
			System.gc();
			window.getMotorConnectButton().setText("连接");
			window.getComboBox_1().setSelectedIndex(0);
			window.getLblNewLabel_13().setText("未连接");
			window.getLblNewLabel_13().setForeground(Color.red);
		}
	}

}
