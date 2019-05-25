package doctor_appointment_app;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Utilities {

	public void renameColumns(JTable table, String[] columnNames) {
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		for(int i = 0; i < columnNames.length; i++) {
			table.getTableHeader().getColumnModel().getColumn(i+1).setHeaderValue(columnNames[i]);
		}
		
		table.repaint();

	}
	
	public void formatColumn(int column, JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
	}
	
}
