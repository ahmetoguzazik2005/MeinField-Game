

import java.util.Random;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;


import java.awt.*;

public class mayinTarlasi {
        public static boolean firstTime = true;
        public static int counter = 0;
        int rowNumber , colNumber ;
        public static String choice = "no";
        public static int rowNow, columnNow;
        Boolean [][] mineBool; 
        int [][] howManyMineArr; 
        Boolean [][] check; // I made a check list for putting true for all the places that have been played in order to be able to check wheter the player win or not.
        String [][] show; // What player see
        button [][] buttonArr;
        int mineNumber;
        ImageIcon flag = new ImageIcon("flag.png");
        ImageIcon flagSelected = new ImageIcon("flagSelected.png");
        ImageIcon mine = new ImageIcon("mine.png");



        boolean isGameCont = true;
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        boolean win;
        boolean winControl = false;
        int leftFlag; //  leftFlag = total mine number - number of flag you put
        int totalMineNumber; //  total mine number 
        int totalFlag = 0;//number of flag you put        
        public static JFrame f;
        public static JPanel jPanel, panel;
        public static JTextArea t;
        public static JButton flagButton;
        public static JButton cancelButton;


        mayinTarlasi( int rowNumber, int colNumber ){
            this.rowNumber = rowNumber;
            this.colNumber = colNumber;
            this.mineBool = new Boolean[rowNumber][colNumber];
            this.check = new Boolean[rowNumber][colNumber];
            for ( int c = 0; c < colNumber; c++ ){
                for ( int r = 0; r < rowNumber; r++ ){
                    mineBool[r][c] = false;
                    check[r][c] = false;
                }
            }
            this.howManyMineArr = new int[rowNumber][colNumber];
            this.show = new String[rowNumber][colNumber];
            this.buttonArr = new button[rowNumber][colNumber];
        }
        public int mineNumberCalculater(String difficulty ){
            if ( difficulty.equals("easy")){

                totalMineNumber = colNumber * rowNumber * 1 / 10;
                return colNumber * rowNumber * 1 / 10 ;
            }
            else if ( difficulty.equals("medium")){

                totalMineNumber = colNumber * rowNumber * 15 / 100;
                return colNumber * rowNumber * 15 / 100 ; 
                
            }
            else if (difficulty.equals("hard") ){

                totalMineNumber = colNumber * rowNumber * 2 / 10;
                return colNumber * rowNumber * 2 / 10 ;
            }
            else{

                totalMineNumber = 0;
                return 0;
            }

        }
        public void winCheck(){
            if ( !winControl ){
                win = true;
                int checkNumber = 0;
                for ( int r = 0; r < check.length; r++ ){
                for ( int c = 0; c < check[0].length; c++){
                    if ( show[r][c].equals("0")){
                        checkNumber++;
                    }
                    if ( mineBool[r][c] == true ){
                        if( !show[r][c].equals("S") ){
                            win = false;

                        }
                    }

                }
            }
            
            if ( checkNumber == 0 && win ){
                
                t.setText("You won the game, congratulations.");
                    // Create a timer and start it
                    Timer flagTimer = new Timer(2000, (ActionEvent e) -> {
                    // Action performed after 2 seconds
                    System.exit(0); // Programı tamamen durdurure
                    });
                    flagTimer.setRepeats(false);  // Only execute once
                    flagTimer.start();  // Start the timer

                        
                    

                
                isGameCont = false;
                winControl = true;
            }
           }  
        
           
        }
        public void run() {

            winCheck();
                
                if ( isGameCont ){
                    int row = rowNow;
                    int column = columnNow;
                    
                    if ( mineBool[row][column] == false ){
                        if ( check[row][column] == false){
                            howManyMines(row, column);
                        }
                        else{
                        t.setText("You have already entered this location, enter another location");
                        // Create a timer and start it
                        Timer flagTimer = new Timer(2000, (ActionEvent e) -> {
                        // Action performed after 2 seconds
                         t.setText("");
                        });
                        flagTimer.setRepeats(false);  // Only execute once
                        flagTimer.start();  // Start the timer
                                    
                               

                            
                        }
                    }
                    else{
                        if ( firstTime ){
                            while ( mineBool[rowNow][columnNow] == true  ){
                                for ( int c = 0; c < colNumber; c++ ){
                                    for ( int r = 0; r < rowNumber; r++ ){
                                        mineBool[r][c] = false;
                                        check[r][c] = false;
                                        show[r][c] = "0";
                                    }
                                }
                            
                                prepareGame(totalMineNumber);
                            }
                            howManyMines(rowNow, columnNow);
                            MapChanger();
                            
                            

                        }
                        else{
                            isGameCont = false;
                            t.setText("You have losted the game, game over.");
                            for ( int r = 0; r < rowNumber; r++ ){
                                for ( int c = 0; c < colNumber; c++ ){
                                    if ( mineBool[r][c] ){
                                        buttonArr[r][c].setIcon(mine);
                                    }
                                }
                            }
                        // Create a timer and start it
                        Timer flagTimer = new Timer(2000, (ActionEvent e) -> {
                        // Action performed after 2 seconds
                        System.exit(0); // Programı tamamen durdurur
                        });
                        flagTimer.setRepeats(false);  // Only execute once
                        flagTimer.start();  // Start the timer

                            
                                    
                                

                                
                        }
                        
                    }
                    // Checking for if the player win the game
                    winCheck();

                }
        


        }
        public void prepareGame( int mineNumber ){
            int randRow, randCol;

            for ( int i = 1; i <= mineNumber; i++){
                randCol = rand.nextInt(0 , colNumber );
                randRow = rand.nextInt(0 , rowNumber );
                if ( mineBool [randRow] [randCol] ==  false){ // 9 = mine
                    mineBool [ randRow] [randCol] = true;
                    check [ randRow ][ randCol ] = true;
                }
                else{
                    i--;
                }

            }

        }
        
