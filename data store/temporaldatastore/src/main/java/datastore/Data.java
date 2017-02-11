package datastore;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import commandbase.CommandIf;

public class Data {
	private  final Map<Integer , TreeMap<Long, String >> cache = new HashMap <Integer, TreeMap<Long, String>> ();
//	Date d  = new Date();

	public Map<Integer , TreeMap<Long, String >> getCache (){
		return cache ;
	}
	
	public void insertDataInCache( int id , Long timeStamp , String observation ){
		TreeMap<Long, String> timeStampMap = new TreeMap<Long, String>();
		timeStampMap.put(timeStamp, observation);
		TreeMap<Long, String>  existingMap = cache.put(id, timeStampMap);
		if(existingMap != null ){
			existingMap.put(timeStamp, observation);
		}
	}
	
	public String createData (int id , Long timeStamp, String observation ){
		String result = "";
			if (cache.get(id) == null ){
				insertDataInCache(id , timeStamp, observation);
				result =  "OK " + observation ;
			} else {
				result = "ERR A history already exists for identifier '" + id + "'" ;
			}
			return result ;
	}
	
	public String updateData(int id , Long timeStamp, String observation ){
		String result = "" ;
		TreeMap<Long, String> timeStampMap = cache.get(id) ; 
			if ( timeStampMap != null ){
				String existingObservation = timeStampMap.put(timeStamp, observation);
				if(existingObservation == null ){
					Map.Entry<Long, String> value = timeStampMap.floorEntry(timeStamp -1) ;
					if (value != null ){
						result = "OK " + value.getValue();
					}
				} else {
					result = "OK " + existingObservation;
				}
				
			}
			return result;
	}
	
	public String deleteData(int id , Long timeStamp ){
		TreeMap <Long, String > timeStampMap = cache.get(id);
		String result = "" ; 
		if (timeStampMap != null  ){
			if (timeStamp == CommandIf.TIMESTAMP_INVALID_PARAM ){
				result = timeStampMap.lastEntry().getValue();
				timeStampMap.clear();
				cache.remove(id);
				if (result == null ) {
					throw new RuntimeException("No Observation is available for this timestamp.");
				}
			} else {
				timeStampMap.tailMap(timeStamp).clear();
				result = timeStampMap.lastEntry().getValue();
			}
		}
		return "OK " + result ;
	}
	
	public String getData(int id , Long timeStamp){
		TreeMap <Long, String > timeStampMap = cache.get(id);
		String result = "" ;
		if (timeStampMap != null ){
			result  = timeStampMap.floorEntry(timeStamp).getValue() ;
			if (result == null ){
				throw new RuntimeException(" ERR ! No Observation is available for this timestamp.");
			} 
		}
		return "OK " + result ;
	}
	
	
	public String getLatest(int id ){
		TreeMap <Long, String > t = cache.get(id);
		Map.Entry<Long, String> entry ;
		String result = "";
		if (t != null ){
			entry  = t.lastEntry();
			if (entry == null ){
				throw new RuntimeException(" ERR ! this Error should not be thrown in all feasible scenarios.");
			} 
			result = "OK " + entry.getKey() + " " + entry.getValue() ;
		} else {
			result = "ERR No history exists for identifier '" + id + "'";
		}
		return result ;
	}
}
