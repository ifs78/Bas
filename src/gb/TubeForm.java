package gb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class TubeForm extends JFrame {
    private JPanel panel1;
    private JButton button1 = new JButton("Press");
    private JTextField textField1 = new JTextField();
    private JTextField textField2 = new JTextField();
    private JTextField textField3;
    private JTextField textField4;


    public TubeForm() {
        setContentPane(panel1);
        setBounds(0,0,900,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = textField1.getText();
                textField2.setText(s);
            }
        });



    }



    }