        public void print( ){
            totalFlag = 0;
            for ( int c = 0; c < colNumber; c++ ){
                for ( int r = 0; r < rowNumber; r++ ){
                    if ( buttonArr[r][c].flag ){
                        totalFlag++;
                    } 

                }
            }       


        if( counter == 0 ){ 
            t.setText("Game is starting, enjoy!");          
                // Create a timer and start it
                Timer flagTimer = new Timer(2000, (ActionEvent e) -> {
                    // Action performed after 2 seconds
                    leftFlag = totalMineNumber - totalFlag;
                    t.setText(" Total mine number is: "  + totalMineNumber +
                            "\n You put " + totalFlag + " signs" +
                            "\n You have " + leftFlag + " signs to put.");
                    });
                    flagTimer.setRepeats(false);  // Only execute once
                    flagTimer.start();  // Start the timer            
            counter ++;
        }else{
        
            leftFlag = totalMineNumber - totalFlag;
            t.setText( " Total mine number is: "  + totalMineNumber +
                    "\n You put " + totalFlag + " signs" +
                    "\n You have " + leftFlag + " signs to put.");
        }
   
        }

        public void howManyMines( int row, int column ){
            int mineCounter = 0;
            if ( row > 0 && row < mineBool.length - 1 && column > 0 && column < mineBool[0].length - 1 ){//ortalar
                for ( int i = 0; i < 3; i++){
                    if ( mineBool [row -1][column - 1 + i] == true){
                        mineCounter++;
                    }
                }
                if ( mineBool[row][column-1] == true){
                    mineCounter++;
                }
                if ( mineBool[row][column+1] == true){
                    mineCounter++;
                }

            
                for ( int i = 0; i < 3; i++){
                    if ( mineBool [row + 1][column - 1 + i] == true){
                        mineCounter++;
                    }
                }
            howManyMineArr[row][column] = mineCounter; 
            check[row][column] = true;
            if ( mineCounter == 0 ){
                checkNear(row, column);
            }
            else{
                mineCounter = 0; 
            }
              
            } 
            else if( row == 0 && column == 0){// sol üst köşe
                if ( mineBool[0][1] == true){
                    mineCounter++;
                }
                if ( mineBool[1][1] == true ){
                    mineCounter++;
                }
                if ( mineBool[1][0] == true ){
                    mineCounter++;
                }
                howManyMineArr[row][column] = mineCounter;
                check[row][column] = true; 
                if ( mineCounter == 0 ){
                    checkNear(row, column);
                }
                else{
                    mineCounter = 0; 
                } 
            }

            else if ( row == 0 && column == mineBool[0].length - 1 ){// sağ üst köşe
                if ( mineBool[0][mineBool[0].length - 2] == true){
                    mineCounter++;
                }
                if ( mineBool[1][mineBool[0].length - 2] == true ){
                    mineCounter++;
                }
                if ( mineBool[1][mineBool[0].length - 1] == true ){
                    mineCounter++;
                }
                howManyMineArr[row][column] = mineCounter; 
                check[row][column] = true;
                if ( mineCounter == 0 ){
                    checkNear(row, column);
                }
                else{
                    mineCounter = 0; 
                } 
            }
            else if ( row == mineBool.length - 1 && column == 0 ){// sol alt köşe
                if ( mineBool[mineBool.length - 2][0] == true ){
                    mineCounter++;
                }
                if ( mineBool[mineBool.length - 2][1] == true ){
                    mineCounter++;
                }
                if ( mineBool[mineBool.length - 1][1] == true ){
                    mineCounter++;
                }
                howManyMineArr[row][column] = mineCounter; 
                check[row][column] = true;
                if ( mineCounter == 0 ){
                    checkNear(row, column);
                }
                else{
                    mineCounter = 0; 
                }  
            }
            else if ( row == mineBool.length - 1 && column == mineBool[0].length - 1){// sağ alt köşe
                if ( mineBool[mineBool.length - 2][mineBool[0].length - 1] == true ){
                    mineCounter++;
                }
                if( mineBool[mineBool.length - 2][mineBool[0].length - 2] == true ){
                    mineCounter++;
                }
                if ( mineBool[mineBool.length - 1][mineBool[0].length - 2] == true ){
                    mineCounter++;
                }
                howManyMineArr[row][column] = mineCounter; 
                check[row][column] = true;
                if ( mineCounter == 0 ){
                    checkNear(row, column);
                }
                else{
                    mineCounter = 0; 
                } 

            }
            else if ( row == 0 && column != 0 && column != mineBool[0].length - 1){// en üst sıra köşeler hariç
                if ( mineBool[row][column - 1] == true ){
                    mineCounter++;
                }
                if ( mineBool[row + 1][column - 1] == true ){
                    mineCounter++;
                }
                if( mineBool[row + 1][column] == true ){
                    mineCounter++;
                }
                if( mineBool[row ][column + 1] == true ){
                    mineCounter++;

                }
                if( mineBool[row + 1][column + 1] == true ){
                    mineCounter++;
                }
                howManyMineArr[row][column] = mineCounter; 
                check[row][column] = true;
                if ( mineCounter == 0 ){
                    checkNear(row, column);
                }
                else{
                    mineCounter = 0; 
                } 
            }
            else if ( row != 0 && row != mineBool.length - 1 && column == 0 ){// sol sıra köşeler hariç
                if ( mineBool[row - 1][column] == true ){
                    mineCounter++;
                }
                if ( mineBool[row - 1 ][column + 1] == true){
                    mineCounter++;
                }
                if ( mineBool[row][column + 1] == true){
                    mineCounter++;
                }
                if ( mineBool[row + 1][column + 1 ] == true ){
                    mineCounter++;
                }
                if (mineBool[row + 1][column] == true){
                    mineCounter++;
                }
                howManyMineArr[row][column] = mineCounter; 
                check[row][column] = true;
                if ( mineCounter == 0 ){
                    checkNear(row, column);
                }
                else{
                    mineCounter = 0; 
                }  

            }
            else if ( column == mineBool[0].length - 1  && row != 0 && row != mineBool.length - 1 ){// sağ sıra köşeler hariç
                if ( mineBool[row - 1][column] == true ){
                    mineCounter++;
                }
                if ( mineBool[row - 1 ][column - 1] == true){
                    mineCounter++;
                }
                if ( mineBool[row][column - 1] == true){
                    mineCounter++;
                }
                if ( mineBool[row + 1][column - 1 ] == true ){
                    mineCounter++;
                }
                if (mineBool[row + 1][column] == true){
                    mineCounter++;
                }
                howManyMineArr[row][column] = mineCounter; 
                check[row][column] = true;
                if ( mineCounter == 0 ){
                    checkNear(row, column);
                }
                else{
                    mineCounter = 0; 
                } 

            }
            else if ( row == mineBool.length - 1  && column != 0 && column != mineBool[0].length - 1 ){// en alt sıra köşeler hariç
                if ( mineBool[row ][column - 1] == true ){
                    mineCounter++;
                }
                if ( mineBool[row - 1 ][column - 1] == true ){
                    mineCounter++;
                }
                if ( mineBool[row - 1][column ] == true ){
                    mineCounter++;
                }
                if ( mineBool[row - 1][column + 1 ] == true  ){
                    mineCounter++;
                }
                if (mineBool[row][column + 1] == true ){
                    mineCounter++;
                }
                howManyMineArr[row][column] = mineCounter; 
                check[row][column] = true;
                if ( mineCounter == 0 ){
                    checkNear(row, column);
                }
                else{
                    mineCounter = 0; 
                }  

            }
            MapChanger();
            
        }

