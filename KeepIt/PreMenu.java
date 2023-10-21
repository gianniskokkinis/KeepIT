import java.awt.*;

import javax.print.attribute.Size2DSyntax;
import javax.swing.*;

import org.w3c.dom.css.Counter;

import java.awt.event.*;
import java.io.File;




public class PreMenu implements ActionListener{

    //vars
    JFrame windowMenu;
    ImageIcon logo = new ImageIcon(".//icons//icon.png");
    JMenuBar exitMenu;
    JMenuItem exitButton;
    
    //count files vars
    String path = "./saveFilesLog";
    File file = new File(path);
    int fileCounter=0;
    int setFiles=0;  


    //get files 
    String[] files = file.list(); // files name 
    JButton[] items = new JButton[files.length]; //button with files



    //create main here 
    





    //test here
    public static void main(String[] args) {
        new PreMenu();
    }

    

    public PreMenu(){
        DisplayMenu();
        DisplayNotes();
        DisplayExitButton(); //DISPLAY EXIT BUTTON
        setFiles = CountFiles();
        System.out.println("Number of Files: "+setFiles);
        windowMenu.setVisible(true); //always display
    }


    public void DisplayMenu(){
        windowMenu = new JFrame("Note Manager");
        windowMenu.setBounds(1200,350,300,450);
        windowMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        windowMenu.setIconImage(logo.getImage());
        windowMenu.getContentPane().setBackground(Color.decode("#444444"));
        windowMenu.setUndecorated(true);
        
        
    }


    public void DisplayNotes(){ // This is the list with the notes
        JPanel noteMenu = new JPanel();
        noteMenu.setBackground(Color.decode("#444444"));
        windowMenu.add(noteMenu);


        for (int i=0; i<items.length; i++){ //initialize buttons and add them to noteMenu
            items[i] = new JButton(files[i]);
            items[i].addActionListener(this); //set this button clickable
            items[i].setActionCommand(files[i]); // set command here
            noteMenu.add(items[i]);
        }
        JButton createNewButton = new JButton("Create New");
        createNewButton.addActionListener(this);
        createNewButton.setActionCommand("createNewFile");
        noteMenu.add(createNewButton);
        noteMenu.add(createNewButton);
        
    }



    public void DisplayExitButton(){
        exitMenu = new JMenuBar();
        windowMenu.setJMenuBar(exitMenu);
        exitButton = new JMenuItem();
        exitButton.addActionListener(this);
        exitButton.setActionCommand("Exit");
        exitButton.setBackground(Color.decode("#EEB76B"));
        exitMenu.setBorder(null);
        exitMenu.add(exitButton);

    }
    
   

    //test code here
    public int CountFiles(){
        // String str[] = file.list();
        // System.out.println("Files: "+ str.length);
        // for(String s: str){
        //     //check extension
        //     String extension="";
        //     boolean afterDot=false;
        //     //take extension of files 
        //     for (int i=0; i<s.length(); i++){
        //         if (Character.toString(s.charAt(i)).equals(".")){
        //             afterDot = true;
        //         }
        //         if (afterDot){
        //             extension+=Character.toString(s.charAt(i));
        //         }
        //     } 
        //     if (extension.equals(".txt")){
        //         fileCounter++;
        //     }
        //     extension="";
        //     afterDot = false;         
        // }
        // System.out.println("Files "+fileCounter);
        return file.list().length;
    }


    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();

        if (command.equals("Exit")){
            windowMenu.dispose();
        }

        //set commands for every button 
        for(String fileName: files){ //check if we call something
            if (command.equals(fileName)){
                System.out.println(fileName);
                new Main("./saveFilesLog/"+fileName);
            }
            
        }

        if (command.equals("createNewFile")){ //create new file
            new Main();
        }
        
        
    }
}
