package julien_origami.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class FirstWindow extends AppCompatActivity implements View.OnClickListener{

    Spinner levelChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_window);

        levelChoice = (Spinner) findViewById(R.id.spinner);
        Button validateBtn = (Button) findViewById(R.id.levelChoice);
        validateBtn.setOnClickListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Niveau Facile");
        categories.add("Niveau Moyen");
        categories.add("Niveau Difficile");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelChoice.setAdapter(dataAdapter);
    }


    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        Bundle bun = new Bundle();
        bun.putString("firstString",levelChoice.getSelectedItem().toString());
        Intent defineIntent = new Intent(this, LevelChoice.class);
        defineIntent.putExtra("passInfo", bun);
        this.startActivity(defineIntent);
    }
}
