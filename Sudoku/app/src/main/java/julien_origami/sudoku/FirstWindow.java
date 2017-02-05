package julien_origami.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FirstWindow extends AppCompatActivity implements View.OnClickListener{

    Spinner levelChoice;
    Button validateBtn;
    Button loadDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_window);

        levelChoice = (Spinner) findViewById(R.id.spinner);
        validateBtn = (Button) findViewById(R.id.levelChoice);
        validateBtn.setOnClickListener(this);
        loadDatabase = (Button) findViewById(R.id.loadDatabase);
        loadDatabase.setOnClickListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Niveau Facile");
        categories.add("Niveau Moyen");
        categories.add("Niveau Difficile");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        levelChoice.setAdapter(dataAdapter);
    }


    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(validateBtn)){
            Bundle bun = new Bundle();
            bun.putString("firstString",levelChoice.getSelectedItem().toString());
            Intent defineIntent = new Intent(this, LevelChoice.class);
            defineIntent.putExtra("passInfo", bun);
            this.startActivity(defineIntent);
        }
        else if(v.equals(loadDatabase)){
            new BddManager(this).constructDatabase();
        }
    }
}
