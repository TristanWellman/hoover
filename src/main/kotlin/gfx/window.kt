package gfx

import java.awt.FlowLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class window {

    fun init() {
        val frame: JFrame = JFrame("Hoover");
        val pannel: JPanel = JPanel();
        val testlabel: JLabel = JLabel("test label");
        val button: JButton = JButton()
        pannel.setLayout(FlowLayout());
        button.setText("button");
        pannel.add(testlabel);
        pannel.add(button);
        frame.add(pannel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}