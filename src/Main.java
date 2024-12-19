/*
* https://github.com/zpell057
* zpell057@uottawa.ca
*
* This program allows the user to create a .h header for the arduino project that contains the information of a
* drawn image.
* */
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DrawingApp::new);
    }
}