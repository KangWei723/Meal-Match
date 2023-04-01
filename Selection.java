import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Selection extends JFrame{
	
	private static String[] materials = {"Bay Leaf", "Cayenne Pepper", "Chicken", "Coconut Milk", "Curry Powder", 
			"Fish", "Garlic", "Ginger", "Ground Cinnamon", "Lemon", "Milk", "Olive Oil", "Onion", "Paprika", "Plain Yogurt", 
			"Salt", "Sesame Oil", "Soy Sauce", "Steak", "Tomato Paste", "White Sugar", "Yellow Wine"};
	final static int MAX_MAT = materials.length;
	public static String[] storage = new String[MAX_MAT];
	
	public static int pos = 0;
	private boolean[] checkSelected = new boolean[MAX_MAT];
	private boolean clearAll;
	
	private JButton[] materialsObject = new JButton[MAX_MAT];
	private JButton clear;
	private JButton select;
	private JTextField record;
	private JPanel panel1;

	public Selection() {
		super("Material Selection");
		setLayout(new FlowLayout());
		
		for(int i = 0; i < MAX_MAT; i ++) {
			materialsObject[i] = new JButton(materials[i]);
			add(materialsObject[i]);
			panel1 = new JPanel();
			panel1.add(materialsObject[i]);
			add(panel1);
		}

		record = new JTextField(20);
		record.setEditable(false);
		add(record);
		
		clear = new JButton("Clear");
		add(clear);
		
		select = new JButton("I'm done");
		add(select);
	
		for(int i = 0; i < checkSelected.length; i++) 
			checkSelected[i] = true;
		
		HandlerClass handler = new HandlerClass();
		
		for(int i = 0; i < MAX_MAT; i++) 
			materialsObject[i].addActionListener(handler);
		
		record.addActionListener(handler);
		clear.addActionListener(handler);
		select.addActionListener(handler);
	}
	
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			for (int i = 0; i < materialsObject.length; i++) {
				if(event.getSource() == materialsObject[i] && checkSelected[i] == true) {
					clearAll = false;
					materialsObject[i].setText("selected");
					updateRecord(record, storage, materials[i], pos, clearAll);
					pos++;
					checkSelected[i] = false;
				} 
			}
			
			if(event.getSource() == clear) {
				clearAll = true;
				updateRecord(record, storage, null, pos, clearAll);
				pos = 0;
				for(int i = 0; i < checkSelected.length; i++) {
					checkSelected[i] = true;
					materialsObject[i].setText(materials[i]);
				}
			}
			
			if(event.getSource() == select && storage != null) {
				if(!record.getText().toString().equals("")) 
				{
					dispose();
					GenerateList GenerateList = new GenerateList();
				} 
				else 
				{
					JOptionPane.showMessageDialog(null, "Please Select the Ingredients!");
				}
				//JOptionPane.showMessageDialog(null, foodName);
			}
		}
	}
	
	/**
	 * Update the text box to display the ingredients that the user have selected.
	 * 
	 * @param record
	 * 			text Box
	 * @param storage
	 * 			all selected ingredients 
	 * @param material
	 * 			the selected ingredients
	 * @param pos
	 * 			the number of ingredients
	 * @param clearAll
	 * 			detect the clear all button whether is clicked by the user
	 */
	public void updateRecord(JTextField record, String[] storage, String material, int pos, boolean clearAll) {
		String total;
		String []temp = new String[pos+1];
		int i = 0;
		
		if(!clearAll) {
			if(pos > 0 && pos < MAX_MAT) {
				storage[pos] = material;
				while(i < temp.length && storage[i] != null ) {
					temp[i] = storage[i];
					i++;
				}
				total = String.join(", ", temp);
				record.setText(total);
			} else if(pos == 0) {
				storage[pos] = material;
				record.setText(storage[pos]);
			}
		} else {
			storage = null;
			record.setText("");
		}
	}
}
