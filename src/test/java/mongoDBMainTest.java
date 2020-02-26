import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

public class mongoDBMainTest {

    public static void main(String[] args)
    {
        String timeStamp = new SimpleDateFormat("HHmmssddMMyyyy").format(new Date());
        String databaseName = "myNewDatabase" + "_" + timeStamp;
        String collectionName = "myNewCollection" + "_" + timeStamp;
        String documentName = "myNewDocument" + "_" + timeStamp;
        Document mongoDocument = new Document("ID",documentName)
                .append("title","Dr")
                .append("name","John H.")
                .append("surname","Watson")
                .append("dateOfBirth","01/05/1893")
                .append("occupation","Doctor in medicine and writer");

        mongoDB_CollectionFunctionality.createNewCollection("myNewDatabase_1","myNewCollection_5");
        mongoDB_CollectionFunctionality.dropCollection("myNewDatabase_1","myNewCollection_5");
        mongoDB_CollectionFunctionality.dropAllCollectionInDatabase("myNewDatabase_1");
        mongoDB_DocumentFunctionality.insertDocument("myNewDatabase_1","myNewCollection_3",mongoDocument);
        mongoDB_DocumentFunctionality.retrieveAllDocuments("myNewDatabase_1","myNewCollection_2");
        mongoDB_DocumentFunctionality.updateDocument("myNewDatabase_1","myNewCollection_3","ID","myNewDocument_12161606022020","title","Sir");
        mongoDB_DocumentFunctionality.deleteDocument("myNewDatabase_1","myNewCollection_3","ID","myNewDocument_12415906022020");

    }
}
