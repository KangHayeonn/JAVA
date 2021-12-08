// 회장뽑기 (백준 2660번)

/* [ 알고리즘 ]
 * 
 * 1. 각 정점 별로 BFS(너비우선탐색)을 돌고 다른 정점과의 최단거리를 갱신해줌
 * 2. 시작점 1일 때, [0,1,2,2,3] | 시작점 2일 때, [1,0,1,1,2] 이런식으로 5까지
 * 3. 각 정점별로 다른 정점과 연결된 최단거리 배열에서 최댓값을 구해줘 각각의 시작점에 맞는 인덱스에 넣어줌 ex. [3,2,2,2,3]
 * 4. 3번에서 최솟값을 구해 그것과 같은 인덱스를 새로운 배열(answer)에 저장해주고 count++해줌
 * 5. 최솟값과 count 그리고 answer 배열에 저장되어 있는 값들을 오름차순으로 출력
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2660 {
	public static class type {
		private int node; 
		private int depth; 

		public type (int node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][N+1];
		StringTokenizer st = new StringTokenizer("");

		while(true) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if(from == -1 && to == -1) break;
			
			arr[from][to] = 1;
			arr[to][from] = 1;
		}
		
		Boolean[] isVisited =  new Boolean[N+1];
		int[] shortPath = new int[N+1];
		int[] maxList = new int[N];
		int min = Integer.MAX_VALUE; // 회장 후보의 점수
		
		for(int i=1; i<N+1; i++) {
			Arrays.fill(isVisited, false);
		    shortPath = BFS(arr, isVisited, shortPath, i);
		    int maxPath = Arrays.stream(shortPath).max().getAsInt();
		    if(maxPath <= min) min = maxPath;
		    //if(maxPath == min) count++;
		    maxList[i-1] = maxPath;
		}
		
		int count =0; // 회장 후보의 수
		ArrayList<Integer> answer = new ArrayList<>();
		for(int i=0; i<maxList.length; i++) {
			if(maxList[i]==min) {
				count++;
				answer.add(i+1);
			}
		}
		
		System.out.println(min + " " + count);
		for(Integer i: answer) {
			System.out.print(i + " ");
		}
	}
	public static int[] BFS(int[][] arr, Boolean[] isVisited, int[] answer, int V) {
		Queue<type> queue = new LinkedList<type>();
		isVisited[V] = true;
		queue.add(new type(V, 0));
		answer[V] = 0;
		
		while(!queue.isEmpty()) {
			type q = queue.poll();
			int x = q.node;
			int depth = q.depth;
			
			for(int i=1; i<arr.length; i++) {
				if(arr[x][i]==1 && !isVisited[i]) {
					queue.add(new type(i, depth+1));
					isVisited[i] = true;
					answer[i] = depth+1;
				}
			}
		}
		return answer;
	}
}
