package julien_origami.sudoku;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by julienpons on 03/02/2017.
 */

public class BddManager{

    private Activity levelActivity;
    private DatabaseSudoku databaseSudoku;

    public BddManager(Activity levelActivity) {
        this.levelActivity = levelActivity;
        databaseSudoku = new DatabaseSudoku(levelActivity);
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
                categories = this.getStringList(0, R.raw.level_0);
                break;
        }

        MonAdapteur dataAdapter = new MonAdapteur(this.levelActivity, categories);
        return dataAdapter;
    }


    public ArrayList<SudokuGrid> getStringList(int bddInfo, int ressourceFile) {
        /*InputStream is = levelActivity.getResources().openRawResource(ressourceFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String str = new String();
        String buf = str;*/

        //ArrayList<SudokuGrid> categories = databaseSudoku.getGridsByLevel(bddInfo);
        /*if (is != null) {
            try {
                int index = 0;
                while ((str = reader.readLine()) != null) {
                    databaseSudoku.ajouter(new SudokuGrid((bddInfo*9+index)/*ID PROVISOIRE* /,bddInfo, index, 0, str));
                    categories.add(new SudokuGrid((bddInfo*9+index)/*ID PROVISOIRE* /,bddInfo, index, 30, str));
                    index++;
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        /*for(int i=0;i<10;i++){
            SudokuGrid sudokuGrid = new SudokuGrid((bddInfo*9+i)/*ID PROVISOIRE* /,bddInfo, i, 0, i+"08203500009670408346050702430010059967005001000496203280034067703500904004107020");
            databaseSudoku.ajouter(sudokuGrid);
            categories.add(sudokuGrid);
        }*/
        return databaseSudoku.getGridsByLevel(bddInfo);
    }

    public void constructDatabase(){
        TextView counter = (TextView) levelActivity.findViewById(R.id.countDbLoading);
        for(int x = 1; x<4;x++) {
            int ressourceName ;
            switch (x){
                case 0:
                    ressourceName = R.raw.level_0;
                    break;
                case 1:
                    ressourceName = R.raw.level_1;
                    break;
                case 2:
                    ressourceName = R.raw.level_2;
                    break;
                default:
                    ressourceName = R.raw.level_0;
                    break;
            }

            InputStream is = levelActivity.getResources().openRawResource(ressourceName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String str = new String();
            String buf = str;
            String playerGrid = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";

            //ArrayList<SudokuGrid> categories = new ArrayList();
            if (is != null) {
                try {
                    int index = 0;
                    while ((str = reader.readLine()) != null) {
                        if(index<=1000) {
                            if(index%100==0){
                                Log.d("show index Number", index+"");
                                //counter.setText((x-1 * 1000 + index)+"");
                            }
                            SudokuGrid sudokuGrid = new SudokuGrid((x * 9 + index), x, index, 0, str);
                            sudokuGrid.setPlayerGrid(playerGrid);
                            databaseSudoku.ajouter(sudokuGrid);
                            //categories.add(new SudokuGrid((x * 9 + index)/*ID PROVISOIRE*/, x, index, 30, str));
                        }
                        index++;
                    }
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        counter.setText("La base de donnée est chargée");
    }

}
