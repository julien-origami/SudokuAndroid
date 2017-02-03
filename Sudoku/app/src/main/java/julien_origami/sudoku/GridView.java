package julien_origami.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by julienpons on 03/02/2017.
 */

public class GridView extends View implements View.OnTouchListener{

    LinkedList<Case> cases;

    public GridView(Context context, AttributeSet attrs){
        super(context,attrs);
        cases = new LinkedList<Case>();
        this.setOnTouchListener(this);
    }

    @Override
    public void onDraw(Canvas canvas){

        //for(int i=0;i<){
        int rectCote = this.getWidth()/9;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        canvas.drawLine(rectCote*3,0,rectCote*3,rectCote*9,paint);
        canvas.drawLine(rectCote*6,0,rectCote*6,rectCote*9,paint);
        canvas.drawLine(0,rectCote*3,rectCote*9,rectCote*3,paint);
        canvas.drawLine(0,rectCote*6,rectCote*9,rectCote*6,paint);

        int posX = 0;
        int posY = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                Case newCase = new Case(posX, posY, rectCote, i);
                cases.add(newCase);
                newCase.draw(canvas);
                posX+=rectCote;
            }
            posX = 0;
            posY+=rectCote;
        }
        //}
        /*for (NumPos cercle : cercles){
            cercle.draw(canvas);
        }*/
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        /*switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                cases.add(new NumPos(x,y,1));
                break;

            case MotionEvent.ACTION_MOVE:
                Case cercle = cases.getLast();
                //cercle. = (int)Math.sqrt((x-cercle.xc)*(x-cercle.xc) + (y-cercle.yc)*(y-cercle.yc));
                break;
        }
        this.invalidate();*/
        return true;
    }
}
