package test;





import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author lanwei
 * @email 335747949@qq.com
 */
public class MongodbTest {

    //mongo客户端
    private MongoClient mongoClient;
    //集合
    private MongoCollection<Document> comment;

    @Before
    public void init(){
        //1.创建操作mongo的客户端
        mongoClient = new MongoClient("192.168.1.100");
        //2.选择数据库
        MongoDatabase commentdb = mongoClient.getDatabase("conmmentdb");
        //3.获取集合
        comment = commentdb.getCollection("comment");
    }

    //根据条件查询
    @Test
    public void test2(){
        //封装查询条件
        BasicDBObject basic = new BasicDBObject("name","jay");
        //执行查询
        FindIterable<Document> documents = comment.find(basic);
        for (Document document : documents) {
            System.out.println("__________");
            System.out.println(document.get("_id"));
            System.out.println(document.get("name"));
        }
    }

    @Test
    public void test() throws UnknownHostException {

        //4.使用集合进行查询
        FindIterable<Document> documents = comment.find();
        //5.遍历输出集合数据
        for (Document document : documents) {
            System.out.println("__________________");
            System.out.println(document.get("name"));
        }
    }

    //新增
    @Test
    public void test3() {
        //封装新增数据
        Map<String, Object> map = new HashMap<>();
        map.put("name","邓紫棋");
        map.put("age",24);
        map.put("sex","女");
        //封装新增文档对象
        Document document = new Document(map);
        //执行插入
        comment.insertOne(document);
    }

    //删除
    @Test
    public void tets4() {
        //封装条件
        BasicDBObject bson = new BasicDBObject("name","李四");
        //执行删除
        comment.deleteOne(bson);
    }

    //修改
    @Test
    public void test5() {
        //创建修改条件
        BasicDBObject bson = new BasicDBObject("name","jay");
        //修改后的值
        BasicDBObject bson1 = new BasicDBObject("$set",new Document("name","jay-chou"));
        comment.updateOne(bson,bson1);
    }

    @After
    public void after(){
        //6.释放资源，关闭客户端
        mongoClient.close();
    }
}
