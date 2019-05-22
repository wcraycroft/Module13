/* BinaryConverter.java - A Swing app that converts a binary number to decimal and vice versa
 * Author:     Will Craycroft
 * Module:     13
 * Project:    Homework 13
 * Problem Statement: Use the Swing library to create a GUI that allows a user to input a binary or decimal number.
 *      When the user prompts, convert the number to its binary or decimal equivalent
 *
 * Algorithm:
 *   1.  Create BinaryConverter class extending JFrame
 *   2.  In constructor, create 4 JPanels
 *   3.  Populate first JPanel with label showing the app title
 *   4.  Populate second JPanel with 2 labels showing the input/output headings
 *   5.  Populate third JPanel with 2 text areas showing the decimal/binary headings
 *   6.  Populate forth JPanel with 2 Buttons to handle each conversions
 *   7.  Create on click method for both buttons, which based on the button click converts the number to binary/decimal
 *       using recursive methods from Week 7.
 *   8.. In main method, instantiate/build the BinaryConverter JFrame.
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryConverter extends JFrame implements ActionListener {

    // Constants
    private static final int WIDTH = 600;
    private static final int HEIGHT = 250;
    private static final String FRAME_TITLE = "Binary-Decimal Converter";
    private static final String SCREEN_TITLE = "Binary-Decimal Converter";
    private static final String BINARY_HEADING = "Enter a Binary Number";
    private static final String CENTER_HEADING = "or";
    private static final String DECIMAL_HEADING = "Enter a Decimal Number";
    private static final String DECIMAL_BUTTON_TEXT = "Convert to Decimal";
    private static final String BINARY_BUTTON_TEXT = "Convert to Binary";

    // Member variables
    private JTextArea decimalInputArea;
    private JTextArea binaryInputArea;

    // Constructor
    public BinaryConverter() {
        setTitle(FRAME_TITLE) ;
        setSize(WIDTH, HEIGHT) ;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        setLocationRelativeTo(null) ;
        setLayout(new GridLayout(5, 1)) ;

        // Create 5 JPanels
        JPanel titlePanel = new JPanel() ;
        add(titlePanel) ;
        JPanel secondPanel = new JPanel() ;
        add(secondPanel) ;
        JPanel inputPanel = new JPanel() ;
        add(inputPanel) ;
        JPanel buttonPanel = new JPanel() ;
        add(buttonPanel) ;
        JPanel samplePanel = new JPanel() ;
        add(samplePanel) ;

        // Build first Panel - Screen Title
        JLabel titleLabel = new JLabel(SCREEN_TITLE) ;
        titlePanel.add(titleLabel) ;
        titleLabel.setHorizontalAlignment(JLabel.CENTER) ;
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 22)) ;

        // Build second Panel - Headings
        secondPanel.setLayout(new GridLayout(1, 2)) ;

        JLabel binaryLabel = new JLabel(BINARY_HEADING) ;
        binaryLabel.setHorizontalAlignment(JLabel.RIGHT) ;
        secondPanel.add(binaryLabel) ;

        JLabel centerLabel = new JLabel(CENTER_HEADING) ;
        centerLabel.setHorizontalAlignment(JLabel.CENTER) ;
        secondPanel.add(centerLabel) ;

        JLabel decimalLabel = new JLabel(DECIMAL_HEADING) ;
        decimalLabel.setHorizontalAlignment(JLabel.LEFT) ;
        secondPanel.add(decimalLabel) ;

        // Build third Panel - Input and output areas
        inputPanel.setLayout(new GridLayout(1, 2)) ;

        decimalInputArea = new JTextArea() ;
        decimalInputArea.setLineWrap(true) ;
        decimalInputArea.setBackground(new Color(150, 150, 210)) ;
        decimalInputArea.setWrapStyleWord(true) ;
        decimalInputArea.setEditable(true) ;

        binaryInputArea = new JTextArea() ;
        binaryInputArea.setBackground(new Color(150, 210, 150)) ;
        binaryInputArea.setLineWrap(true) ;
        binaryInputArea.setWrapStyleWord(true) ;
        binaryInputArea.setEditable(true) ;

        inputPanel.add(binaryInputArea) ;
        inputPanel.add(decimalInputArea) ;

        // Build fourth Panel - Buttons
        buttonPanel.setLayout(new FlowLayout()) ;

        JButton decimalButton = new JButton(DECIMAL_BUTTON_TEXT) ;
        decimalButton.setActionCommand("decimal") ;
        decimalButton.addActionListener(this) ;
        decimalButton.setHorizontalAlignment(JLabel.LEFT);
        buttonPanel.add(decimalButton) ;

        JButton binaryButton = new JButton(BINARY_BUTTON_TEXT) ;
        binaryButton.setActionCommand("binary") ;
        binaryButton.addActionListener(this) ;
        decimalButton.setHorizontalAlignment(JLabel.RIGHT);
        buttonPanel.add(binaryButton) ;

    }

    // Helper methods
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand() ;
        if (actionCommand.equals("decimal")) {
            String binaryInput = binaryInputArea.getText();

            // Validate input (only 0 or 1's)
            if (binaryInput.matches("[0-1]+") && binaryInput.length() > 0)
            {
                // Translate to decimal int
                //int decimalOutput = ;
                String stringOutput = String.valueOf(recursiveToDecimal(0, binaryInput));
                decimalInputArea.setText(stringOutput);
            }
            else
            {
                decimalInputArea.setText("Invalid input.");
            }
        } else if (actionCommand.equals("binary")){
            String decimalInput = decimalInputArea.getText();

            // Validate input (valid integers only)
            if (decimalInput.matches("[0-9]+") && decimalInput.length() > 0) {
                // Translate to binary String and output
                String binaryOutput = recursiveToBinary(Integer.parseInt(decimalInput), "");
                binaryInputArea.setText(binaryOutput);
            }
            else
            {
                binaryInputArea.setText("Invalid input.");
            }
        }
    }


    // The initial call should always pass an empty binary String
    public static String recursiveToBinary(int decimal, String binary)
    {

        // Base case, if decimal number / 2 = 0
        if (decimal / 2 == 0) {
            return binary + (decimal % 2);
        }
        // Add remainder of decimal / 2 to end of recursive String
        return recursiveToBinary(decimal / 2, binary) + (decimal % 2);

    }

    // The initial call should always pass a decimalSum of 0.
    public static int recursiveToDecimal(int decimalSum, String binary) {

        // Base case, if only 1 character remains in String
        if (binary.length() == 1) {
            return decimalSum * 2 + Integer.parseInt(binary);
        }
        // Double the recursive sum and add first character in String
        // Send sum to recursive method and remove first character from String
        return recursiveToDecimal(decimalSum * 2 + Integer.parseInt(binary.substring(0, 1)), binary.substring(1));

    }


    // Main method
    public static void main(String[] args) {
        BinaryConverter frame = new BinaryConverter();
        frame.setVisible(true);
    }


}


