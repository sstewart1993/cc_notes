const express = require('express');
const app = express();
const bodyParser = require('body-parser');

const cors = require("cors");

app.use(cors())

app.use(bodyParser.json());
const MongoClient = require('mongodb').MongoClient;
const createRouter = require('./helpers/create_router.js');

MongoClient.connect('mongodb://localhost:27017')
  .then((client) => {
    const db = client.db('hotel_checkins');
    const bookingsCollection = db.collection('bookings');
    app.use('/api/bookings', createRouter(bookingsCollection));
  })
  .catch(console.error);

app.listen(3000, function() {
  console.log(`Hotel server running on port ${this.address().port}`);
});
