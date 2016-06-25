var express = require("express");
var app = express();
var db = require("./dbaccess.js");

//"insert into groups values ('*','*',true);
app.post("/create/:groupname",function(req,res)
{
    db.createGroup(req.params.groupname, function (info) {
        if (info.err)
        {
            res.send(info.err).status(501);
        }
        else
        {
            res.send(info.guid).status(200);
        }
    });
});

app.get("/join/:name",function (req,res) {
    db.join(req.params.name,function (info) {
        if (info.err)
        {
            res.send(info.err).status(501);
        }
        else
        {
            res.send(info.guid).status(200);
        }
    })
});

app.post("/group/:groupid/add/:songid",function(req,res)
{
    db.add(req.params.groupid,req.params.songid,function (info) {
        if (info.err)
        {
            res.send(info.err).status(501);
        }else
        {
            res.send("").status(200);
        }
    });
});

app.get("/group/:groupid/next",function(req,res)
{
    db.getNext(req.params.groupid,function (info) {
       if (info.err)
       {
           res.send(info.err).status(501);
       }
       else
       {
            res.send(info.songs).status(200);
       }
    });
});

app.get("/close/:groupid",function (req,res) {
    db.close(req.params.groupid,function (info) {
        if (info.err)
        {
            res.send(info.err).status(501);
        }
        else
        {
            res.send("Closed group").status(200);
        }
    })
});

app.delete("/group/:groupid/remove/:songid",function (req, res) {
    db.removeSong(req.params.groupid,req.params.songid);
    res.send("").status(200);
});

app.listen(8080);

