package com.example.lxs.ContentResolver;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {
    private static final UriMatcher uriMatcher;
    public static  final int TABLE1_DIR=0;
    public static  final int TABLE1_ITEM=1;
    public static  final int TABLE2_DIR=2;
    public static  final int TABLE2_ITEM=3;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.lxs.provider","table1",TABLE1_DIR);
        uriMatcher.addURI("com.example.lxs.provider","table1/#",TABLE1_ITEM);
        uriMatcher.addURI("com.example.lxs.provider","table2",TABLE2_DIR);
        uriMatcher.addURI("com.example.lxs.provider","table2/#",TABLE2_ITEM);
    }
    @Override
    public boolean onCreate() {
        return false;
    }
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                break;
            case TABLE1_ITEM:
                break;
            case TABLE2_DIR:
                break;
            case TABLE2_ITEM:
                break;
            default:
        }
        return null;
    }
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
