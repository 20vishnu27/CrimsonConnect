package DS_Mini;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HospitalFinder {
   static HashMap<String, ArrayList<Hospital>> areaMap = new HashMap<>(20);
   static ArrayList<Appointment> appointments = new ArrayList<>();

   public static void main(String[] args) {
   
       Scanner sc = new Scanner(System.in);
       int choice = 0;

       System.out.println("________________________________________________________");
       System.out.println("1910 - PUNE CENTRALIZED MEDICAL HELPLINE NUMBER");
       System.out.println("In case of emergency, we're there for you!");
       System.out.println("________________________________________________________");
       System.out.println("########### HOSPITAL AND APPOINTMENT SYSTEM ###########");
       System.out.println("1:Minor--------> 10:Extremely Critical");
       System.out.print("Rate the emergency: ");

       int emergencyLevel = sc.nextInt();
       sc.nextLine(); // Consume newline

       while (choice != 5) {
           System.out.println("\n1. Find hospitals by area and emergency level");
           System.out.println("2. Make an appointment");
           System.out.println("3. View previous appointments");
           System.out.println("4. Update an appointment");
           System.out.println("5. Exit");
           System.out.print("Enter your choice: ");

           choice = sc.nextInt();
           sc.nextLine(); // Consume newline

           switch (choice) {
               case 1:
            	   loadHospitalData();
                   break;
               case 2:
                   makeAppointment(sc);
                   break;
               case 3:
                   viewAppointments();
                   break;
               case 4:
                   updateAppointment(sc);
                   break;
               case 5:
                   System.out.println("Exiting the system. Stay safe!");
                   break;
               default:
                   System.out.println("Invalid choice! Please try again.");
           }
       }
   }

   // Load hospital data from JSON file
   public static void loadHospitalData() {
   	 try {
	            // Load the JSON data from file
	            File jsonFile = new File("C:\\Users\\hp\\Downloads\\export.json"); // Path to your downloaded JSON file
	            FileReader fileReader = new FileReader(jsonFile);
	            StringBuilder stringBuilder = new StringBuilder();
	            int i;

                 while ((i = fileReader.read()) != -1) {
	                stringBuilder.append((char) i);
	            }
	            // Parse the JSON data
	            JSONObject rootObject = new JSONObject(stringBuilder.toString());
	            JSONArray elements = rootObject.getJSONArray("elements"); // Adjusted to match your JSON structure

	            // Group hospitals by area
	            HashMap<String, ArrayList<Hospital>> areaMap = groupHospitalsByArea(elements);

	            // Print out the hospitals in a given area based on user input
	            Scanner scanner = new Scanner(System.in);
	            System.out.print("Enter area name to get hospitals: ");
	            String areaName = scanner.nextLine().trim();
	            
	            ArrayList<Hospital> hospitalsInArea = getHospitalsByArea(areaMap, areaName);

	            if (hospitalsInArea == null || hospitalsInArea.size() == 0) {
	                System.out.println("No hospitals found in the area: " + areaName);
	            } else {


	                System.out.println("Hospitals in " + areaName + ":");
	                for (Hospital hospital : hospitalsInArea) {
	                    System.out.println(hospital);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();

	        } catch (org.json.JSONException e) {
	            e.printStackTrace();

	        }
  	 }
   // Method to group hospitals by area using HashMap and ArrayList
   public static HashMap<String, ArrayList<Hospital>> groupHospitalsByArea(JSONArray elements) {
       HashMap<String, ArrayList<Hospital>> areaMap = new HashMap<>(20);
       // Traverse the JSON array (each element is a hospital)
       for (int j = 0; j < elements.length(); j++) {
           JSONObject element = elements.getJSONObject(j);
           // Extract relevant fields: name, lat, lon, area
           String name = element.optJSONObject("tags") != null && element.optJSONObject("tags").optString("name", null) != null
                   ? element.optJSONObject("tags").optString("name")
                   : "Unknown";

           double lat = element.optDouble("lat", 0.0);
           double lon = element.optDouble("lon", 0.0);
           String area = element.optJSONObject("tags") != null && element.optJSONObject("tags").optString("addr:full", null) != null
                   ? element.optJSONObject("tags").optString("addr:full")
                   : "Unspecified Area";

          // Normalize area names for comparison
           area = area.trim().toLowerCase();

           // Create a Hospital object
           Hospital hospital = new Hospital(name, lat, lon, area);

          // Add to the area map
           if (!areaMap.containsKey(area)) {
               areaMap.put(area, new ArrayList<>());
           }
           areaMap.get(area).add(hospital);
       }
      return areaMap;
   }
   // Method to get hospitals by area using HashMap
   public static ArrayList<Hospital> getHospitalsByArea(HashMap<String, ArrayList<Hospital>> areaMap, String areaName) {
       // Normalize the input area name for comparison
       areaName = areaName.trim().toLowerCase();
       ArrayList<Hospital> hospitalsInArea = new ArrayList<>();

      // Search for hospitals in the map by matching area names (case-insensitive and allow partial matching)
       for (String area : areaMap.keySet()) {

           if (area.contains(areaName)) {  // Partial matching of area (case-insensitive)
               hospitalsInArea.addAll(areaMap.get(area)); // Add matching hospitals
           }
      }







       return hospitalsInArea;



   }



   // Find hospitals by area and emergency level
  /* public static void findHospitalsByAreaAndEmergency(Scanner sc, int emergencyLevel) {
       System.out.print("Enter area name to get hospitals: ");
       String areaName = sc.nextLine().trim().toLowerCase();
       ArrayList<Hospital> hospitalsInArea = areaMap.getOrDefault(areaName, new ArrayList<>());
       if (hospitalsInArea.isEmpty()) {
           System.out.println("No hospitals found in the area: " + areaName);
       } else {
           System.out.println("Hospitals in " + areaName + " for emergency level " + emergencyLevel + ":");
           for (Hospital hospital : hospitalsInArea) {
               System.out.println(hospital);
           }
       }
   }*/


  // Make a new appointment
   public static void makeAppointment(Scanner sc) {
   	System.out.println("__________________________________________");
       System.out.print("Enter patient's name: ");
       String patientName = sc.nextLine();
       System.out.print("Enter hospital name: ");
       String hospitalName = sc.nextLine();
       System.out.print("Enter appointment date (YYYY-MM-DD): ");
       String appointmentDate = sc.nextLine();
       System.out.print("Enter age range of the patient (e.g., infant, child, adult, elderly): ");
       String ageRange = sc.nextLine();
       System.out.println("__________________________________________");

       appointments.add(new Appointment(patientName, hospitalName, appointmentDate, ageRange));
       System.out.println("Appointment created successfully!");

   }

   // View all previous appointments
   public static void viewAppointments() {
       if (appointments.isEmpty()) {
           System.out.println("No previous appointments found.");
       } else {
           System.out.println("Previous Appointments:");
           for (Appointment appointment : appointments) {
               System.out.println(appointment);
           }
       }
   }



   // Update an existing appointment
   public static void updateAppointment(Scanner sc) {
       System.out.print("Enter patient's name to update appointment: ");
       String patientName = sc.nextLine();
       Appointment existingAppointment = null;
       for (Appointment appointment : appointments) {

           if (appointment.patientName.equalsIgnoreCase(patientName)) {
               existingAppointment = appointment;
               break;
           }
       }


       if (existingAppointment != null) {
           System.out.println("What would you like to update?");
           System.out.println("1. Appointment date");
           System.out.println("2. Hospital name");
           System.out.println("3. Age range");
          
           System.out.print("Enter your choice: ");
           int updateChoice = sc.nextInt();
           sc.nextLine(); // Consume newline

           switch (updateChoice) {
           		case 1:

                   System.out.print("Enter new appointment date (YYYY-MM-DD): ");
                   String newDate = sc.nextLine();
                   existingAppointment.appointmentDate = newDate;
                   System.out.println("Appointment date updated successfully!");
                   break;

               case 2:

                   System.out.print("Enter new hospital name: ");
                   String newHospitalName = sc.nextLine();
                   existingAppointment.hospitalName = newHospitalName;
                   System.out.println("Hospital name updated successfully!");
                   break;

               case 3:

                   System.out.print("Enter new age range (e.g., infant, child, adult, elderly): ");
                   String newAgeRange = sc.nextLine();
                   existingAppointment.ageRange = newAgeRange;
                   System.out.println("Age range updated successfully!");
                   break;

               default:

                   System.out.println("Invalid choice! No updates made.");
           }
       } else {
           System.out.println("No appointment found for the patient: " + patientName);

       }
   }
}

