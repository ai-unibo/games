package it.unibo.games.model;


public class Game {
	private String name;
	
	private GameType type;
	
	private String suggest;

	


	public Game(String name, GameType type, String suggest) {
		super();
		this.name = name;
		this.type = type;
		this.suggest=suggest;
	}
	
	
	public GameType getType() {
		return type;
	}


	public void setType(GameType type) {
		this.type = type;
	}

	public String getSuggest() {
		return suggest;
	}


	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	
	public String getName() {
		return name;
	}
	
	
	

}
