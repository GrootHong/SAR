package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import listener.BtnNewButtonListener;
import util.FrameUtil;

public class SystemWindow {
	
	private Map<JLabel,JTextField> texts= new HashMap<JLabel,JTextField>();
	private Properties lang = new Properties();
	private JFrame frame;
	private JFrame systemFrame;
	private JTextField textField;
	public SystemWindow()
	{
		initialize();
	}
	public SystemWindow(JFrame frame)
	{
		this.frame = frame;
		initialize();
	}
	
	public void initialize()
	{		
		systemFrame = new JFrame("系统设置");
		systemFrame.setVisible(true);
		systemFrame.setBounds(FrameUtil.getCenter(400,500));
		systemFrame.setAlwaysOnTop(true);
		JPanel panel = new JPanel();
		systemFrame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Map<String, Integer> properties = FrameUtil.loadProperties();
		int count = 1;
		for(Entry<String, Integer> entry:properties.entrySet())
		{
			JLabel lblNewLabel = new JLabel(entry.getKey());
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
			lblNewLabel.setBounds(20, 50*count, 150, 25);
			panel.add(lblNewLabel);
			
			textField = new JTextField();
			textField.setFont(new Font("宋体", Font.PLAIN, 16));
			textField.setBounds(200, 50*count, 100, 25);
			textField.setColumns(20);
			textField.setText(entry.getValue().toString());
			panel.add(textField);
			texts.put(lblNewLabel, textField);
			count++;
		}
		
		JButton btnNewButton = new JButton("保存配置");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(150, 350, 100, 35);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new BtnNewButtonListener(this));
		
		//关闭窗口前将主窗口设置可用
		systemFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		systemFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.setEnabled(true);
				systemFrame.dispose();
			}
		});
	}
	
	public Map<String,Integer> getInfo()
	{
		try {
			lang.load(new FileInputStream(new File("src/config/lang.properties")));
			Map<String,Integer> map = new HashMap<String,Integer>();
			for(Entry<JLabel,JTextField> entry : texts.entrySet())
			{
				JLabel label = (JLabel) entry.getKey();
				JTextField field = (JTextField) entry.getValue();
				String key = lang.getProperty(label.getText());
				Integer value = Integer.parseInt(field.getText());
				map.put(key, value);
			}
			return map;
		} 
		catch (Exception e) {
			throw new RuntimeException("加载lang错误！");
		}
	}
	
	//关闭本窗口
	public void close()
	{
		systemFrame.dispose();
		frame.setEnabled(true);
	}
	
}






















