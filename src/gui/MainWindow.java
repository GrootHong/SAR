package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import listener.BtnNewButton_3Listener;
import listener.MotorConnectButtonListener;
import listener.StartButtonListener;
import listener.SwitchConnectButtonListener;
import listener.TextField_3Listener;
import listener.TextField_4Listener;
import util.FrameUtil;
import util.SerialPortUtil;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private ButtonGroup bg1;
	private ButtonGroup bg2;
	private JComboBox<String> comboBox;
	private ButtonGroup bg3;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox_1_1;
	private JButton motorConnectButton;
	private JButton switchConnectButton;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_13_1;
	private JLabel lblNewLabel_13_2;
	private SerialPort switchPort = null;
	private SerialPort motorPort = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		setPorts();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame("SAR Controller");
		frame.setBounds(FrameUtil.getCenter(1000, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel left = new JPanel();
		left.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
			}
		});
		left.setBackground(Color.WHITE);
		left.setForeground(Color.BLACK);
		left.setPreferredSize(new Dimension(240, 680));
		frame.getContentPane().add(left, BorderLayout.WEST);
		left.setLayout(null);

		JLabel lblNewLabel = new JLabel("步进参数：");
		lblNewLabel.setBounds(9, 28, 70, 16);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		left.add(lblNewLabel);

		textField_2 = new JTextField();
		textField_2.setBounds(84, 25, 146, 22);
		textField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_2.setColumns(20);
		textField_2.setText("0:5:200");
		left.add(textField_2);

		JLabel lblNewLabel_1 = new JLabel("频率参数：");
		lblNewLabel_1.setBounds(9, 78, 70, 16);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		left.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(84, 75, 146, 22);
		textField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_1.setColumns(20);
		textField_1.setText("24:0.1:30");
		left.add(textField_1);

		JLabel lblNewLabel_2 = new JLabel("物体距离：");
		lblNewLabel_2.setBounds(9, 128, 70, 16);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		left.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(84, 125, 146, 22);
		textField.setFont(new Font("宋体", Font.PLAIN, 14));
		textField.setColumns(20);
		textField.setText("150mm");
		left.add(textField);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("平移台");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setBounds(29, 175, 70, 25);
		rdbtnNewRadioButton.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnNewRadioButton.setSelected(true);
		left.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("旋转台");
		rdbtnNewRadioButton_1.setBounds(141, 175, 70, 25);
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		rdbtnNewRadioButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		left.add(rdbtnNewRadioButton_1);

		bg1 = new ButtonGroup();
		bg1.add(rdbtnNewRadioButton);
		bg1.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("正常扫");
		rdbtnNewRadioButton_2.setBackground(Color.WHITE);
		rdbtnNewRadioButton_2.setBounds(29, 225, 70, 25);
		rdbtnNewRadioButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		left.add(rdbtnNewRadioButton_2);

		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("错开扫");
		rdbtnNewRadioButton_3.setBackground(Color.WHITE);
		rdbtnNewRadioButton_3.setBounds(141, 225, 70, 25);
		rdbtnNewRadioButton_3.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnNewRadioButton_3.setSelected(true);
		left.add(rdbtnNewRadioButton_3);

		bg2 = new ButtonGroup();
		bg2.add(rdbtnNewRadioButton_2);
		bg2.add(rdbtnNewRadioButton_3);

		JList list = new JList();
		list.setBounds(78, 371, 95, -70);
		left.add(list);

		comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(new Color(0, 0, 255));
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setBounds(15, 275, 210, 25);
		comboBox.addItem("多频多角度模式");
		comboBox.addItem("单点模式");
		comboBox.addItem("单列模式");
		comboBox.addItem("单频多角度模式");
		left.add(comboBox);

		JButton startButton = new JButton("开始");
		startButton.setBackground(Color.WHITE);
		startButton.setFont(new Font("宋体", Font.PLAIN, 16));
		startButton.setBounds(15, 302, 210, 100);
		startButton.addActionListener(new StartButtonListener(this));
		left.add(startButton);

		JButton resetButton = new JButton("重置");
		resetButton.setFont(new Font("宋体", Font.PLAIN, 16));
		resetButton.setBounds(15, 400, 105, 100);
		left.add(resetButton);

		JButton pauseButton = new JButton("暂停");
		pauseButton.setFont(new Font("宋体", Font.PLAIN, 16));
		pauseButton.setBounds(120, 400, 105, 100);
		left.add(pauseButton);

		JButton btnNewButton_3 = new JButton("系统设置");
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton_3.setBounds(15, 500, 210, 25);
		btnNewButton_3.addActionListener(new BtnNewButton_3Listener(frame));
		left.add(btnNewButton_3);

		JLabel lblNewLabel_3 = new JLabel("当前频率：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(9, 550, 70, 16);
		left.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("当前坐标：");
		lblNewLabel_3_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(9, 575, 70, 16);
		left.add(lblNewLabel_3_1);

		JLabel lblNewLabel_3_2 = new JLabel("当前开关：");
		lblNewLabel_3_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3_2.setBounds(9, 600, 70, 16);
		left.add(lblNewLabel_3_2);

		JLabel lblNewLabel_4 = new JLabel("GHz");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(140, 550, 54, 15);
		left.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("mm");
		lblNewLabel_4_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_4_1.setBounds(140, 575, 54, 15);
		left.add(lblNewLabel_4_1);

		JLabel cur_frequency = new JLabel("0");
		cur_frequency.setFont(new Font("宋体", Font.PLAIN, 14));
		cur_frequency.setHorizontalAlignment(SwingConstants.CENTER);
		cur_frequency.setBounds(76, 551, 54, 15);
		left.add(cur_frequency);

		JLabel cur_position = new JLabel("0");
		cur_position.setHorizontalAlignment(SwingConstants.CENTER);
		cur_position.setFont(new Font("宋体", Font.PLAIN, 14));
		cur_position.setBounds(76, 576, 54, 15);
		left.add(cur_position);

		JLabel cur_switch = new JLabel("1");
		cur_switch.setFont(new Font("宋体", Font.PLAIN, 14));
		cur_switch.setHorizontalAlignment(SwingConstants.CENTER);
		cur_switch.setBounds(76, 601, 54, 15);
		left.add(cur_switch);

		JLabel lblNewLabel_5 = new JLabel("等待开始. . . ");
		lblNewLabel_5.setForeground(new Color(0, 191, 255));
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(10, 636, 100, 15);
		left.add(lblNewLabel_5);

		JPanel right = new JPanel();
		right.setBackground(Color.WHITE);
		right.setForeground(Color.BLACK);
		right.setPreferredSize(new Dimension(240, 680));
		frame.getContentPane().add(right, BorderLayout.EAST);
		right.setLayout(null);

		JLabel lblNewLabel_6 = new JLabel("连接步进电机控制器：");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 10, 220, 25);
		right.add(lblNewLabel_6);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 35, 160, 25);
		comboBox_1.addItem("选择串口号");
		right.add(comboBox_1);

		motorConnectButton = new JButton("连接");
		motorConnectButton.setFont(new Font("宋体", Font.PLAIN, 14));
		motorConnectButton.setHorizontalAlignment(SwingConstants.LEFT);
		motorConnectButton.setBounds(170, 35, 80, 25);
		motorConnectButton.addActionListener(new MotorConnectButtonListener(this));
		right.add(motorConnectButton);

		comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(10, 110, 160, 25);
		comboBox_1_1.addItem("选择串口号");
		right.add(comboBox_1_1);

		switchConnectButton = new JButton("连接");
		switchConnectButton.setFont(new Font("宋体", Font.PLAIN, 14));
		switchConnectButton.setHorizontalAlignment(SwingConstants.LEFT);
		switchConnectButton.setBounds(170, 110, 80, 25);
		switchConnectButton.addActionListener(new SwitchConnectButtonListener(this));
		right.add(switchConnectButton);

		JButton moveButton = new JButton("确定");
		moveButton.setFont(new Font("宋体", Font.PLAIN, 14));
		moveButton.setHorizontalAlignment(SwingConstants.LEFT);
		moveButton.setBounds(170, 180, 80, 25);
		right.add(moveButton);

		JButton freqButton = new JButton("确定");
		freqButton.setFont(new Font("宋体", Font.PLAIN, 14));
		freqButton.setHorizontalAlignment(SwingConstants.LEFT);
		freqButton.setBounds(170, 261, 80, 25);
		right.add(freqButton);

		JLabel lblNewLabel_7 = new JLabel("连接开关控制器：");
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(10, 85, 220, 25);
		right.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("移动到：");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(10, 145, 220, 25);
		right.add(lblNewLabel_8);

		textField_3 = new JTextField("mm/°");
		textField_3.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_3.setCaretColor(Color.GRAY);
		textField_3.setBounds(10, 180, 160, 25);
		textField_3.addFocusListener(new TextField_3Listener(this));
		textField_3.setColumns(10);
		right.add(textField_3);

		JLabel lblNewLabel_9 = new JLabel("改变频率：");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(10, 226, 220, 25);
		right.add(lblNewLabel_9);

		textField_4 = new JTextField("GHz");
		textField_4.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(10, 261, 160, 25);
		textField_4.addFocusListener(new TextField_4Listener(this));
		right.add(textField_4);

		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("显示图像");
		rdbtnNewRadioButton_4.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnNewRadioButton_4.setBackground(Color.WHITE);
		rdbtnNewRadioButton_4.setBounds(10, 309, 93, 25);
		right.add(rdbtnNewRadioButton_4);

		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("不显示图像");
		rdbtnNewRadioButton_5.setFont(new Font("宋体", Font.PLAIN, 14));
		rdbtnNewRadioButton_5.setBackground(Color.WHITE);
		rdbtnNewRadioButton_5.setBounds(122, 309, 100, 25);
		rdbtnNewRadioButton_5.setSelected(true);
		right.add(rdbtnNewRadioButton_5);

		bg3 = new ButtonGroup();
		bg3.add(rdbtnNewRadioButton_4);
		bg3.add(rdbtnNewRadioButton_5);

		JLabel lblNewLabel_10 = new JLabel("步进电机控制器：");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(10, 542, 120, 25);
		right.add(lblNewLabel_10);

		JLabel lblNewLabel_10_1 = new JLabel("开关控制器：");
		lblNewLabel_10_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_10_1.setBounds(10, 577, 120, 25);
		right.add(lblNewLabel_10_1);

		JLabel lblNewLabel_10_2 = new JLabel("采集卡：");
		lblNewLabel_10_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_10_2.setBounds(10, 612, 120, 25);
		right.add(lblNewLabel_10_2);

		lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_13.setBounds(122, 542, 54, 25);
		lblNewLabel_13.setText("未连接");
		lblNewLabel_13.setForeground(Color.red);
		right.add(lblNewLabel_13);

		lblNewLabel_13_1 = new JLabel("");
		lblNewLabel_13_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_13_1.setBounds(122, 577, 54, 25);
		lblNewLabel_13_1.setText("未连接");
		lblNewLabel_13_1.setForeground(Color.red);
		right.add(lblNewLabel_13_1);

		lblNewLabel_13_2 = new JLabel("");
		lblNewLabel_13_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_13_2.setBounds(122, 612, 54, 25);
		lblNewLabel_13_2.setText("未连接");
		lblNewLabel_13_2.setForeground(Color.red);
		right.add(lblNewLabel_13_2);

