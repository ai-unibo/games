package it.unibo.games;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DownloadController {	
	
	private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);
	private StringBuffer messageBuffer = new StringBuffer();

	

	@RequestMapping(value={"/download"})
	public String welcomePage(Locale locale, Model model) {
		logger.info("Download page loaded.", locale);
		model.addAttribute("title", "Games -- Download Page");
		return "download";
	}
	

	
}