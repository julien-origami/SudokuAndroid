package julien_origami.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameGrid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_grid);

        TextView textchampsaisie = (TextView) findViewById(R.id.textView4);
        Bundle objetbunble = this.getIntent().getExtras();

        String infoGrid = objetbunble.getBundle("passInfo").getString("itemGrid");
        GridView gridView = (GridView) findViewById(R.id.dessin);
        gridView.setInfoGrid(infoGrid);
        //textchampsaisie.setText(infoPasse);
    }
}
