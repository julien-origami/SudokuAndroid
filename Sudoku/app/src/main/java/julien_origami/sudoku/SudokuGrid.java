package julien_origami.sudoku;

/**
 * Created by julienpons on 03/02/2017.
 */

public class SudokuGrid {

    private int level;
    private int num;
    private int done;

    public SudokuGrid(int level, int num, int done){
        this.level = level;
        this.num = num;
        this.done = done;
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

    public String toString(){
        return "Grid n°"+num+" level "+level+" \n "+done+"%";
    }
}
