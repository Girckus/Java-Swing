package demos;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardLayoutDemo extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public CardLayoutDemo() throws Exception {
		setTitle("CardLayout Test");
		setSize(455, 450);
		
		//Panel para os botões e as imagens
		JPanel panelScreen = new JPanel(new BorderLayout());
		
		//Panel para os botões
		JPanel buttonPanel = new JPanel();
		
		//Panel para as imagens, o card
		JPanel cards = new JPanel(new CardLayout());
		
		//Botão para voltar a imagem anterior
		JButton previousButton = new JButton("<");
		previousButton.addActionListener(e -> {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.previous(cards);
		});
		buttonPanel.add(previousButton);
		
		//Botão para ir a imagem anterior
		JButton nextButton = new JButton(">");
		nextButton.addActionListener(e -> {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.next(cards);
		});
		buttonPanel.add(nextButton);
		
		// Cria as imagens
		BufferedImage img1 = ImageIO.read(new File("img1.jpg"));
		JLabel label1 = new JLabel(new ImageIcon(img1));
		
		BufferedImage img2 = ImageIO.read(new File("img2.jpg"));
		JLabel label2 = new JLabel(new ImageIcon(img2));
		
		BufferedImage img3 = ImageIO.read(new File("img3.jpg"));
		JLabel label3 = new JLabel(new ImageIcon(img3));
		
		BufferedImage img4 = ImageIO.read(new File("img4.jpg"));
		JLabel label4 = new JLabel(new ImageIcon(img4));
		
		// Checkbox para GrayFilter
		JPanel filterPanel = new JPanel();
		JCheckBox grayFilterCheck = new JCheckBox("Show Gray Filter");
		grayFilterCheck.addActionListener(e -> {
			if(grayFilterCheck.isSelected()) {
				label1.setIcon(new ImageIcon(GrayFilter.createDisabledImage(img1)));
				label2.setIcon(new ImageIcon(GrayFilter.createDisabledImage(img2)));
				label3.setIcon(new ImageIcon(GrayFilter.createDisabledImage(img3)));
				label4.setIcon(new ImageIcon(GrayFilter.createDisabledImage(img4)));
			} else {
				label1.setIcon(new ImageIcon(img1));
				label2.setIcon(new ImageIcon(img2));
				label3.setIcon(new ImageIcon(img3));
				label4.setIcon(new ImageIcon(img4));
			}
		});
		filterPanel.add(grayFilterCheck);
		
		// Adiciona as imagens no card
		cards.add(label1);
		cards.add(label2);
		cards.add(label3);
		cards.add(label4);
		
		panelScreen.add(buttonPanel, BorderLayout.NORTH);
		panelScreen.add(filterPanel, BorderLayout.CENTER);
		panelScreen.add(cards, BorderLayout.SOUTH);
		
		add(panelScreen);
		
		pack();
        setVisible(true);
	}
	
}