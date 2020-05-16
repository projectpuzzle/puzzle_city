package puzzle_city_bollards;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Window extends JFrame {

	public Window(){
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTable");
		this.setSize(300, 120);

  //Les données du tableau
		Object[][] data = {
			{"Cysboy", "28 ans", "1.80 m"},
			{"BZHHydde", "28 ans", "1.80 m"},
			{"IamBow", "24 ans", "1.90 m"},
			{"FunMan", "32 ans", "1.85 m"}
		};

  //Les titres des colonnes
		String  title[] = {"Pseudo", "Age", "Taille"};
		JTable tableau = new JTable(data, title);
  //Nous ajoutons notre tableau à notre contentPane dans un scroll
  //Sinon les titres des colonnes ne s'afficheront pas !
		this.getContentPane().add(new JScrollPane(tableau));
	}   

	public static void main(String[] args){
		Window win = new Window();
		win.setVisible(true);
	}   
}