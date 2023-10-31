package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import ListaEncadeada.ListaEncadeada;
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
	private JScrollPane scrollPane_1;
	private JTable table;
	private JPanel panel_2;
	private JButton btnSelecionar;
	private JScrollPane scrollPane;

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
		JFileChooser fc = new JFileChooser();
		int opcao = fc.showOpenDialog(this);
		if (opcao == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String url = file.getAbsolutePath();
			tfCaminho.setText(url);
			analisar(url);
		}
	}
	
	private void analisar(String path) {
		try {
			Interpretador interpretador = new Interpretador(path);
			textArea.setText("O arquivo está bem formatado.");
			ListaEncadeada<Tag> tags = interpretador.getTags();
			tableModel = new TagTableModel(tags);
			table.setModel(tableModel);	
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao importar o arquivo", "ERRO", JOptionPane.ERROR_MESSAGE);
		} catch (HTMLSyntaxException e) {
			textArea.setText(e.getMessage());
			table.setModel(new DefaultTableModel());
		}
	}

	/**
	 * Create the frame.
	 */
	public Main() {
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
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblArquivo = new JLabel("Arquivo:");
		GridBagConstraints gbc_lblArquivo = new GridBagConstraints();
		gbc_lblArquivo.insets = new Insets(0, 0, 0, 5);
		gbc_lblArquivo.anchor = GridBagConstraints.EAST;
		gbc_lblArquivo.gridx = 0;
		gbc_lblArquivo.gridy = 0;
		panel.add(lblArquivo, gbc_lblArquivo);
		
		tfCaminho = new JTextField();
		GridBagConstraints gbc_tfCaminho = new GridBagConstraints();
		gbc_tfCaminho.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCaminho.gridx = 1;
		gbc_tfCaminho.gridy = 0;
		panel.add(tfCaminho, gbc_tfCaminho);
		tfCaminho.setColumns(10);
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		btnSelecionar = new JButton("Selecionar arquivo");
		btnSelecionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarArquivo();
			}
		});
		GridBagConstraints gbc_btnSelecionar = new GridBagConstraints();
		gbc_btnSelecionar.anchor = GridBagConstraints.EAST;
		gbc_btnSelecionar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSelecionar.gridx = 0;
		gbc_btnSelecionar.gridy = 0;
		panel_2.add(btnSelecionar, gbc_btnSelecionar);
		
		btnAnalisar = new JButton("Analisar");
		btnAnalisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		GridBagConstraints gbc_btnAnalisar = new GridBagConstraints();
		gbc_btnAnalisar.gridx = 1;
		gbc_btnAnalisar.gridy = 0;
		panel_2.add(btnAnalisar, gbc_btnAnalisar);
		btnAnalisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				analisar(tfCaminho.getText());
			}
		});
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		panel_1.add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
	}

}
