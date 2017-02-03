package julien_origami.sudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by julienpons on 03/02/2017.
 */

public class Case {

    protected float x;
    protected float y;
    protected Paint paint;
    protected int gridCote;
    protected int numCase;
    protected boolean canReceiveNumber;
    protected int numberColor;

    public Case(float x, float y, int gridCote, int numCase){
        this.y = y;
        this.x = x;
        this.gridCote = gridCote;
        this.numCase = numCase;
        paint = new Paint();
        if(numCase>0){
            canReceiveNumber = false;
            numberColor = Color.BLACK;
        }else{
            canReceiveNumber = true;
            numberColor = Color.BLUE;
        }
    }


    public void draw(Canvas canvas){
        drawNumber(canvas,numCase);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(x, y, (x+gridCote), (y+gridCote), paint);
    }


    public void drawNumber(Canvas canvas, int numToDraw){
        if(numToDraw>0){
            paint.setColor(numberColor);
            paint.setTextSize(gridCote/2);
            canvas.drawText(numToDraw+"",x+(gridCote/5)*2,y+(gridCote/5)*4,paint);
        }
    }


    public int getNumCase(){
        return numCase;
    }


    public boolean setNumCase(int numCase){
        if (canReceiveNumber){
            this.numCase = numCase;
            /*Redraw*/
            return canReceiveNumber;
        }else {
            return canReceiveNumber;
        }
    }


    public boolean isCanReceiveNumber(){
        return canReceiveNumber;
    }
}
