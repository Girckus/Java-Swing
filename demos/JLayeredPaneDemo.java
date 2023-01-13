package demos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class JLayeredPaneDemo extends JFrame implements ActionListener, MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	
	private String[] layerStrings = {"Yellow (0)", "Magenta (1)", "Cyan (2)", "Red (3)", "Green (4)"};
	private Color[] layerColors = {Color.yellow, Color.magenta, Color.cyan, Color.red, Color.green};
	
	private JLayeredPane layeredPane;
	private JLabel spfcLabel;
	private JCheckBox onTop;
	private JComboBox<String> layerList;
	
	private static String ON_TOP_COMMAND = "ontop";
	private static String LAYER_COMMAND = "layer";
	
	private static final int XFUDGE = 40;
	private static final int YFUDGE = 57;
	
	public JLayeredPaneDemo() {
		super("JlayeredPane Demo");
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		
		final ImageIcon icon = new ImageIcon("spfc.gif");
		
		layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 310));
        layeredPane.setBorder(BorderFactory.createTitledBorder("Move the Mouse to Move SPFC"));
        layeredPane.addMouseMotionListener(this);
		
        //Ponto de origem
        Point origin = new Point(10, 20);
        
        //Offset para o próximo ponto
        int offset = 35;
        
        for (int i = 0; i < layerStrings.length; i++) {
            JLabel label = createColoredLabel(layerStrings[i], layerColors[i], origin);
            layeredPane.add(label, new Integer(i));
            origin.x += offset;
            origin.y += offset;
        }
        
        //cria o label do logo do spfc
        spfcLabel = new JLabel(icon);
        spfcLabel.setBounds(15, 225, icon.getIconWidth(), icon.getIconHeight());
        layeredPane.add(spfcLabel, new Integer(2), 0);
        
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(createControlPanel());
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(layeredPane);
        
        content.setOpaque(true);
        
        setContentPane(content);
        pack();
        setVisible(true);
	}
	
    private JLabel createColoredLabel(String text, Color color, Point origin) {
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBounds(origin.x, origin.y, 140, 140);
        return label;
    }
    
    private JPanel createControlPanel() {
        onTop = new JCheckBox("Top Position in Layer");
        onTop.setSelected(true);
        onTop.setActionCommand(ON_TOP_COMMAND);
        onTop.addActionListener(this);
 
        layerList = new JComboBox<String>(layerStrings);
        layerList.setSelectedIndex(2);
        layerList.setActionCommand(LAYER_COMMAND);
        layerList.addActionListener(this);
 
        JPanel controls = new JPanel();
        controls.add(layerList);
        controls.add(onTop);
        controls.setBorder(BorderFactory.createTitledBorder("Choose SPFC's Layer and Position"));
        return controls;
    }
    
    public void mouseMoved(MouseEvent e) {
        spfcLabel.setLocation(e.getX()-XFUDGE, e.getY()-YFUDGE);
    }
    
    public void mouseDragged(MouseEvent e) {}
 
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
 
        if (ON_TOP_COMMAND.equals(cmd)) {
            if (onTop.isSelected())
                layeredPane.moveToFront(spfcLabel);
            else
                layeredPane.moveToBack(spfcLabel);
 
        } else if (LAYER_COMMAND.equals(cmd)) {
            int position = onTop.isSelected() ? 0 : 1;
            layeredPane.setLayer(spfcLabel, layerList.getSelectedIndex(), position);
        }
    }
		
}
