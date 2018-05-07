var db=require('../dbconnection'); //reference of dbconnection.js
var sleep = require('../helpers/sleep');
 
var Player={
 
	getAllPlayers:function(callback){ 
	    return db.query("SELECT * FROM playerbase",callback);
	},

	getPlayerById:function(id, callback){
	 
	    return db.query("SELECT * FROM playerbase WHERE Id=?",[id],callback);
	},
	addPlayer:function(Player, callback){
		createdAt = new Date();
		updatedAt = new Date();
		return db.query("INSERT INTO playerbase (created_at, first_name, last_name, position, updated_at) VALUES(?,?,?,?,?)",
		[createdAt, Player.first_name, Player.last_name, Player.position, updatedAt], callback);
	},
	deletePlayer:function(id, callback){
	    return db.query("DELETE FROM playerbase WHERE id=?", [id], callback);
	},
	updatePlayer:function(id, Player, callback){
		updatedAt = new Date();
	    return db.query("UPDATE playerbase SET first_name=?, last_name=?, position=?, updated_at=? where id=?",[Player.first_name, Player.last_name, 
	    																										Player.position, updatedAt, id], callback);
	}
 
};
 module.exports=Player;
