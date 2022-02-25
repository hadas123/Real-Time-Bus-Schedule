import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Message {
	
	String BusLine;
	String StationName;
	int arrivalTime;
	int id;
	boolean isExitStation;
	
	public Message(String busLine, int arrivalTime, int id,boolean isExitStation) {
		super();
		this.BusLine = busLine;
		this.arrivalTime = arrivalTime;
		this.id=id;
		this.isExitStation=isExitStation;
	}
	
	public Message() {
		super();
		
	}
	

	public String Serilize() {
		
		GsonBuilder builder = new GsonBuilder(); 
	    Gson gson = builder.create();
	    return  gson.toJson(this) ;
	      
	}
	
	public Message Deserilize(String json) {
		
		 GsonBuilder builder = new GsonBuilder(); 
	     Gson gson = builder.create(); 
	     Message m=gson.fromJson(json, Message.class); 
	     this.BusLine = m.BusLine;
		 this.arrivalTime = m.arrivalTime;
		 this.id=m.id;
		 this.isExitStation=m.isExitStation;
		 return this;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getBusLine() {
		return BusLine;
	}

	public void setBusLine(String busLine) {
		BusLine = busLine;
	}

	

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
	

}
