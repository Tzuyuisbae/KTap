package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame implements ActionListener{

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	JLabel background = new JLabel("Testing ABCDEFGHI");
	JLabel image = new JLabel();

	private int frameWidthPosition = (screenSize.width / 2) - 500;
	private int frameWidth = 1000;
	int[] num = new int[10];

	public Frame() throws IOException {

		setLayout(null);
		setBounds(frameWidthPosition,0,frameWidth,screenSize.height);

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Superstar Bighit Entertainment");
		setVisible(true);

		//set background image
		Font font = new Font("Bauhaus 93", Font.BOLD, 30);
		background.setFont(font);
		background.setBackground(Color.BLACK);
		background.setBounds(0,0,1000,300);
		background.setOpaque(true);
		add(background);

		BufferedWriter writer;
		
		try {
			int x = 600;
			writer = new BufferedWriter(new FileWriter("highscore"));
			writer.write(Integer.toString(x));
			writer.close();
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
