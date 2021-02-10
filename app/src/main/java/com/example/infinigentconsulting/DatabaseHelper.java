package com.example.infinigentconsulting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "qt_infinigentdb.db";

    /*--Table Name LU_AIC --*/
    public static final String TABLE_NAME_LU_AIC  = "LU_AIC";
    public static final String LU_AIC_COL_1 = "Id";
    public static final String LU_AIC_COL_2 = "Name";
    public static final String LU_AIC_COL_3 = "IsActive";

    /*--Table Name LU_ASM --*/
    public static final String TABLE_NAME_LU_ASM  = "LU_ASM";
    public static final String LU_ASM_COL_1 = "Id";
    public static final String LU_ASM_COL_2 = "Name";
    public static final String LU_ASM_COL_3 = "IsActive";


    /*--Table Name LU_ChallanType--*/
    public static final String TABLE_NAME_LU_ChallanType = "LU_ChallanType";
    public static final String LU_ChallanType_COL_1 = "Id";
    public static final String LU_ChallanType_COL_2 = "Name";

    /*--Table Name LU_CommentsType --*/
    public static final String TABLE_NAME_LU_CommentsType  = "LU_CommentsType";
    public static final String LU_CommentsType_COL_1 = "Id";
    public static final String LU_CommentsType_COL_2 = "CommentsType";

    /*--Table Name LU_Comments --*/
    public static final String TABLE_NAME_LU_Commnets  = "LU_Commnets";
    public static final String LU_Commnets_COL_1 = "Id";
    public static final String LU_Commnets_COL_2 = "CommentsTypeId";
    public static final String LU_Commnets_COL_3 = "Comments";

    /*--Table Name LU_DeviceInfo --*/
    public static final String TABLE_NAME_LU_DeviceInfo  = "LU_DeviceInfo";
    public static final String LU_DeviceInfo_COL_1 = "Id";
    public static final String LU_DeviceInfo_COL_2 = "Name";
    public static final String LU_DeviceInfo_COL_3 = "UniqueNumber";

    /*--Table Name LU_DistributorDetails --*/
    public static final String TABLE_NAME_LU_DistributorDetails  = "LU_DistributorDetails";
    public static final String LU_DistributorDetails_COL_1 = "Id";
    public static final String LU_DistributorDetails_COL_2 = "Name";
    public static final String LU_DistributorDetails_COL_3 = "IsActive";

    /*--Table Name LU_Employee --*/
    public static final String TABLE_NAME_LU_Employee  = "LU_Employee";
    public static final String LU_Employee_COL_1 = "Id";
    public static final String LU_Employee_COL_2 = "Name";
    public static final String LU_Employee_COL_3 = "EmployeeTypeId";

    /*--Table Name LU_OutletType --*/
    public static final String TABLE_NAME_LU_OutletType  = "LU_OutletType";
    public static final String LU_OutletType_COL_1 = "Id";
    public static final String LU_OutletType_COL_2 = "Name";

    /*--Table Name LU_SchemeMediaType --*/
    public static final String TABLE_NAME_LU_SchemeMediaType  = "LU_SchemeMediaType";
    public static final String LU_SchemeMediaType_COL_1 = "Id";
    public static final String LU_SchemeMediaType_COL_2 = "Name";

    /*--Table Name LU_SchemeName --*/
    public static final String TABLE_NAME_LU_SchemeName  = "LU_SchemeName";
    public static final String LU_SchemeNameType_COL_1 = "Id";
    public static final String LU_SchemeNameType_COL_2 = "Name";

    /*--Table Name LU_User--*/
    public static final String TABLE_NAME_LU_User = "LU_User";
    public static final String LU_User_COL_1 = "Id";
    public static final String LU_User_COL_2 = "Name";
    public static final String LU_User_COL_3 = "Email";
    public static final String LU_User_COL_4 = "MobileNo";
    public static final String LU_User_COL_5 = "Password";
    public static final String LU_User_COL_6 = "IsActive";

    /*--Table Name TRN_SchemeAuditChild--*/
    public static final String TABLE_NAME_TRN_SchemeAuditChild = "TRN_SchemeAuditChild";
    public static final String TRN_SchemeAuditChild_COL_1 = "Id";
    public static final String TRN_SchemeAuditChild_COL_2 = "Number";
    public static final String TRN_SchemeAuditChild_COL_3 = "ImageLocation";
    public static final String TRN_SchemeAuditChild_COL_4 = "IsSignature";

    /*--Table Name TRN_SchemeAuditParent--*/
    public static final String TABLE_NAME_TRN_SchemeAuditParent = "TRN_SchemeAuditParent";
    public static final String TRN_SchemeAuditParent_COL_1 = "Id";
    public static final String TRN_SchemeAuditParent_COL_2 = "Number";
    public static final String TRN_SchemeAuditParent_COL_3 = "UserId";
    public static final String TRN_SchemeAuditParent_COL_4 = "OutlateName";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_LU_User + " (Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Email TEXT,MobileNo TEXT,Password TEXT,IsActive NUMERIC)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_TRN_SchemeAuditChild + " (Id INTEGER PRIMARY KEY AUTOINCREMENT,Number TEXT, ImageLocation  blob,IsSignature NUMERIC)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_LU_DistributorDetails + " (Id INTEGER ,Name TEXT, IsActive NUMERIC)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_LU_AIC + " (Id INTEGER ,Name TEXT, IsActive NUMERIC)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_LU_ASM + " (Id INTEGER ,Name TEXT, IsActive NUMERIC)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_LU_CommentsType + " (Id INTEGER ,CommentsType TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_LU_Commnets+ " (Id INTEGER ,CommentsTypeId TEXT, Comments TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LU_User);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_TRN_SchemeAuditChild);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_LU_DistributorDetails);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_LU_AIC);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_LU_ASM);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_LU_CommentsType);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_LU_Commnets);
        onCreate(db);
    }


    public boolean addData(byte[] img)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TRN_SchemeAuditChild_COL_2, "Test");
        contentValues.put(TRN_SchemeAuditChild_COL_3, img);
        contentValues.put(TRN_SchemeAuditChild_COL_4, true);
        boolean Isresult= false;
        try
        {
            db.beginTransaction();
            long  result = db.insert(TABLE_NAME_TRN_SchemeAuditChild, null, contentValues);
            db.setTransactionSuccessful();
            Isresult= result != -1;
        }
        catch (Exception exception)
        {

            db.endTransaction();
        }
        finally {
            db.endTransaction();
        }
        return  Isresult;

    }

    // INSERT DISTRIBUTOR LIST IN SQLITE DATABASE
    public boolean   insertDistributorList(Integer Id , String Name, boolean IsActive) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LU_DistributorDetails_COL_1, Id);
        contentValues.put(LU_DistributorDetails_COL_2, Name);
        contentValues.put(LU_DistributorDetails_COL_3, IsActive);
        boolean Isresult= false;
        try
        {
            db.beginTransaction();
            long  result = db.insert(TABLE_NAME_LU_DistributorDetails, null, contentValues);
            db.setTransactionSuccessful();
            Isresult= result != -1;
        }
        catch (Exception exception)
        {

            db.endTransaction();
        }
        finally {
            db.endTransaction();
        }
        return  Isresult;
    }

    // INSERT AIC LIST IN SQLITE DATABASE
    public boolean   insertAICList(Integer Id , String Name, boolean IsActive) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LU_AIC_COL_1, Id);
        contentValues.put(LU_AIC_COL_2, Name);
        contentValues.put(LU_AIC_COL_3, IsActive);
        boolean Isresult= false;
        try
        {
            db.beginTransaction();
            long  result = db.insert(TABLE_NAME_LU_AIC, null, contentValues);
            db.setTransactionSuccessful();
            Isresult= result != -1;
        }
        catch (Exception exception)
        {

            db.endTransaction();
        }
        finally {
            db.endTransaction();
        }
        return  Isresult;
    }

    // INSERT ASM LIST IN SQLITE DATABASE
    public boolean   insertASMList(Integer Id , String Name, boolean IsActive) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LU_ASM_COL_1, Id);
        contentValues.put(LU_ASM_COL_2, Name);
        contentValues.put(LU_ASM_COL_3, IsActive);
        boolean Isresult= false;
        try
        {
            db.beginTransaction();
            long  result = db.insert(TABLE_NAME_LU_ASM, null, contentValues);
            db.setTransactionSuccessful();
            Isresult= result != -1;
        }
        catch (Exception exception)
        {

            db.endTransaction();
        }
        finally {
            db.endTransaction();
        }
        return  Isresult;
    }


    // INSERT CommentsType LIST IN SQLITE DATABASE
    public boolean   insertCommentsTypeList(Integer Id , String CommentsType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LU_CommentsType_COL_1, Id);
        contentValues.put(LU_CommentsType_COL_2, CommentsType);
        boolean Isresult= false;
        try
        {
            db.beginTransaction();
            long  result = db.insert(TABLE_NAME_LU_CommentsType, null, contentValues);
            db.setTransactionSuccessful();
            Isresult= result != -1;
        }
        catch (Exception exception)
        {

            db.endTransaction();
        }
        finally {
            db.endTransaction();
        }
        return  Isresult;
    }

    // INSERT Comments LIST IN SQLITE DATABASE
    public boolean   insertCommentsList(Integer Id , Integer CommentsTypeId, String Comments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LU_Commnets_COL_1, Id);
        contentValues.put(LU_Commnets_COL_2, CommentsTypeId);
        contentValues.put(LU_Commnets_COL_2, Comments);
        boolean Isresult= false;
        try
        {
            db.beginTransaction();
            long  result = db.insert(TABLE_NAME_LU_Commnets, null, contentValues);
            db.setTransactionSuccessful();
            Isresult= result != -1;
        }
        catch (Exception exception)
        {

            db.endTransaction();
        }
        finally {
            db.endTransaction();
        }
        return  Isresult;
    }

    // INSERT USER DATE IN SQLITE DATABASE
    public boolean   insertData(String Name, String Email, String MobileNo, String Password,boolean IsActive) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LU_User_COL_2, Name);
        contentValues.put(LU_User_COL_3, Email);
        contentValues.put(LU_User_COL_4, MobileNo);
        contentValues.put(LU_User_COL_5, Password);
        contentValues.put(LU_User_COL_6, IsActive);
        boolean Isresult= false;
        try
        {
            db.beginTransaction();
            long  result = db.insert(TABLE_NAME_LU_User, null, contentValues);
            db.setTransactionSuccessful();
            Isresult= result != -1;
        }
        catch (Exception exception)
        {

            db.endTransaction();
        }
        finally {
            db.endTransaction();
        }
        return  Isresult;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LU_AIC,null);
        return res;
    }
    public Cursor getDistributorList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LU_DistributorDetails,null);
        return res;
    }

    public Cursor getAICNameList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LU_AIC,null);
        return res;
    }

    public Cursor getASMNameList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LU_ASM,null);
        return res;
    }

    public Cursor getCommentsTypeList() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LU_CommentsType,null);
        return res;
    }
    public Cursor getCommentList(int _comments_type_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strrr="select * from "+TABLE_NAME_LU_Commnets +" where "+ LU_Commnets_COL_2 +" = " +_comments_type_id;
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LU_Commnets +" where "+ LU_Commnets_COL_2 +" = " +_comments_type_id,null);
        return res;
    }
    public Integer deleteData () {
        SQLiteDatabase db = this.getWritableDatabase();
        /* return db.delete(TABLE_NAME, "ID = ?",new String[] {id});*/
        return db.delete(TABLE_NAME_LU_User,"1= ?",new String[] {"1"});
    }

    public Cursor getImageData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_TRN_SchemeAuditChild,null);
        return res;
    }
}