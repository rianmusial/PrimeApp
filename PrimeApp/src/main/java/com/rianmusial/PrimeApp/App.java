package com.rianmusial.PrimeApp;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class App {
	
	private static Logger logger = LoggerFactory.getLogger(App.class);

	@RequestMapping(value = "/ctrl/primes", method = {RequestMethod.GET})
	public static ModelAndView calculatePrimes(HttpServletRequest req, HttpServletResponse resp) {
		Map<String, String[]> queryParams = new HashMap<>(req.getParameterMap());
		logger.info("rendering page for request with parameters" + queryParams.toString());
		
		if (queryParams != null && queryParams.get("x") != null && queryParams.get("y") != null) {
			configurePrimeListOutputFromInput(queryParams);
		}
		else {
			configureDefaults(queryParams);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.getModel().put("x", queryParams.get("x"));
		mav.getModel().put("y", queryParams.get("y"));
		mav.getModel().put("z", queryParams.get("z"));
		mav.setViewName("/primes.jsp");
		return mav;
	}

	private static void configureDefaults(Map<String, String[]> queryParams) {
		queryParams.put("x", new String[]{});
		queryParams.put("y", new String[]{});
		queryParams.put("z", new String[]{});
	}

	private static void configurePrimeListOutputFromInput(Map<String, String[]> queryParams) {
		PrimeListGenerator primeListGenerator = PrimeListGenerator.getInstance();
		try {
			int x = Integer.parseInt(queryParams.get("x")[0]);
			int y = Integer.parseInt(queryParams.get("y")[0]);
			
			List<Long> primes = primeListGenerator.getPrimesBetween(x, y);
			String[] primeStrings = new String[primes.size()];
			for (int i = 0; i < primes.size(); i++)
				primeStrings[i] = String.valueOf(primes.get(i));
			queryParams.put("z", primeStrings);
		}
		catch (NumberFormatException e) {
			queryParams.put("z", new String[]{"invalid input provided"});
		}
	}
}
