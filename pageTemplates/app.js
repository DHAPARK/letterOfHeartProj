const express = require("express");
const fs = require("fs");
const app = express();

app.use(express.static(__dirname + "/images"));

app.get("/", (req, res) => {
  res.send(fs.readFileSync("./index.html", "utf-8"));
});
app.get("/d", (req, res) => {
  res.send(fs.readFileSync("./design.html", "utf-8"));
});
app.listen(443, () => {
  console.log(`443번 포트로 서버 대기중..`);
});
