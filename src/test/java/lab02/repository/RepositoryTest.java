package lab02.repository;

import junit.framework.TestCase;
import lab02.exceptions.PatientException;
import lab02.model.Patient;
import org.junit.Test;

public class RepositoryTest extends TestCase {

    Repository repository;

    public void setUp() throws Exception {
        super.setUp();
        repository = new Repository();
    }

    public void testAddPatient() throws Exception {
        assertEquals(0,repository.getPatientList().size());
        repository.addPatient(new Patient("2960809125790", "Numel", "Apoca"));
        assertEquals(1,repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase1() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
        repository.addPatient(new Patient("2960809125790","Numele","Apocalipsei 5"));
        assertEquals(1,repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase2() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
        repository.addPatient(new Patient("2960809125790","Numele","Apocal"));
        assertEquals(1,repository.getPatientList().size());
    }

    @Test(expected = PatientException.class)
    public void testAddPatientCase3() throws PatientException {
        assertEquals(0,repository.getPatientList().size());

//        repository.addPatient(new Patient("2960809125.,;","Numele","Apocalipsei 5"));
    }

    @Test
    public void testAddPatientCase4() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
//        repository.addPatient(new Patient("2960809125790","Numele .","Apocalipsei 5"));
        assertEquals(0,repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase5() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
//        repository.addPatient(new Patient("2960809125790","Numele","Apocalipsei, 5."));
        assertEquals(0,repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase6() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
//        repository.addPatient(new Patient("296080912579","Numele","Apocalipsei 5"));
        assertEquals(0,repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase7() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
//        repository.addPatient(new Patient("29608091257902","Numele","Apocalipsei 5"));
        assertEquals(0,repository.getPatientList().size());
    }

    @Test
    public void testAddPatientCase8() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
//        repository.addPatient(new Patient("2960809125790","Nume","Apocalipsei 5"));
        assertEquals(0,repository.getPatientList().size());
    }
    @Test
    public void testAddPatientCase9() throws PatientException {
        assertEquals(0,repository.getPatientList().size());
//        repository.addPatient(new Patient("2960809125790","Numel","Apoc"));
        assertEquals(0,repository.getPatientList().size());
    }





    public void testAddConsultation() throws Exception {

        assertEquals(3,3);

    }

}