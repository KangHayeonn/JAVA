// �޴� ������ (���α׷��ӽ� LEVEL2 - Kakao)

/* [ �˰��� ]
 * 
 * 1. �־��� ���̸�ŭ ������ ����� ���� ArrayList�� ���� (���� ���� �ϱ�)
 * 2. 1���� ArrayList�� ������ �� ���ڿ��� key�� �ΰ� ������ ���ڿ��� ���� ��� count�� �÷��� (map)
 * 3. value�� ���� ū ���� ã�� �װŶ� ������ value�� ����� key�� ã�Ƽ� answer�迭�� ����
 * 4. ���� ���̵� 1-3�� ������ ��ħ
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
				
				// ���ڿ� ����
				String str = "";
				char[] charArr = orders[j].toCharArray();
				Arrays.sort(charArr);
				str = new StringBuilder(new String(charArr)).toString();
				
				combination(str, visited, 0, len, course[i]);
				/* ����
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
	
	// ����
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
	
	// ����
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
