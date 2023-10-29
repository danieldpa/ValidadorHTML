package gui;

import javax.swing.table.AbstractTableModel;

import validador.Tag;

public class TagTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] colunas;
	private Tag[] tags;
	public static final int TAG = 0;
	public static final int COUNT = 1;
	
	public TagTableModel(Tag[] tags) {
		super();
		colunas = new String[] {"Tag", "Número de ocorrências"};
		this.tags = tags;
	}
	
	private void verifyRowIndex(int rowIndex) {
		int length = tags.length;
		if (rowIndex >= length || rowIndex < 0) {
			throw new IndexOutOfBoundsException("Row index " + rowIndex + " out of bounds for length " + length);
		}
	}
	
	private void verifyColumnIndex(int columnIndex) {
		if (columnIndex > COUNT || columnIndex < 0) {
			throw new IndexOutOfBoundsException("Column index " + columnIndex + " out of bounds for length 1");
		}
	}
	
	private void verifyIndex(int rowIndex, int columnIndex) {
		verifyRowIndex(rowIndex);
		verifyColumnIndex(columnIndex);
	}

	@Override
	public int getRowCount() {
		return tags.length;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		verifyIndex(rowIndex, columnIndex);
		switch (columnIndex) {
		case TAG:
			return tags[rowIndex].getNome();
		case COUNT:
			return tags[rowIndex].getCount();
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		verifyColumnIndex(columnIndex);
		return String.class;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		verifyIndex(rowIndex, columnIndex);
		return false;
	}
}
