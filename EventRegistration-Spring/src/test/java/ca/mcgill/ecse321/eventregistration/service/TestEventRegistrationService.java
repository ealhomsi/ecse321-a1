package ca.mcgill.ecse321.eventregistration.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;

public class TestEventRegistrationService {

	private RegistrationManager rm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output" + File.separator + "data.xml");
	}

	@Before
	public void setUp() throws Exception {
		rm = new RegistrationManager();
	}

	@After
	public void tearDown() throws Exception {
		rm.delete();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreateParticipant() {
		assertEquals(0, rm.getParticipants().size());

		String name = "Oscar";

		EventRegistrationService erc = new EventRegistrationService(rm);
		try {
			erc.createParticipant(name);
		} catch (InvalidInputException e) {
			// Check that no error occured
			fail();
		}

		// check model in memory
		checkResultParticipant(name, rm);

		rm = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultParticipant(name, rm);
	}

	private void checkResultParticipant(String name, RegistrationManager rm2) {
		assertEquals(1, rm2.getParticipants().size());
		assertEquals(name, rm2.getParticipant(0).getName());
		assertEquals(0, rm2.getEvents().size());
		assertEquals(0, rm2.getRegistrations().size());
	}

}
