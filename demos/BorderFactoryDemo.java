package demos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class BorderFactoryDemo extends JFrame {

	private static final long serialVersionUID = 1L;

	public BorderFactoryDemo() {
		
		super("BorderFactory Demo");
		
		Border paneEdge = BorderFactory.createEmptyBorder(0, 10, 10, 10);
		
		Border bevel = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.ORANGE, Color.RED, Color.GREEN);
		Border softBevel = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.PINK);
		Border dashed = BorderFactory.createDashedBorder(Color.RED, 2.0f, 8.0f, 1.0f, true);
		Border etched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.PINK);
		Border line = BorderFactory.createLineBorder(Color.YELLOW, 2, true);
		Border matteColor = BorderFactory.createMatteBorder(5, 10, 5, 10, Color.GREEN);
		Border matteIcon = BorderFactory.createMatteBorder(5, 5, 5, 5, new ImageIcon("smily.gif"));
		Border stroke = BorderFactory.createStrokeBorder(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] {16.0f,20.0f}, 0.0f), Color.WHITE);
		Border title = BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.PINK), "Title", TitledBorder.RIGHT, TitledBorder.BOTTOM, new Font("Monospaced", Font.ITALIC, 15), Color.MAGENTA);
		Border compound = BorderFactory.createCompoundBorder(matteIcon, dashed);
		Border compoundTitle = BorderFactory.createCompoundBorder(stroke, title);
		compoundTitle = BorderFactory.createCompoundBorder(bevel, compoundTitle);
		compoundTitle = BorderFactory.createCompoundBorder(line, compoundTitle);
		
		JLabel labelA = new JLabel("Bavel Border");
		Border borderA = BorderFactory.createCompoundBorder(paneEdge, bevel);
		labelA.setBorder(borderA);
		labelA.setSize(50, 50);
		
		JLabel labelB = new JLabel("Dashed Border");
		Border borderB = BorderFactory.createCompoundBorder(paneEdge, dashed);
		labelB.setBorder(borderB);
		labelB.setSize(50, 50);
		
		JLabel labelC = new JLabel("Etched Border");
		Border borderC = BorderFactory.createCompoundBorder(paneEdge, etched);
		labelC.setBorder(borderC);
		labelC.setSize(50, 50);
		
		JLabel labelD = new JLabel("Line Border");
		Border borderD = BorderFactory.createCompoundBorder(paneEdge, line);
		labelD.setBorder(borderD);
		labelD.setSize(50, 50);
		
		JLabel labelE = new JLabel("Matte Color");
		Border borderE = BorderFactory.createCompoundBorder(paneEdge, matteColor);
		labelE.setBorder(borderE);
		labelE.setSize(50, 50);
		
		JLabel labelF = new JLabel("Matte Icon");
		Border borderF = BorderFactory.createCompoundBorder(paneEdge, matteIcon);
		labelF.setBorder(borderF);
		labelF.setSize(50, 50);
		
		JLabel labelG = new JLabel("Stroke");
		Border borderG = BorderFactory.createCompoundBorder(paneEdge, stroke);
		labelG.setBorder(borderG);
		labelG.setSize(50, 50);
		
		JLabel labelH = new JLabel("Title");
		Border borderH = BorderFactory.createCompoundBorder(paneEdge, title);
		labelH.setBorder(borderH);
		labelH.setSize(50, 50);
		
		JLabel labelI = new JLabel("Soft Bavel");
		Border borderI = BorderFactory.createCompoundBorder(paneEdge, softBevel);
		labelI.setBorder(borderI);
		labelI.setSize(50, 50);
		
		JLabel labelJ = new JLabel("Compound");
		Border borderJ = BorderFactory.createCompoundBorder(paneEdge, compound);
		labelJ.setBorder(borderJ);
		labelJ.setSize(50, 50);
		
		JLabel labelK = new JLabel("Compound title");
		Border borderK = BorderFactory.createCompoundBorder(paneEdge, compoundTitle);
		labelK.setBorder(borderK);
		labelK.setSize(50, 50);
		
		JLabel labelL = new JLabel("Compound of Compound");
		Border borderL= BorderFactory.createCompoundBorder(paneEdge, BorderFactory.createCompoundBorder(borderC, borderI));
		labelL.setBorder(borderL);
		labelL.setSize(50, 50);
		
	    setLayout(new GridLayout(6, 6));
	    add(labelA);
	    add(labelB);
	    add(labelC);
	    add(labelD);
	    add(labelE);
	    add(labelF);
	    add(labelG);
	    add(labelH);
	    add(labelI);
	    add(labelJ);
	    add(labelK);
	    add(labelL);
	    
	    pack();
	    setVisible(true);
	}
	
}
