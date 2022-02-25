import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MessageHandler {

	private Map<String, HashMap<Integer,Integer>> arrivalTimes=new HashMap<String, HashMap<Integer,Integer>>();
	String StationName;
	

	public String getStationName() {
		return StationName;
	}

	public void setStationName(String stationName) {
		StationName = stationName;
	}

	public void add_message(Message message) {
		if(!message.isExitStation) {
			if(arrivalTimes.containsKey(message.BusLine)) {
				arrivalTimes.get(message.BusLine).put(message.id, message.arrivalTime);
			}
			else {
				HashMap<Integer,Integer> m=new HashMap<Integer,Integer>();
				m.put(message.id, message.arrivalTime);
				arrivalTimes.put(message.BusLine, m);
			}
		}
		else {
			arrivalTimes.get(message.BusLine).remove(message.id);
			if(arrivalTimes.get(message.BusLine).isEmpty())
				arrivalTimes.remove(message.BusLine);
			
		}
		
					
	}
	
	public String createScreenMessage() {
		
	String SreenMessage="Station number: "+this.StationName+"\n";
	
	for (String busName:arrivalTimes.keySet()) {
		SreenMessage+=("bus number "+busName+" comming in           ");
		for (Integer id:arrivalTimes.get(busName).keySet()) {
			SreenMessage+=(arrivalTimes.get(busName).get(id)+"  ");
		}
		SreenMessage+="\n";
		
	}
	return SreenMessage;
				
	}

	
	
	
	
	
	
}
