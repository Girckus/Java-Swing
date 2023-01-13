package demos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;


public class JMenuBarDemo extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private final Color colorValues[] =  { Color.BLACK, Color.BLUE, Color.RED, Color.GREEN };   
	private JRadioButtonMenuItem fonts[];
	private JCheckBoxMenuItem styleItems[];
	private JLabel displayJLabel;
	private ButtonGroup fontButtonGroup;
	private int style;
	private JSlider sizeText;
   	private JPopupMenu menuPopup;

   	public JMenuBarDemo() {
   		super( "Using JMenus" );     
     
   		// Menu File
   		JMenu fileMenu = new JMenu( "File" );
   		fileMenu.setMnemonic( 'F' );

   		// Menu File. MenuItem About...
   		JMenuItem aboutItem = new JMenuItem( "About..." );
   		aboutItem.setMnemonic( 'A' );
   		aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(JMenuBarDemo.this, "This is an example\nof using menus", "About", JOptionPane.PLAIN_MESSAGE));
   		fileMenu.add( aboutItem );
 
   		// Meny File. MenuItem Exit
   		JMenuItem exitItem = new JMenuItem( "Exit" );
   		exitItem.setMnemonic( 'x' );
   		exitItem.addActionListener(e -> System.exit(0));
   		fileMenu.add( exitItem );

   		// Menu Format
   		JMenu formatMenu = new JMenu( "Format" );
   		formatMenu.setMnemonic( 'r' );

   		// Menu Format. MenuItem Color
   		JMenuItem colorMenu = new JMenuItem( "Color" );
   		colorMenu.setMnemonic( 'C' );
   		colorMenu.addActionListener(e -> {
   			Color color = JColorChooser.showDialog( JMenuBarDemo.this, "Choose a color", null );
   			displayJLabel.setForeground( color );
		   
   			repaint();
   		});
   		formatMenu.add( colorMenu );
      
   		// Menu Format. Separator
   		formatMenu.addSeparator();

   		// Menu Format. Submenu Font
   		JMenu fontMenu = new JMenu( "Font" );
   		fontMenu.setMnemonic( 'n' );
      
   		// Menu Format. Submenu Font. Font Types
   		ItemHandler itemHandler = new ItemHandler();
   		String fontNames[] = { "Serif", "Monospaced", "SansSerif" };
   		fonts = new JRadioButtonMenuItem[ fontNames.length ];
     
   		fontButtonGroup = new ButtonGroup();
   		for ( int count = 0; count < fonts.length; count++ ) {
   			fonts[ count ] = new JRadioButtonMenuItem( fontNames[ count ] );
   			fontMenu.add( fonts[ count ] );
   			fontButtonGroup.add( fonts[ count ] );
   			fonts[ count ].addActionListener( itemHandler );
   		}
   		fonts[ 0 ].setSelected( true );
      
   		// Menu Format. Separator
   		fontMenu.addSeparator();
      
   		// Menu Format. Submenu Font. Font Styles
   		String styleNames[] = { "Bold", "Italic" };
   		styleItems = new JCheckBoxMenuItem[ styleNames.length ];
   		StyleHandler styleHandler = new StyleHandler();
   		for ( int count = 0; count < styleNames.length; count++ ) {
   			styleItems[ count ] = new JCheckBoxMenuItem( styleNames[ count ] );
   			fontMenu.add( styleItems[ count ] );
   			styleItems[ count ].addItemListener( styleHandler );
   		}

   		// Adiciona font submenu
   		formatMenu.add( fontMenu );
	  
   		JMenuBar bar = new JMenuBar();
   		setJMenuBar( bar );
   		bar.add( fileMenu );
   		bar.add( formatMenu );
	  
   		// Label
   		displayJLabel = new JLabel( "Sample Text", SwingConstants.CENTER );
   		displayJLabel.setForeground( colorValues[ 0 ] );
   		displayJLabel.setFont( new Font( "Serif", Font.PLAIN, 72 ) );

   		getContentPane().setBackground( Color.CYAN );
   		add( displayJLabel, BorderLayout.CENTER ); 
	  
   		// JSlider
   		sizeText = new JSlider( SwingConstants.HORIZONTAL, 1, 100, 72 );
   		sizeText.setMajorTickSpacing( 10 );
   		sizeText.setPaintTicks( true );
   		sizeText.addChangeListener(e -> {
   			displayJLabel.setFont(new Font( displayJLabel.getFont().getName(), style, sizeText.getValue() ));
			repaint();
   		});
	  
   		menuPopup = new JPopupMenu();
   		JMenuItem editar = new JMenuItem("Edit");
   		menuPopup.add(editar);
	  
   		editar.addActionListener(e -> {
   			String text = JOptionPane.showInputDialog("Edit the text");
			displayJLabel.setText( text );
   		});
	  
   		displayJLabel.addMouseListener( 
   				new MouseAdapter() {
					public void mousePressed(MouseEvent event) {
						if(event.isPopupTrigger())
							menuPopup.show( event.getComponent(), event.getX(), event.getY() );
					}
					
					public void mouseReleased(MouseEvent event) {
						if(event.isPopupTrigger())
							menuPopup.show( event.getComponent(), event.getX(), event.getY() );
					}
   				}
   		);
	  
   		add(sizeText, BorderLayout.SOUTH);
   		
        setSize( 500, 200 );
        setVisible( true );
   	}

   	private class ItemHandler implements ActionListener {
	   public void actionPerformed( ActionEvent event ) { 
		   for ( int count = 0; count < fonts.length; count++ ){
			   if ( event.getSource() == fonts[ count ] ) {
				   displayJLabel.setFont( new Font( fonts[ count ].getText(), style, 72 ) );
			   }
		   }

		   repaint();
	   }
   	}

   	private class StyleHandler implements ItemListener {
   		public void itemStateChanged( ItemEvent e ) {
   			style = 0;

   			if ( styleItems[ 0 ].isSelected() )
   				style += Font.BOLD; 

   			if ( styleItems[ 1 ].isSelected() )
   				style += Font.ITALIC; 

   			displayJLabel.setFont( new Font( displayJLabel.getFont().getName(), style, 72 ) );
   			repaint();
   		}
   	} 
   
}