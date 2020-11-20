import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame implements ActionListener {
    JButton button;
    JTextField field;
    JPanel panel;
    int[][] array= new int[50][50];


    public static void main(String[] args) {
        Main frame = new Main();
        frame.setSize(600, 800);
        frame.createGUI();
        frame.setVisible(true);
        frame.setTitle("Game of life");
    }

    /**
     * this function makes the gui
     */
    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        //creates label
        JLabel title = new JLabel("fill in the desired amount of rounds");
        window.add(title);
        title.setPreferredSize(new Dimension(500,25));

        //creates label
        JLabel label=new JLabel("round: ");
        window.add(label);
        label.setPreferredSize(new Dimension(150,25));

        //creates field
        field= new JTextField("");
        window.add(field);
        field.setPreferredSize(new Dimension(150,25));

        // creation button
        button = new JButton("start");
        window.add(button);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(400,25));


        // creates panel
        panel =new JPanel();
        window.add(panel);
        panel.setPreferredSize(new Dimension(500,500));


    }


    /** pauses the program and catches the needed exception*/
    public void sleep(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
    }

    /**
     * generates the see the program starts with
     * @param array, 2darray hold the information on the status of the cells
     *               1 for life ones; 0 for dead ones
     */
    private void createseed(int[][] array) {
        int min=0;
        int max=10;
        // it randomly selects a number out of this list, so there is a 1/10 change on a 1
        int[] seedlist= new int[]{0,0,0,1,0,0,0,0,0,0,0};
        //loops through all the numbers in the list
        for(int i=1; i<array.length-1;i++){
            for(int o=1;o<array.length-1;o++) {
                //gets a random nummer between the 0 and 10
                int randomnummer = (int) (Math.random() * (max - min + 1) + min);
                //selects the number in the seedlist array on the index that was chosen at random
                array[i][o]= seedlist[randomnummer];
            }} }

    private void checkstatus(int[][] array){
        //loops through all the numbers in the list
        for(int i=1; i<array.length-1;i++){
            for(int o=1;o<array.length-1;o++){
                // checks how many living neighboors it has
                int score= array[i-1][o-1]+array[i-1][o]+array[i-1][o+1]+array[i][o-1]+array[i][o]+array[i][o+1]+
                        array[i+1][o-1]+array[i+1][o]+array[i+1][o+1];
                //checks if the cell is alive
                if(array[i][o]==1) {
                    if(score<2||score>3)        // checks if it has less than 2 living neighboors or more than 3
                    { array[i][o] = 0; }     // and than dies
                }
                if(array[i][o]==0){           // checks if the cell is death
                    if(score==3) {                   // checks if it has exactly 3 neighboors
                        array[i][o]=1;}                 // and it comes to life
                } } } }


    /**
     * plays the game of life if the button gets pressed
     * @param e when the button gets pressed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int round;
        // gets the round from the text field
        try{
        round=Integer.parseInt(field.getText());}
        catch(NumberFormatException numberformatexception) {
            JOptionPane.showMessageDialog(null,
                    numberformatexception.getMessage());
            round=0;
        }

        int r = 0;      //this keeps track on how many rounds are done
        createseed(array); //creates the beginning seed
        //keeps going untill the desired amount of rounds are played
        while (r < round) {
            r++;
            checkstatus(array);  //kills the cells that need to die and retrives those that need to be alife
            Graphics paper = panel.getGraphics();
            int x=5; //keeps up where on the x-axis the rectangles need to be drawn
            int y=5; //keeps up where on the y-axis the rectangles need to be drawn
            //loops through all the numbers in the list
            for(int i=1; i<array.length-1;i++){
                y+=11; //moves down, so the next row is drawn up below the previous one
                x=5; //makes sure the next row starts under the last one
                for(int o=1;o<array.length-1;o++){
                    x+=11; //moves over to the right so the colomns are drawn next to eachother
                    if(array[i][o]==1){//checks if it is alive
                        //draws a black rectangle
                        paper.setColor(Color.black);
                        paper.fillRect(x,y,10,10);
                    }
                    if(array[i][o]==0){ //checks if it dead
                        //draws a white rectangle
                        paper.setColor(Color.white);
                        paper.fillRect(x,y,10,10);
                    }

                }}
                sleep(100);// this slows it down with 100 milisec
             }
    }}


