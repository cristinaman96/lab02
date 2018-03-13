package lab02.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lab02.exceptions.*;
import lab02.repository.Repository;
import lab02.validator.ConsultationValidation;
import lab02.validator.PatientValidation;
import lab02.model.Consultation;
import lab02.model.Patient;

public class DoctorController {

	private List<Patient> PatientList;
	private List<Consultation> ConsultationList;
	private Repository rep;

	/** Constructors */

	public DoctorController(Repository rep) {
		this.rep = rep;
		this.PatientList = rep.getPatientList();
		this.ConsultationList = rep.getConsultationList();
		// Get list from file in order to avoid duplicates.
	}

	/** Getters */
	public List<Patient> getPatientList() {
		return rep.getPatientList();
	}

	public List<Consultation> getConsultationList() {
		return rep.getConsultationList();
	}

	public void setConsulationList(List<Consultation> consultationList) {
		ConsultationList = consultationList;
	}

	public int getPatientBySSN(String SSN) {
		for (int i = 0; i < PatientList.size(); i++) {
			if (PatientList.get(i).getSSN().equals(SSN))
				return i;
		}

		return -1;
	}

	public int getConsByID(int ID) {
		for (int i = 0; i < ConsultationList.size(); i++) {
			if (ConsultationList.get(i).getConsID() == ID) {
				/*
				 * System.out.println("I proud to have found " + ID + " here: "
				 * + i); System.out.println("Proof : " +
				 * ConsultationList.get(i).toString());
				 */
				return i ;
			}
		}

		return -1;
	}

	public Repository getRepository() {
		return rep;
	}

	/** Others */
	public void addPatient(Patient p) throws PatientException {
		if (p.getName() != null && p.getSSN() != null && p.getAddress() != null) {
			PatientValidation.nameValidate(p.getName());
			PatientValidation.ssnValidate(p.getSSN());
			PatientValidation.addressValidate(p.getAddress());
		} else {
			throw new PatientException("Null fields");
		}
		try {
			rep.addPatient(p);
			rep.savePatientToFile(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// adding of a new consultation for a patient (consultation date,
	// diagnostic, prescription drugs)

	public void addConsultation(int consID, String patientSSN, String diag,
								List<String> meds, String date) throws ConsultationException, PatientException {
		if (meds == null)
			throw new ConsultationException("meds is null");

		if (consID != 0 && patientSSN != null && diag != null && meds.size() != 0 && this.getPatientBySSN(patientSSN) > -1
				&& this.getConsByID(consID) == -1) {
			ConsultationValidation.ssnValidate(patientSSN);
			Consultation c = new Consultation(consID, patientSSN, diag, meds, date);
			try {
				rep.addConsultation(c);
				rep.saveConsultationToFile(c);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Patient p = new Patient();
			p = this.getPatientList().get(
					this.getPatientBySSN(c.getPatientSSN()));
			p.setConsNum(p.getConsNum() + 1);
		}
		else {
			throw new ConsultationException("invalid arguments");
		}

	}

	public List<Patient> getPatientsWithDisease(String disease) throws PatientException {
		List<Consultation> c = this.getConsultationList();
		List<Patient> p = new ArrayList<Patient>();
		if (disease != null) {
			if (disease.length() == 0) {
				throw new PatientException("Empty disease provided");
			}
			int chk = 1;

			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getDiagnostic().toLowerCase()
						.contains(disease.toLowerCase())) // so that it is case
				// insensitive
				{
					for (int j = 0; j < p.size(); j++) // verify patient was
					// not already added
					{
						if (p.get(j).getSSN().equals(c.get(i).getPatientSSN())) {
							chk = p.get(j).getConsNum();
						}
					}

					if (chk == 1) {
						p.add(this.getPatientList().get(
								this.getPatientBySSN(c.get(i).getPatientSSN()))); // get
						// Patient
						// by
						// SSN
					}
					chk = 1;
				}
			}

			// Sort the list

			Patient paux = new Patient();

			for (int i = 0; i < p.size(); i++)
				for (int j = i + 1; j < p.size() - 1; j++)
					if (p.get(j - 1).getConsNum() < p.get(j).getConsNum()) {
						paux = p.get(j - 1);
						p.set(j - 1, p.get(j));
						p.set(j, paux);
					}
		}
		else {
			throw new PatientException("Null disease parameter provided");
		}
		return p;
	}

	/*
	 * For debugging purposes public void printList() { for (int i = 0; i <
	 * PatientList.size(); i++) {
	 * System.out.println(PatientList.get(i).toString()); } }
	 */

}
