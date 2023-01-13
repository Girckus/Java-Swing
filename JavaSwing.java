import java.awt.Adjustable;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JWindow;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.LayerUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import data.DAO;
import data.Pessoa;
import demos.BorderDemo;
import demos.BorderFactoryDemo;
import demos.CardLayoutDemo;
import demos.JEditorPaneDemo;
import demos.JLayeredPaneDemo;
import demos.JMenuBarDemo;
import demos.ProgressMonitorDemo;
import demos.UndoRedoDemo;

public class JavaSwing {
	
	private JFrame frame;
	private List<JTree> peoplesTree = new ArrayList<>();
	private PessoaTable tablePessoas;
	private static DAO dao = new DAO();
	
	public static void main(String args[]) throws Exception {
		JavaSwing app = new JavaSwing();
		
		app.inicializar();
	}
	
	public JavaSwing()  throws Exception {
		frame = new JFrame("Java Swing Examples");

		showMessageWindow();
		passwordDialog();
	}
	
	public void inicializar() throws Exception {
		configureFrame();
		addComponents();
		addMenu();
	}

	private void showMessageWindow()  {
		final MessageWindow window = new MessageWindow(frame, "This is a program example of Java Swing");
		window.setVisible(true);
		
		Timer windowCloseTimer = new Timer(3000, (e) -> window.dispose());
		
		while(window.isVisible()) {
			windowCloseTimer.start();
		}

		windowCloseTimer.stop();
	}
	
