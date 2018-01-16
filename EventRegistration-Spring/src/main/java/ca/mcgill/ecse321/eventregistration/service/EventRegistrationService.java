package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.model.Participant;
import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;

@Service
public class EventRegistrationService {
	private RegistrationManager rm;

	public EventRegistrationService(RegistrationManager rm) {
		this.rm = rm;
	}

	public Participant createParticipant(String name) throws InvalidInputException
	{
		if(name == null)
			throw new InvalidInputException("Empty name now allowed");
	  Participant p = new Participant(name);
	  rm.addParticipant(p);
	  PersistenceXStream.saveToXMLwithXStream(rm);
	  return p;
	}
}
