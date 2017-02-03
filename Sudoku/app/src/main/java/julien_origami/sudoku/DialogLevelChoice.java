package julien_origami.sudoku;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by julienpons on 03/02/2017.
 */

public class DialogLevelChoice extends DialogFragment {

    /*@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        String infoGrid = savedInstanceState.getBundle("passInfo").getString("itemGrid");
        String itemLevel  = savedInstanceState.getBundle("passInfo").getString("itemLevel");
        String itemNum = savedInstanceState.getBundle("passInfo").getString("itemNum");
        String itemDone = savedInstanceState.getBundle("passInfo").getString("itemDone");




        builder.setMessage("Difficulté : "+itemLevel+" / Niveau "+itemNum+", terminé à "+itemDone+"%")
                .setTitle("Informations")
                .setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Bundle bun = new Bundle();
                        bun.putString("itemGrid",items.get(position).getGrid());
                        bun.putString("itemLevel",items.get(position).getLevel()+"");
                        bun.putString("itemNum",items.get(position).getNum()+"");
                        bun.putString("itemDone",items.get(position).getDone()+"");
                        Intent defineIntent = new Intent(context, GameGrid.class);
                        defineIntent.putExtra("passInfo", bun);
                        this.startActivity(defineIntent);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }*/
}
