// 여행경로 (프로그래머스 LEVEL3)

/* [ 알고리즘 ]
 * 
 * 1. 방향성이 있는 그래프를 만듦 (연결리스트를 이용)
 * 2. DFS 사용해서 그래프를 돌기!
 * 
 */

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TravelRoute {
	static int j=1;
	static String[] answer;
	
	public static class type implements Comparable<type> {
		int x;
		String airport;
		public type(int x, String airport) {
			this.x = x;
			this.airport = airport;
		}
		@Override
		public int compareTo(type t) { // 이 부분을 이용해서 풀라면 어떻게 해야하나요..? (해결)
			return this.airport.compareTo(t.airport);
		}
	}
	
	public static void main(String args[]){
		//String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
		System.out.print(Arrays.toString(solution(tickets)));	
	}
	
	public static String[] solution(String[][] tickets) {
		int N = tickets.length;
		answer = new String[N+1];
		
		HashMap<String, ArrayList<type>> map = new HashMap<>();
		
		for(int i=0; i<tickets.length; i++) {
			String from = tickets[i][0];
			String to = tickets[i][1];
			
			ArrayList<type> value = map.containsKey(from) ? map.get(from) : new ArrayList<type>();
			value.add(new type(i, to));
			map.put(from, value);
		}
		
		for(String i: map.keySet()) { // 오름차순 정렬
			Collections.sort(map.get(i));
		}

		int first=0;
		int idx=0;
		String str="";
		
		for(Map.Entry<String, ArrayList<type>> element : map.entrySet()) {
			String key = element.getKey();
			ArrayList<type> value = element.getValue();
			
			System.out.print(key + " : ");
			for(int i=0; i<value.size(); i++) {
				if(first==0) {
					idx = value.get(i).x;
					str = value.get(i).airport;
					first++;
				}
				System.out.print(value.get(i).x + " " + value.get(i).airport +" ");
			}
			
			System.out.println("");
		}
		
		//System.out.println(map.keySet().toArray()[0]); // 첫번째 키 값 구하는 법
		//System.out.println(map.values().toArray()[0]); // 첫번째 값 구하는 법

		answer[0] = "ICN";
		boolean[] isVisited = new boolean[N];
		DFS(map, isVisited, N, idx, str);
		
		return answer;
	}
	
	public static void DFS(HashMap<String, ArrayList<type>> map, boolean[] isVisited, int N, int idx, String airport) {
		if(N==0) {
			return;
		}
		answer[j++] = airport;
		isVisited[idx] = true; // 1 인덱스가 가리키고 있는 ATL에 가서 돌기
		ArrayList<type> arr = map.get(airport);
		
		for(int i=0; i<arr.size(); i++) {
			int temp = arr.get(i).x;
			if(!isVisited[temp]) {
				//if(arr.get(i).airport == null) return;
				
				DFS(map, isVisited, N-1, temp, arr.get(i).airport);
			}
		}
	}	
}
