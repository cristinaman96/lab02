package lab02.model;

import java.util.ArrayList;
import java.util.List;

public class Consultation {
	private String PatientSSN;
	private String diagnostic;
	private List<String> medicine;
	private String consultation_date;
	
	public Consultation() 
	{ 
		this.medicine = new ArrayList<String>();
	}
	
	public Consultation ( String PatientSSN, String diag, List<String> meds, String date)
	{
		this.PatientSSN = PatientSSN;
		this.diagnostic = diag;
		this.medicine = meds;
		this.consultation_date = date;
	}

	
	public String getPatientSSN() {
		return PatientSSN;
	}
	public void setPatientSSN(String patientSSN) {
		PatientSSN = patientSSN;
	}
	public String getDiagnostic() {
		return diagnostic;
	}
	public void setDiagnostic(String diag) {
		this.diagnostic = diag;
	}
	public List<String> getMedicine() {
		return medicine;
	}
	public void setMeds(List<String> meds) {
		this.medicine = meds;
	}
	public String getConsultation_date() {
		return consultation_date;
	}
	public void setConsultation_date(String consultation_date) {
		this.consultation_date = consultation_date;
	}

	@Override
	public String toString() {
		return "Consultation{" +
				", PatientSSN='" + PatientSSN + '\'' +
				", diagnostic='" + diagnostic + '\'' +
				", medicine=" + medicine +
				", consultation_date='" + consultation_date + '\'' +
				'}';
	}
}
