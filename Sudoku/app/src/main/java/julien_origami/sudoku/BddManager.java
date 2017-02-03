package julien_origami.sudoku;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

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
        }

        //ArrayAdapter<SudokuGrid> dataAdapter = new ArrayAdapter(this.levelActivity, android.R.layout.simple_spinner_item, (ArrayList<SudokuGrid>) categories);
        MonAdapteur dataAdapter = new MonAdapteur(this.levelActivity, categories);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return dataAdapter;
    }


    public ArrayList<SudokuGrid> getStringList(int bddInfo){
        ArrayList<SudokuGrid> categories = new ArrayList();
        for(int i=0;i<100;i++){
            categories.add(new SudokuGrid(bddInfo,i,30));
        }
        return categories;
    }
}
