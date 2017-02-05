package julien_origami.sudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by julienpons on 03/02/2017.
 */

public class MouvCase extends Case{

    private int currentBackGroundColor;
    private boolean isSelected;

    public MouvCase(float x, float y, int gridCote, int numCase){
        super(x,y,gridCote,numCase);
        numberColor = Color.BLACK;
        isSelected = false;
        resetBackGround();
    }


    public void draw(Canvas canvas){
        paint.setColor(currentBackGroundColor);
        canvas.drawRect(x, y, (x+gridCote), (y+gridCote), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(x, y, (x+gridCote), (y+gridCote), paint);
        paint.setStyle(Paint.Style.FILL);
        drawNumber(canvas,numCase);
    }

    public void drawNumber(Canvas canvas, int numToDraw){
        if(numToDraw>0){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.rgb(217,200,156));
            paint.setTextSize(gridCote/2);
            canvas.drawText(numToDraw+"",x+(gridCote/5)*2,y+(gridCote/5)*4,paint);
        }
    }

    public void setNewBackGround(){
        currentBackGroundColor = Color.rgb(44,40,26);
        isSelected = true;
    }

    public void resetBackGround(){
        currentBackGroundColor = Color.rgb(156,96,76);
        isSelected = false;
    }

    public boolean getIsSelected(){
        return isSelected;
    }
}
