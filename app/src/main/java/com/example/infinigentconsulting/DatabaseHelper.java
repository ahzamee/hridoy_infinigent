package com.example.infinigentconsulting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "qt_infinigentdb.db";


    /*--Table Name LU_ChallanType--*/
    public static final String TABLE_NAME_LU_ChallanType = "LU_ChallanType";
    public static final String LU_ChallanType_COL_1 = "Id";
    public static final String LU_ChallanType_COL_2 = "Name";

    /*--Table Name LU_CommentsType --*/
    public static final String TABLE_NAME_LU_CommentsType  = "LU_CommentsType";
    public static final String LU_CommentsType_COL_1 = "Id";
    public static final String LU_CommentsType_COL_2 = "Name";

    /*--Table Name LU_Comments --*/
    public static final String TABLE_NAME_LU_Commnets  = "LU_Commnets";
    public static final String LU_Commnets_COL_1 = "Id";
    public static final String LU_Commnets_COL_2 = "Name";

    /*--Table Name LU_DeviceInfo --*/
    public static final String TABLE_NAME_LU_DeviceInfo  = "LU_DeviceInfo";
    public static final String LU_DeviceInfo_COL_1 = "Id";
    public static final String LU_DeviceInfo_COL_2 = "Name";
    public static final String LU_DeviceInfo_COL_3 = "UniqueNumber";

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



   /* [Id]
            ,[Number]
            ,[UserId]
            ,[OutlateName]
            ,[Date]
            ,[GccCode]
            ,[RetailSellerName]
            ,[MobileNumber]
            ,[OutlateTypeId]
            ,[VisitedDate]
            ,[DistributorName]
            ,[AsmId]
            ,[AicId]
            ,[OutlateAddress]
            ,[IsKnowenAboutScheme]
            ,[SchemeDetails]
            ,[SchemeMediaTypeId]
            ,[IsFacilitatedByScheme]
            ,[DateOfScheme]
            ,[IsWrittenRecordAvailable]
            ,[LatestChallanDate]
            ,[ChallanAmount]
            ,[DoesGotAnyChallan]
            ,[ChallanTypeId]
            ,[DoesExpiredProductAvailable]
            ,[DoesSatisfiedWithSallesOfficer]
            ,[DoesSatisfiedWithProductOrderAndService]
            ,[SallesOfficerVisitingDay]
            ,[DoesGotLatestDiscountOffer]
            ,[WillGetAnyDiscountOfferFromDistributor]
            ,[DoesCocaColaLabelAvailable]
            ,[IsGccCodeAvailable]
            ,[CommentsType]
            ,[Comments]
            ,[CommentDetails]
            ,[CreatorId]
            ,[CreationDate]
            ,[ModifierId]
            ,[ModificationDate]
    */


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_LU_User + " (Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Email TEXT,MobileNo TEXT,Password TEXT,IsActive NUMERIC)");

        db.execSQL("CREATE TABLE " + TABLE_NAME_TRN_SchemeAuditChild + " (Id INTEGER PRIMARY KEY AUTOINCREMENT,Number TEXT, ImageLocation  blob,IsSignature NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LU_User);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME_TRN_SchemeAuditChild);
        onCreate(db);
    }

    //
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
            if (result == -1)
                Isresult= false;
            else
                Isresult= true;
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
    //
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
            if (result == -1)
                Isresult= false;
            else
                Isresult= true;
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
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_LU_User,null);
        return res;
    }
    public Integer deleteData () {
        SQLiteDatabase db = this.getWritableDatabase();
       /* return db.delete(TABLE_NAME, "ID = ?",new String[] {id});*/
        return db.delete(TABLE_NAME_LU_User,"1= ?",new String[] {"1"});
    }


}



