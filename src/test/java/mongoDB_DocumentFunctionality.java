import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.json.JSONObject;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class mongoDB_DocumentFunctionality {

    static String path = System.getProperty("user.dir");
    static String userName = "mfbarquilla";
    static String password = "1234";

    public static void insertDocument(String databaseName, String collectionName, Document mongoDocument) {
        System.out.println("\ninsertDocument\n----------------------------");
        try{
            //CREATE CLIENT
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            //CREATE CREDENTIALS
            MongoCredential mongoCredential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
            System.out.println("userName : " + mongoCredential.getUserName());

            //ACCESS TO DATABASE: IF THE DATABASE DOES NOT EXIST IT IS CREATED
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Database : " + mongoDatabase.getName());

            //ACCESS TO COLLECTION: IF THE COLLECTION DOES NOT EXIST IT IS CREATED
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);
            System.out.println("mongoCollection : " + mongoCollection.getNamespace().getCollectionName());

            //INSERT A NEW DOCUMENT IN THE COLLECTION
            mongoCollection.insertOne(mongoDocument);
            System.out.println("New document '" + mongoDocument.getObjectId("ID").toString() + "' inserted in '" + mongoCollection.getNamespace().getCollectionName() + "' collection!!\n");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void updateDocument(String databaseName, String collectionName, String documentKey, String documentValue, String fieldKey, String fieldKValue) {
        System.out.println("\nupdateDocument\n----------------------------");
        try{
            //CREATE CLIENT
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            //CREATE CREDENTIALS
            MongoCredential mongoCredential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
            System.out.println("userName : " + mongoCredential.getUserName());

            //ACCESS TO DATABASE: IF THE DATABASE DOES NOT EXIST IT IS CREATED
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Database : " + mongoDatabase.getName());

            //ACCESS TO COLLECTION: IF THE COLLECTION DOES NOT EXIST IT IS CREATED
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);
            System.out.println("mongoCollection : " + mongoCollection.getNamespace().getCollectionName());

            //UPDATE THE DOCUMENT
            mongoCollection.updateOne(Filters.eq(documentKey,documentValue), Updates.set(fieldKey,fieldKValue));
            System.out.println("Document '" + documentValue + "' updated!!");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void deleteDocument(String databaseName, String collectionName, String documentKey, String documentValue) {
        System.out.println("\ndeleteDocument\n----------------------------");
        try{
            //CREATE CLIENT
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            //CREATE CREDENTIALS
            MongoCredential mongoCredential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
            System.out.println("userName : " + mongoCredential.getUserName());

            //ACCESS TO DATABASE: IF THE DATABASE DOES NOT EXIST IT IS CREATED
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Database : " + mongoDatabase.getName());

            //ACCESS TO COLLECTION: IF THE COLLECTION DOES NOT EXIST IT IS CREATED
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);
            System.out.println("mongoCollection : " + mongoCollection.getNamespace().getCollectionName());

            //DELETE THE DOCUMENT
            mongoCollection.deleteOne(Filters.eq(documentKey,documentValue));
            System.out.println("Document '" + documentValue + "' deleted!!");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void retrieveAllDocuments(String databaseName, String collectionName) {
        System.out.println("\nretrieveAllDocuments\n----------------------------");
        try {
            //CREATE CLIENT
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            //CREATE CREDENTIALS
            MongoCredential mongoCredential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
            System.out.println("userName : " + mongoCredential.getUserName());

            //ACCESS TO DATABASE: IF THE DATABASE DOES NOT EXIST IT IS CREATED
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Database : " + mongoDatabase.getName());

            //ACCESS TO A COLLECTION: IF THE COLLECTION DOES NOT EXIST IT IS CREATED
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collectionName);
            for (String name : mongoDatabase.listCollectionNames())
            {
                System.out.println("Collection name : " + name);
            }

            //GET THE ITERABLE DOCUMENT OBJECT
            FindIterable<Document> documentFindIterable = mongoCollection.find();
            Iterator iterator = documentFindIterable.iterator();

            //RETRIEVE ALL THE DOCUMENTS IN A LIST STRING
            List<Object> iteratorListObject = new ArrayList<>();
            while (iterator.hasNext()) {
                iteratorListObject.add(iterator.next());
                System.out.println("iteratorListObject :: " + iteratorListObject);
            }

            //SAVE THE DOCUMENTS IN A COLLECTION JSON OBJECT
            JSONObject documentJSONObject = new JSONObject();
            JSONObject collectionJSONObject = new JSONObject();
            for (Object iteratorObject : iteratorListObject){
                documentJSONObject.put("Document["+ iteratorListObject.indexOf(iteratorObject) +"]", iteratorObject);
                System.out.println("documentJSONObject : " + documentJSONObject);
            }
            collectionJSONObject.put(mongoCollection.getNamespace().getCollectionName(),documentJSONObject);
            //System.out.println("collectionJSONObject : " + collectionJSONObject);

            //SAVE JSON OBJECT IN A JSON FILE
            FileWriter JSONFile = new FileWriter(path + "\\collectionJSONObject.json");
            JSONFile.write(collectionJSONObject.toString());
            JSONFile.close();
            System.out.println("collectionJSONObject.json file saved!!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