        public void checkNear(int row, int column ){
            if ( row - 1 >= 0 && column - 1 >= 0  && check[row - 1][column - 1]  != true){

                howManyMines( row - 1 , column - 1);
            }
            if ( row - 1 >= 0  && check[row - 1][column]  != true){
                howManyMines(row - 1 , column);
            }
            if ( row - 1 >= 0 && column + 1 < colNumber  && check[row - 1][column + 1]  != true){
                howManyMines(row - 1 , column + 1 );
            }
            if ( column - 1 >= 0  && check[row ][column - 1]  != true){
                howManyMines(row, column - 1);
            }
            if ( column + 1 < colNumber  && check[row ][column + 1]  != true){
                howManyMines ( row, column + 1 );
            }
            if ( row + 1 < rowNumber && column - 1 >= 0 && check[row + 1][column - 1]  != true ){
                howManyMines(row + 1 , column - 1 );
            }
            if ( row + 1 < rowNumber  && check[row + 1][column]  != true){
                howManyMines(row + 1, column);
            }
            if ( row + 1 < rowNumber && column + 1 < colNumber && check[row + 1][column + 1]  != true ){
                howManyMines(row + 1, column + 1);
            }
        }

      public void MapChanger(){
            for ( int r = 0; r < rowNumber; r++ ){
                for ( int c = 0; c < colNumber; c++ ){
                    if ( mineBool[r][c] == false && check[r][c] == true && howManyMineArr[r][c] == 0 ){
                        show[r][c] = "B"; // This means empty
                        if ( buttonArr[r][c].flag ){
                            buttonArr[r][c].setIcon(null);
                            buttonArr[r][c].flag = false;


                        }
                        buttonArr[r][c].setBackground(new Color(255, 102, 51));
                        firstTime = false;
                    }
                    else if( mineBool[r][c] == false && check[r][c] == true && howManyMineArr[r][c] != 0 ){
                        show[r][c] = String.valueOf(howManyMineArr[r][c]);
                        if ( buttonArr[r][c].flag ){
                            buttonArr[r][c].setIcon(null);
                            buttonArr[r][c].flag = false;
                        }
                        buttonArr[r][c].setBackground(new Color(255, 102, 51));
                        buttonArr[r][c].setText(String.valueOf(howManyMineArr[r][c]));
                        firstTime = false;
                    }

                }
        
            }
            print();
            
        }
        public void MapBuilder(){
            f = new JFrame();
            f.setBounds(0, 0, 500, 600);
            jPanel = new JPanel();
		    jPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            f.setContentPane(jPanel);
            jPanel.setLayout(null);
            JPanel panel = new JPanel();
            panel.setBounds(10, 85, 466, 466);
            jPanel.add(panel);
            t = new JTextArea();
            t.setBounds(10,0,400,75);
            t.setEditable(false);
            t.setForeground(new Color(128, 64, 0));
            t.setLineWrap(true);
            jPanel.add(t);
            panel.setLayout(new GridLayout(rowNumber, colNumber, 5, 5));
            flagButton = new JButton();
            flagButton.setBorderPainted(false);
            flagButton.setContentAreaFilled(true);
            flagButton.setFocusPainted(false);
            flagButton.setBounds(435, 0, 30, 30);
            flagButton.setIcon(flag);
            jPanel.add(flagButton);
            flagButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    flagButton.setIcon(flagSelected);
                if ( totalFlag < totalMineNumber ){

                    choice = "yes";
                }
                    
                    
                    
                }
            });

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    flagButton.setIcon(flag);
                    choice = "no";           
                    
                }
                
            });
            cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
            cancelButton.setBackground(Color.LIGHT_GRAY);
            cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
            cancelButton.setBounds(420,35,65,30);
            jPanel.add(cancelButton);
            for (int r = 0; r < rowNumber; r++) {
                for (int c = 0; c < colNumber; c++) {
                    show[r][c] = "0";
                    button button = new button(r, c);
                    button.setBackground(new Color(255, 204, 153));
                    panel.add(button);
                    buttonArr[r][c] = button;

            
                    button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        totalFlag = 0;

                        // Left click
                        if (SwingUtilities.isLeftMouseButton(e)) {
                            for (int c = 0; c < colNumber; c++) {
                                for (int r = 0; r < rowNumber; r++) {
                                    if (buttonArr[r][c].flag) {
                                        totalFlag++;
                                    }
                                }
                            }

                            rowNow = button.row;
                            columnNow = button.column;
                            if (!choice.equals("yes")) {
                                run();
                            } else if (totalMineNumber > totalFlag) {
                                putFlag();
                            } else {
                                t.setText("You cannot put any other flags, you have reached the limit");
                            }
                        }

                        // Right click
                        if (SwingUtilities.isRightMouseButton(e)) {
                            rowNow = button.row;
                            columnNow = button.column;
                            removeFlag();
                            

                        }
                    }
                });
                }
            }
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
        }
        public void putFlag(){
            winCheck();
            if(choice.equals("yes") && isGameCont){
                t.setText("Choose a block to put flag.");
                // Create a timer and start it
                Timer flagTimer = new Timer(2000, (ActionEvent e) -> {
                // Action performed after 2 seconds
                });
                flagTimer.setRepeats(false);  // Only execute once
                flagTimer.start();  // Start the timer
                    
                

                

                if ( show[rowNow][columnNow].equals("0")){
                    show[rowNow][columnNow] = "S"; //Means that there might be a bomb.
                    buttonArr[rowNow][columnNow].setIcon(flag);
                    buttonArr[rowNow][columnNow].flag = true;
                    t.setText(""); // Clear the message

                    

                    }
                else{
                         
                    t.setText("Not a valid location.");

                    // Create a timer and start it
                    Timer flagTimer2 = new Timer(2000, (ActionEvent e) -> {
                    // Action performed after 2 seconds
                    });
                    flagTimer2.setRepeats(false);  // Only execute once
                    flagTimer2.start();  // Start the timer

                    }

                    MapChanger();
                   
            
            winCheck();
            }

           
        }
        public void removeFlag(){
            show[rowNow][columnNow] = "0"; //Means that there might be a bomb.
            buttonArr[rowNow][columnNow].setIcon(null);
            buttonArr[rowNow][columnNow].flag = false;
            t.setText(""); // Clear the message

            MapChanger();


        }
    }
