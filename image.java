package javaasg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author acer
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class image extends JPanel {
    private BufferedImage originalImage;

    public image() {
        try {
            originalImage = ImageIO.read(new File("C:\\Users\\acer\\Pictures\\Screenshots\\gobank shadow.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        setPreferredSize(new Dimension(560, 410)); // Adjust panel size as needed
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (originalImage != null) {
            int newWidth = 560; // Adjust width as needed
            int newHeight = 410; // Adjust height as needed
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); // Adjust size as needed
            g.drawImage(scaledImage, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new image());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
