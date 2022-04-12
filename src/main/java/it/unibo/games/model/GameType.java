package it.unibo.games.model;

public enum GameType {
	NUMBERS_IN_DIAGRAM,
	GEOMETRICAL_FIGURE,
	SPATIAL_LOGIC;
	
	public String human_readable_name() {
		if (this==GameType.NUMBERS_IN_DIAGRAM) return "Numbers-in-diagram";
		if (this==GameType.GEOMETRICAL_FIGURE) return "Geometrical figure";
		if (this==GameType.SPATIAL_LOGIC) return "Spatial logic";
		return "Unknown game type";
		
	}
}
