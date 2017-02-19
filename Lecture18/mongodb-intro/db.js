/**
 * Created by championswimmer on 19/02/17.
 */
const mongodb = require('mongodb');

let myDb;
const url = "mongodb://localhost:27017/mydb";

function init(done) {
    mongodb.MongoClient.connect(url, (err, db) => {
        if (err) throw err;

        myDb = db;
        done();
    })
}

function showTasks(done) {
    myDb.collection("todos").find({}).toArray((err, result) => {
        if(err) throw err;

        done(result);
    })
}

function addTask(task, done) {
    myDb.collection("todos").insertOne(task, (err, result) => {
        if (err) throw err;

        if (result.insertedCount == 1) {
            done(result.insertedId);
        } else {
            done("ERROR");
        }
    })
}

module.exports = {
    init,
    showTasks,
    addTask
};