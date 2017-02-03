package julien_origami.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class LevelChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_choice);

        TextView textchampsaisie = (TextView) findViewById(R.id.textView3);
        Bundle objetbunble = this.getIntent().getExtras();

        String infoPasse = objetbunble.getBundle("passInfo").getString("firstString");
        textchampsaisie.setText(infoPasse);

        BddManager bddManager = new BddManager(this);

        ListView levelList = (ListView) findViewById(R.id.levelList);
        levelList.setAdapter(bddManager.getCorrectObject(infoPasse));

        //bddManager.getCorrectObject(infoPasse);
    }
}
