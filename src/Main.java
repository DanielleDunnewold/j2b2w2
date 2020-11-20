import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame implements ActionListener {
    JButton button;
    JTextField field;
    JPanel panel;
    int[][] array= new int[20][20];


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

        // creation button
        button = new JButton("start");
        window.add(button);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(300,25));

        //creates field
        field= new JTextField("");
        window.add(field);


        // creates panel
        panel =new JPanel();
        window.add(panel);
        panel.setPreferredSize(new Dimension(500,500));
    }


    /** Pauzeer gedurende x millisecondes (gekopieerd van de onderwijsonline)*/
    public void pauzeer(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            System.out.println("Pauze interruptie");
        }
    }

    private void createseed(int[][] array) {
        int min=0;
        int max=1;
        for(int i=1; i<array.length-1;i++){
            for(int o=1;o<array.length-1;o++) {
                array[i][o]= (int) (Math.random() * (max - min + 1) + min);
            }} }

    private void checkstatus(int[][] array){
        for(int i=1; i<array.length-1;i++){
        for(int o=1;o<array.length-1;o++){
            // checks how many living neighboors it has
            int score= array[i-1][o-1]+array[i-1][o]+array[i-1][o+1]+array[i][o-1]+array[i][o]+array[i][o+1]+
                    array[i+1][o-1]+array[i+1][o]+array[i+1][o+1];
            //checks if the cell is alive
            if(array[i][o]==1) {
                if(score<2||score>3)        // checks if it has less than 2 living neighboors or more than 3
                { array[i][o] = 0; }     // and than dies
                if(score==2||score==3){ //checks is it has 2 or 3 living neighboors and than stays alive
                    array[i][o]=1;
                }
            }
            if(array[i][o]==0){           // checks if the cell is death
                if(score==3) {                   // checks if it has exactly 3 neighboors
                    array[i][o]=1;}                 // and it comes to life
            } } } }

    @Override
    public void actionPerformed(ActionEvent e) {
        int round = 10;
        //int round=Integer.parseInt(field.getText);
        int r = 0;
        createseed(array);
        while (r < round) {
            r++;
            checkstatus(array);
            Graphics paper = panel.getGraphics();
            paper.clearRect(0,0,500,500);
            int x=5;
            int y=5;
            for(int i=1; i<array.length-1;i++){
                y+=11;
                x=5;
                for(int o=1;o<array.length-1;o++){
                    x+=11;
                    if(array[i][o]==1){
                        paper.fillRect(x,y,10,10);
                    }
                    if(array[i][o]==0){
                        paper.drawRect(x,y,10,10);
                    }

                }}
                pauzeer(500);}
    }}


