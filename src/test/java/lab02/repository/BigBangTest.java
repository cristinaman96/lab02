package lab02.repository;

import junit.framework.TestCase;
import lab02.exceptions.PatientException;
import lab02.model.Consultation;
import lab02.model.Patient;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BigBangTest extends TestCase {

    Repository repository;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        repository = new Repository();
    }

    @Test
    public void testGetPatientsWithDisease() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
        repository.addPatient(new Patient("2960809125790","Numele","Apocalipsei 5"));
        assertEquals(1,repository.getPatientList().size());
        List<String> meds = new ArrayList<String>();
        meds.add("nurofen");
        repository.addConsultation(new Consultation( "2960809125790", "ill", meds, "23.03.2018"));

        assertEquals(1, repository.getPatientsWithDisease("ill").size());
    }
}
