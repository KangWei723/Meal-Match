import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.swing.JFrame;

public class GenerateRecipe{
	
	Selection userInput = new Selection();
	
	final int SIZE_STORAGE = userInput.pos;
	
	private String[] userSelection = new String[SIZE_STORAGE];
	private String[][] materials = {{"Chicken", "Curry"}, {"Fish", "Curry"}};
	private Map<String, String[]> recipe = new HashMap<String, String[]>();
	private int match = 0;
	private String food;
	
	public GenerateRecipe() {}
	
	public String generating() {
		
		for(int i = 0; i < userSelection.length; i++) {
			userSelection[i] = userInput.storage[i];
		}
		
		recipe.put("Curry Chicken", materials[0]);
		recipe.put("Curry Fish", materials[1]);
		
		for(Map.Entry<String, String[]> m : recipe.entrySet()) {
			String[] temp = m.getValue();
			for(int i = 0; i < userSelection.length; i++) {
				for(int j = 0; j < temp.length; j++) {
					if(userSelection[i] == temp[j]) {
						match++;
						if(match == temp.length) {
							food = m.getKey();
							i = userSelection.length;
							j = temp.length;
						}
					}
				}
			}
		}
		return food;
	}
}
