package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.MainWindow;
import util.ShowUtils;

public class StartButtonListener implements ActionListener {

	private MainWindow window;
	private String path;
	
	public StartButtonListener() {
	}

	public StartButtonListener(MainWindow window) {
		this.window = window;
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		// 收集扫描参数
		Map<String, String> scanParam = getScanInfo();
		// 判断驱动是否连接
		if (window.getMotorConnectButton().getText().equals("断开")
				&& window.getSwitchConnectButton().getText().equals("断开"))// 开关、电机驱动连接
		{
			// 设置保存地址
			path = saveFile();
			
		} else {
			ShowUtils.warningMessage("请先连接驱动！");
		}
		// 开始扫描
		
		
	}

	// 获取扫描配置
	private Map<String, String> getScanInfo() {
		Map<String, String> map = new HashMap<String, String>();

		String stepParam = window.getTextField_2().getText();
		String freqParam = window.getTextField_1().getText();
		String distance = window.getTextField().getText();
		map.put("步进参数", stepParam);
		map.put("频率参数", freqParam);
		map.put("物体距离", distance);

		Enumeration<AbstractButton> elements1 = window.getBg1().getElements();
		while (elements1.hasMoreElements()) {
			AbstractButton ele = elements1.nextElement();
			if (ele.isSelected()) {
				map.put("平台类型", ele.getText());
			}
		}
		Enumeration<AbstractButton> elements2 = window.getBg2().getElements();
		while (elements2.hasMoreElements()) {
			AbstractButton ele = elements2.nextElement();
			if (ele.isSelected()) {
				map.put("扫描类型", ele.getText());
			}
		}
		JComboBox<String> comboBox = window.getComboBox();
		map.put("扫描模式", (String) comboBox.getSelectedItem());
		return map;
	}

	public String saveFile()
	{
		//弹出保存文件对话框
		File file = null;
		FileNameExtensionFilter filter=new FileNameExtensionFilter("*.dat", "dat");
		JFileChooser jFileChooser=new JFileChooser();
		jFileChooser.setFileFilter(filter);
		int result = jFileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) 
		{
			file = jFileChooser.getSelectedFile();
			if (!file.getName().endsWith(".dat")) 
			{
			   file=new File(file.getPath()+".dat");
			}
	
		}
		return file.getPath();
	
	}
	public String getPath() {
		return path;
	}
	
}
