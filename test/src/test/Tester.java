package test;
import javax.swing.*;
import java.awt.Graphics;

public class Tester extends JFrame{

	
	 public static void main(String []args){
		 Tester frame= new Tester();
		 frame.setSize(800,900);
		 frame.setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setVisible(true);
	 }
	 public Tester(){
		 add(new NewPanel());
	 }
}
