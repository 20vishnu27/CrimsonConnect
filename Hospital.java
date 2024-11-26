package DS_Mini;

class Hospital {
	   String name;
	   double lat;
	   double lon;
	   String area;

	   public Hospital(String name, double lat, double lon, String area) {
	       this.name = name;
	       this.lat = lat;
	       this.lon = lon;
	       this.area = area;
	   }

	   @Override
	   public String toString() {
	       return "Hospital{" +
	               "name='" + name + '\'' +
	               ", lat=" + lat +
	               ", lon=" + lon +
	               ", area='" + area + '\'' +
	               '}';
	   }
	}
