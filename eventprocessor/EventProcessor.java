package test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EventProcessor {

	private void updateCache(int[] events , Map<Integer, int[] > cache){
		for (int e : events){
			int[] returnValue = cache.get(e);
			if (returnValue == null ){
				cache.put(e, new int[]{1});
			} else {
				returnValue[0]++ ;
			}
		}
	}

	private int findWinnerKey(int maxValue , Map<Integer, int[] > cache){
		int winner = -1 ;
		for (Entry<Integer, int[]> entry : cache.entrySet()) {
			if (maxValue ==  entry.getValue()[0]) {
				winner = entry.getKey();
				break ;
			}
		}

		return winner ;

	}
	
	public int getWinner(int[] events){
		Map<Integer, int[] > cache = new LinkedHashMap<Integer, int[]>();
		int winner = -1 ;  //Assuming events can'be negative

		if (events == null || events.length == 0 ) {
			throw new RuntimeException("Empty Array Pased.");
		}

		updateCache(events, cache);

		List<Integer> listValues = new ArrayList<Integer>() ;
		for ( int[] a : cache.values()){
			listValues.add(a[0]);
		}

		int maxValue = Collections.max(listValues);

		winner = findWinnerKey(maxValue, cache);
		return winner ;
	}

}
