// 거짓말 (백준 1043번)

/* [ 알고리즘 ]
 * 
 * 1. 진실을 알고 있는 사람들을 배열에 저장
 * 2. 각 동일한 파티에 있는 사람들을 기준으로 그래프 만들어줌 (양방향 그래프) & 같은 파티에 있는 사람들을 따로 저장
 * 3. 1번을 기준으로 2번에 대해 DFS 를 돌려 진실을 아는 사람들을 모두 체크
 * 4. 진실을 아는 사람이 party에 한명이라도 있으면 그 파티는 진실을 얘기할 수 없는 파
 * 5. 4번에 해당하지 않는 경우만 count해줌
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1043 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람의 수
		int M = Integer.parseInt(st.nextToken()); // 파티의 수
		st = new StringTokenizer(br.readLine());
		int count = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		boolean[] trueKnow = new boolean[N+1];

		for(int i=0; i<count; i++) {
			int x = Integer.parseInt(st.nextToken());
			trueKnow[x] = true;
		}
		
		int[][] party = new int[M][1]; // party에 참여한 사람 중 한명만 저장
		int[][]	graph = new int[N+1][N+1]; // 같은 파티에 참가한 사람은 1, 아닌 경우는 0
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int[] list = new int[number];
			
			for(int j=0; j<number; j++) {
				int person = Integer.parseInt(st.nextToken());
				list[j] = person;
				if(j==0) {
					party[i][0] = person;
				}
			}
			
			for(int x=0; x<list.length; x++) {
				int person1 = list[x];
				
				for(int y=0; y<list.length; y++) {
					int person2 = list[y];
					
					if(person1!=person2) { // 양방향 그래프 (같은 파티인 사람들은 모두 연결)
						graph[person1][person2] = 1;
						graph[person2][person1] = 1;
					}
				}
			}
		}
		
		// 진실을 아는 사람을 기준으로 DFS 돌림
		for(int i=1; i<trueKnow.length; i++) {
			if(trueKnow[i]) {
				trueKnow = DFS(graph, trueKnow, N, M, i);
			}
		}
		
		int answer = 0;
		for(int i=0; i<party.length; i++) {
			int person = party[i][0];
			
			if(!trueKnow[person]) answer = answer+1;
		}

		System.out.println(answer);
	}
	
	
	public static boolean[] DFS(int[][] graph, boolean[] trueKnow, int N, int M, int V) {
		Stack<Integer> stack = new Stack<>();
		boolean check;
		stack.push(V);
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			check = false;
			
			for(int i=1; i<graph[0].length; i++) {
				if(graph[x][i] == 1 && !trueKnow[i]) {
					trueKnow[i] = true;
					stack.push(i);
					check = true;
					break;
				}
			}
			
			if(!check) stack.pop();
		}
		
		return trueKnow;
	}
}
