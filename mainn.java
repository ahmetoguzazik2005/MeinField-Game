import javax.swing.JOptionPane;
public class mainn {
    public static void main(String[] args) {
        int row, column;
        // Show a welcome message using JOptionPane
        JOptionPane.showMessageDialog(null, "Welcome to the Minefield game!");

        // Prompt the user to enter the row size
        String rowInput = JOptionPane.showInputDialog(null, 
        "Please enter the row size. Row size must be 3 < row size < 20:");
        row = Integer.parseInt(rowInput); // Convert string input to an integer
        while ( row < 4 || row > 20 ){
            rowInput = JOptionPane.showInputDialog(null, 
            "Please enter the row size. Row size must be 3 < row size < 20:");
            row = Integer.parseInt(rowInput); // Convert string input to an integer
        }
        
        // Prompt the user to enter the column size
        String columnInput = JOptionPane.showInputDialog(null, 
        "Please enter the column size. Column size must be 3 < column size < 20:");
        column = Integer.parseInt(columnInput); // Convert string input to an integer
        while ( column < 4 || column > 20 ){
            columnInput = JOptionPane.showInputDialog(null, 
            "Please enter the column size. Column size must be 3 < column size < 20:");
            column = Integer.parseInt(columnInput); // Convert string input to an integer
        }

        mayinTarlasi Map1 = new mayinTarlasi(row, column);

        String difficulty = JOptionPane.showInputDialog(null, 
        "Choose difficulty level.\nEasy, %10 mine \nMedium, %15 mine\nHard, %20 mine\nDifficulty: ");

        difficulty = difficulty.toLowerCase();
        

        int mineNumber = Map1.mineNumberCalculater(difficulty);
        while( mineNumber == 0 ){
            difficulty = JOptionPane.showInputDialog(null, 
            "You have entered invalid hardness level for game, choose difficulty level again.\nChoose difficulty level.\nEasy, %10 mine \nMedium, %15 mine\nHard, %20 mine\nDifficulty: ");
            mineNumber = Map1.mineNumberCalculater(difficulty);
        }
        
        Map1.prepareGame(mineNumber);
        Map1.MapBuilder();
        Map1.MapChanger();
        

    }
}
