package julien_origami.sudoku;

import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

public class GameGrid extends AppCompatActivity implements View.OnTouchListener{

  private GridView gridView;
  private int selectedInt;
  private SudokuGrid sudokuGrid;
  private DatabaseSudoku databaseSudoku;
  private TextView textWin;
  private Chronometer chronometer;
  private boolean chronometerIsRunning;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_grid);

    Bundle objetbunble = this.getIntent().getExtras();

    String idGrid = objetbunble.getBundle("passInfo").getString("itemID");

    databaseSudoku = new DatabaseSudoku(this);
    sudokuGrid = databaseSudoku.getGridById(Integer.parseInt(idGrid));
    gridView = (GridView) findViewById(R.id.dessin);
    gridView.setSudokuGrid(sudokuGrid);

    TextView gridTitle = (TextView) findViewById(R.id.textView4);
    gridTitle.setText("Niveau "+sudokuGrid.getNum());
    Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fontai.ttf");
    gridTitle.setTypeface(typeface);

    textWin = (TextView) findViewById(R.id.textWin);
    textWin.setTypeface(typeface);
    textWin.setText("");

    gridView = (GridView) findViewById(R.id.dessin);
    gridView.getCurrentGrid();
    gridView.setOnTouchListener(this);
    selectedInt = 0;

    chronometer = (Chronometer) findViewById(R.id.chronometer2);
    int chronometerValue = sudokuGrid.getChrono();
    chronometer.setBase(SystemClock.elapsedRealtime() - chronometerValue*1000);
    chronometer.start();

    if(sudokuGrid.getDone()==100){
      textWin.setText("Niveau Gagné");
      chronometer.stop();
      chronometerIsRunning = false;
    }else {
      chronometerIsRunning = true;
    }

  }


  public void levelWin(){
    textWin.setText("Niveau Gagné");
    for (Case caseBg : gridView.cases){
      if (caseBg.getNumCase()==0){
        textWin.setText("");
        long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
        sudokuGrid.setChrono((int)elapsedMillis/1000);
        chronometer.stop();
        chronometerIsRunning = false;
      }
    }
  }


  @Override
  public void onPause(){
    super.onPause();
    if(chronometerIsRunning){
      long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
      sudokuGrid.setChrono((int)elapsedMillis/1000);
    }
    sudokuGrid.setPlayerGrid(gridView.getCurrentGrid());
    sudokuGrid.setDone(gridView.getCurrentDone());
    databaseSudoku.update(sudokuGrid);
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {

    int x = (int)event.getX();
    int y = (int)event.getY();

    gridView = (GridView) v;

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if(y <= (9*gridView.rectCote)){
          int caseSelected = 9*(y/gridView.rectCote)+(x/gridView.rectCote);
          if (checkIsPossible(caseSelected, gridView)){
            gridView.cases.get(caseSelected).setNumCase(selectedInt);
            gridView.modifyBgColorCaseSelected(-1);
            selectedInt = 0;
          }
        }

        if(y <= (11*gridView.rectCote) && y >= (10*gridView.rectCote)) {
          int newSelectedInt = gridView.clickCases.get(x / gridView.rectCote).getNumCase();
          if(selectedInt == newSelectedInt){
            selectedInt = 0;
          }else {
            selectedInt = newSelectedInt;
          }
          gridView.modifyBgColorCaseSelected(selectedInt-1);
        }
        break;
    }

    gridView.invalidate();
    levelWin();
    return true;
  }

  public boolean checkIsPossible(int caseSelected, GridView gridView){
    for (Case caseBg : gridView.cases){
      caseBg.setDrawBackground(false);
    }

    boolean res = true;

    for(int i = (caseSelected-caseSelected%9);i<(caseSelected-caseSelected%9)+9;i++){
      if(i!=caseSelected && selectedInt > 0) {
        if (selectedInt == gridView.cases.get(i).getNumCase()){
          gridView.cases.get(i).setDrawBackground(true);
          res = false;
        }
      }
    }

    for(int i = ((caseSelected)%9);i<=((caseSelected)%9)+72;i+=9){
      if(i!=caseSelected && selectedInt > 0) {
        if (selectedInt == gridView.cases.get(i).getNumCase()){
          gridView.cases.get(i).setDrawBackground(true);
          res = false;
        }
      }
    }

    int rowPos = (int) Math.floor(((caseSelected-(caseSelected%9))/9)/3);
    int colPos = (int) Math.floor(((caseSelected%9)/3));

    for(int i = (3*colPos+9*3*rowPos);i<(3*colPos+9*3*(rowPos+1));i+=9){
      for (int j=0;j<3;j++){
        int currentInt = i+j;
        if(currentInt!=caseSelected && selectedInt > 0) {
          if (selectedInt == gridView.cases.get(currentInt).getNumCase()){
            gridView.cases.get(currentInt).setDrawBackground(true);
            res = false;
          }
        }
      }
    }

    return res;
  }
}