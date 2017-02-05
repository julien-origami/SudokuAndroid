package julien_origami.sudoku;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class LevelChoice extends AppCompatActivity {

    TextView textchampsaisie;
    Bundle objetbunble;
    BddManager bddManager;
    String infoPasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_choice);

        textchampsaisie = (TextView) findViewById(R.id.textView3);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fontai.ttf");
        textchampsaisie.setTypeface(typeface);

        objetbunble = this.getIntent().getExtras();

        infoPasse = objetbunble.getBundle("passInfo").getString("firstString");
        textchampsaisie.setText(infoPasse);

        bddManager = new BddManager(this);
    }


    @Override
    public void onResume(){
        super.onResume();
        ListView levelList = (ListView) findViewById(R.id.levelList);
        levelList.setAdapter(bddManager.getCorrectObject(infoPasse));
    }
}
