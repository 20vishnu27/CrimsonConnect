package DS_Mini;

class Appointment {

	String patientName;
	   String hospitalName;
	   String appointmentDate;
	   String ageRange;

	   public Appointment(String patientName, String hospitalName, String appointmentDate, String ageRange) {
	       this.patientName = patientName;
	       this.hospitalName = hospitalName;
	       this.appointmentDate = appointmentDate;
	       this.ageRange = ageRange;
	   }

	   @Override
	   public String toString() {
	       return "Appointment{" +
	               "patientName='" + patientName + '\'' +
	               ", hospitalName='" + hospitalName + '\'' +
	               ", appointmentDate='" + appointmentDate + '\'' +
	               ", ageRange='" + ageRange + '\'' +
	               '}';
	   }
	}
