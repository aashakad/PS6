package base;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {
	
	private static PersonDomainModel per1 = new PersonDomainModel();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		PersonDAL.deletePerson(per1.getPersonID());
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddPerson() {
		per1.setBirthday(new Date(0));
		per1.setCity("Townsend");
		per1.setFirstName("Bert");
		per1.setLastName("Gibbons");
		per1.setPostalCode(19734);
		per1.setStreet("214 Labrador Lane");
		
		PersonDAL.addPerson(per1);
		
		PersonDomainModel per2 = PersonDAL.getPerson(per1.getPersonID());
		//Test for get and add
		assertNotNull(per2);
		//Test for add
		assertEquals(per2.getPersonID(), per1.getPersonID());
		
		per2.setLastName("Potter");
		PersonDAL.updatePerson(per2);
		
		PersonDomainModel per3 = PersonDAL.getPerson(per2.getPersonID());
		//Test for update
		assertEquals(per2.getLastName(), per3.getLastName());
		assertNotEquals(per2.getLastName(), per1.getLastName());
		
		//ArrayList <PersonDomainModel> pers1 = new ArrayList<PersonDomainModel>();
		//pers1.add(per1);
		ArrayList <PersonDomainModel> pers2 = PersonDAL.getPersons();
		//Test for getPersons
		assertNotNull(pers2);
		
		PersonDAL.deletePerson(per1.getPersonID());
		//Test for delete
		assertNull(PersonDAL.getPerson(per1.getPersonID()));
		
	}

}
