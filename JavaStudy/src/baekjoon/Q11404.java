// 플로이드 (백준 11404번)

// 플로이드 와샬 이용
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11404 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 도시(정점)의 개수
		int m = Integer.parseInt(br.readLine()); // 버스(간선)의 개수
		StringTokenizer st = new StringTokenizer("");
		int[][] list = new int[n][n];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==j) list[i][j] = 0;
				else {
					list[i][j] = 10000000;
					list[j][i] = 10000000;
				}
			}
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(list[from-1][to-1] > weight) list[from-1][to-1] = weight; // 노선이 하나가 아닐 경우 더 작은 값을 weight로 설정
		}
		
		floyd_warshall(list, n);
		System.out.print(sb);
	}
	public static void floyd_warshall(int[][] list, int V) {
		for(int i=0; i<V; i++) { // i : 경유지 설정
			for(int j=0; j<V; j++) { // j: 시작정점 , k : 도착정점
				for(int k=0; k<V; k++) {
					list[j][k] = Math.min(list[j][k], list[j][i] + list[i][k]);
				}
			}
		}
		
		for(int i=0; i<V; i++) {
			for(int j=0; j<V; j++) {
				if(list[i][j]==10000000) sb.append(0).append(" ");
				else sb.append(list[i][j]).append(" ");
				//System.out.print(list[i][j] +" ");
			}
			sb.append("\n");
			//System.out.print("\n");
		}
	}
}
