import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.*;;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Main { // Mongo Driver 3 style

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection business;
    private static MongoCollection review;
    private static MongoCollection user;
    private static MongoCollection tip;

    static {
        mongoClient = new MongoClient(); // Default localhost connection
        database = mongoClient.getDatabase("DawidMajchrowski2");
        business = database.getCollection("business");
        review = database.getCollection("review");
        user = database.getCollection("user");
        tip = database.getCollection("tip");
    }

    static DistinctIterable a() {
        // db.business.distinct("city");
        return business.distinct("city", String.class);
    }

    static long b() {
        // db.review.find({date: /201(1|2)/}).count();
        return review.countDocuments(Filters.regex("date", Pattern.compile("201[12]")));
    }

    static FindIterable c() {
        // db.business.find({open: true}, {name: 1, full_address: 1});
        return business.find(Filters.eq("open", true)).projection(Projections.include("name", "full_address"));
    }

    static FindIterable d() {
        // db.user.find({$or: [{"votes.funny": {$gte: 1}}, {"votes.useful": {$gte: 1}}, {"votes.cool": {$gte: 1}}]}).sort({name: 1});
        Bson or = Filters.or(
                Filters.gte("votes.useful", 1),
                Filters.gte("votes.funny", 1),
                Filters.gte("votes.cool", 1)
        );
        return user.find(or).sort(Sorts.ascending("name"));
    }

    static AggregateIterable e() {
        // db.tip.aggregate([ {$match:  {date: /2013/}}, {$group:  { _id: '$business_id', count: { $sum: 1 } } },
        // {$lookup: { from: "business", localField: "_id", foreignField: "business_id", as: "business"}},
        // {$unwind:"$business"}, {$project: {name: "$business.name", count : 1}},
        // {$sort: {"name": 1}}])
        return tip.aggregate(Arrays.asList(
                Aggregates.match(Filters.regex("date", Pattern.compile("2013"))),
                Aggregates.group("$business_id", Accumulators.sum("count", 1)),
                Aggregates.limit(100), // just for faster join
                Aggregates.lookup("business", "_id", "business_id", "business"),
                Aggregates.unwind("$business"),
                Aggregates.project(Projections.include("count", "business.name")),
                Aggregates.sort(Sorts.ascending("business.name"))));
    }

    static AggregateIterable f() {
        // db.review.aggregate([{$group:  { _id: '$business_id', average: { $avg: "$stars" } } }, {$sort: {average: -1}},
        // {$lookup: { from: "business", localField: "_id", foreignField: "business_id", as: "business"}}, {$unwind:"$business"},
        //  {$project: {name: "$business.name", average: 1}}])
        return review.aggregate(Arrays.asList(
                Aggregates.group("$business_id", Accumulators.avg("average", "$stars")),
                Aggregates.sort(Sorts.descending("average")),
                Aggregates.limit(100), // just for faster join
                Aggregates.lookup("business", "_id", "business_id", "business"),
                Aggregates.unwind("$business"),
                Aggregates.project(Projections.include("average", "business.name"))));
    }


    static void g() {
    // db.business.remove({stars: {$lt: 3}});
        business.deleteMany(Filters.lt("stars", 3));
    }

    public static void main(String[] args) {
//        System.out.println(a().into(new LinkedList())); // a)
//        System.out.println(b());                        // b)
//        System.out.println(c().into(new LinkedList())); // c)
//        System.out.println(d().into(new LinkedList())); // d)
//        System.out.println(e().into(new LinkedList())); // e)
//        System.out.println(f().into(new LinkedList())); // f)
//        g();                                            // g)
    }
}
