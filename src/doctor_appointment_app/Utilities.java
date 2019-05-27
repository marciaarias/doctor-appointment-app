package doctor_appointment_app;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Utilities {

	//Rename columns in a JTable.
	
	public void renameColumns(JTable table, String[] columnNames) {
		
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		for(int i = 0; i < columnNames.length; i++) {
			table.getTableHeader().getColumnModel().getColumn(i+1).setHeaderValue(columnNames[i]);
			
		}
		
		table.repaint();

	}
	
	//Format columns (CENTER) in a JTable.
	
	public void formatColumn(int column, JTable table) {
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
		
	}
	
	//Display "Confirm Quit" dialog.
	
	public void confirmQuitDialog(JFrame frame) {
		
		int clickedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm Quit", JOptionPane.YES_NO_OPTION);
	    if(clickedOption == JOptionPane.YES_OPTION) {
	    	frame.dispose();
	    }
	    
	}
	
	//Retrieve date field as "yyyy-MM-dd".
	
	public String retrieveFormattedDate(String date) {
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");  
			Date formattedDate = simpleDateFormat.parse(date);  
			
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = simpleDateFormat.format(formattedDate);
			
			return date;
			
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return null;
	}
	
	//Clear a JTable.
	
	public void clearTable(JTable table) {
		
	    DefaultTableModel model = (DefaultTableModel)table.getModel();
	    model.setRowCount(0);
	    
	}
	
}