	public void passwordDialog() throws Exception {
		JDialog passwordDialog = new JDialog(frame, "Java Swing", true);
		passwordDialog.setLocationRelativeTo(frame);
		passwordDialog.setLayout(new FlowLayout()); 
		
		/* Adiciona os componentes */
		JLabel labelPassword = new JLabel("Digite a senha");
		passwordDialog.add(labelPassword);
		
		JPasswordField password = new JPasswordField(10);
		password.setEchoChar('~');
		password.setDisabledTextColor(Color.GREEN);
		passwordDialog.add(password);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(e -> {
			String pass = new String(password.getPassword());
			
			if(!(pass.equals("senha"))) { // Se a senha nao estiver correta
				JOptionPane.showMessageDialog(null, "Senha Incoreta", "Erro", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} else {
				try {
					passwordDialog.dispose();
					inicializar();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		passwordDialog.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Tchau", "Sair", JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
		});
		passwordDialog.add(cancelButton);
		
		passwordDialog.getRootPane().setDefaultButton(okButton);
		
		passwordDialog.setSize(275, 150);
		passwordDialog.setVisible(true);
		passwordDialog.pack();
	}
	
	private void configureFrame() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(870, 650);
	    Dimension dim = frame.getToolkit().getScreenSize();
	    frame.setLocation(dim.width/2 - frame.getWidth()/2, dim.height/2 - frame.getHeight()/2);
	    
	    ToolTipManager.sharedInstance().setInitialDelay(0);
	    
	    ImageIcon image = new ImageIcon("smily.gif");
	    frame.setIconImage(image.getImage());
	    frame.setVisible(true);
	    
	    WindowListener windowListener = new WindowAdapter() {
	    	public void windowClosing(WindowEvent e) {
	    		int confirm = JOptionPane.showOptionDialog(frame, "Do you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
	    	
	    		if (confirm == 0) {
	    			frame.dispose();
	    			System.exit(0);
	    		}
	    	}
	    };
	    
	    frame.addWindowListener(windowListener);
	}

	private void addMenu() throws Exception {
		// Menu File
   		JMenu fileMenu = new JMenu("File");
   		fileMenu.setMnemonic('F');

   		// Menu File. MenuItem About...
   		JMenuItem aboutItem = new JMenuItem("About...");
   		aboutItem.setMnemonic('A');
   		aboutItem.addActionListener(e -> {
   			JDialog dialog = new JDialog(frame, "About", true);
   			dialog.setLocationRelativeTo(frame);
   			dialog.setSize(150, 80);
   			dialog.setLayout(new FlowLayout());  
   			dialog.add(new JLabel("Java Swing Examples"));
   			dialog.setVisible(true);
   			dialog.pack();
   		});
   		fileMenu.add(aboutItem);
 
   		// Meny File. MenuItem Exit
   		JMenuItem exitItem = new JMenuItem( "Exit" );
   		exitItem.setMnemonic( 'x' );
   		exitItem.addActionListener(e -> System.exit(0));
   		fileMenu.add(exitItem);
		
		JMenuBar bar = new JMenuBar();
   		frame.setJMenuBar(bar);
   		bar.add(fileMenu);
   		
   		updateMetalLookAndFeel(bar);
	}
	
	private void addComponents() throws Exception {
		//Panel principal que contem os botoes do lado esquerdo e as abas do lado direito
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		
		// Panel do botoes
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(leftButtonsPanels());
		
		// Panel para as abas
		JPanel plafTabbedPanel = new JPanel(new BorderLayout());
		plafTabbedPanel.add(plafTabbedPanes());
		
		// Splitpane para separar os botoes das abas, adicionando os dois panels
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonPanel, plafTabbedPanel);
		splitPane.setDividerSize(8);
		splitPane.setContinuousLayout(true);
	    
		//Adicionar o splitpane contendo os dois panels no panel principal
	    controlPanel.add(splitPane, BorderLayout.CENTER);
	    
	    //Adiciona o ToolBar
   		JToolBar tabelaPessoasToolBar = new JToolBar();
		
		JButton tabelaPessoasButton = new JButton("People Table");
		tabelaPessoasButton.addActionListener(e -> {
			try {
				tablePessoas = new PessoaTable();
				openFrame(tablePessoas);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		tabelaPessoasToolBar.add(tabelaPessoasButton);
		updateMetalLookAndFeel(tabelaPessoasToolBar);
		
		frame.add(tabelaPessoasToolBar, BorderLayout.NORTH);
		frame.add(controlPanel, BorderLayout.SOUTH);
	}
	
	private JComponent leftButtonsPanels() throws Exception {
		//Cria os botoes e os damos
		JPanel bordersPanel = addButtonDemo("Border Example", "Examples of borders", 0);
		JPanel bordersFactoryPanel = addButtonDemo("Border Factory Example", "Exemples of borders using BorderFactory", 1);
		JPanel cardLayoutPanel = addButtonDemo("CardLayout Example", "Exemple of CardLayout", 2);
		JPanel editorPanePanel = addButtonDemo("EditorPane Example", "Exemple of EditorPane", 3);
		JPanel menuPanel = addButtonDemo("Menu Example", "Exemple of using menus", 4);
		JPanel layeredPanel = addButtonDemo("Layered Example", "Exemple of LayeredPane", 5);
		JPanel undoRedoPanel = addButtonDemo("Undo/Redo Example", "Exemple of Undo/Redo on a TextPane", 6);
		JPanel progressMonitorPanel = addButtonDemo("Progress Monitor Example", "Exemple of Progress Monitor", 7);
		
		//Cria o painel para adicionar os botoes
		JPanel panelButton = new JPanel(new GridLayout(8, 1)); 
		
		panelButton.add(bordersPanel);
		panelButton.add(bordersFactoryPanel);
		panelButton.add(cardLayoutPanel);
		panelButton.add(editorPanePanel);
		panelButton.add(menuPanel);
		panelButton.add(layeredPanel);
		panelButton.add(undoRedoPanel);
		panelButton.add(progressMonitorPanel);
		
		return panelButton;
	}
	
	private JTabbedPane plafTabbedPanes() {
		JTabbedPane panes = new JTabbedPane();
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		
		try
		{
			/* Metal UI */
			JInternalFrame frame1 = createInternelFramePeopleAdd(looks[0].getClassName());
			JInternalFrame frame2 = createInternelFramePeopleList(looks[0].getClassName());
			
			JDesktopPane desktopMetal = new JDesktopPane();
			desktopMetal.add(frame1);
			desktopMetal.add(frame2);
			frame1.setVisible(true);
			frame2.setVisible(true);
			
			/* Nimbus UI */
			JInternalFrame frame3 = createInternelFramePeopleAdd(looks[1].getClassName());
			JInternalFrame frame4 = createInternelFramePeopleList(looks[1].getClassName());
			
			JDesktopPane desktopNimbus = new JDesktopPane();
			desktopNimbus.add(frame3);
			desktopNimbus.add(frame4);
			frame3.setVisible(true);
			frame4.setVisible(true);
			
			/* Motif UI */
			JInternalFrame frame5 = createInternelFramePeopleAdd(looks[2].getClassName());
			JInternalFrame frame6 = createInternelFramePeopleList(looks[2].getClassName());
			
			JDesktopPane desktopMotif = new JDesktopPane();
			desktopMotif.add(frame5);
			desktopMotif.add(frame6);
			frame5.setVisible(true);
			frame6.setVisible(true);
			
			panes.addTab("Metal", null, desktopMetal, "Metal Desktop");
			panes.addTab("Nimbus", null, desktopNimbus, "Nimbus Desktop");
			panes.addTab("Motif", null, desktopMotif, "Motif Desktop");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return panes;
	}
	
	private JInternalFrame createInternelFramePeopleAdd(String lookAndFeel) throws Exception {
		JInternalFrame internalFrame = new JInternalFrame("People Add", true, true, true, true);
		
		JList<String> cities;
		
		Box box = Box.createVerticalBox();
		
		JPanel infoPanel = new JPanel();
		GroupLayout layout = new GroupLayout(infoPanel);
		infoPanel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// Field para o nome
		JLabel nameLabel = new JLabel("Name:");
		JTextField nameField = new JTextField();
		
		// Cria a lista de estados
		JLabel stateLabel = new JLabel("State:");
		JComboBox<String> statesCombo = new JComboBox<String>(dao.getStates().toArray(new String[4]));
		statesCombo.setSelectedItem(dao.getStates().get(0));
					
		// Cria a lista de cidades
		JLabel cityLabel = new JLabel("City:");
		cities = new JList<String>(dao.getCities(dao.getStates().get(0)).toArray(new String[8]));
		cities.setVisibleRowCount(4);
		cities.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		cities.setAutoscrolls(true);
		JScrollPane citiesPane = new JScrollPane(cities);
		
		// Adiciona os Handlers
		statesCombo.addItemListener(e -> {
			if( e.getStateChange() == ItemEvent.SELECTED ) {
				String state = (String) statesCombo.getSelectedItem();
				
				cities.setListData(dao.getCities(state).toArray(new String[8]));
			}
		});
		
		cities.addListSelectionListener(e -> System.out.println(cities.getSelectedValue()));
		
		//Spinner para data
		JLabel dateLabel = new JLabel("Birthdate");
		SpinnerModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DATE);
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
		
		// Layout dos componentes
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup().addComponent(nameLabel).addComponent(stateLabel).addComponent(cityLabel).addComponent(dateLabel));
		hGroup.addGroup(layout.createParallelGroup().addComponent(nameField).addComponent(statesCombo).addComponent(citiesPane).addComponent(dateSpinner));
		layout.setHorizontalGroup(hGroup);
		   
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(nameLabel).addComponent(nameField));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(stateLabel).addComponent(statesCombo));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(cityLabel).addComponent(citiesPane));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(dateLabel).addComponent(dateSpinner));
		layout.setVerticalGroup(vGroup);
		
		// Checkbox para sexo
		JPanel sexPanel = new JPanel();
		sexPanel.setBorder(new TitledBorder(new EtchedBorder(), "Sex"));
					
		JRadioButton maleCheck = new JRadioButton("Male");
		maleCheck.setActionCommand("Male");
		JRadioButton femaleCheck = new JRadioButton("Female");
		femaleCheck.setActionCommand("Female");
		JRadioButton otherCheck = new JRadioButton("Other");
		otherCheck.setActionCommand("Other");
		
		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(maleCheck);
		sexGroup.add(femaleCheck);
		sexGroup.add(otherCheck);
		
		sexPanel.add(maleCheck);
		sexPanel.add(femaleCheck);
		sexPanel.add(otherCheck);
		
		//ScrollBar para idade
		JLabel ageLabel = new JLabel("Age:");
		JLabel showAge = new JLabel("50");
		
		JScrollBar ageBar = new JScrollBar(Adjustable.HORIZONTAL);
		ageBar.setPreferredSize(new Dimension(170, 20));
		ageBar.setValue(50);
		ageBar.setMaximum(100);
		ageBar.setMinimum(1);	    
		ageBar.addAdjustmentListener(e -> showAge.setText(Integer.toString(ageBar.getValue())));
		
		// JPanel para ScrollBar e label para mostrar idade
		JPanel agePanel = new JPanel();
		agePanel.add(ageLabel);
		agePanel.add(ageBar);
		agePanel.add(showAge);
		
		//Button para busca
		//Layer para ser aplicado
		final LayerUI<JComponent> layerUI4 = new JavaSwing.WaitLayerUI();
		JLayer<JComponent> layer4 = new JLayer<JComponent>(box, layerUI4);
		
		final Timer stopper = new Timer(4000, new ActionListener() {
	        public void actionPerformed(ActionEvent ae) {
	        	((WaitLayerUI) layerUI4).stop();
	        }
	    });
	    stopper.setRepeats(false);

	    //Button para adicionar arquivo de bio
	    JPanel bioPanel = new JPanel();
	   
	    JLabel fileLabel = new JLabel("No file");
	    
	    JButton fileButton = new JButton("Choose File");
	    fileButton.addActionListener(new JavaSwing.FileChooserListener(fileLabel));
	    
	    bioPanel.add(new JLabel("Bio"));
	    bioPanel.add(fileButton);
	    bioPanel.add(fileLabel);
	    
	    //Button para adicionar
		JPanel buttonAddPanel = new JPanel();
		JButton addButton = new JButton("Add");
		addButton.addActionListener(e -> {
			((WaitLayerUI) layerUI4).start();
            if (!stopper.isRunning()) {
              stopper.start();
            }
            
            String nome = nameField.getText();
            String cidade = cities.getSelectedValue();
            String sexo = sexGroup.getSelection().getActionCommand();
            Integer idade = ageBar.getValue();
            Date nascimento = (Date) dateSpinner.getValue();
            String fileBioName = fileLabel.getText();
            String estado = (String) statesCombo.getSelectedItem();
            
            Pessoa novaPessoa = new Pessoa(nome, idade, sexo, cidade, nascimento, fileBioName);
            dao.addPessoa(novaPessoa);
            
            atualizaTree(estado, cidade, nome);
            tablePessoas.atualizaTabela(novaPessoa);
        });
		
		buttonAddPanel.add(addButton);
		
		// Adiciona os componentes
		box.add(infoPanel);
		box.add(sexPanel);
		box.add(agePanel);
		box.add(bioPanel);
		box.add(buttonAddPanel);

		UIManager.setLookAndFeel(lookAndFeel);
		SwingUtilities.updateComponentTreeUI(box);
		
		// Adiciona o layer no frame externo
		frame.add(layer4);
		
		// Adiciona o layout no frame interno
		internalFrame.add(box, BorderLayout.CENTER);
		internalFrame.pack();
		internalFrame.setVisible(true);
		
		return internalFrame;
	}
	
