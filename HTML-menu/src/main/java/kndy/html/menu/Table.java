package kndy.html.menu;

public class Table extends Bloque{

    private int row, col;
    
    public String toString(){
        
        String res = "\t\t<table id=\""+this.getId()+"\">\n";
        for(int i = 1; i<=row; i++){
            res = res + "\t\t\t<tr>\n";
            res = res + "\t\t\t\t";
            for(int j = 1; j<=col; j++){
                res = res + "<td></td> ";
            }
            res = res + "\n\t\t\t</tr>\n";
        }
        res = res +"\t\t</table>\n";
        
        return res;
        
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Table(int row, int col, String id) {
        super(id);
        this.row = row;
        this.col = col;
    }
}