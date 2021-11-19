// ������ (���α׷��ӽ� LEVEL3)

/* [ �˰��� ]
 * 
 * 1. ���⼺�� �ִ� �׷����� ���� (���Ḯ��Ʈ�� �̿�)
 * 2. DFS ����ؼ� �׷����� ����!
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
		public int compareTo(type t) { // �� �κ��� �̿��ؼ� Ǯ��� ��� �ؾ��ϳ���..? (�ذ�)
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
		
		for(String i: map.keySet()) { // �������� ����
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
		
		//System.out.println(map.keySet().toArray()[0]); // ù��° Ű �� ���ϴ� ��
		//System.out.println(map.values().toArray()[0]); // ù��° �� ���ϴ� ��

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
		isVisited[idx] = true; // 1 �ε����� ����Ű�� �ִ� ATL�� ���� ����
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
