package demos;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ProgressMonitor;
import javax.swing.Timer;

public class ProgressMonitorDemo extends JFrame {
	private static final long serialVersionUID = 1L;

	static ProgressMonitor monitor;
	static int progress;
	static Timer timer;
	  
	public ProgressMonitorDemo()
	{
	   super("ProgressMonitor Demo");
	   
	   setLayout(new GridLayout(0, 1));
	   
	   JButton startButton = new JButton("Start");
	   ActionListener startActionListener = new ActionListener() {
		   public void actionPerformed(ActionEvent actionEvent) {
			   Component parent = (Component) actionEvent.getSource();
			   monitor = new ProgressMonitor(parent, "Loading Progress", "Getting Started...", 0, 200);
			   progress = 0;
		   }
	   };
	   startButton.addActionListener(startActionListener);
	   
	   JButton increaseButton = new JButton("Manual Increase");
	   ActionListener increaseActionListener = new ActionListener() {
		   public void actionPerformed(ActionEvent actionEvent) {
			   if (monitor == null)
				   return;
			   if (monitor.isCanceled()) {
				   System.out.println("Monitor canceled");
			   } else {
				   progress += 5;
				   monitor.setProgress(progress);
				   monitor.setNote("Loaded " + progress + " files");
			   }
		   }
	   };
	   increaseButton.addActionListener(increaseActionListener);
	   
	   JButton autoIncreaseButton = new JButton("Automatic Increase");
	   ActionListener autoIncreaseActionListener = new ActionListener() {
		   public void actionPerformed(ActionEvent actionEvent) {
			   if (monitor != null) {
				   if (timer == null) {
					   timer = new Timer(250, new ActionListener() {
						   public void actionPerformed(ActionEvent e) {
							   if (monitor == null)
								   return;
							   if (monitor.isCanceled()) {
								   System.out.println("Monitor canceled");
								   timer.stop();
							   } else {
								   progress += 3;
								   monitor.setProgress(progress);
								   monitor.setNote("Loaded " + progress + " files");
							   }
						   }
					   });
				   }
				   timer.start();
			   }
		   }
	   };
	   autoIncreaseButton.addActionListener(autoIncreaseActionListener);
	   
	   add(increaseButton);
	   add(startButton);
	   add(autoIncreaseButton);
	   
	   setSize(300, 300);
	   setVisible(true);
	}
	
}