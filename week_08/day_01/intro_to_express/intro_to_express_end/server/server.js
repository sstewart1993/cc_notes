const express = require("express");
const app = express();

const cors = require("cors");
app.use(cors());

app.get("/", (req, res) => {
	res.json({ message: "Hello World!" });
});

app.listen(3000, () => {
	console.log("Listening on port 3000");
});
