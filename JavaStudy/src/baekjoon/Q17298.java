// 오큰수 (백준 17298번)

/* [ 알고리즘 ]
 * 일단 입력된 값을 스택에 모두 저장
 * 3 5 2 7
 * 제일 오른쪽에 있는 수부터 ← 이 방향으로 탐색
 * 제일 우측에 있는건 무조건 -1을 하고 비교 대상의 초기값으로 잡아줌
 * 
 * 2의 경우 7보다 작으니깐 출력
 * 중간 스택에 다시 저장 [7, 2]
 * 정답 스택 [-1, 7]
 * 
 * 5의 경우 중간스택에서 하나를 pop 해서 비교 2보다 크니깐 2는 삭제, 그다음 값 7로 비교
 * -> 7이 더 크니깐 냅두고 5를 중간 스택에 add
 * 중간스택 [7, 5]
 * 정답 스택[-1, 7, 7]
 * 
 * 3의 경우 중간스택에서 하나를 pop해서 비교 3보다 5가 크니깐 5를 그대로 냅두고 3도 add
 * 중간 스택 [7, 5, 3]
 * 정답 스택 [-1, 7, 7, 5]
 * 
 * 인덱스가 끝났으니깐 끝내줌
 * 해당 값을 거꾸로 출력
 */

// ver1 : 실패 -> 시간초과
/*
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
*/

