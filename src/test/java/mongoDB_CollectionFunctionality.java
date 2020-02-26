import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class mongoDB_CollectionFunctionality {

    static String userName = "mfbarquilla";
    static String password = "1234";

    public static void createNewCollection(String databaseName, String collectionName) {
        System.out.println("\ncreateNewCollection\n----------------------------");
        try{
            //CREATE CLIENT
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            //CREATE CREDENTIALS
            MongoCredential mongoCredential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
            System.out.println("userName : " + mongoCredential.getUserName());

            //ACCESS TO DATABASE: IF THE DATABASE DOES NOT EXIST IT IS CREATED
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Database : " + mongoDatabase.getName());

            //CREATE NEW COLLECTION
            mongoDatabase.createCollection(collectionName);
            System.out.printf("New Collection '" + collectionName + "' created in '" + mongoDatabase.getName() + "' database!!\n");

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void dropCollection(String databaseName,String collectionName) {
        System.out.println("\ndeleteCollectionAndDatabase\n----------------------------");
        try{
            //CREATE CLIENT
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            //CREATE CREDENTIALS
            MongoCredential mongoCredential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
            System.out.println("userName : " + mongoCredential.getUserName());

            //ACCESS TO DATABASE: IF THE DATABASE DOES NOT EXIST IT IS CREATED
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Database : " + mongoDatabase.getName());

            //DELETE THE COLLECTION INDICATED IN DATABASE.
            mongoDatabase.getCollection(collectionName).drop();
            System.out.println("Collection '" + mongoDatabase.getCollection(collectionName).getNamespace().getCollectionName() + "' dropped!!");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void dropAllCollectionInDatabase(String databaseName) {
        System.out.println("\ndeleteCollectionAndDatabase\n----------------------------");
        try{
            //CREATE CLIENT
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            //CREATE CREDENTIALS
            MongoCredential mongoCredential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
            System.out.println("userName : " + mongoCredential.getUserName());

            //ACCESS TO DATABASE: IF THE DATABASE DOES NOT EXIST IT IS CREATED
            MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
            System.out.println("Database : " + mongoDatabase.getName());

            //DELETE EVERY COLLECTION CONTAINED IN DATABASE: WHEN ALL THE COLLECTIONS ARE DROPPED THE DATABASE IT BELONGS IS DROPPED TOO
            for (String name : mongoDatabase.listCollectionNames())
            {
                mongoDatabase.getCollection(name).drop();
                System.out.println("Collection '" + name + "' dropped!!");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