	private JInternalFrame createInternelFramePeopleList(String lookAndFeel) throws Exception {
		JInternalFrame internalFrame = new JInternalFrame("People List", true, true, true, true);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cities");
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		
		Font font = new Font("Segoe Script", Font.BOLD, 20);
		JTextArea pessoaInfo = new JTextArea(10,10);
		
		List<String> states = dao.getStates();
		for(String state : states) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(state);
			
			List<String> cities = dao.getCities(state);
			for(String city : cities) {
				DefaultMutableTreeNode nodeCity = new DefaultMutableTreeNode(city);
				
				List<Pessoa> pessoas = dao.getPessoasByCity(city);
				for(Pessoa pessoa : pessoas) {
					nodeCity.add(new DefaultMutableTreeNode(pessoa.getNome()));
				}
				
				node.add(nodeCity);
			}
			
			root.add(node);
		}
		
		JTree tree = new JTree(treeModel);
		tree.getSelectionModel().addTreeSelectionListener(e -> {
			pessoaInfo.setFont(font);
			
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
			String key = selectedNode.getUserObject().toString();
			
			Optional<Pessoa> pessoa = dao.getPessoaByName(key);
			
			if(pessoa.isPresent()) {
				pessoaInfo.setText(pessoa.get().toString());
			}
		});
		
