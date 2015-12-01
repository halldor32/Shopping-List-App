package is.tskoli.halldor.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by halldor32 on 27.9.2015.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "shoppinglistManager";

    private static final String TABLE_LISTS = "lists";

    private static final String KEY_ID = "id";
    private static final String KEY_TEXT = "text";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //    Búa til table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALARMS_TABLE = "CREATE TABLE " + TABLE_LISTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TEXT + " TEXT" + ")";
        db.execSQL(CREATE_ALARMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS);

        onCreate(db);
    }

    //    bæta við nýju item í listann
    public void addToList(String item) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, item);


        db.insert(TABLE_LISTS, null, values);
        db.close();
    }

    //  fá eitt item
    ShoppingList getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LISTS, new String[] { KEY_ID,
                        KEY_TEXT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        ShoppingList alarm = new ShoppingList(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));

        return alarm;
    }

    //  ná í alla items
    public List<ShoppingList> getAllItems() {
        List<ShoppingList> shoppingList = new ArrayList<ShoppingList>();

        String selectQuery = "SELECT  * FROM " + TABLE_LISTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // fer í gegnum hvert row og bætir í listann
        if (cursor.moveToFirst()) {
            do {
                ShoppingList shopping = new ShoppingList();
                shopping.setID(Integer.parseInt(cursor.getString(0)));
                shopping.setText(cursor.getString(1));

                // bætir alarm í listann
                shoppingList.add(shopping);
            } while (cursor.moveToNext());
        }

        return shoppingList;
    }

    //  ná í fjölda items
    public int getItemsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LISTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    //  uppfæra sérstaka alarm
    public int updateShoppingList(ShoppingList Item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEXT, Item.getText());

        // updating row
        return db.update(TABLE_LISTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(Item.GetID()) });
    }

    //  Eyða sérstöku alarm
    public void deleteItem(ShoppingList Item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LISTS, KEY_ID + " = ?",
                new String[] { String.valueOf(Item.GetID()) });
        db.close();
    }
}
