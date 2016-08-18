package com.sapient.fees.processor.controller;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fees.processor.controller.FeesControllerAPI;
import com.fees.processor.exception.FeeProcessingException;

public class ControllerTest {

	@Before
	public void setUp() throws Exception {
		//Not recommended
	}

	@After
	public void tearDown() throws Exception {
		//Not recommended
	}

	@Test
	public void testIntraDayScenario() throws IOException, FeeProcessingException {
		String file = "C://Users/rku140/workspace/fees-processor/inputFileContainer/transaction.txt";
		
		FeesControllerAPI controller = new FeesControllerAPI();
		assertEquals(20.0, controller.triggerController(file).get("ASRELSELL11062013N").getFees(), 0.0);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testSameDayScenario() throws IOException, FeeProcessingException {
		String file = "C://Users/rku140/workspace/fees-processor/inputFileContainer/transaction.txt";
		
		FeesControllerAPI controller = new FeesControllerAPI();
		assertEquals(1000.0, controller.triggerController(file).get("APICICIBUY11092013Y").getFees(), 0.0);
	}
	
	@Test
	public void testNormalDayScenario() {
		String file = "C://Users/rku140/workspace/fees-processor/inputFileContainer/transaction.txt";
		
		FeesControllerAPI controller = new FeesControllerAPI();
		//assertEquals(10.0,controller.triggerController(file));
	}
	
	@Test
	public void testEmptyFileScenario() {
		String file = "C://Users/rku140/workspace/fees-processor/inputFileContainer/transaction.txt";
		
		FeesControllerAPI controller = new FeesControllerAPI();
		//assertEquals(10.0,controller.triggerController(file));
	}

}
