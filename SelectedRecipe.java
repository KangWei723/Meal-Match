import java.awt.FlowLayout;

import javax.swing.JFrame;

public class SelectedRecipe extends JFrame{
	
	SelectedRecipe(String selectedValue)
	{
		super(selectedValue);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 1200);
		setVisible(true);	
	}
	
}
