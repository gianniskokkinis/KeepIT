import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.Random;
import javax.swing.ImageIcon;




import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Main implements ActionListener,KeyListener{


    //initialize window
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;

    //initialize icon
    ImageIcon logo = new ImageIcon(".//icons//icon.png");

    //initialize fonts
    Font defaultFont = new Font("Comic Sans MS", Font.PLAIN, 28);

    //menu
    JMenuBar menu;
    JMenuItem exit;

    //vars for shortcuts
    String fileName;
    String filePath;

    //random Potision windows
    Random random = new Random();

    //set potisions for move 

    private int x;
    private int y;

    //constructor
    public Main(){
        DisplayWindow(random.nextInt(450), random.nextInt(350));
        DisplayTextArea();
        DisplayMenu();
        //always display
        window.setVisible(true);
    }



    //constructor with argument
    public Main(String filePath){
        this.filePath = filePath;
        x = random.nextInt(450);
        y = random.nextInt(350);
        DisplayWindow(x, y);
        DisplayTextArea(); //here change
        System.out.println("The path is: "+filePath);//test
        open(filePath);
        DisplayMenu();
        //always display
        window.setVisible(true);
    }

    //maybe i don't need this
    public static void main(String[] args) {
        new Main();

    }

    public void DisplayWindow(int x, int y){
        window = new JFrame("KeepIt");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setBounds(x,y, 800, 600);
        window.setIconImage(logo.getImage()); //set logo
        window.getContentPane().setBackground(Color.decode("#444444"));
        window.setUndecorated(true); //no frame

    }

    public void DisplayTextArea(){
        textArea = new JTextArea("Shortcuts:\nCtrl+O - Open files \nCtrl+S - Save files \nCtrl+N - Create New Window \nCtrl+X - Exit Window \nClick on top to exit...\n");
        textArea.setFont(defaultFont);
        textArea.addKeyListener(this);
        textArea.setBackground(Color.decode("#444444"));
        textArea.setForeground(Color.decode("#E2703A"));
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollPane);
    }

    public void DisplayMenu(){
        menu = new JMenuBar();
        window.setJMenuBar(menu);
        exit = new JMenuItem();
        exit.addActionListener(this);
        exit.setActionCommand("Exit");
        exit.setBackground(Color.decode("#EEB76B"));
        menu.setBorder(null);
        menu.add(exit);

    }

    /**key shortcuts functions */
    public void save(){
        if (fileName == null){
            /** Save Us function */
            FileDialog saveFile = new FileDialog(window, "Save As", FileDialog.SAVE);
            saveFile.setVisible(true);
            if(saveFile.getFile() != null){
                fileName = saveFile.getFile();
                filePath = saveFile.getDirectory();
                window.setTitle(fileName);
            }
            try {
                FileWriter fileWriter = new FileWriter(filePath+fileName);
                fileWriter.write(textArea.getText());
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }
        else{
            try {
                FileWriter fileWriter = new FileWriter(filePath+fileName);
                fileWriter.write(textArea.getText());
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("ERROR");
            }
        }
    }

    public void open(){  //open without argument and dialog box
        FileDialog fileDialog = new FileDialog(window, "Open", FileDialog.LOAD);
        fileDialog.setVisible(true);
        if(fileDialog.getFile() != null){
            fileName = fileDialog.getFile();
            filePath = fileDialog.getDirectory();
            window.setTitle(fileName);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath + fileName));
            textArea.setText("");//set empty area to load new file
            String line;
            while ((line = br.readLine()) != null){
                textArea.append(line + "\n");
            }
            br.close();
        } catch (Exception c) {
            System.out.println("ERROR FILE NOT FOUND");
        }
    }

    public void open(String fileNameAndPath){ //open with argument and without file path
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileNameAndPath));
            textArea.setText("");//set empty area to load new file
            String line;
            while ((line = br.readLine()) != null){
                textArea.append(line + "\n");
            }
            br.close();
        } catch (Exception c) {
            System.out.println("ERROR FILE NOT FOUND");
        }

    }


    //mew method here move with shortcuts
    public void move(int pixel,String command){

      /*
      * Down
      * UP
      * RIGHT
      * LEFT
      */

        if (command.equals("DOWN")){

            y+=pixel;
            window.setBounds(x,y, 800, 600);

        }if (command.equals("UP")){

            y-=pixel;
            window.setBounds(x,y, 800, 600);

        }if (command.equals("RIGHT")){
            x+=pixel;
            System.out.println("X = "+x);
            window.setBounds(x,y, 800, 600);

        }if (command.equals("LEFT")){
            System.out.println("LEFT SET COMMAND");
            x-=pixel;
            System.out.println("X = "+x);
            window.setBounds(x,y, 800, 600);
        }

    }




    /** functions */

    @Override
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();

        //exit command
        if (cmd.equals("Exit")){
                window.dispose();
        }
    }

    /********************************************
     *
     * initialize key bindings
     *
     */

    public void keyTyped(KeyEvent kt){

    }

    public void keyPressed(KeyEvent kp){

        //Ctrk+N
        if (kp.isControlDown() && kp.getKeyCode() == KeyEvent.VK_N){
            System.out.println("Created new!");
            new Main();

        }

        //Ctrl+X
        if (kp.isControlDown() && kp.getKeyCode() == KeyEvent.VK_X){
            window.dispose();
        }

        //Ctrl+S (save or save as)
        if(kp.isControlDown() && kp.getKeyCode() == KeyEvent.VK_S){
            save();
        }

        //Ctrl+O (open file)
        if (kp.isControlDown() && kp.getKeyCode() == KeyEvent.VK_O){
            open();
        }

        // Ctrl + Down
        if (kp.getKeyCode() == KeyEvent.VK_DOWN && kp.isControlDown()){
            move(30,"DOWN");
        }

        // Ctrl + UP
        if (kp.getKeyCode() == KeyEvent.VK_UP && kp.isControlDown()){
            move(30,"UP");
        }
        
        // Ctrl + RIGHT
        if (kp.getKeyCode() == KeyEvent.VK_RIGHT && kp.isControlDown()){
            move(30,"RIGHT");
        }

        // Ctrl + LEFT
        if (kp.getKeyCode() == KeyEvent.VK_LEFT && kp.isControlDown()){
            move(30,"LEFT");
        }
    }

    public void keyReleased(KeyEvent kr){

    }

}
