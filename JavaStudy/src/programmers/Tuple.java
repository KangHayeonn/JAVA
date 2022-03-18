// Ʃ�� (���α׷��ӽ� LEVEL2 - Kakao)

/* [ �˰��� ]
 * 
 * 1. ���ڿ��� �Ǿ� �ǵ� {}�� ������ �� , �� ��������  ������.
 * 2. �ѹ��� 1���� �ݺ��ϸ� �׶��� �迭�� �迭�� ���̸� �켱���� ť�� �������ش�(�迭�� ���� �������� ��������).
 * 3. �켱����ť���� �ϳ��� �̾��ָ� �迭�� �����Ѵ�. (������ ���� ���� �ߺ����� �ʴ��� üũ�ϸ�)
 * 4. �켱����ť ����� 0�� �ɰ�� 3������ �����Ų �迭�� �������� ���
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
			return this.arrLength - t.arrLength; // arrLength�� �������� ��������
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
				
				// �����Ѱ� ���� ���� �迭�� ������
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
		
		// Map.Entry ����Ʈ �ۼ�
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