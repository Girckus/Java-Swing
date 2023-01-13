package demos;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class JEditorPaneDemo extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	final Stack<String> urlStack = new Stack<String>();
    final JEditorPane editorPane = new JEditorPane();
    final JTextField url = new JTextField(30);
	
	/* Construtor */
	public JEditorPaneDemo ()
	{
		/* Configura o conteiner */
		super("JEdtiroPane Demo");   // Chama o construtor de JFrame

		editorPane.setEditable(false);
	    editorPane.addHyperlinkListener(new HyperlinkListener()
	    {
	    	public void hyperlinkUpdate(HyperlinkEvent event)
	        {
	    		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
	            {
	    			try
	                {
	                     // remember URL for back button
	                     urlStack.push(event.getURL().toString());
	                     // show URL in text field
	                     url.setText(event.getURL().toString());
	                     editorPane.setPage(event.getURL());
	                }
	                catch (IOException e)
	                {
	                	editorPane.setText("Exception: " + e);
	                }
	            }
	        }
	    });
	    
	    final JCheckBox editable = new JCheckBox();
	    editable.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent event)
	        {
	    		editorPane.setEditable(editable.isSelected());
	        }
	    });
	    
	    ActionListener listener = new ActionListener()
        {
	    	public void actionPerformed(ActionEvent event)
            {
	    		try
	    		{
	    			// remember URL for back button
	    			urlStack.push(url.getText());
	    			editorPane.setPage(url.getText());
	    		}
	            catch (IOException e)
	            {
	            	editorPane.setText("Exception: " + e);
	            }
            }
        };
        
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(listener);
        url.addActionListener(listener);
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent event)
            {
        		if (urlStack.size() <= 1) return;
                try
                {
                    // get URL from back button
                    urlStack.pop();
                    // show URL in text field
                    String urlString = urlStack.peek();
                    url.setText(urlString);
                    editorPane.setPage(urlString);
                }
                catch (IOException e)
                {
                	editorPane.setText("Exception: " + e);
                }
            }
        });
	      
	    add(new JScrollPane(editorPane), BorderLayout.CENTER);
	    
	    JPanel panel = new JPanel();
	    panel.add(new JLabel("URL"));
	    panel.add(url);
	    panel.add(loadButton);
	    panel.add(backButton);
	    panel.add(new JLabel("Editable"));
	    panel.add(editable);

	    add(panel, BorderLayout.SOUTH);
	    
		setSize( 600, 400 );
		setVisible( true );
	}
	
}