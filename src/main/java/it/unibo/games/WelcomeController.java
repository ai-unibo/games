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
public class WelcomeController {	
	
	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	private StringBuffer messageBuffer = new StringBuffer();

	

	@RequestMapping(value={"/"})
	public String welcomePage(Locale locale, Model model) {
		logger.info("Prolog spatial resoning page loaded.", locale);
		model.addAttribute("title", "Games -- Home Page");
		return "welcome";
	}
	

	
}