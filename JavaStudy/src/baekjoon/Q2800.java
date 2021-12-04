// 괄호 제거 (백준 2800번)

/* [ 알고리즘 ]
 * 
 * 1. 좌측괄호의 인덱스를 스택에 저장함
 * 2. 우측괄호가 나올 때마다 해당 스택에서 하나씩 pop해주고 그때의 값과 해당 우측괄호의 인덱스를 ArrayList에 쌍으로 저장해줌
 * 3. ArrayList에서 나올 수 있는 경우의 수를 조합으로 구해줌
 * 4. 해당 조합에 따라 문자열을 새로 만들어줌 (이때의 문자열은 Set에 저장) - 중복을 없애기 위해
 * 5. Set을 사전 순으로 출력 (정렬) || TreeSet
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Q2800 {
	static Set<String> set = new TreeSet<String>();
	
	public static class type {
		private int start;
		private int end;
		
		public type (int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		Stack<Integer> stack = new Stack<>();
		ArrayList<type> arr = new ArrayList<>();
		
		String result = "";
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='(') stack.add(i);
			if(str.charAt(i)==')') {
				int start = stack.pop();
				arr.add(new type(start, i));
			}
			if(String.valueOf(str.charAt(i)).matches("[0-9[+][-][*][/]]")) {
				result += str.charAt(i);
			}
		}
		/*
		for(int i=0; i<arr.size(); i++) {
			System.out.println(arr.get(i).start + " : " + arr.get(i).end);
		}*/
		boolean[] visited = new boolean[arr.size()];

		for(int i=1; i<arr.size(); i++) {
			combi(arr, visited, 0, arr.size(), i, str);
		}
		set.add(result);

		for(String s : set) {
			System.out.println(s);
		}
	}
	public static void combi(ArrayList<type> arr, boolean[] visited, int start, int n, int r, String str) {
		if(r==0) {
			print(arr, visited, n, str);
			return;
		}
		
		for(int i=start; i<n; i++) {
			visited[i] = true;
			combi(arr, visited, i+1, n, r-1, str);
			visited[i] = false;
		}
	}
	
	static void print(ArrayList<type> arr, boolean[] visited, int n, String str) {
		StringBuffer s = new StringBuffer(str);

        for (int i = 0; i < n; i++) {
        	s = new StringBuffer(s);
            if (!visited[i]) {
            	s.replace(arr.get(i).start, arr.get(i).start+1, "&");
            	s.replace(arr.get(i).end, arr.get(i).end+1, "&");
            }  
        }
        String newStr = s.toString();
        newStr = newStr.replace("&", "");
        set.add(newStr);
    }
}