/*-----------------------------测试串口通信----------------------------------------------------------------------*/
/**************************************************************************************************************/
		//目前存在问题：一次扫描时间太长，想办法缩短时间，尽量在一秒内完成，目前75秒左右。
		JButton btnNewButton = new JButton("发送数据");
		btnNewButton.setBounds(10, 374, 93, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=1;i<41;i++) {
					int x = 4000*i;
					SerialPortUtil.sendToPort(motorPort, ("X="+x+"/").getBytes());
					byte[] msg = SerialPortUtil.readFromPort(motorPort);
					System.out.println(new String(msg));					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				SerialPortUtil.sendToPort(motorPort, "X=0/".getBytes());
			}

			/*
			@Override
			public void actionPerformed(ActionEvent e) {
				long start = System.currentTimeMillis();
				for(int i=0;i<31;i++)
				{					
					SerialPortUtil.sendToPort(switchPort, ("k"+i+"\r\n").getBytes());
					//byte[] msg = SerialPortUtil.readFromPort(switchPort);
					//System.out.println(i+":"+new String(msg));
					for(double f=0;f<=6.1;f+=0.1)
					{
						SerialPortUtil.sendToPort(switchPort, ("v"+f+"\r\n").getBytes());
						//byte[] msg2 = SerialPortUtil.readFromPort(switchPort);
						//System.out.println(f+":"+new String(msg2));
					}
				}
				long end = System.currentTimeMillis();
				System.out.println("运行时间："+(end-start));
			}
			*/
			
			
		});
		right.add(btnNewButton);
