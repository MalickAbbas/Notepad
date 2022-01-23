package notepad;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class Notepad extends JFrame implements ActionListener ,KeyListener {

    private JTextArea area;
    private JScrollPane pane;
    private String text;
    private Stack stack = new Stack();

    Notepad() {

        setBounds(0, 0, 850, 550);

        JMenuBar menu = new JMenuBar();
//-------------------------------------------------------------------------------------------------------------
        JMenu file = new JMenu("File");
        JMenuItem item = new JMenuItem("New");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        item.addActionListener(this);


        JMenuItem item1 = new JMenuItem("Open");
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        item1.addActionListener(this);

        JMenuItem item2 = new JMenuItem("Save");
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        item2.addActionListener(this);

        JMenuItem item3 = new JMenuItem("Exit");
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        item3.addActionListener(this);

        file.add(item);
        file.add(item1);
        file.add(item2);
        file.add(item3);


//--------------------------------------------------------------------------------------------------

        JMenu edit = new JMenu("Edit");

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem select = new JMenuItem("Select All");
        select.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        select.addActionListener(this);

        JMenuItem undo = new JMenuItem("UNDO");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undo.addActionListener(this);

        JMenuItem redo = new JMenuItem("REDO");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redo.addActionListener(this);

        JMenuItem find = new JMenuItem("Find");
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        find.addActionListener(this);

        JMenuItem replace = new JMenuItem("Replace");
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        replace.addActionListener(this);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(undo);
        edit.add(redo);
        edit.add(find);
        edit.add(replace);
        edit.add(select);

//_____________________________________________________________________________________________
        JMenu view = new JMenu("View");

        JMenuItem dark = new JMenuItem("Dark");
        dark.addActionListener(this);

        JMenuItem light = new JMenuItem("Light");
        light.addActionListener(this);

        view.add(dark);
        view.add(light);
//--------------------------------------------------------------------------------------------------------
        JMenu help = new JMenu("Help");

        JMenuItem about = new JMenuItem("About My Notepad");
        help.add(about);
        about.addActionListener(this);

//---------------------------------------------------------------------------------------------------------
        menu.add(file);
        menu.add(edit);
        menu.add(view);
        menu.add(help);

        setJMenuBar(menu);
//----------------------------------------------------------------------------------------------------------

        area = new JTextArea();
        area.setFont(new Font("Elephant", Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);
        area.addKeyListener((KeyListener) this);

    }

    public static void main(String[] args) {

        new Notepad().setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("New")) {
            area.setText("");

        } else if (e.getActionCommand().equals("Save")) {

               JFileChooser save = new JFileChooser();
               save.setApproveButtonText("Save");
               int action = save.showOpenDialog(this);
               if(action != JFileChooser.APPROVE_OPTION){
                   return;
               }

               File name = new File(save.getSelectedFile() + ".txt");
            BufferedWriter writer =null;
            try{

                    writer = new BufferedWriter(new FileWriter(name));
                    area.write(writer);

            }catch(Exception a){

            }
        }
        else if(e.getActionCommand().equals("Open")){

            JFileChooser open = new JFileChooser();
            open.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("only txt files ", "txt");
            open.addChoosableFileFilter(filter);

            int action = open.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = open.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader , null);


            }catch(Exception a){}


        }
        else if(e.getActionCommand().equals("Exit")){

            System.exit(0);


        }

        //-----------------------------------------------------------------------------------------------------

        else if(e.getActionCommand().equals("Copy")){

            text = area.getSelectedText();
        }
        else if(e.getActionCommand().equals("Paste")){

            area.insert(text , area.getCaretPosition());

        }
        else if(e.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange(" " , area.getSelectionStart() , area.getSelectionEnd() );


        }

        else if(e.getActionCommand().equals("Select All")){

            area.selectAll();
        }

        else if(e.getActionCommand().equals("UNDO")){

                String text = stack.undo();
                area.setText(text);

        }
        else if(e.getActionCommand().equals("REDO")){

            String text = stack.redo();
            area.setText(text);

        }

        else if(e.getActionCommand().equals("Find")){

            new find(area).setVisible(true);


        }
        else if(e.getActionCommand().equals("Replace")){

            new replace(area).setVisible(true);


        }

        //_______________________________________________________________________________________

        else if(e.getActionCommand().equals("Dark")){

                 area.setBackground(Color.black);
                 area.setForeground(Color.white);

        }

        else if(e.getActionCommand().equals("Light")){

            area.setBackground(Color.white);
            area.setForeground(Color.black);
        }
//_____________________________________________________________________________________________________

        else if(e.getActionCommand().equals("About My Notepad")){

            new about().setVisible(true);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE || e.getKeyChar()==',' || e.getKeyChar()=='.' || e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
        {
            stack.push(area.getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
