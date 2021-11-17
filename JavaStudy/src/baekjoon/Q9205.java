// 맥주 마시면서 걸어가기

/* [ 알고리즘 ]
 * 
 * 1. 맨해튼거리를 확인해준 후 각 좌표별로 맨해튼거리가 성립하는지 배열을 만들어줌 (양방향)
 * 2. BFS를 돌려 시작지점(상근이집)에서 페스티벌 좌표까지 연결되는지 찾기
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q9205 {
	static StringBuilder sb = new StringBuilder();
	
	public static class type {
		private int idx;
		private int dx;
		private int dy;
		
		public type(int idx, int dx, int dy) {
			this.idx = idx;
			this.dx = dx;
			this.dy = dy;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine()); // 편의점 개수
			type[] arr = new type[n+2];
			
			for(int j=0; j<n+2; j++) {
				StringTokenizer st =new StringTokenizer(br.readLine());
				arr[j] = new type(j, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			boolean[][] street = new boolean[n+2][n+2];
			if(arr[0].dx == arr[n+1].dx && arr[0].dy == arr[n+1].dy) {
				sb.append("happy").append("\n");
				continue;
			}
			else {
				Manhattan(arr, street);		
				BFS(street, arr[0].idx, arr[arr.length-1].idx, n+2);
			}
		}
		
		System.out.print(sb);
	}
	public static void Manhattan(type[] arr, boolean[][] street) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length; j++) {
				if(i==j) continue;
				else {
					int distance = Math.abs(arr[i].dx-arr[j].dx)+Math.abs(arr[i].dy-arr[j].dy);
					if(distance <= 1000) {
						street[arr[i].idx][arr[j].idx] = true;
						street[arr[j].idx][arr[i].idx] = true;
					}
					else continue;
				}
			}
		}
	}
	public static void BFS(boolean[][] street, int start, int end, int N) {
		boolean isVisited[] = new boolean[N];
		Queue<Integer> queue = new LinkedList<Integer>();
		isVisited[start] = true;
		queue.add(start);
		
		int x=0;
		
		while(!queue.isEmpty()) {
			x = queue.poll();
			
			if(x==end) {  // 이 부분 없으면 틀렸습니다. 뜹니다 ㅠㅠ
				sb.append("happy").append("\n");
				return;
			}
			
			for(int i=0; i<N; i++) {
				if(street[x][i] == true && !isVisited[i]) {
					queue.add(i);
					isVisited[i] = true;
				}
			}
		}
		
		if(x==end) sb.append("happy").append("\n");
		else sb.append("sad").append("\n");
	}
}

