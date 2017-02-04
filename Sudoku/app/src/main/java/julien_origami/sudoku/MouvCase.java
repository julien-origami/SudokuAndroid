package julien_origami.sudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by julienpons on 03/02/2017.
 */

public class MouvCase extends Case{

    private int currentBackGroundColor;

    public MouvCase(float x, float y, int gridCote, int numCase){
        super(x,y,gridCote,numCase);
        numberColor = Color.BLACK;
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

    public void setNewBackGround(){
        currentBackGroundColor = Color.rgb(189,190,191);
    }

    public void resetBackGround(){
        currentBackGroundColor = Color.rgb(209,210,211);
    }
}
