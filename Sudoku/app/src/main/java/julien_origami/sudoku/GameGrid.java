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

        String infoPasse = objetbunble.getBundle("passInfo").getString("itemGrid");
        textchampsaisie.setText(infoPasse);

    }
}
