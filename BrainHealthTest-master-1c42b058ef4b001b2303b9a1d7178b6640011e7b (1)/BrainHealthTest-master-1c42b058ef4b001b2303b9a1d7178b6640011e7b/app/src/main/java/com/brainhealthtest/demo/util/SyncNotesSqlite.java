package com.brainhealthtest.demo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class SyncNotesSqlite
{
    public static int BaseVersion = 1;
    public static String DataBaseName = "pendata";
    public static String TableName = "datateble";
    public static SQLiteDatabase dbs;
    public static SyncSqliteDatebase dhelper;

    class SyncSqliteDatebase extends SQLiteOpenHelper
    {
        public SyncSqliteDatebase(Context context)
        {
            super(context, SyncNotesSqlite.DataBaseName, null, SyncNotesSqlite.BaseVersion);
        }

        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("create table " + SyncNotesSqlite.TableName + "(" + "_id" + " INTEGER PRIMARY KEY, " + TableRow.syncpath + " text," + TableRow.note_state + " INTEGER," + TableRow.syncname + " text);");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("drop table if exists " + SyncNotesSqlite.TableName);
            onCreate(db);
        }
    }

    static class TableRow implements BaseColumns
    {
        static String note_state = "state";
        static String syncname = "name";
        static String syncpath = "path";

        TableRow()
        {
        }
    }

    public void open(Context context)
    {
        dhelper = new SyncSqliteDatebase(context);
        dbs = dhelper.getWritableDatabase();
    }

    public void close(Context context)
    {
        dhelper.close();
        dbs.close();
    }

    public Cursor getTable()
    {
        return dbs.query(TableName, null, null, null, null, null, null);
    }

    public void insert(String name, String path, int state)
    {
        ContentValues values = new ContentValues();
        values.put(TableRow.syncname, name);
        values.put(TableRow.syncpath, path);
        values.put(TableRow.note_state, Integer.valueOf(state));
        System.out.println("insert,name:" + name + ",path:" + path);
        dbs.insert(TableName, null, values);
    }

    public void modify(String name, String path, int state)
    {
        ContentValues values = new ContentValues();
        values.put(TableRow.syncname, name);
        values.put(TableRow.syncpath, path);
        values.put(TableRow.note_state, Integer.valueOf(state));
        dbs.update(TableName, values, TableRow.syncname + " = " + "'" + name + "'", null);
    }

    public void delete(String name)
    {
        System.out.println("deleteCount:" + dbs.delete(TableName, TableRow.syncname + " = " + "'" + name + "'", null));
    }

    public int getSingleCursor(String name)
    {
        Cursor cur = dbs.rawQuery("select * from cloudmemo where name=?", new String[]{name});
        if (cur.getCount() > 0 && cur.moveToFirst())
        {
            return cur.getInt(cur.getColumnIndex(TableRow.note_state));
        }
        return 0;
    }
}
