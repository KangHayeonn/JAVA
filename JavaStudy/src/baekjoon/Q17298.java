// 오큰수 (백준 17298번)

/* 알고리즘
 * stack  : 9 5 4 8
 * ans(1) : 0 1 1 0 -> 8일때
 * stack  : 9 5 4 
 * ans(2) : 0 1 1 0 -> 4일때
 * stack  : 9 5 
 * ans(3) : 0 1 1 0 -> 5일때
 * stack  : 9 
 * ans(4) : 0 1 1 0 -> 9일때
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q17298 {
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
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<type> stack = new Stack<>(); 
		int[] arr= new int[count];
		
		for(int i=0; i<count; i++) {
			int num = Integer.parseInt(st.nextToken()); 
			stack.add(new type(i, num));
			arr[i] = num;
		}
		
		int[] answer= new int[count];
		//Arrays.fill(answer, -1);
	
		while(!stack.isEmpty()) {
			int val = stack.peek().value;
			int pos = stack.peek().position;
			
			for(int i=0; i< pos; i++) {
				if(val > arr[i]) answer[i] = pos;
			}
			
			stack.pop();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<count; i++) {
			if(answer[i]==0) sb.append(-1).append(" ");
			else sb.append(arr[answer[i]]).append(" ");
		}
		
		System.out.print(sb);
	}
}
