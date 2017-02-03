package julien_origami.sudoku;

/**
 * Created by julienpons on 03/02/2017.
 */

public class SudokuGrid {

    private int level;
    private int num;
    private int done;
    private String grid;

    public SudokuGrid(int level, int num, int done, String grid){
        this.level = level;
        this.num = num;
        this.done = done;
        this.grid = grid;
    }

    public int getDone(){
        return done;
    }

    public int getNum(){
        return num;
    }

    public int getLevel(){
        return level;
    }

    public String getGrid(){
        return grid;
    }

    public String toString(){
        return "Grid n°"+num+" level "+level+" \n "+done+"%";
    }
}
