// ��ȣ ���� (���� 2800��)

/* [ �˰��� ]
 * 
 * 1. ������ȣ�� �ε����� ���ÿ� ������
 * 2. ������ȣ�� ���� ������ �ش� ���ÿ��� �ϳ��� pop���ְ� �׶��� ���� �ش� ������ȣ�� �ε����� ArrayList�� ������ ��������
 * 3. ArrayList���� ���� �� �ִ� ����� ���� �������� ������
 * 4. �ش� ���տ� ���� ���ڿ��� ���� ������� (�̶��� ���ڿ��� Set�� ����) - �ߺ��� ���ֱ� ����
 * 5. Set�� ���� ������ ��� (����) || TreeSet
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
		int count=0;
		int x = 0;
        for (int i = 0; i < n; i++) {
        	s = new StringBuffer(s);
            if (!visited[i]) {
            	if(arr.get(i).start <= x) {
            		s.replace(arr.get(i).start, arr.get(i).start+1, "");
                	s.replace(arr.get(i).end-(count+1), arr.get(i).end-count, "");
            	} else {
            		s.replace(arr.get(i).start-count, arr.get(i).start-count+1, "");
                	s.replace(arr.get(i).end-(count+1), arr.get(i).end-count, "");
            	}
            	x = arr.get(i).start;
            	count= count+2;
            }  
        }
        set.add(s.toString());
    }
}
