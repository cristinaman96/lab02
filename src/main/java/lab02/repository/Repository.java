package lab02.repository;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import lab02.model.*;


public class Repository {

	private String patientsFile; // list of patients
	private String consultationsFile; // list of consultation

	private ArrayList<Consultation> consultationList;
	private ArrayList<Patient> patientList;

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
			consultation.setConsID(Integer.getInteger(result[0]));
			consultation.setPatientSSN(result[1]);
			consultation.setDiagnostic(result[2]);
			String[] r = result[3].split(" + ");
			ArrayList<String> res = new ArrayList<String>();
			for (int i = 0; i<r.length; i++)
				res.add(r[i]);
			consultation.setMeds(res);
			consultation.setConsultation_date(result[4]);
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
//		int n=0;
//		BufferedReader in = new BufferedReader(new FileReader(patients));
//		while((in.readLine())!=null)
//			n++;
//		in.close();
//		String[] sl=new String[n];
//		String str;
//		int i=0;
//		in = new BufferedReader(new FileReader(patients));
//		while((str=in.readLine())!=null)
//		{
//			sl[i] = str;
//			i++;
//		}
//		in.close(); // append
//		FileWriter fw=new FileWriter(patients);
//		PrintWriter out=new PrintWriter(fw);
//		for (i=1; i<sl.length-1; i++)
//			out.println(sl[i]);
//		out.println(p.toString());
//		out.close();
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
		br.close();
	}

	public void addPatient(Patient p) {

		patientList.add(p);
	}

	public void addConsultation(Consultation c){
		consultationList.add(c);
	}

}
