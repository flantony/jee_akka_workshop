package nl.fontys.jee.workshop.akka.util;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextArea chatWindow;
	private JTextField chatInput;
	private JButton chatButton;
	
	public ChatPanel(){
		chatWindow = new JTextArea();
		chatInput = new JTextField();
		chatButton = new JButton();
		chatWindow.setEnabled(false);
		chatButton.setText("send");
		
		
		
		initPanel();
	}
	
	private void initPanel(){
		setLayout(new GridLayout(3,3));
		add(chatWindow);
		add(chatInput);
		add(chatButton);
	
	}
}
