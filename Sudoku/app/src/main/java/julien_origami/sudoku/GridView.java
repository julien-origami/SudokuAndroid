package julien_origami.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by julienpons on 03/02/2017.
 */

public class GridView extends View{

    LinkedList<Case> cases;
    LinkedList<MouvCase> clickCases;
    SudokuGrid sudokuGrid;
    int rectCote;
    boolean firstDraw;

    public GridView(Context context, AttributeSet attrs){
        super(context,attrs);
        cases = new LinkedList<Case>();
        clickCases = new LinkedList<MouvCase>();
        firstDraw = true;
    }

    public int getCurrentDone(){
        int nbNumberIn = 0;
        int nbEmptyCase = 0;
        for (Case caseGrid : cases){
            if (caseGrid.isCanReceiveNumber()){
                nbEmptyCase++;
                if(caseGrid.getNumCase()>0) {
                    nbNumberIn++;
                }
            }
        }
        nbNumberIn = (nbNumberIn/nbEmptyCase)*100;
        return nbNumberIn;
    }


    public String getCurrentGrid(){
        String res = new String();
        for (Case caseGrid : cases){
            if (caseGrid.isCanReceiveNumber()){
                res += caseGrid.getNumCase();
            }else {
                res +="0";
            }
        }
        return res;
    }


    @Override
    public void onDraw(Canvas canvas){

        rectCote = this.getWidth()/9;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        canvas.drawLine(rectCote*3,0,rectCote*3,rectCote*9,paint);
        canvas.drawLine(rectCote*6,0,rectCote*6,rectCote*9,paint);
        canvas.drawLine(0,rectCote*3,rectCote*9,rectCote*3,paint);
        canvas.drawLine(0,rectCote*6,rectCote*9,rectCote*6,paint);


        if(firstDraw){
            firstDraw = false;
            int posX = 0;
            int posY = 0;

            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++) {
                    Case newCase = new Case(posX, posY, rectCote, getCurrentInt(sudokuGrid.getGrid(), i, j));
                    cases.add(newCase);
                    newCase.draw(canvas);
                    posX+=rectCote;
                }
                posX = 0;
                posY+=rectCote;
            }
            for(int i=1;i<10;i++){
                MouvCase newCase = new MouvCase(posX, 10*rectCote, rectCote, i);
                newCase.draw(canvas);
                clickCases.add(newCase);
                posX+=rectCote;
            }

            int nbNumberIn = 0;
            for (Case caseGrid : cases){
                if(sudokuGrid.getPlayerGrid()!=null) {
                    if (caseGrid.isCanReceiveNumber() && sudokuGrid.getGrid().length() == sudokuGrid.getPlayerGrid().length()) {
                        Case newCase = getOneCase(nbNumberIn);
                        newCase.setNumCase(getCurrentInt(sudokuGrid.getPlayerGrid(), nbNumberIn));
                        newCase.draw(canvas);
                    }
                }
                nbNumberIn++;
            }
        }else{
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++) {
                    Case newCase = getOneCase(9*i+j);
                    newCase.draw(canvas);
                }
            }
            for(int i=1;i<10;i++){
                MouvCase newCase = getOneMouvCase(i-1);
                newCase.draw(canvas);
            }
        }

    }

    public Case getOneCase(int id){
        return cases.get(id);
    }

    public MouvCase getOneMouvCase(int id){
        return clickCases.get(id);
    }

    public void setSudokuGrid(SudokuGrid sudokuGrid){
        this.sudokuGrid = sudokuGrid;
    }

    public int getCurrentInt(String string, int i, int j){
        return Character.digit(string.charAt(9*i+j), 10);
    }

    public int getCurrentInt(String string, int i){
        return Character.digit(string.charAt(i), 10);
    }

    public void modifyBgColorCaseSelected(int selectedMouvCase){
        for(int i = 0; i<clickCases.size(); i++){
            if(i==selectedMouvCase){
                clickCases.get(i).setNewBackGround();
            }else{
                clickCases.get(i).resetBackGround();
            }
        }

    }
}
