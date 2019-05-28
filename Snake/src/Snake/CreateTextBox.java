package Snake;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JTextField;


    public class CreateTextBox extends JFrame implements ActionListener {

        JTextField input;
        JButton print;
        public Dimension dim;
        public String name;

        public CreateTextBox(){
            dim = Toolkit.getDefaultToolkit().getScreenSize();

            setLayout(null);

            input = new JTextField("PODAJ NICK",5);
            input.setBounds(50,20,100,20);
            add(input);


            print = new JButton("Get Name");
            print.setBounds(50,40,100,20);
            print.addActionListener(this);
            add(print);
            this.setVisible(true);
            this.setLocation(dim.width/2-this.getWidth(), dim.height/2-this.getHeight());
            this.setSize(200, 100);
            //this.setTitle("Create Textbox");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        }



        public void actionPerformed(ActionEvent e) {
            if(e.getSource()== print )
            {
                this.name=input.getText();
            }
        }

    }
