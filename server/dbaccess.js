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

function getGroupByGUID(guid,cb)
{
    connection.query('SELECT * FROM songs WHERE groupguid = ?',[guid],cb);
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
    close: function (groupname, callback) {
        getGroup(groupname,function (err, rows) {
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
            connection.query("UPDATE groups SET open=FALSE WHERE name=?",[groupname]);
            callback({});
        });
    },
};