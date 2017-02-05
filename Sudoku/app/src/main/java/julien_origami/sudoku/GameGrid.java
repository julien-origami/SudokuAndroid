package julien_origami.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameGrid extends AppCompatActivity implements View.OnTouchListener{

    private GridView gridView;
    private int selectedInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_grid);

        TextView textchampsaisie = (TextView) findViewById(R.id.textView4);
        Bundle objetbunble = this.getIntent().getExtras();

        String idGrid = objetbunble.getBundle("passInfo").getString("itemID");

        DatabaseSudoku databaseSudoku = new DatabaseSudoku(this);
        SudokuGrid sudokuGrid = databaseSudoku.getGridById(Integer.parseInt(idGrid));

        //String infoGrid = objetbunble.getBundle("passInfo").getString("itemGrid");
        GridView gridView = (GridView) findViewById(R.id.dessin);
        gridView.setInfoGrid(sudokuGrid.getGrid());
        //textchampsaisie.setText(infoPasse);

        gridView = (GridView) findViewById(R.id.dessin);
        gridView.setOnTouchListener(this);
        selectedInt = 0;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = (int)event.getX();
        int y = (int)event.getY();

        gridView = (GridView) v;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(y <= (9*gridView.rectCote)){
                    if (checkIsPossible()){
                        gridView.cases.get(9*(y/gridView.rectCote)+(x/gridView.rectCote)).setNumCase(selectedInt);
                        gridView.modifyBgColorCaseSelected(-1);
                        selectedInt = 0;
                    }
                }

                if(y <= (11*gridView.rectCote) && y >= (10*gridView.rectCote)) {
                    selectedInt = gridView.clickCases.get(x / gridView.rectCote).getNumCase();
                    gridView.modifyBgColorCaseSelected(selectedInt-1);
                }
                break;
        }

        gridView.invalidate();

        return true;
    }

    public boolean checkIsPossible(){

        return true;
    }
}
