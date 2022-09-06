package com.example.prm391x_project_1_quangntnfx05544;
import static com.example.prm391x_project_1_quangntnfx05544.Constant.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static DatabaseHandler instance;

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHandler(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_animals_table = String.format(
                "CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s BOOLEAN)",
                TABLE_NAME, KEY_ID, KEY_NAME, KEY_BACKGROUND_IMAGE, KEY_DESCRIPTION, KEY_FAVORITE, KEY_IS_FAVORITE);
        db.execSQL(create_animals_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    public void addAnimal(Animal animal) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, animal.getId());
        values.put(KEY_NAME, animal.getName());
        values.put(KEY_BACKGROUND_IMAGE, animal.getBackgroundImage());
        values.put(KEY_DESCRIPTION, animal.getDescription());
        values.put(KEY_FAVORITE, animal.getFavorite());
        values.put(KEY_IS_FAVORITE, animal.isFavorite());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Animal getAnimal(int animalId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Animal animal = null;

        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(animalId) },null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
            animal = new Animal(
                    cursor.getInt(1),
                    cursor.getInt(0),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5) > 0);
            cursor.close();
        }

        return animal;
    }

    public ArrayList<Animal> getAllAnimals() {
        ArrayList<Animal>  animalList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Animal animal = new Animal(
                    cursor.getInt(1),
                    cursor.getInt(0),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5) > 0);
            animalList.add(animal);
            cursor.moveToNext();
        }
        cursor.close();
        return animalList;
    }

    public void updateAnimalFavorite(int animalId, boolean isFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IS_FAVORITE, isFavorite);

        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(animalId) });
        db.close();
    }

    public boolean isEmpty() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count == 0;
    }

}
