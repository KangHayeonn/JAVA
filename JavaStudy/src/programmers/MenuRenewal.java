// 메뉴 리뉴얼 (프로그래머스 LEVEL2 - Kakao)

/* [ 알고리즘 ]
 * 
 * 1. 주어진 길이만큼 조합의 경우의 수를 ArrayList에 저장 (정렬 먼저 하기)
 * 2. 1번의 ArrayList를 돌리며 각 문자열을 key로 두고 동일한 문자열이 나올 경우 count를 늘려줌 (map)
 * 3. value가 가장 큰 값을 찾고 그거랑 동일한 value인 경우의 key를 찾아서 answer배열에 저장
 * 4. 다음 길이도 1-3번 과정을 거침
 * 
 */

package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MenuRenewal {
	static ArrayList<String> arr;
	static ArrayList<String> resultArr;
	
	public static void main(String args[]) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		System.out.println(Arrays.toString(solution(orders, course)));
	}
	public static String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		resultArr = new ArrayList<>();
		
		for(int i=0; i<course.length; i++) {
			arr = new ArrayList<>();
			for(int j=0; j<orders.length; j++) {
				int len = orders[j].length();
				boolean[] visited = new boolean[len];
				
				// 문자열 정렬
				String str = "";
				char[] charArr = orders[j].toCharArray();
				Arrays.sort(charArr);
				str = new StringBuilder(new String(charArr)).toString();
				
				combination(str, visited, 0, len, course[i]);
				/* 순열
				char[] output = new char[course[i]];
				permutation(str, output, visited, 0, len, course[i]);
				*/
			}
			countCheck();
		}
		
		answer = resultArr.toArray(new String[0]);
		Arrays.sort(answer);
		
		return answer;
	}
	
	public static void countCheck() {
		Map<String, Integer> map = new HashMap<>();
		
		for(String s : arr) {
			if(s == null) continue;
			
			if(!map.containsKey(s)) {
				map.put(s, 1);
			} else {
				map.put(s, map.get(s)+1);
			}
			System.out.println(s);
		}
		
		int max = 0;
		for(String key : map.keySet()) {
			int value = map.get(key);
			
			if(value > max) {
				max = map.get(key);
			}
		}
		
		if(max < 2) return;
		
		for(String key: map.keySet()) {
			int value = map.get(key);
			
			if(max == value) {
				resultArr.add(key);
			}
		}
	}
	
	// 순열
	public static void permutation(String str, char[] output, boolean[] visited, int depth, int n, int r) {
		if(depth == r) {
			String s = "";
			for(int j=0; j<output.length; j++) {
				s += output[j];
			}
			arr.add(s);
			System.out.println(s+" ");
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = str.charAt(i);
				permutation(str, output, visited, depth+1, n, r);
				// output[depth] = "";
				visited[i] = false;
			}
		}
	}
	
	// 조합
	public static void combination(String str, boolean[] visited, int start, int n, int r) {
		if(r==0) {
			String s = "";
			for (int i = 0; i < n; i++) {
	            if (visited[i]) {
	                s += str.charAt(i);
	            	// System.out.print(str.charAt(i));
	            }
	        }
			arr.add(s);
			return;
		}
		
		for(int i=start; i<n; i++) {
			visited[i] = true;
			combination(str, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}
}
