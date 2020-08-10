package listener;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import gui.MainWindow;

public class TextField_3Listener implements FocusListener {

	private MainWindow window;
	public TextField_3Listener() {
		super();
	}
	public TextField_3Listener(MainWindow window) {
		super();
		this.window = window;
	}
	@Override
	public void focusGained(FocusEvent e) {
		if(window.getTextField_3().getText().equals("mm/°"))
		{			
			window.getTextField_3().setText("");
			window.getTextField_3().setCaretColor(Color.black);
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(window.getTextField_3().getText().trim().equals(""))
		{
			window.getTextField_3().setText("mm/°");
			window.getTextField_3().setCaretColor(Color.GRAY);
		}
	}
	

}
