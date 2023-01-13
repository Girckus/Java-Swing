import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class FileLoadProgressBar extends JFrame {

	private static final long serialVersionUID = 1L;

	private JProgressBar progressBar;
	
	public FileLoadProgressBar() 
	{
		super("Loading File");
		setLayout(new BorderLayout());
		
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
 
        JPanel panel = new JPanel();
        panel.add(progressBar);
 
        add(panel, BorderLayout.PAGE_START);
		
        Task task = new Task();
    	task.addPropertyChangeListener(e -> {
    		if("progress" == e.getPropertyName()) {
                int progress = (Integer) e.getNewValue();
                progressBar.setValue(progress);
            }
    	});
        task.execute();
        
        Dimension dim = getToolkit().getScreenSize();
	    setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
	    
		setSize(400, 100);
		setVisible(true);
	}
	
	private class Task extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            setProgress(0);
            
            while (progress < 100) {
                try {
                    Thread.sleep(random.nextInt(1000));
                } 
                catch (InterruptedException e) {
                	e.printStackTrace();
                }
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            }
            return null;
        }
 
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
        }
    }
	
}