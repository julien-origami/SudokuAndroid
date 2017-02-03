package julien_origami.sudoku;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by julienpons on 03/02/2017.
 */

public class NumPos {
    int xc, yc, rayon;
    private Paint paint;

    public NumPos(int x, int y, int r){
        xc = x; yc = y; rayon = r;
        paint = new Paint();
        paint.setColor(Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(xc,yc,rayon,paint);
    }
}
