package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class replace extends JFrame {
    private Stack stack = new Stack();

    replace(JTextArea area){

        setBounds(200 , 200 ,400 , 300);
        setLayout(null);
        setResizable(false);

        //---------------------------------------------------------------------
        JLabel find = new JLabel("Find");
        find.setBounds(30,30,50,30);
        find.setFont(new Font("SAN_SERIF",Font.PLAIN , 18));
        add(find);

        JTextField findText = new JTextField();
        findText.setBounds(100 , 35 , 200, 20);
        add(findText);

        //---------------------------------------------------------------------

        JLabel replace = new JLabel("Replace");
        replace.setBounds(30,70, 100 , 30);
        replace.setFont(new Font("SAN_SERIF",Font.PLAIN , 18));
        add(replace);

        JTextField replaceText = new JTextField();
        replaceText.setBounds(100 , 75 , 200, 20);
        add(replaceText);

        JLabel label2 = new JLabel();
        label2.setBounds(100,150,200,40);
        add(label2);

        //----------------------------------------------------------------------

        JButton btn = new JButton("Replace");
        btn.setBounds(120,120,120,30);
        add(btn);

          btn.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {

                  String text = area.getText();
                  String fText = findText.getText();
                  String rText = replaceText.getText();

                  if(text.contains(fText))
                  {
                      label2.setText("");

                      String replaceString=text.replace(fText,rText);
                      area.setText(replaceString);
                      stack.push(replaceString);
                      return;
                  }
                  else{

                      label2.setText("Match Not Found");


                  }
              }
          });


    }


    public static void main(String [] args){


    }
}
