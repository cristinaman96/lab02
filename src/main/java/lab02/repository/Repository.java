package lab02.repository;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import lab02.exceptions.PatientException;
import lab02.model.*;
import lab02.validator.PatientValidation;


public class Repository {

	private String patientsFile; // list of patients
	private String consultationsFile; // list of consultation

	private ArrayList<Consultation> consultationList;
	private ArrayList<Patient> patientList;

	public Repository(){
        consultationList = new ArrayList<Consultation>();
        patientList = new ArrayList<Patient>();
    }

	public Repository(String patients, String consultations)
	{
		this.patientsFile = patients;
		this.consultationsFile = consultations;

		consultationList = new ArrayList<Consultation>();
		patientList = new ArrayList<Patient>();
	}

	/*	public void cleanFiles()
        {
            FileWriter fw;
            try {
                fw = new FileWriter(patients);
                PrintWriter out=new PrintWriter(fw);
                out.print("");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileWriter fwc;
            try {
                fwc = new FileWriter(consultations);
                PrintWriter out=new PrintWriter(fwc);
                out.print("");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            consultationList = new ArrayList<Consultation>();
            patientList = new ArrayList<Patient>();
        }
        */
	public ArrayList<Patient> getPatientsFromFile() throws IOException
	{
		FileReader fr = new FileReader(patientsFile);
		BufferedReader br = new BufferedReader(fr);
//		while ((in.readLine()) != null) {
//			n++;
//		}
//		in.close();
//
//		String[] la=new String[n];
//		String s = new String();
//		int i = 0;
//		in = new BufferedReader(new FileReader(patients));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] result = line.split(",");
			Patient p = new Patient();
			p.setName(result[0]);
			p.setSSN(result[1]);
			p.setAddress(result[2]);
			patientList.add(p);
		}
		br.close();
		return patientList;
	}

	public ArrayList<Consultation> getConsultationsFromFile() throws IOException
	{
		FileReader fr = new FileReader(patientsFile);
		BufferedReader br = new BufferedReader(fr);
//		while ((in.readLine()) != null) {
//			n++;
//		}
//		in.close();
//
//		String[] la=new String[n];
//		String s = new String();
//		int i = 0;
//		in = new BufferedReader(new FileReader(consultations));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] result = line.split(",");
			Consultation consultation = new Consultation();
			consultation.setPatientSSN(result[0]);
			consultation.setDiagnostic(result[1]);
			String[] r = result[2].split(" + ");
			ArrayList<String> res = new ArrayList<String>();
			for (int i = 0; i<r.length; i++)
				res.add(r[i]);
			consultation.setMeds(res);
			consultation.setConsultation_date(result[3]);
			consultationList.add(consultation);
		}
		br.close();
		return consultationList;
	}

	public List<Patient> getPatientList()
	{
//		List<Patient> lp = new ArrayList<Patient>();
//		try {
//			String[] tokens = getPatientsFromFile();
//
//			String tok = new String();
//			String[] pat;
//			int i = 0;
//			while (i < tokens.length)
//			{
//				tok = tokens[i];
//				pat = tok.split(",");
//				lp.add(new Patient(pat[0],pat[1],pat[2]));
//				i = i + 1;
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return lp;
		return patientList;
	}

	public List<Consultation> getConsultationList()
	{
//		List<Consultation> lp = new ArrayList<Consultation>();
//		try {
//			String[] tokens = getConsultationsFromFile();
//
//
//			String tok = new String();
//			String[] cons;
//			String[] meds;
//			List<String> med = new ArrayList<String>();
//			int i = 0;
//			while (i < tokens.length)
//			{
//				tok = tokens[i];
//				cons = tok.split(",");
//				meds = cons[3].split("\\+");
//				Consultation consultation = new Consultation(cons[0],cons[1],cons[2],med,cons[4]);
//				for (int j = 0; j < meds.length-1; j++)
//				{
//					consultation.getMedicine().add(meds[j]);
//				}
//				lp.add(consultation);
//				i = i + 1;
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return lp;
		return consultationList;
	}

	public void savePatientToFile(Patient p) throws IOException		// save to file
	{
		FileWriter fileWriter = new FileWriter(patientsFile,true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.write(p.toString());
		bufferedWriter.newLine();
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	public void saveConsultationToFile(Consultation c) throws IOException		// save to file
	{
		FileWriter fr = new FileWriter(consultationsFile, true);
		BufferedWriter br = new BufferedWriter(fr);
		br.write(c.toString());
		br.newLine();
		br.flush();
		br.close();
	}

	public void addPatient(Patient p) throws PatientException {

            try {
                PatientValidation.nameValidate(p.getName());
                PatientValidation.ssnValidate(p.getSSN());
                PatientValidation.addressValidate(p.getAddress());

            } catch (PatientException e) {
                throw new PatientException(e.toString());
            }

		patientList.add(p);
	}

	public void addConsultation(Consultation c){
		consultationList.add(c);
	}

	public List<Patient> getPatientsWithDisease(String disease) throws PatientException{
		List<Consultation> c = this.getConsultationList();
		List<Patient> p = new ArrayList<Patient>();
		if (disease != null) {
			if (disease.length() == 0) {
				throw new PatientException("Empty disease provided");
			}
			int chk = 1;

			for (int i = 0; i < c.size(); i++) {
				if (c.get(i).getDiagnostic().toLowerCase()
						.contains(disease.toLowerCase())) // so that it is case insensitive
				{
					for (int j = 0; j < p.size(); j++) // verify patient was  not already added
					{
						if (p.get(j).getSSN().equals(c.get(i).getPatientSSN())) {
							chk = p.get(j).getConsNum();
						}
					}

					if (chk == 1) {
						p.add(this.getPatientList().get(
								this.getPatientBySSN(c.get(i).getPatientSSN()))); // get Patient by SSN
					}
					chk = 1;
				}
			}

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

	private int getPatientBySSN(String SSN) {
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getSSN().equals(SSN))
				return i;
		}
		return -1;
	}


}
