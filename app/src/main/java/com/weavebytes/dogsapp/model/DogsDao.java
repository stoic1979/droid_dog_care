package com.weavebytes.dogsapp.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.weavebytes.dogsapp.model.Dogs;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table Dogs.
*/
public class DogsDao extends AbstractDao<Dogs, Long> {

    public static final String TABLENAME = "Dogs";

    /**
     * Properties of entity Dogs.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "Name", false, "NAME");
        public final static Property BirthDate = new Property(2, String.class, "BirthDate", false, "BIRTH_DATE");
        public final static Property Weight = new Property(3, Integer.class, "Weight", false, "WEIGHT");
        public final static Property Withers = new Property(4, Integer.class, "Withers", false, "WITHERS");
        public final static Property Breed = new Property(5, String.class, "Breed", false, "BREED");
        public final static Property Chip = new Property(6, String.class, "Chip", false, "CHIP");
        public final static Property Sex = new Property(7, String.class, "Sex", false, "SEX");
        public final static Property Picture = new Property(8, byte[].class, "picture", false, "PICTURE");
    };


    public DogsDao(DaoConfig config) {
        super(config);
    }
    
    public DogsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'Dogs' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'NAME' TEXT," + // 1: Name
                "'BIRTH_DATE' TEXT," + // 2: BirthDate
                "'WEIGHT' INTEGER," + // 3: Weight
                "'WITHERS' INTEGER," + // 4: Withers
                "'BREED' TEXT," + // 5: Breed
                "'CHIP' TEXT," + // 6: Chip
                "'SEX' TEXT," + // 7: Sex
                "'PICTURE' BLOB);"); // 8: picture
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'Dogs'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Dogs entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String Name = entity.getName();
        if (Name != null) {
            stmt.bindString(2, Name);
        }
 
        String BirthDate = entity.getBirthDate();
        if (BirthDate != null) {
            stmt.bindString(3, BirthDate);
        }
 
        Integer Weight = entity.getWeight();
        if (Weight != null) {
            stmt.bindLong(4, Weight);
        }
 
        Integer Withers = entity.getWithers();
        if (Withers != null) {
            stmt.bindLong(5, Withers);
        }
 
        String Breed = entity.getBreed();
        if (Breed != null) {
            stmt.bindString(6, Breed);
        }
 
        String Chip = entity.getChip();
        if (Chip != null) {
            stmt.bindString(7, Chip);
        }
 
        String Sex = entity.getSex();
        if (Sex != null) {
            stmt.bindString(8, Sex);
        }
 
        byte[] picture = entity.getPicture();
        if (picture != null) {
            stmt.bindBlob(9, picture);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Dogs readEntity(Cursor cursor, int offset) {
        Dogs entity = new Dogs( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // BirthDate
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // Weight
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // Withers
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // Breed
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // Chip
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // Sex
            cursor.isNull(offset + 8) ? null : cursor.getBlob(offset + 8) // picture
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Dogs entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBirthDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setWeight(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setWithers(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setBreed(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setChip(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSex(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPicture(cursor.isNull(offset + 8) ? null : cursor.getBlob(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Dogs entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Dogs entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
