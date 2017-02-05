package julien_origami.sudoku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by julienpons on 04/02/2017.
 */

public class DatabaseSudoku {

    protected final static int VERSION = 11;
    protected final static String NOM = "sudokuDatabase.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DatabaseSudoku(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
        open();
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }


    public ArrayList<SudokuGrid> getGridsByLevel(int level){
        ArrayList<SudokuGrid> categories = new ArrayList();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + mHandler.SUDOKU_TABLE_NAME + " WHERE level = ?", new String[]{""+level});
        while (cursor.moveToNext()) {
            categories.add(new SudokuGrid(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4)));
        }
        cursor.close();
        return categories;
    }


    public SudokuGrid getGridById(int id){
        SudokuGrid sudokuGrid = new SudokuGrid(0,0,0,0,"ERROR");
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + mHandler.SUDOKU_TABLE_NAME + " WHERE id = ?", new String[]{""+id});
        if (cursor!=null) {
            cursor.moveToFirst();
            sudokuGrid = new SudokuGrid(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4));
            sudokuGrid.setPlayerGrid(cursor.getString(5));
        }
        cursor.close();
        return sudokuGrid;
    }


    public void ajouter(SudokuGrid sudokuGrid) {
        ContentValues value = new ContentValues();
        value.put(mHandler.SUDOKUGRID_LEVEL, sudokuGrid.getLevel());
        value.put(mHandler.SUDOKUGRID_NUM, sudokuGrid.getNum());
        value.put(mHandler.SUDOKUGRID_DONE, sudokuGrid.getDone());
        value.put(mHandler.SUDOKUGRID_GRID, sudokuGrid.getGrid());
        value.put(mHandler.SUDOKUGRID_PLAYERGRID, sudokuGrid.getPlayerGrid());
        value.put(mHandler.SUDOKUGRID_CHRONO, sudokuGrid.getChrono());
        mDb.insert(mHandler.SUDOKU_TABLE_NAME, null, value);
    }


    public void update(SudokuGrid sudokuGrid) {
        ContentValues value = new ContentValues();
        value.put(mHandler.SUDOKUGRID_DONE, sudokuGrid.getDone());
        value.put(mHandler.SUDOKUGRID_PLAYERGRID, sudokuGrid.getPlayerGrid());
        value.put(mHandler.SUDOKUGRID_CHRONO, sudokuGrid.getChrono());
        mDb.update(mHandler.SUDOKU_TABLE_NAME, value, mHandler.SUDOKUGRID_KEY + " = ?", new String[] {String.valueOf(sudokuGrid.getId())});
    }

}
