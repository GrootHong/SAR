package util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class FrameUtil {
	
	/**
	 * 设置窗口大小，且居中
	 * @param width 窗口宽度
	 * @param height 窗口高度
	 * @return 返回矩形框
	 */
	public static Rectangle getCenter(int width,int height)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width-width)/2;
		int y = (screenSize.height-height)/2;
		return new Rectangle(x,y,width,height);
	}
	
	/**
	 * 加载系统配置文件
	 * @return
	 */
	public static Map<String,Integer> loadProperties()
	{
		Map<String,Integer> properties = new HashMap<String,Integer>();
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File("src/config/system.properties")));
			properties.put("每屏显示数据个数", Integer.parseInt(p.getProperty("dataShowNum")));
			properties.put("电机速度(Hz)", Integer.parseInt(p.getProperty("motorSpeed")));
			properties.put("采集的数据长度", Integer.parseInt(p.getProperty("dataLength")));
			properties.put("采集卡采样率", Integer.parseInt(p.getProperty("samplingRate")));
			properties.put("采集卡最大量程", Integer.parseInt(p.getProperty("captureCardRange")));
			
			return properties;
		} catch (IOException e1) {	
			throw new RuntimeException("系统配置文件加载错误！");
		}
	}
	
	
	
}
