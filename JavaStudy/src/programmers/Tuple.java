// 튜플 (프로그래머스 LEVEL2 - Kakao)

/* [ 알고리즘 ]
 * 
 * 1. 문자열을 맨앞 맨뒤 {}를 삭제한 뒤 , 를 기준으로  나눈다.
 * 2. 한번더 1번을 반복하며 그때의 배열과 배열의 길이를 우선순위 큐에 저장해준다(배열의 길이 기준으로 오름차순).
 * 3. 우선순위큐에서 하나씩 뽑아주며 배열에 저장한다. (이전에 나온 값이 중복되지 않는지 체크하며)
 * 4. 우선순위큐 사이즈가 0이 될경우 3번에서 저장시킨 배열을 정답으로 출력
 * 
 */
package programmers;

//ver 1
/*
import java.util.Arrays;
import java.util.PriorityQueue;

public class Tuple {
	public static class type implements Comparable<type> {
		private String[] arr;
		private int arrLength;
		
		public type(String[] arr, int arrLength) {
			this.arr = arr;
			this.arrLength = arrLength;
		}
		
		@Override
		public int compareTo(type t) {
			return this.arrLength - t.arrLength; // arrLength를 기준으로 오름차순
		}
	}
	public static void main(String args[]) {
		String str = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		System.out.println(Arrays.toString(solution(str)));
	}
	public static int[] solution(String s) {
		PriorityQueue<type> pq = new PriorityQueue<>();
		
		s = s.substring(1, s.length()-1);
		
		String[] strArr = s.split("}");
		
		for(int i=0; i<strArr.length; i++) {
			String str = strArr[i];
			
			if(str.charAt(0)==',') {
				str = str.substring(2, str.length());
				String[] newArr = str.split(",");
				pq.add(new type(newArr, newArr.length));
			} else {
				str = str.substring(1, str.length());
				String[] newArr = str.split(",");
				pq.add(new type(newArr, newArr.length));
			}
		}
		
		int[] answer = new int[pq.size()];
		int idx = 0;
		
		while(!pq.isEmpty()) {
			type t = pq.poll();
			
			if(t.arrLength==1) {
				answer[idx] = Integer.parseInt(t.arr[0]);
				idx++;
				continue;
			}
			
			boolean check = false;
			
			for(int i=0; i<t.arrLength; i++) {
				Integer num = Integer.parseInt(t.arr[i]);
				
				for(int j=0; j< idx; j++) {
					if(answer[j]==num) {
						check = true;
						break;
					}
				}
				
				// 동일한게 기존 정답 배열에 없으면
				if(!check) {
					answer[idx] = num;
					break;
				}
				
				check = false;
			}
			idx++;
		}
		
		return answer;
	}
}
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// ver2
public class Tuple {
	public static void main(String args[]) {
		String str = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		System.out.println(Arrays.toString(solution(str)));
	}
	public static int[] solution(String s) {
		Map<Integer, Integer> map = new HashMap<>();
		
		String[] strArr = s.split("\\{|\\}|,");
		
		for(int i=0; i<strArr.length; i++) {
			
			if(strArr[i]==null || strArr[i].isEmpty()) continue;
			
			if(Character.isDigit(strArr[i].charAt(0))) {
				int num = Integer.parseInt(strArr[i]);
				
				if(map.containsKey(num)) {
					map.put(num, map.get(num)+1);
				} else {
					map.put(num, 1);
				}
			}
		}
		
		// Map.Entry 리스트 작성
		List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, (o1, o2) -> o2.getValue() - o1.getValue());
		
		int[] answer = new int[map.size()];
		int i=0;
		
		for(Map.Entry<Integer, Integer> entry : list) {
			answer[i] = entry.getKey();
			i++;
		}
		
		return answer;
	}
}