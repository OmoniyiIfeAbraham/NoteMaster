package com.ife.todolist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class NoteHandler extends DatabaseHelper{
    public NoteHandler(Context context){
        super(context);
    }

    public boolean create(Note note){

        ContentValues values = new ContentValues();

        values.put("title", note.getTitle());
        values.put("description", note.getDescrition());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isSuccesful =  db.insert("Note", null, values) > 0;
        db.close();
        return  isSuccesful;

    }

    public ArrayList<Note> readNotes(){
        ArrayList<Note> notes = new ArrayList<>();

        String sqlQuery = "SELECT * FROM Note ORDER BY id ASC";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()){
            do {

                @SuppressLint("Range") int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                Note note = new Note(title, description);
                note.setId(id);
                notes.add(note);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return notes;
    }

    public Note readSingleNote(int id){
        Note note = null;
        String sqlQuery = "SELECT * FROM Note WHERE id="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst()){
            @SuppressLint("Range") int noteId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

            note = new Note(title, description);
            note.setId(noteId);
        }
        cursor.close();
        db.close();
        return  note;
    }

    public boolean update(Note note){
        ContentValues values = new ContentValues();
        values.put("title", note.getTitle());
        values.put("description", note.getDescrition());
        SQLiteDatabase db = this.getWritableDatabase();
        boolean isSuccesful =  db.update("Note", values, "id='"+note.getId()+"'", null) > 0;
        db.close();
        return isSuccesful;
    }

    public boolean delete(int id){
        boolean isDeleted;
        SQLiteDatabase db = this.getWritableDatabase();
        isDeleted = db.delete("Note", "id='" + id+"'", null) > 0;
        db.close();
        return isDeleted;
    }
}
