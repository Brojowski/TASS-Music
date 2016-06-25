/**
 * Created by alex on 6/25/16.
 */
var mysql = require("mysql");
var connection = mysql.createConnection({
    host: 'localhost',
    user: 'tass',
    password:'password',
    database:'tassmusic'
});
var guidGen = require("node-uuid");

connection.connect(function (err) {
    if (err)
    {
        console.log(err);
    }
    else
    {
        console.log("DB Connected.");
    }
});

function getGroup(groupname,cb)
{
    connection.query('SELECT * FROM groups WHERE name = ?',[groupname],cb);
}

module.exports =
{
    createGroup:function (groupname, callback) {
        getGroup(groupname,function(err,results) {
            if (err) {
                callback({err: "Database error"});
                return;
            }
            if (results.length > 0 && results[0].open) {
                callback({err: "Group already exists."})
                return;
            }
            var guid = guidGen.v4();
            if (results.length > 0)
            {
                connection.query('UPDATE groups SET open=TRUE,guid=? WHERE name=?',[guid,groupname])
            }else
            {
                connection.query('INSERT INTO groups VALUES (?,?,true)', [groupname, guid]);
            }
            callback({guid: guid});
        });
    },
    join: function (groupname, callback) {
        getGroup(groupname,function (err, results) {
            if (err)
            {
                callback({err:"Database error"});
                return;
            }
            if (results.length === 0)
            {
                callback({err:"No groups"});
                return;
            }
            if (!results[0].open)
            {
                callback({err:"Group closed"});
                return;
            }
            callback({guid:results[0].guid});
        })
    },
    close: function (guid, callback) {
        connection.query("SELECT * FROM groups WHERE guid=?",[guid],function (err, rows) {
            if (err)
            {
                callback({err:"Database error"});
                return;
            }
            if (rows.length === 0)
            {
                callback({err:"No group exists"});
                return;
            }
            connection.query("UPDATE groups SET open=FALSE WHERE guid=?",[guid]);
            callback({});
        });
    },
    add: function (guid, uri, callback) {
        connection.query("SELECT * FROM songs WHERE uri=?",[uri],function (err, rows) {
            if (err)
            {
                callback({err:"Database error"});
                return;
            }
            if (rows.length < 1)
            {
                //add to table
                connection.query("INSERT INTO songs VALUES (?,1,?)",[guid,uri]);
            }
            connection.query("UPDATE songs SET votes = votes + 1 WHERE groupguid=?",[guid]);
            callback({});
        });
    },
    getNext: function (guid,callback) {
        connection.query("SELECT * FROM songs WHERE groupguid=? ORDER BY votes DESC",[guid],function (err, rows) {
            if (err)
            {
                callback({err:"Database error"});
                return;
            }
            callback({songs:rows});
        });
    },
    removeSong: function (guid, uri) {
        connection.query("DELETE FROM songs WHERE groupguid=? AND uri=?",[guid,uri]);
    }
};