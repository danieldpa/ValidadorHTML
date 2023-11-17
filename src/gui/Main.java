package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import validador.HTMLInvalidFile;
import validador.HTMLSyntaxException;
import validador.Interpretador;
import validador.Tag;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Cursor;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCaminho;
	private JButton btnAnalisar;
	private JTextArea textArea;
	private TagTableModel tableModel;
	private JScrollPane scrollPane02;
	private JTable table;
	private JPanel panel02;
	private JButton btnSelecionar;
	private JScrollPane scrollPane01;
	private JButton btnDigitar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void selecionarArquivo() {
		JFileChooser fc = new JFileChooser("../ValidadorHTML");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("HTML file (*.html)", "html");
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int opcao = fc.showOpenDialog(this);
		if (opcao == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String url = file.getAbsolutePath();
			tfCaminho.setText(url);
			analisarPath(url);
		}
	}
	
	private void setModel(Interpretador interpretador) {
		Tag[] tags =  interpretador.getTags();
		tableModel = new TagTableModel(tags);
		table.setModel(tableModel);	
	}
	
	private void analisarPath(String path) {
		try {
			Interpretador interpretador = new Interpretador();
			interpretador.setPath(path);
			textArea.setText("O arquivo está bem formatado.");
			setModel(interpretador);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar abrir o arquivo.", "ERRO", JOptionPane.ERROR_MESSAGE);
		} catch (HTMLInvalidFile e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		} catch (HTMLSyntaxException e) {
			textArea.setText(e.getMessage());
			table.setModel(new DefaultTableModel());
		} 
	}
	
	private void analisarFile(String file) {
		try {
			Interpretador interpretador = new Interpretador();
			interpretador.setFile(file);
			textArea.setText("O arquivo está bem formatado.");
			
			setModel(interpretador);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar abrir o arquivo.", "ERRO", JOptionPane.ERROR_MESSAGE);
		} catch (HTMLInvalidFile e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
		} catch (HTMLSyntaxException e) {
			textArea.setText(e.getMessage());
			table.setModel(new DefaultTableModel());
		} 
	}
	
	private void digitar() {
		FrameDigitar dialog = new FrameDigitar(this);
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (dialog.getOpcao() == 1) {
					String text = dialog.getText();
					if(text == null) {
						text = "";
					}
					analisarFile(text);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Validadador HTML");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel01 = new JPanel();
		GridBagConstraints gbc_panel01 = new GridBagConstraints();
		gbc_panel01.insets = new Insets(0, 0, 5, 0);
		gbc_panel01.fill = GridBagConstraints.BOTH;
		gbc_panel01.gridx = 0;
		gbc_panel01.gridy = 0;
		contentPane.add(panel01, gbc_panel01);
		GridBagLayout gbl_panel01 = new GridBagLayout();
		gbl_panel01.columnWidths = new int[]{0, 0, 0};
		gbl_panel01.rowHeights = new int[]{0, 0};
		gbl_panel01.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel01.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel01.setLayout(gbl_panel01);
		
		JLabel lblArquivo = new JLabel("Arquivo:");
		GridBagConstraints gbc_lblArquivo = new GridBagConstraints();
		gbc_lblArquivo.insets = new Insets(0, 0, 0, 5);
		gbc_lblArquivo.anchor = GridBagConstraints.EAST;
		gbc_lblArquivo.gridx = 0;
		gbc_lblArquivo.gridy = 0;
		panel01.add(lblArquivo, gbc_lblArquivo);
		
		tfCaminho = new JTextField();
		GridBagConstraints gbc_tfCaminho = new GridBagConstraints();
		gbc_tfCaminho.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCaminho.gridx = 1;
		gbc_tfCaminho.gridy = 0;
		panel01.add(tfCaminho, gbc_tfCaminho);
		tfCaminho.setColumns(10);
		tfCaminho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == 10) {
					btnAnalisar.doClick();
				}
			}
		});
		
		panel02 = new JPanel();
		GridBagConstraints gbc_panel02 = new GridBagConstraints();
		gbc_panel02.insets = new Insets(0, 0, 5, 0);
		gbc_panel02.fill = GridBagConstraints.BOTH;
		gbc_panel02.gridx = 0;
		gbc_panel02.gridy = 1;
		contentPane.add(panel02, gbc_panel02);
		GridBagLayout gbl_panel02 = new GridBagLayout();
		gbl_panel02.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel02.rowHeights = new int[]{0, 0};
		gbl_panel02.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel02.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel02.setLayout(gbl_panel02);
		
		btnDigitar = new JButton("Digitar");
		btnDigitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				digitar();
			}
		});
		btnDigitar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnDigitar = new GridBagConstraints();
		gbc_btnDigitar.insets = new Insets(0, 0, 0, 5);
		gbc_btnDigitar.gridx = 1;
		gbc_btnDigitar.gridy = 0;
		panel02.add(btnDigitar, gbc_btnDigitar);
		
		btnSelecionar = new JButton("Selecionar arquivo");
		btnSelecionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnSelecionar = new GridBagConstraints();
		gbc_btnSelecionar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelecionar.anchor = GridBagConstraints.EAST;
		gbc_btnSelecionar.gridx = 2;
		gbc_btnSelecionar.gridy = 0;
		panel02.add(btnSelecionar, gbc_btnSelecionar);
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarArquivo();
			}
		});
		
		btnAnalisar = new JButton("Analisar");
		btnAnalisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnAnalisar = new GridBagConstraints();
		gbc_btnAnalisar.gridx = 3;
		gbc_btnAnalisar.gridy = 0;
		panel02.add(btnAnalisar, gbc_btnAnalisar);
		btnAnalisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analisarPath(tfCaminho.getText());
			}
		});
		
		JPanel panel03 = new JPanel();
		GridBagConstraints gbc_panel03 = new GridBagConstraints();
		gbc_panel03.fill = GridBagConstraints.BOTH;
		gbc_panel03.gridx = 0;
		gbc_panel03.gridy = 2;
		contentPane.add(panel03, gbc_panel03);
		GridBagLayout gbl_panel03 = new GridBagLayout();
		gbl_panel03.columnWidths = new int[]{0, 0};
		gbl_panel03.rowHeights = new int[]{0, 0, 0};
		gbl_panel03.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel03.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel03.setLayout(gbl_panel03);
		
		scrollPane01 = new JScrollPane();
		GridBagConstraints gbc_scrollPane01 = new GridBagConstraints();
		gbc_scrollPane01.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane01.fill = GridBagConstraints.BOTH;
		gbc_scrollPane01.gridx = 0;
		gbc_scrollPane01.gridy = 0;
		panel03.add(scrollPane01, gbc_scrollPane01);
		
		textArea = new JTextArea();
		scrollPane01.setViewportView(textArea);
		textArea.setEditable(false);
		
		scrollPane02 = new JScrollPane();
		GridBagConstraints gbc_scrollPane02 = new GridBagConstraints();
		gbc_scrollPane02.fill = GridBagConstraints.BOTH;
		gbc_scrollPane02.gridx = 0;
		gbc_scrollPane02.gridy = 1;
		panel03.add(scrollPane02, gbc_scrollPane02);
		
		table = new JTable();
		scrollPane02.setViewportView(table);
	}
}