package it.unibo.games.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GamesService {

	@Autowired
    ServletContext context;
	

	@Value("${it.unibo.games.spatial-resoning-path}")
	private String spatial_resoning_path;

	private ArrayList<Game> games;
	
	
	
	Map<String, GameType> gameTypes = new HashMap<String, GameType>() {{
        put("2004A05", GameType.SPATIAL_LOGIC);
        put("2005A01", GameType.NUMBERS_IN_DIAGRAM);
        put("2005A04", GameType.GEOMETRICAL_FIGURE);
        put("2006A03", GameType.NUMBERS_IN_DIAGRAM);
        put("2006A04", GameType.SPATIAL_LOGIC);
        put("2006A07", GameType.NUMBERS_IN_DIAGRAM);
        put("2006A08", GameType.NUMBERS_IN_DIAGRAM);
        put("2007A02", GameType.NUMBERS_IN_DIAGRAM);
        put("2007A08", GameType.SPATIAL_LOGIC);
        put("2008A06", GameType.SPATIAL_LOGIC);
        put("2012A01", GameType.NUMBERS_IN_DIAGRAM);
        put("2012A02", GameType.GEOMETRICAL_FIGURE);
        put("2014A05", GameType.SPATIAL_LOGIC);
        put("2017A01", GameType.NUMBERS_IN_DIAGRAM);
        put("2017A05", GameType.SPATIAL_LOGIC);
    }};
    Map<String, String> gameSuggest = new HashMap<String, String>() {{
        put("2004A05", "divide into a different number of pieces, e.g., divide_same_shape(35,[rotate,overturn]).");
        put("2005A01", "change the domain of the digits, e.g., constr(I/variable(I)->(var_value(I,X),X in 3..6)),");
        put("2005A04", "change the polygon, e.g., count_polygons(rectangle).");
        put("2006A03", "render the resulting image by changing the last constraint: constr(R,write_vars(R)) or change the problem into count_polygons(hexagon).");
        put("2006A04", "divide into a different number of pieces, e.g., 21");
        put("2006A07", "change the domain of the digits");
        put("2006A08", "change the domain of the digits (e.g.,X in 1..8) and the sum on the lines (e.g., sum(LX,#=,13)), or turn the puzzle into count_polygons(hexagon).");
        put("2007A02", "add another constraint, e.g., constr(all_different)");
        put("2007A08", "divide into a different number of pieces (e.g., 3) or remove one of the options");
        put("2008A06", "divide into a different number of pieces (e.g., 3) or remove one of the options");
        put("2012A01", "change the domain of the digits and/or the sum on the lines");
        put("2012A02", "remove the constraint on the polygon area or change its type, e.g., count_polygons(rectangle).");
        put("2014A05", "change or remove the options");
        put("2017A01", "change the domain of the digits and/or the sum on the lines");
        put("2017A05", "change or remove the options");
    }};

	public synchronized ArrayList<Game> getGames() {
		if (games == null)
			try {
				init();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return games;
	}

	public Game getGameByName(String name) {
		for (int i=0;i<games.size();i++) {
			if (games.get(i).getName().equals(name))
				return games.get(i);
		}
		return null;
	}

	private synchronized void init() throws IOException {
		//System.out.println("INIT");
		games=new ArrayList<>();
		String prolog_text_path = context.getRealPath("/in/pl/");
		
		File[] listFiles=new File(prolog_text_path).listFiles();
		Arrays.sort(listFiles);
		for (final File fileEntry : (listFiles)) {
			String name=fileEntry.getName().replaceAll(".pl","");
			Game g = new Game(name, gameTypes.get(name), gameSuggest.get(name));
			games.add(g);
		}
	}



}