/**************************************************************************************************************/
/*-----------------------------测试串口通信----------------------------------------------------------------------*/

		JPanel center = new JPanel();
		center.setBorder(new EmptyBorder(0, 0, 0, 20));
		center.setBackground(Color.WHITE);
		frame.getContentPane().add(center, BorderLayout.CENTER);
		center.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 504, 661);
		center.add(tabbedPane);

		JLabel lblNewLabel_12 = new JLabel("ad");
		lblNewLabel_12.setBackground(Color.WHITE);
		tabbedPane.addTab("AD1/2", null, lblNewLabel_12, null);
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_11 = new JLabel("图像");
		lblNewLabel_11.setBackground(Color.WHITE);
		tabbedPane.addTab("图像", null, lblNewLabel_11, null);
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 16));
	}

	private void setPorts() {
		ArrayList<String> ports = SerialPortUtil.findPorts();
		/*------------------------------------------------------------*/
		// 将扫描到的第一个串口始终打开不用，程序退出时关闭
		/* 原因：RXTX库在调用c库时容易出现崩溃；尤其是对第一个扫描到的串口打开关闭再打开时 */
		try {
			SerialPortUtil.openPort(ports.get(ports.size() - 1), 9600);
			SerialPortUtil.openPort(ports.get(ports.size() - 2), 9600);
		} catch (PortInUseException e) {
			e.printStackTrace();
		}
		/*------------------------------------------------------------*/
		// 添加到当前comboBox中

		for (int i = 0; i < ports.size(); i++) {
			this.getComboBox_1().addItem(ports.get(i));
			this.getComboBox_1_1().addItem(ports.get(i));
		}

	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public JTextField getTextField_3() {
		return textField_3;
	}

	public JTextField getTextField_4() {
		return textField_4;
	}

	public ButtonGroup getBg1() {
		return bg1;
	}

	public ButtonGroup getBg2() {
		return bg2;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public JFrame getFrame() {
		return frame;
	}

	public ButtonGroup getBg3() {
		return bg3;
	}

	public JComboBox<String> getComboBox_1() {
		return comboBox_1;
	}

	public JComboBox<String> getComboBox_1_1() {
		return comboBox_1_1;
	}

	public JButton getMotorConnectButton() {
		return motorConnectButton;
	}

	public JButton getSwitchConnectButton() {
		return switchConnectButton;
	}

	public JLabel getLblNewLabel_13() {
		return lblNewLabel_13;
	}

	public JLabel getLblNewLabel_13_1() {
		return lblNewLabel_13_1;
	}

	public JLabel getLblNewLabel_13_2() {
		return lblNewLabel_13_2;
	}

	public SerialPort getSwitchPort() {
		return switchPort;
	}

	public void setSwitchPort(SerialPort switchPort) {
		this.switchPort = switchPort;
	}

	public SerialPort getMotorPort() {
		return motorPort;
	}

	public void setMotorPort(SerialPort motorPort) {
		this.motorPort = motorPort;
	}
}
