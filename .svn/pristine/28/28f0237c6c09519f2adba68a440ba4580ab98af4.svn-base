package listener;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import gui.MainWindow;

public class TextField_4Listener implements FocusListener {

	private MainWindow window;
	public TextField_4Listener() {
		super();
	}
	public TextField_4Listener(MainWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(window.getTextField_4().getText().equals("GHz"))
		{			
			window.getTextField_4().setText("");
			window.getTextField_4().setCaretColor(Color.black);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(window.getTextField_4().getText().trim().equals(""))
		{
			window.getTextField_4().setText("GHz");
			window.getTextField_4().setCaretColor(Color.GRAY);
		}
	}

}
