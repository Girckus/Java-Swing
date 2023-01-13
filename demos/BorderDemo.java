package demos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.border.TitledBorder;

public class BorderDemo extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public BorderDemo() {
		setTitle("Border Test");
		setSize(455, 450);
		
		JPanel content = (JPanel) getContentPane();
		content.setLayout(new GridLayout(6, 2, 5, 5));
		
		JPanel p = new JPanel();
		p.setBorder(new BevelBorder (BevelBorder.RAISED));
		p.add(new JLabel("RAISED BevelBorder"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new BevelBorder (BevelBorder.LOWERED));
		p.add(new JLabel("LOWERED BevelBorder"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new LineBorder (Color.black, 4, true));
		p.add(new JLabel("Black LineBorder, thickness = 4"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new EmptyBorder (10,10,10,10));
		p.add(new JLabel("EmptyBorder with thickness of 10"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new EtchedBorder (EtchedBorder.RAISED));
		p.add(new JLabel("RAISED EtchedBorder"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new EtchedBorder (EtchedBorder.LOWERED));
		p.add(new JLabel("LOWERED EtchedBorder"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new SoftBevelBorder (SoftBevelBorder.RAISED));
		p.add(new JLabel("RAISED SoftBevelBorder"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new StrokeBorder (new BasicStroke(1.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND)));
		p.add(new JLabel("StrokeBorder"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new MatteBorder (new ImageIcon("smily.gif")));
		p.add(new JLabel("MatteBorder"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new TitledBorder (new LineBorder (Color.black, 5),"Title String"));
		p.add(new JLabel("TitledBorder using LineBorder"));
		content.add(p);
		
		p = new JPanel();
		p.setBorder(new TitledBorder (new EmptyBorder (10,10,10,10),"Title String"));
		p.add(new JLabel("TitledBorder using EmptyBorder"));
		content.add(p);
		
		Color c1 = new Color(86, 86, 86);
		Color c2 = new Color(192, 192, 192);
		Color c3 = new Color(204, 204, 204);
		Border b1 = new BevelBorder(EtchedBorder.RAISED, c3, c1);
		Border b2 = new MatteBorder(3,3,3,3,c2);
		Border b3 = new BevelBorder (EtchedBorder.LOWERED, c3, c1);
		
		p = new JPanel();
		p.setBorder(new CompoundBorder(new CompoundBorder(b1, b2), b3));
		p.add(new JLabel("CompoundBorder"));
		content.add(p);
	}
	
}