		pessoaInfo.setEditable(false);
		
		peoplesTree.add(tree);
		
		Box box = Box.createVerticalBox();
		
		JScrollPane scrollPane = new JScrollPane(pessoaInfo);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		box.add(new JScrollPane(tree));
		box.add(scrollPane);
		
		UIManager.setLookAndFeel(lookAndFeel);
		SwingUtilities.updateComponentTreeUI(box);
		internalFrame.add(box, BorderLayout.CENTER);
		
		internalFrame.setSize(370, 373);
		internalFrame.setVisible(true);
		internalFrame.setLocation(250, 0);
		
		return internalFrame;
	}
	
	private JPanel addButtonDemo(String title, String toolTip, int demo) throws Exception {
		Dimension buttonDimension = new Dimension(155, 50);
				
		JButton button = new JButton(title);
		button.setPreferredSize(buttonDimension);
		button.setToolTipText(toolTip);
		
		button.addActionListener(e -> {
			try {
				JFrame frame = new JFrame();
				switch(demo) {
					case 0: frame = new BorderDemo(); break;
					case 1: frame = new BorderFactoryDemo(); break;
					case 2: frame = new CardLayoutDemo(); break;
					case 3: frame = new JEditorPaneDemo(); break;
					case 4: frame = new JMenuBarDemo(); break;
					case 5: frame = new JLayeredPaneDemo(); break;
					case 6: frame = new UndoRedoDemo(); break;
					case 7: frame = new ProgressMonitorDemo(); break;
				}
				openFrame(frame);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		JPanel panelButton = new JPanel();
		panelButton.setBorder(new EmptyBorder(1, 0, 1, 0));
		panelButton.add(button);
		
		return panelButton;
	}

	private void openFrame(JFrame frame) {
		EventQueue.invokeLater(() -> {
			frame.setVisible(true);
			try {
				updateMetalLookAndFeel(frame);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private void updateMetalLookAndFeel(Component componet) throws Exception {
		LookAndFeelInfo metal = UIManager.getInstalledLookAndFeels()[0];
		
		UIManager.setLookAndFeel(metal.getClassName());
		SwingUtilities.updateComponentTreeUI(componet);
	}
	
	@SuppressWarnings("unchecked")
	private void atualizaTree(String estado, String cidade, String nome) {
		for(JTree peopleTree: peoplesTree) {
			DefaultTreeModel model = (DefaultTreeModel)peopleTree.getModel();
	        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
	        Enumeration<DefaultMutableTreeNode> nodes = root.children();
	        
	        DefaultMutableTreeNode stateNode = null;
	        while(nodes.hasMoreElements()) {
	        	DefaultMutableTreeNode node = nodes.nextElement();
	        	String state = node.getUserObject().toString();
	        	if(estado.equals(state)) {
	        		stateNode = node;
	        		break;
	        	}
	        }
	        
	        Enumeration<DefaultMutableTreeNode> stateNodes = stateNode.children();
	        while(stateNodes.hasMoreElements()) {
	        	DefaultMutableTreeNode node = stateNodes.nextElement();
	        	String city = node.getUserObject().toString();
	        	if(city.equals(cidade)) {
	        		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(nome);
	        		node.add(childNode);
	        		break;
	        	}
	        }
	        model.reload(root);
		}
	}
	
	private class MessageWindow extends JWindow {
		private static final long serialVersionUID = 1L;

		private final String message; 

	    public MessageWindow(final JFrame parent, final String message) { 
	         super(parent);               
	         this.message = message; 
	         setSize(350, 100);       
	         setLocationRelativeTo(parent);
	    }

	    public void paint(Graphics g) 
	    { 
	         super.paint(g);
	         g.drawRect(0,0,getSize().width - 1,getSize().height - 1); 
	         g.drawString(message,50,50); 
	    } 
	}
	
	private class WaitLayerUI extends LayerUI<JComponent> implements ActionListener {
		private static final long serialVersionUID = 1L;
		
		private boolean mIsRunning;
		  private boolean mIsFadingOut;
		  private Timer mTimer;

		  private int mAngle;
		  private int mFadeCount;
		  private int mFadeLimit = 15;

		  @Override
		  public void paint(Graphics g, JComponent c) {
			  int w = c.getWidth();
			  int h = c.getHeight();

			  // Paint the view.
			  super.paint(g, c);

			  if (!mIsRunning) {
				  return;
			  }

			  Graphics2D g2 = (Graphics2D)g.create();

			  float fade = (float)mFadeCount / (float)mFadeLimit;
			  
			  // Gray it out.
			  Composite urComposite = g2.getComposite();
			  g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f * fade));
			  g2.fillRect(0, 0, w, h);
			  g2.setComposite(urComposite);

			  // Paint the wait indicator.
			  int s = Math.min(w, h) / 5;
			  int cx = w / 2;
			  int cy = h / 2;
			  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			  g2.setStroke(new BasicStroke(s / 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			  g2.setPaint(Color.white);
			  g2.rotate(Math.PI * mAngle / 180, cx, cy);
			  for (int i = 0; i < 12; i++) {
				  float scale = (11.0f - (float)i) / 11.0f;
				  g2.drawLine(cx + s, cy, cx + s * 2, cy);
				  g2.rotate(-Math.PI / 6, cx, cy);
				  g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, scale * fade));
			  }

			  g2.dispose();
		  }

		  public void actionPerformed(ActionEvent e) {
			  if (mIsRunning) {
				  firePropertyChange("tick", 0, 1);
				  mAngle += 3;
				  if (mAngle >= 360) {
					  mAngle = 0;
				  }
				  if (mIsFadingOut) {
					  if (--mFadeCount == 0) {
						  mIsRunning = false;
						  mTimer.stop();
					  }
				  }
				  else if (mFadeCount < mFadeLimit) {
					  mFadeCount++;
				  }
			  }
		  }

		  public void start() {
			  if (mIsRunning) {
				  return;
			  }
		    
			  // Run a thread for animation.
			  mIsRunning = true;
			  mIsFadingOut = false;
			  mFadeCount = 0;
			  int fps = 24;
			  int tick = 1000 / fps;
			  mTimer = new Timer(tick, this);
			  mTimer.start();
		  }

		  public void stop() {
			  mIsFadingOut = true;
		  }

		  @SuppressWarnings("rawtypes")
		  @Override
		  public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
			  if ("tick".equals(pce.getPropertyName())) {
				  l.repaint();
			  }
		  }
	}
	
	private class FileChooserListener implements ActionListener, PropertyChangeListener {
		
		private JLabel fileLabel;
		
		public FileChooserListener(JLabel fileLabel) {
			super();
			this.fileLabel = fileLabel;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(false);
			fileChooser.addPropertyChangeListener(this);
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt");
			fileChooser.setFileFilter(filter);
			
	        int option = fileChooser.showOpenDialog(frame);
	        
	        if (option == JFileChooser.APPROVE_OPTION) {
	        	File file = fileChooser.getSelectedFile();
	        	fileLabel.setText(file.getName());
	        	JFrame loadFrame = new FileLoadProgressBar();
	        	openFrame(loadFrame);
	        }
		}
		
		public void propertyChange(PropertyChangeEvent e) {
			String prop = e.getPropertyName();

	        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
	        	fileLabel.setText("No file");
	        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
	            File file = (File) e.getNewValue();
	            
	            if(file != null) {
	            	fileLabel.setText(file.getName());
	            } else {
	            	fileLabel.setText("No file");
	            }
	        }
	    }
		
	}
	
}