package it.unibo.games;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unibo.games.model.Game;
import it.unibo.games.model.GamesService;

@Controller
public class DemoController {
	@Autowired
	private GamesService gamesManager;
	private String[] allowedGames = {"2004A05","2005A01","2005A04","2006A03","2006A04",
			"2006A07","2006A08","2007A02","2007A08","2008A06","2012A01","2012A02",
			"2014A05","2017A01","2017A05"};
	
	@Autowired
    ServletContext context;
	
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
//	private StringBuffer messageBuffer = new StringBuffer();
	
	 @Value("${it.unibo.games.spatial-resoning-path}")
	 private String spatial_resoning_path;

	@RequestMapping(value={"/demo"})
	public String demoPage(Locale locale, Model model) {
		logger.info("Prolog spatial resoning Demo page loaded.", locale);
		model.addAttribute("title", "Games -- Demo  Page");
		//System.out.println(gamesManager.getGames());
		model.addAttribute("activeGameList", gamesManager.getGames());
		return "demo";
	}
	
	@RequestMapping(value = "/demo/solve", method = RequestMethod.POST)
	@ResponseBody
    public String solve(@RequestParam String gameid, @RequestParam String inpl) {
		logger.info("Demo page calling solve");
		Date start= new Date();
		logger.info("Demo page: calling Solve on gameid at "+start);
		
		String rand=gameid.split("-")[1];
		gameid=gameid.split("-")[0];
		
		if (!Arrays.asList(allowedGames).contains(gameid))
			return "Game id not allowed";
		
		String out="";
		try {
			out=StringEscapeUtils.escapeHtml4(solve_and_save( gameid, rand, inpl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;
    }
	@RequestMapping(value = "/demo/suggest", method = RequestMethod.POST)
	@ResponseBody
	public String suggestion(@RequestParam String gameid) {
		return gamesManager.getGameByName(gameid).getSuggest();
	}

	@RequestMapping(value = "/demo/aftersolving", method = RequestMethod.POST)
	@ResponseBody
	public String aftersolving(@RequestParam String imagename) {
		logger.info("Calling aftersolving...");
		
		if (imagename.contains("."))
			return "no";
		String outImgPath=context.getRealPath("/out/img/")+imagename+".png";
		File f = new File(outImgPath);
		if(f.delete())     
			logger.info(f.getName() + " deleted");   //getting and printing the file name   
		else   
			logger.info(f.getName() + " deletion failed");  	
		return "ok";
	}

	
	public String solve_and_save(String gameid, String rand, String inpl) throws IOException {
		logger.info("Calling solve and save");
		
		String prologSrc_path=spatial_resoning_path+File.separator+"prologSrc"+File.separator;
		
		String inImgPath=context.getRealPath("/in/img/")+gameid+".png";
		String outImgPath=context.getRealPath("/out/img/")+gameid+"-"+rand+".png";
		
		ProcessBuilder pb = new ProcessBuilder();
		pb.command("/bin/sh", "-c", "swipl");
		pb.redirectErrorStream(true);
		pb.directory(new File(prologSrc_path));
		Process p=pb.start();
		BufferedReader p_stdout = new BufferedReader( new InputStreamReader(p.getInputStream()));
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
		
		String line;
		String out="";
		
		String cmd="set_prolog_flag(web, true).\n";
		p_stdin.write(cmd);
		p_stdin.flush();
		System.out.print("?- "+cmd);
		while ((line = p_stdout.readLine())!=null && !line.contains("true"));
		System.out.println(line);
		
		cmd="['load.pl'].\n";
		p_stdin.write(cmd);
		p_stdin.flush();
		System.out.print("?- "+cmd);
		out+="?- "+cmd;
		while ((line = p_stdout.readLine())!=null && !line.contains("true"))
			System.out.println(line);
		System.out.println(line);
		
		//cmd="solver:demo('"+gameid+"').\n";
		inpl=inpl.replace("\\/", "\\\\/").replace("\\=", "\\\\=");
		
		
		cmd="solver:demo('"+inImgPath+"', '"+inpl+"', '"+outImgPath+"').\n";		
		p_stdin.write(cmd);
		p_stdin.flush();
		String cmd_short = "solver:demo('in/img/"+gameid+".png', '"+inpl+"', 'out/img/"+gameid+"-"+rand+".png').\n";
		out+="?- "+cmd_short;
		System.out.print(out);
		while ( (line = p_stdout.readLine())!=null ) {// && !line.contains("result") && !line.contains("false") ) {
			System.out.println(line);
			out+=line+"\n";
			if (line.contains("result") )
				break;
			if (line.contains("false") || line.contains("ERROR")) {
				p_stdin.write("halt.\n");
				p_stdin.flush();
				break;
			}
		}
		
		
		return out;
	}

	
	public String solve_and_save(String gameid) throws IOException {
		logger.info("Calling solve and save");
		
		String prologSrc_path=spatial_resoning_path+File.separator+"prologSrc"+File.separator;
		
		String out_pl_path = context.getRealPath("/out/pl/")+gameid;
		File out_pl= new File(out_pl_path);
		out_pl.createNewFile();
		FileWriter fw = new FileWriter(out_pl);
		
		
		ProcessBuilder pb = new ProcessBuilder();
		pb.command("/bin/sh", "-c", "swipl");
		pb.redirectErrorStream(true);
		pb.directory(new File(prologSrc_path));
		Process p=pb.start();
		BufferedReader p_stdout = new BufferedReader( new InputStreamReader(p.getInputStream()));
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
		
		String line;
		String out="";
		
		String cmd="set_prolog_flag(web, true).\n";
		p_stdin.write(cmd);
		p_stdin.flush();
		System.out.println(cmd);
		
		while ((line = p_stdout.readLine())!=null && !line.contains("true"));
		System.out.println(line);
		
		cmd="['load.pl'].\n";
		p_stdin.write(cmd);
		p_stdin.flush();
		System.out.println("?- "+cmd);
		fw.write("?- "+cmd);
		out+="?- "+cmd;
		while ((line = p_stdout.readLine())!=null && !line.contains("true"))
			System.out.println(line);
		System.out.println(line);
		
		cmd="solver:demo('"+gameid+"').\n";
		p_stdin.write(cmd);
		p_stdin.flush();
		System.out.println("?- "+cmd);
		fw.append("?- "+cmd);
		out+="?- "+cmd;
		while ((line = p_stdout.readLine())!=null && !line.contains("result")) {
			System.out.println(line);
			fw.append(line+"\n");
			out+=line+"\n";
		}
		System.out.println(line);
		fw.append(line+"\n");
		out+=line+"\n";
		fw.close();
		
		return out;
	}



	
}