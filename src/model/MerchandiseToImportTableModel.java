package model;

import javax.swing.table.AbstractTableModel;

public class MerchandiseToImportTableModel extends AbstractTableModel {
	String columnNames[] = new String[] { "Merchandise Code", "Merchandise Name", "Quantity odered", "Unit",
			"Desired delivery date" };
	Class columnTypes[] = new Class[] { String.class, String.class, Integer.class, String.class, String.class};

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		return null;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return columnTypes[column];
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public void addRow(Object[] objects) {
		System.out.println(objects[1]);
		
	}
	

}
