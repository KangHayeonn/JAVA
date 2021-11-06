package UteCo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class test1 {
	public static void main(String args[]) {
		int[] arr = {1,2,3,2,3,3,1,1,1,1,2,3,1,1,1}; 
		System.out.print(Arrays.toString(solution(arr)));
	}
	public static int[] solution(int[] arr) {
		int[] answer = new int[3];
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 0); map.put(2, 0); map.put(3, 0);
		
		for(int i=0; i< arr.length; i++) {
			int value = map.containsKey(arr[i]) ? map.get(arr[i]) : 0;
			map.put(arr[i], value + 1);
		}

		Comparator<Entry<Integer, Integer>> comparator = new Comparator<Entry<Integer, Integer>>() {
			@Override
			public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {
			return e1.getValue().compareTo(e2.getValue());
			}
		};
		Entry<Integer, Integer> maxEntry = Collections.max(map.entrySet(), comparator);

		//System.out.println(maxEntry.getKey() + " : " + maxEntry.getValue());
		int i = 0;
		for(Entry<Integer, Integer> entrySet : map.entrySet()) {
			answer[i] = maxEntry.getValue()-entrySet.getValue();
			i++;
		}
		
		return answer;
	}
}
