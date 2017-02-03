package julien_origami.sudoku;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by julienpons on 03/02/2017.
 */

public class BddManager {

    private Activity levelActivity;

    public BddManager(Activity levelActivity){
        this.levelActivity = levelActivity;
    }

    public MonAdapteur getCorrectObject(String idOfObject){

        ArrayList<SudokuGrid> categories = new ArrayList();

        switch (idOfObject){

            case "Niveau Facile":
                categories = this.getStringList(1);
                break;

            case "Niveau Moyen":
                categories = this.getStringList(2);
                break;

            case "Niveau Difficile":
                categories = this.getStringList(3);
                break;

            default:
                categories = this.getStringList(1);
                break;
        }

        MonAdapteur dataAdapter = new MonAdapteur(this.levelActivity, categories);
        return dataAdapter;
    }


    public ArrayList<SudokuGrid> getStringList(int bddInfo){
        ArrayList<SudokuGrid> categories = new ArrayList();
        for(int i=0;i<100;i++){
            categories.add(new SudokuGrid(bddInfo,i,30,bddInfo+" "+i));
        }
        return categories;
    }
}
