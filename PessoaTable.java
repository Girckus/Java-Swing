import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import data.DAO;
import data.Pessoa;

public class PessoaTable extends JFrame {
	private static final long serialVersionUID = 1L;

	private JLabel nomeLabel;
	private JLabel cidadeLabel;
	private JLabel idadeLabel;
	private JLabel nascimentoLabel;
	private JLabel sexoLabel;
	private JTextField nomeField;
	private JTextField cidadeField;
	private JTextField idadeField;
	private JFormattedTextField nascimentoField;
	private JTextField sexoField;
	private String bioFileName;
	private List<Pessoa> listaPessoas = new ArrayList<>();
	private String[] columnNames = new String[] {"Name", "Age", "Sex"};
	private PessoaTableModel model;
	private JTable table;
	private JButton viewBiobutton;
	
	private static DAO dao = new DAO();
	
	public PessoaTable()
	{
	   super("JTable Demo");
	   
	   model = new PessoaTableModel();
	   JPanel panel = new JPanel(new GridLayout(5, 2));
	   
	   nomeLabel = new JLabel("Name ");
	   idadeLabel = new JLabel("Age ");
	   cidadeLabel = new JLabel("City ");
	   nascimentoLabel = new JLabel("Birthdate ");
	   sexoLabel = new JLabel("Sex ");
	   nomeField = new JTextField(10);
	   cidadeField = new JTextField(10);
	   idadeField = new JTextField(10);
	   nascimentoField = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
	   sexoField = new JTextField(10);
	   
	   listaPessoas = dao.getPessoas();

	   panel.add(nomeLabel);
	   panel.add(nomeField);
	   panel.add(idadeLabel);
	   panel.add(idadeField);
	   panel.add(cidadeLabel);
	   panel.add(cidadeField);
	   panel.add(nascimentoLabel);
	   panel.add(nascimentoField);
	   panel.add(sexoLabel);
	   panel.add(sexoField);

	   table = new JTable(model);
	   table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	   table.getSelectionModel().addListSelectionListener(e -> {
		   int viewRow = table.getSelectedRow();
           if (viewRow < 0) {
        	   nomeField.setText("");
        	   idadeField.setText("");
        	   cidadeField.setText("");
        	   nascimentoField.setText("");
        	   sexoField.setText("");
        	   bioFileName = "";
           } else {
        	   int modelRow = table.convertRowIndexToModel(viewRow);
        	   String nome = (String) table.getValueAt(modelRow, 0);
        	   
        	   Pessoa pessoa = dao.getPessoaByName(nome).get();
        	   
        	   nomeField.setText(pessoa.getNome());
        	   idadeField.setText(pessoa.getIdade().toString());
        	   cidadeField.setText(pessoa.getCidade());
        	   nascimentoField.setValue(pessoa.getNascimento());
        	   sexoField.setText(pessoa.getSexo());
        	   bioFileName = pessoa.getBioFileName();
           }
	   });
	   
	   viewBiobutton = new JButton("View Bio");
	   viewBiobutton.addActionListener(new BioActionListener());
	   
	   Box box = Box.createVerticalBox();
	   
	   box.add(new JScrollPane(table));
	   box.add(panel);
	   box.add(viewBiobutton);
	   
	   add(box);
	   setSize(300, 300);
	   setVisible(true);
	}
	
	public void atualizaTabela(Pessoa pessoa) {
		listaPessoas = dao.getPessoas();
		model.addRow(pessoa);
	}
	
	private String getBio(File file) {
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        	ProgressMonitorInputStream pmis = new ProgressMonitorInputStream(this, "Reading... " + file.getAbsolutePath(), bis);
        	ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            
            pmis.getProgressMonitor().setMillisToPopup(10);

            byte[] buffer = new byte[16];
            int nRead = 0;

            while ((nRead = pmis.read(buffer)) != -1) {
            	try {
					Thread.sleep(100);
					baos.write(buffer, 0, nRead);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
            
            return baos.toString("UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "Error";
    }
	
	private class BioActionListener implements ActionListener {
		
		private String bio;
		
        @Override
        public void actionPerformed(ActionEvent e) {

            SwingWorker<String, String> worker = new SwingWorker<String, String>() {

                @Override
                protected String doInBackground() throws Exception {
                	File file = new File(bioFileName);
                	bio = getBio(file);
                    return bio;
                }

                @Override
                protected void done() {
                   JDialog bioDialog = new JDialog(PessoaTable.this, "Bio Info", true);
         		   
         		   JPanel contentPane = new JPanel();
         		   JTextArea bioArea = new JTextArea(10, 20);
         		   bioArea.setText(bio);
         		   contentPane.add(new JScrollPane(bioArea));
         		   
         		   bioDialog.setContentPane(contentPane);
         		   
         		   bioDialog.setSize(250, 250);
         		   bioDialog.setLocationRelativeTo(PessoaTable.this);
         		   bioDialog.setVisible(true);
                }

            };

            worker.execute();
        }

    }
	
	class PessoaTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;

		public int getColumnCount() {
	    	return columnNames.length;
	    }
	    
	    public int getRowCount() {
	    	return listaPessoas.size();
	    }
	    
	    public void setValueAt(Object value, int row, int col) {
	    	Pessoa pessoa = listaPessoas.get(row);

	    	switch (col) {
		      	case 0:
		      		pessoa.setNome((String)value);
		        break;
		      	case 1:
		      		pessoa.setIdade((Integer)value);
		        break;
		      	case 2:
		      		pessoa.setSexo((String)value);
		        break;
	    	}
	    }

	    public String getColumnName(int col) {
	    	return columnNames[col];
	    }

	    public Class<?> getColumnClass(int col) {
	    	Class<?> classe = null;
	    	switch (col) {
		      	case 0: classe = String.class; break;
		      	case 1: classe = Integer.class; break;
		      	case 2: classe = String.class;break;
	    	}
	    	
	    	return classe;
	    }
	    
	    public Object getValueAt(int row, int col) {
	    	Pessoa pessoa = (Pessoa) (listaPessoas.get(row));

		    switch (col) {
		      case 0:
		        return pessoa.getNome();
		      case 1:
		        return pessoa.getIdade();
		      case 2:
		        return pessoa.getSexo();
		      }

		      return new String();
	    }
	    
		public void addRow(Pessoa pessoa) {
			int size = listaPessoas.size();
			listaPessoas.add(pessoa);
			fireTableRowsInserted(0, size);
		}
	}

}