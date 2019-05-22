/* PigLatin.java - A Swing app that translates a String to Pig Latin
 * Author:     Will Craycroft
 * Module:     13
 * Project:    Lab 13
 * Problem Statement: Use the Swing library to create a GUI that allows a user to input a string. Translate the String to
 *  Pig Latin and output it to the view.
 *
 * Algorithm:
 *   1.  Create PigLatin class extending JFrame
 *   2.  In constructor, create 5 JPanels
 *   3.  Populate first JPanel with label showing the app title
 *   4.  Populate second JPanel with 2 labels showing the input/output headings
 *   5.  Populate third JPanel with 2 text areas showing the input/output headings
 *   6.  Populate forth JPanel with 2 Buttons to handle translate and exit actions
 *   7.  Populate fifth JPanel with label containing example text
 *   8.  Create on click method for Exit button, which terminates application
 *   9.  Create on click method for Translate button, which converts the String to Pig Latin using the logic below for each word
 *          If first letter is a vowel, add "way" to the word
 *          If the first two letters are consonants, move them to the end and add "ay"
 *          Else, move the first consonant to the end and add "ay"
 *   10. Edit text in output text area with translated text
 *   11. In main method, instantiate/build the JFrame.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class PigLatin extends JFrame implements ActionListener {

    // Constants
    private static final int WIDTH = 800;
    private static final int HEIGHT = 250;
    private static final String FRAME_TITLE = "Pig Latin Translator";
    private static final String SCREEN_TITLE = "Pig Latin Translator";
    private static final String ENTRY_HEADING = "Please Enter A String to Translate:";
    private static final String TRANSLATED_HEADING = "Translated String";
    private static final String NOTHING_TO_TRANSLATE = "(nothing to translate)";
    private static final String TRANSLATE_BUTTON_TEXT = "Translate";
    private static final String CLOSE_BUTTON_TEXT = "Exit";
    private static final String SAMPLE_TEXT = "Example: \"Oh my word\"" +
            " translates to \"Ohway ymay ordway\"";

    // Member variables
    private JTextArea inputArea;
    private JTextArea translateArea;

    // Constructor
    public PigLatin() {
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

        JLabel entryLabel = new JLabel(ENTRY_HEADING) ;
        entryLabel.setHorizontalAlignment(JLabel.CENTER) ;
        secondPanel.add(entryLabel) ;

        JLabel translatedLabel = new JLabel(TRANSLATED_HEADING) ;
        translatedLabel.setHorizontalAlignment(JLabel.CENTER) ;
        secondPanel.add(translatedLabel) ;

        // Build third Panel - Input and output areas
        inputPanel.setLayout(new GridLayout(1, 2)) ;

        inputArea = new JTextArea() ;
        inputArea.setLineWrap(true) ;
        inputArea.setWrapStyleWord(true) ;
        inputArea.setEditable(true) ;

        translateArea = new JTextArea() ;
        translateArea.setBackground(new Color(150, 210, 150)) ;
        translateArea.setLineWrap(true) ;
        translateArea.setWrapStyleWord(true) ;
        translateArea.setEditable(false) ;

        inputPanel.add(inputArea) ;
        inputPanel.add(translateArea) ;

        // Build fourth Panel - Buttons
        buttonPanel.setLayout(new FlowLayout()) ;

        JButton translateButton = new JButton(TRANSLATE_BUTTON_TEXT) ;
        translateButton.setActionCommand("translate") ;
        translateButton.addActionListener(this) ;
        buttonPanel.add(translateButton) ;

        JButton closeButton = new JButton(CLOSE_BUTTON_TEXT) ;
        closeButton.setActionCommand("stop") ;
        closeButton.addActionListener(this) ;
        buttonPanel.add(closeButton) ;

        // Build fifth Panel - Sample Text
        samplePanel.add(new JLabel(SAMPLE_TEXT)) ;
    }

    // Helper methods
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand() ;
        if (actionCommand.equals("translate")) {
            String textToConvert = inputArea.getText() ;
            // convert to Pig Latin
            String convertedText = translate(textToConvert) ;
            translateArea.setText(convertedText) ;
        } else if (actionCommand.equals("stop")){
            System.exit(0) ;
        }
    }

    public String translate(String textToConvert) {
        Scanner scan = new Scanner(textToConvert);
        String convertedText = "", current;
        while (scan.hasNext()) {

            current = scan.next();
            // Determine if first letter is capitalized
            boolean capitalized = isCapitalized(current);
            current = current.toLowerCase();

            // Check for first letter vowel
            if (isVowel(current.substring(0,1)))
            {
                // add "way" to end
                convertedText += current + "way ";
            }
            // Check for double consonant
            else if (isConsonant(current.substring(0,1)) &&
                    isConsonant(current.substring(1,2)))
            {
                // First two consonants to the end + ay
                convertedText += current.substring(2) + current.substring(0,2) + "ay ";
            }
            // Consonant + vowel
            else
            {
                // First consonants to the end + ay
                convertedText += current.substring(1) + current.substring(0,1) + "ay ";
            }
            // Capitalize first letter if necessary
            if (capitalized)
                convertedText = convertedText.substring(0, 1).toUpperCase() + convertedText.substring(1);
        }
        // undo
        return convertedText;

    }

    // Returns true if the passed String is a consonant
    private boolean isConsonant(String testString)
    {
        String consonants = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        return consonants.contains(testString);
    }

    private boolean isVowel(String testString)
    {
        String vowels = "aeiouAEIOU";
        return vowels.contains(testString);
    }

    private boolean isCapitalized(String testString)
    {
        String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return caps.contains(testString.substring(0,1));
    }


    // Main method
    public static void main(String[] args) {
        PigLatin frame = new PigLatin();
        frame.setVisible(true);
    }


}
