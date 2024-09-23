import javax.swing.JButton;

public class button extends JButton {
    public int row, column;
    public boolean flag;

    button(int row, int column){
        this.row = row;
        this.column = column;
        this.flag = false;
    }
    
}
