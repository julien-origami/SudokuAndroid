package julien_origami.sudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by julienpons on 03/02/2017.
 */

public class MouvCase extends Case{

    public MouvCase(float x, float y, int gridCote, int numCase){
        super(x,y,gridCote,numCase);
        numberColor = Color.BLACK;
    }


    public void draw(Canvas canvas){
        drawNumber(canvas,numCase);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(x, y, (x+gridCote), (y+gridCote), paint);
    }
}
