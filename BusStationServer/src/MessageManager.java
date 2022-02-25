import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MessageManager {

	
	private Map<String,Event64> map;
	private Map<String, ArrayList<String>> lines;
	
	
	public MessageManager() {
		super();
		this.map = new HashMap<String,Event64> ();
		this.lines = new HashMap<String,ArrayList<String>>();
		this.initialize();
		
	}
	private void initialize() {

		 lines.put("0", new ArrayList<String>(Arrays.asList("0", "5", "7")));
		 lines.put("1", new ArrayList<String>(Arrays.asList("1", "5", "3"))); 
		
	}
	public synchronized boolean ExistStation(String StationName) {
		return  map.containsKey(StationName);
	}
	
	public synchronized  void AddStation(String StationName,Event64 event) {
		map.put(StationName, event);
	} 
	public synchronized   ArrayList<String> getStations(String BusNumber) {
		return lines.get(BusNumber);
	} 
	public synchronized  void announceApproach(String BusNumber,String StationName) {
		
		ArrayList<String>station_list=getStations(BusNumber);
		 boolean flag=false;
		
		for(int i=0;i<station_list.size();i++) { 
			
			
			 if( station_list.get(i).equals(StationName)) {flag=true;}
			 if(flag) {
				 if( station_list.get(i).equals(StationName)) {
					 map.get(station_list.get(i)).sendEvent("כעת בתחחנה");
				 }else {
				 map.get(station_list.get(i)).sendEvent("מגיע בעוד עידן ועידנים");
				 }
			 }
			
				

		 } 
		
	}
	
}
