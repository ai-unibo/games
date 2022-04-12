package it.unibo.games;


import java.util.Date;


public class LogMessage {

	public enum Level {
	    INFO, WARNING, ERROR 
	}

	
	
	// Log message unique id
	private long id;
	
	// Time stamp of the message
	private Date timestamp;
	
	// message content
	private String content;
	
	// log message level
	private Level level;

	
	
	
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }

	public Date getTimestamp() { return timestamp; }
	public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
	
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

	public Level getLevel() { return level; }
	public void setLevel(Level level) { this.level = level; }
	
	
	
	
	
}
