package notepad;

import javax.swing.*;
import java.awt.*;

public class about extends JFrame {

    about(){

              setBounds(300 , 100 , 700 , 500);
              setLayout(null);

              ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("notepad/icon/java.png"));
              Image i = img.getImage().getScaledInstance(400 , 80 ,10 );
              ImageIcon im = new ImageIcon(i);
              JLabel label = new JLabel(im);
              label.setBounds(150,40,400,100);
              add(label);


              JLabel label1 = new JLabel("<html> DATA STRUCTURES PROJECT <br> SIR SAIF HASSAN <br>BS-CS III <br><br>MUHAMMAD ALI NASIK AWAN <br> ABDUL RAFAY AFTAB <br> HAMZA ALI BHUTTO <br><br> ALL RIGHTS RESERVED @ MALIK ABBAS<br>Version 2022 <br> SUKKUR IBA University <br><html>");
               label1.setBounds(200,90,500,350);
               label1.setFont(new Font("SAN_SERIF",Font.PLAIN , 18));
               add(label1);
    }

    public static void main(String[] args){

        new about().setVisible(true);

    }
}
