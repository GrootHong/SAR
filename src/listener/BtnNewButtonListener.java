package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Map.Entry;


import gui.SystemWindow;

public class BtnNewButtonListener implements ActionListener {

	private Map<String,Integer> properties;
	private SystemWindow syswin;
	
	public BtnNewButtonListener() {} 
	public BtnNewButtonListener(SystemWindow syswin) 
	{
		this.syswin = syswin; 
	}
	 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//获取配置信息
		this.properties = syswin.getInfo();
		//保存配置信息
		saveProperties();
		//保存成功提示
		syswin.close();
	}
	
	/**
	 * 保存配置信息
	 */
	private void saveProperties() {
		File file = new File("src/config/system.properties");
		StringBuilder sb = new StringBuilder();
		for(Entry<String,Integer> entry : properties.entrySet())
		{
			sb.append(entry.getKey()+"="+entry.getValue()+"\r\n");
		}
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.write(sb.toString());
			bw.close();
		} catch (FileNotFoundException e1) {
			throw new RuntimeException("配置文件目录未找到！");
		} catch (IOException e1) {
			throw new RuntimeException("配置文件保存失败");
		}
	}

	
}
