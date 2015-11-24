package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class DogsDaoGenerator {


    public static void main(String args[]) throws Exception {

        Schema schema = new Schema(1, "com.weavebytes.dogsapp.model");

        // Adding Dogs Table as a Entity

        Entity dog = schema.addEntity("Dogs");
        dog.setTableName("Dogs");
        dog.addIdProperty().autoincrement().primaryKey();
        dog.addStringProperty("Name");
        dog.addStringProperty("BirthDate");
        dog.addIntProperty("Weight");
        dog.addIntProperty("Withers");
        dog.addStringProperty("Breed");
        dog.addStringProperty("Chip");
        dog.addStringProperty("Sex");
        dog.addByteArrayProperty("picture");
        // generatng scheme
        new DaoGenerator().generateAll(schema, "../app/src/main/java");

    }
}
