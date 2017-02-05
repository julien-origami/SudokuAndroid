package julien_origami.sudoku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by julienpons on 04/02/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String SUDOKUGRID_KEY = "id";
    public static final String SUDOKUGRID_LEVEL = "level";
    public static final String SUDOKUGRID_NUM = "num";
    public static final String SUDOKUGRID_DONE = "done";
    public static final String SUDOKUGRID_GRID = "grid";
    public static final String SUDOKUGRID_PLAYERGRID = "playerGrid";
    public static final String SUDOKUGRID_CHRONO = "chrono";

    public static final String SUDOKU_TABLE_NAME = "SudokuGrid";
    public static final String SUDOKU_TABLE_CREATE =
            "CREATE TABLE " + SUDOKU_TABLE_NAME + " (" +
                    SUDOKUGRID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SUDOKUGRID_LEVEL + " INTEGER, " +
                    SUDOKUGRID_NUM + " INTEGER, " +
                    SUDOKUGRID_DONE + " INTEGER, " +
                    SUDOKUGRID_GRID + " TEXT, " +
                    SUDOKUGRID_PLAYERGRID + " TEXT, " +
                    SUDOKUGRID_CHRONO + " INTEGER);";
    public static final String SUDOKU_TABLE_DROP = "DROP TABLE IF EXISTS " + SUDOKU_TABLE_NAME + ";";


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SUDOKU_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SUDOKU_TABLE_DROP);
        onCreate(db);
    }
}
