/**
 * Created by championswimmer on 19/02/17.
 */
const mongodb = require('mongodb');

const mongoURL = "mongodb://localhost:27017/mydb";

function insertTodo(db, done) {

   let todos = db.collection("todos");

   todos.insertOne({
      task: "some task",
       done: false
   }, function (err, result) {
       if (err) throw err;

       console.log(result);
       done();
   })
}

function insertTodos (db, done) {
    let todos = db.collection("todos");

    todos.insertMany([
            {task: "one", done: false},
            {task: "two", done: false},
            {task: "three", done: true},
            {task: "four", done: false},
        ],

        function (err, result) {
            if (err) throw err;

            console.log(result);
            done();
        }
    )
}

function findTodos (db, done) {
    let todos = db.collection("todos");

    todos.find({}).toArray(function(err, data) {
        if (err) throw err;

        done(data)
    })

}

function updateDone(task, db, done) {
    let todos = db.collection("todos");

    todos.updateOne(
        {task: task},
        {
            $set: {
                done: true
            }
        }, function (err, result) {
            if (err) throw err;

            console.log(result);
            done();
        })
}

mongodb.MongoClient.connect(mongoURL, function (err, db) {
   if (err) throw err;

   console.log("We are connected");

   //insertTodo(db, () => {})
    //insertTodos(db, () => {});
    findTodos(db, (data) => {
        console.log(data);
    })

    //updateDone("one", db, () => {})

});

