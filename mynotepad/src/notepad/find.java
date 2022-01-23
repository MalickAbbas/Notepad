package notepad;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class find extends JFrame implements ActionListener {
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter{
        public MyHighlightPainter(Color color){
            super(color);
        }
    }

    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.YELLOW);

    public void highlight(JTextComponent text, String pattern){

        try{
            Highlighter highlight = text.getHighlighter();
            Document doc = text.getDocument();

            String text1 = doc.getText(0,doc.getLength());
            int pos =0;

            while((pos=text1.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0)
            {
                highlight.addHighlight(pos,pos+pattern.length(),myHighlightPainter);
                pos += pattern.length();
            }
        }
        catch(Exception e ){

        }
    }


    private Stack stack = new Stack();
    String text = "";
    //-------------------------------------------------------------------------------------------------------------
    find(JTextArea area){



                 setBounds(200 , 200 , 400 , 200);
                 setLayout(null);
                 setResizable(false);
//-------------------------------------------------------------------------------------------------------------
                 JLabel find = new JLabel("Find");
                 find.setBounds(30,30,50,20);
                 find.setFont(new Font("SAN_SERIF",Font.PLAIN , 18));
                 add(find);



                 JLabel Label1 = new JLabel("");
                 Label1.setBounds(165,70,150,30);
                 add(Label1);

//-------------------------------------------------------------------------------------------------------------

                  JTextArea area1 = new JTextArea();
                  area1.setBounds(90 , 30 , 200, 20);
                  add(area1);

 //----------------------------------------------------------------------------------------------------------

                 JButton btn1 = new JButton("Cancel");
                 btn1.setBounds(70,110,90,25);
                 add(btn1);

                 btn1.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         area.setText(area.getText());
                         new find(area).setVisible(false);
                     }
                 });


 //---------------------------------------------------------------------------------------------------------

                  JButton btn = new JButton("find");
                  btn.setBounds(70,70,90,25);
                  btn.addActionListener(new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent e) {
                          text = area1.getText();
                          String text2 = area.getText();
                          if(text2.contains(text)) {
                             // System.out.println("SB SET HA");

                              //---------------------------------------------------------------------------
                              if(text2.charAt(text2.length()-1) != ' ')
                              {
                                  Label1.setText(" ");
                                  String line = text2+" ";
                                  area.setText(line);
                              }

                              if(!text2.equals("")){

                                  String[] words = text2.split("\\s+");
                                  int count = 0;
                                  for(int i = 0 ; i < words.length ; i++)
                                  {
                                      if(words[i].equals(area1.getText()))
                                      {
                                          count++;
                                      }
                                  }
                                  Label1.setText("Word Exist : " + Integer.toString(count) + " times");
                                  highlight(area , area1.getText()) ;
                                  String areaText = area.getText();
                                  stack.push(areaText);
                              }
                              return;

                              //-----------------------------------------------------------------------------
                          }
                          else {

                              Label1.setText("Match Word Not Found");

                          }
                      }
                  });
                  add(btn);
    }




    //________________________________________________________________________________________________



    public static void main (String[] args){

       // new find().setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
