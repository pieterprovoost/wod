package be.pieterprovoost.wod;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Repository {

    private MongoClient mongoClient;
    private DB db;
    private DBCollection collection;

    public Repository(String host, String db, String collection) {
        this.mongoClient = new MongoClient(host);
        this.db = mongoClient.getDB(db);
        this.collection = this.db.getCollection(collection);
    }

    public Repository() {
        this("localhost", "wod", "wod");
    }

    public void drop() {
        collection.drop();
    }

    public void save(BasicDBObject cast) {
        collection.save(cast);
    }

}
