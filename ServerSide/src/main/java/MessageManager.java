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
	public synchronized  void announceApproach(String BusNumber,String StationName, int id) {
		
		ArrayList<String>station_list=getStations(BusNumber);
		 boolean flag=false;
		 int time=0;
		for(int i=0;i<station_list.size();i++) { 
			
			boolean anotherFlag=false;
			if( station_list.get(i).equals(StationName)) 
			{
				flag=true;
				anotherFlag=true;
			}
			if(flag) {
				if( anotherFlag && i!=0)
					{
						map.get(station_list.get(i-1)).sendEvent(new Message(BusNumber,time-100,id,true).Serilize());
						anotherFlag=false;
					}
				//if( station_list.get(i).equals(StationName)) {
					map.get(station_list.get(i)).sendEvent(new Message(BusNumber,time,id,false).Serilize());
			//	}else {
			//		map.get(station_list.get(i)).sendEvent(new Message(BusNumber,time,id,false).Serilize());
			//	}
				
				time+=100;
				

			}
			
				

		 } 
		
	}
	public synchronized void sendFinishDriving(String busNumber, int id) {
		ArrayList<String>station_list=getStations(busNumber);
		int size=station_list.size();
		map.get(station_list.get(size-1)).sendEvent(new Message(busNumber,-100,id,true).Serilize());
		
	}
	
}
