package julien_origami.sudoku;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by julienpons on 03/02/2017.
 */

public class BddManager {

    private Activity levelActivity;

    public BddManager(Activity levelActivity) {
        this.levelActivity = levelActivity;
    }

    public MonAdapteur getCorrectObject(String idOfObject) {

        ArrayList<SudokuGrid> categories = new ArrayList();

        switch (idOfObject) {

            case "Niveau Facile":
                categories = this.getStringList(1, R.raw.level_0);
                break;

            case "Niveau Moyen":
                categories = this.getStringList(2, R.raw.level_1);
                break;

            case "Niveau Difficile":
                categories = this.getStringList(3, R.raw.level_2);
                break;

            default:
                categories = this.getStringList(1, R.raw.level_0);
                break;
        }

        MonAdapteur dataAdapter = new MonAdapteur(this.levelActivity, categories);
        return dataAdapter;
    }


    public ArrayList<SudokuGrid> getStringList(int bddInfo, int ressourceFile) {
        InputStream is = levelActivity.getResources().openRawResource(ressourceFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String str = new String();
        String buf = str;

        ArrayList<SudokuGrid> categories = new ArrayList();
        if (is != null) {
            try {
                int index = 0;
                while ((str = reader.readLine()) != null) {
                    categories.add(new SudokuGrid(bddInfo, index, 30, str));
                    index++;
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

}
