// 탑 (백준 2493번)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 배열이용시 -> 시간초과
// 스택사용 : 성공
public class Q2493 {
	public static class type {
		private int position;
		private int value;
		
		public type (int position, int value) {
			this.position = position;
			this.value = value;
		}
		
		public String toString() {
			//return position + " : " + value;
			return value + "";
		}
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<type> stack = new Stack<>();
		
		for(int i=0; i<count; i++) {
			int num = Integer.parseInt(st.nextToken());
			/*
			for(int j=i; j>=0; j--) {
				if(num < arr[j]) {
					sb.append(j+1).append(" ");
					break;
				}
				else if(j==0) {
					sb.append(0).append(" ");
					break;
				}
			}*/
			//System.out.println(stack.toString());

			while(!stack.empty()) {
				if(stack.peek().value >= num) {
					sb.append(stack.peek().position).append(" ");
					break;
				}
				stack.pop();
			}
			if(stack.empty()) sb.append(0).append(" ");
			type a = new type(i+1, num);
			//stack.add(new type(i, num));
			stack.add(a);
		}
		
		System.out.print(sb);
	}
}
