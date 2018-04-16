package lab02.controller;

import junit.framework.TestCase;
import lab02.exceptions.ConsultationException;
import lab02.exceptions.PatientException;
import lab02.model.Patient;
import lab02.repository.Repository;
import org.junit.Test;

import javax.naming.ldap.Control;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class DoctorControllerTest extends TestCase {

    Repository repository;
    DoctorController controller;
    String consultationsFile = "FileConsultations.txt";
    String patientsFile = "FilePatients.txt";



    public void setUp() throws Exception {
        super.setUp();
        repository = new Repository(patientsFile,consultationsFile);
        controller = new DoctorController(repository);
    }

    public void testAddConsultation() throws Exception {
        List<String> meds = new ArrayList<String>();
        meds.add("nurofen");
        repository.addPatient(new Patient("2960809125790", "Numel", "Apoca"));
        assertEquals(0,controller.getConsultationList().size());
        controller.addConsultation(1, "2960809125790", "ill", meds, "23.03.2018");
        assertEquals(1, controller.getConsultationList().size());
    }

    @Test(expected = IOException.class)
    public void testAddConsultation2()  {
        List<String> meds = new ArrayList<String>();
        meds.add("nurofen");
        try {

            Repository repository2 = new Repository(patientsFile, "consu!@$%^$^*(*)...xsl");
            repository2.addPatient(new Patient("2960809125790", "Numel", "Apoca"));
            DoctorController controller2 = new DoctorController(repository2);
//        assertEquals(0,controller2.getConsultationList().size());
            controller2.addConsultation(1, "2960809125790", "ill", meds, "23.03.2018");
//        assertEquals(1, controller2.getConsultationList().size());
        }catch (IOException e){
            System.out.println("pass");
        }catch (PatientException e){

        }catch (ConsultationException e) {
        }

    }

    @Test(expected = ConsultationException.class)
    public void testAddConsultation3() throws Exception {
        List<String> meds = new ArrayList<String>();
        try {
            repository.addPatient(new Patient("2960809125790", "Numel", "Apoca"));
//        assertEquals(0,controller.getConsultationList().size());
            controller.addConsultation(1, "2960809125790", "ill", meds, "23.03.2018");
//        assertEquals(1, controller.getConsultationList().size());
        }catch (Exception e){
            System.out.println("pass");
        }
    }

    @Test(expected = ConsultationException.class)
    public void testAddConsultation4() throws Exception {
        List<String> meds = null;
        try {
            repository.addPatient(new Patient("2960809125790", "Numel", "Apoca"));
//        assertEquals(0,controller.getConsultationList().size());
            controller.addConsultation(1, "2960809125790", "ill", meds, "23.03.2018");
//        assertEquals(1, controller.getConsultationList().size());
        }catch (Exception e){
            System.out.println("pass");
        }
    }



}