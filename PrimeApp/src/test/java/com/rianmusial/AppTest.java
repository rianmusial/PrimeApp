package com.rianmusial;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.ModelAndView;

import com.rianmusial.PrimeApp.App;

public class AppTest {

	@Test
	public void testCalculatePrimes() {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		App a = new App();
		ModelAndView mav = a.calculatePrimes(req, null);
		assertEquals("/primes.jsp", mav.getViewName());
	}

}
