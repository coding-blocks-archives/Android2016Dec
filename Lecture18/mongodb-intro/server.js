/**
 * Created by championswimmer on 19/02/17.
 */

const app = require('express')();
const db = require('./db');
const bodyParser = require('body-parser');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));


app.post('/todos/add', (req,res) => {

    db.addTask({
        task: req.body.task,
        done: req.body.done
    }, (status) => {
        res.send(status)
    })


});

app.get('/todos/show', (req, res) => {
    db.showTasks((todos) => {
        res.send(todos)
    })
});

db.init(() => {
    app.listen(7654, () => {
        console.log('Server started');

    });
});

