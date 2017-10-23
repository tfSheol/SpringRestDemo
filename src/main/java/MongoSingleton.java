import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project SpringRestStarter
 */
public class MongoSingleton {
    private static MongoSingleton instance = new MongoSingleton();
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    private MongoSingleton() {

    }

    public static MongoSingleton getInstance() {
        return instance;
    }

    public void config(String ip, int port) {
        this.mongoClient = new MongoClient(ip, port);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoDatabase(String selectDatabase) {
        if (mongoClient != null) {
            mongoDatabase = mongoClient.getDatabase(selectDatabase);
        }
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }
}
