package cmz.alvin.demo3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class Demo3 extends JFrame implements ActionListener,Runnable{

	JButton button;
	URL url;
	JTextField text;
	JEditorPane editPane;
	byte [] b=new byte[118];
	Thread thread;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Demo3();
	}
	
	public Demo3(){
		text=new JTextField(20);
		editPane=new JEditorPane();
		editPane.setEditable(false);
		button=new JButton("确定");
		button.addActionListener(this);
		thread=new Thread(this);
		JPanel p=new JPanel();
		p.add(new JLabel("输入网址:"));
		p.add(text);
		p.add(button);
		this.add(new JScrollPane(editPane),BorderLayout.CENTER);
		this.add(p,BorderLayout.NORTH);
		this.setBounds(160,60,420,300);
		this.setVisible(true);
		this.validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editPane.addHyperlinkListener(new HyperlinkListener(){

			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				// TODO Auto-generated method stub
				if(e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
					try{
						editPane.setPage(e.getURL());
					}catch(Exception e1){}
				}
			}
			
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!(thread.isAlive()))
			thread=new Thread(this);
		try{
			thread.start();
		}catch(Exception e1){
			text.setText("我真在读取"+url);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			editPane.setText(null);
			url=new URL(text.getText().trim());
			editPane.setPage(url);
		}catch(Exception e2){
			text.setText(""+e2);
			return;
		}
	}

}

