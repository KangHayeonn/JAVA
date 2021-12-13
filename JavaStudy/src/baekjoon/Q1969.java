// DNA (백준 1969번)

// 1. 문자열의 각 인덱스 마다 A, C, G, T 중에 제일 많이 나온 알파벳을 찾는다.
// 2. Max 값이 나온 알파벳을 제외하고 나머지의 value 값을 더해서 해밍거리로 저장한다.
// 3. 만약 동일한 값이 나오면 알파벳순이 빠른 알파벳을 출력한다.
// 4. 그렇게 저장된 해밍거리와 알파벳 문자열을 정답으로 출력해준다.

package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class Q1969 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // DNA의 수
		int M = Integer.parseInt(st.nextToken()); // 문자열의 길이
		
		String str_arr[] = new String[N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			str_arr[i] = str;
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();

		int h_distance = 0;
		
		for(int i=0; i<M; i++) {
			int a=0, c=0, g=0, t=0;
			map.put("A", 0);
			map.put("C", 0);
			map.put("G", 0);
			map.put("T", 0);
			
			for(int j=0; j<N; j++) {
				switch(str_arr[j].charAt(i)) {
					case 'A': a++; break;
					case 'C': c++; break;
					case 'G': g++; break;
					default : t++; break;
				}
			}
			map.put("A", a); map.put("C", c); map.put("G", g); map.put("T", t);
			
			List<String> keys = new ArrayList<>(map.keySet());
			Collections.sort(keys); // key 오름차순 정렬 중요 !
			
			String maxString = "";
			int maxInt = 0;
			for(String key : keys) {
				if(maxString == "" || map.get(key) > maxInt) {
					maxString = key;
					maxInt = map.get(key);
				}
			}
			System.out.print(maxString);
			
			/*Set<Entry<String, Integer>> entrySet = map.entrySet();
			for(Entry<String, Integer> entry : entrySet) {
				if(maxEntry == null || entry.getValue()> maxEntry.getValue()) {
					maxEntry = entry;
					System.out.println(maxEntry);
				}
			}*/
			
			//System.out.print(maxEntry.getKey());
			Set<Entry<String, Integer>> entrySet = map.entrySet();
			for(Entry<String, Integer> entry : entrySet) {
				if(!entry.getKey().equals(maxString)) {
					h_distance += entry.getValue();
				}
			}
		}
		System.out.print("\n" + h_distance);
	}
}
