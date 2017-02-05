package julien_origami.sudoku;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import java.util.ArrayList;

/**
 * Created by julienpons on 02/02/2017.
 */

public class MonAdapteur extends BaseAdapter {

    private ArrayList<SudokuGrid> items;
    private Context context;


    public MonAdapteur(Context context, ArrayList<SudokuGrid> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(
                    android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        String level;

        switch (items.get(position).getLevel()){
            case 1:
                level = "*";
                break;
            case 2:
                level = "**";
                break;
            case 3:
                level = "***";
                break;
            default:
                level = "*";
                break;
        }

        text1.setText("Niveau : "+ items.get(position).getNum() + "     Difficulté : " + level);
        text1.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        text1.setTextSize(20);
        text1.setPadding(0,15,0,0);
        text2.setText("rempli à "+items.get(position).getDone()+" %");
        text2.setTextSize(16);
        text2.setPadding(0,5,0,25);
        if (items.get(position).getDone() < 70) {
            text2.setTextColor(Color.RED);
        }
        else {
            text2.setTextColor(Color.GREEN);
        }

        twoLineListItem.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bun = new Bundle();
                bun.putString("itemID",items.get(position).getId()+"");
                bun.putString("itemGrid",items.get(position).getGrid());
                bun.putString("itemLevel",items.get(position).getLevel()+"");
                bun.putString("itemNum",items.get(position).getNum()+"");
                bun.putString("itemDone",items.get(position).getDone()+"");
                Intent defineIntent = new Intent(context, GameGrid.class);
                defineIntent.putExtra("passInfo", bun);
                context.startActivity(defineIntent);
            }
        });

        return twoLineListItem;
    }
}
