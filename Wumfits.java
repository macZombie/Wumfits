import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;




public class Wumfits extends JFrame {
	
	private static final long serialVersionUID = 1L;

	//public static JFrame dynamicPlot;
	
	
	static int wide;
	
	static int high;
	
	int locX;
	
	int locY;;
	
	
	 // Thread handling
	// Thread myThread;
	 

	
	
	
	
	
	
	

	public static void main(String[] args) {

		new Wumfits();
		
		new Dynamic().setVisible(true);


	} // end of main
	
	

	
	
	
	public Wumfits() {
		
		
		locX = 25;
		
		locY = 50;
		
		wide = 800;
		
		high = 600;
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLocation(locX,locY );

		this.setSize(wide,high);
		
		this.setUndecorated(false);
		



	     JFrame dynamicPlot = new JFrame("dynamicPlot");

	     dynamicPlot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	     dynamicPlot.setLocation(locX,locY + 30);
	     dynamicPlot.setSize(wide,high );
	     dynamicPlot.add(new Dynamic() );
	     dynamicPlot.setAlwaysOnTop( true );
	     
	     dynamicPlot.setUndecorated(true);

	     dynamicPlot.setVisible(true);
	     
	     
		
		this.setVisible(true);
		
		
		
	} // end of Wumfits



	
}  // end of program 
