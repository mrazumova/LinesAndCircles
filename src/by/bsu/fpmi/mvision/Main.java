package by.bsu.fpmi.mvision;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        view.setSize(800,600);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setLocationRelativeTo(null);
        view.setResizable(false);
        view.setVisible(true);
    }
}
