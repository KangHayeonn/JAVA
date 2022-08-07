// 플로이드 (백준 골드4)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q11404_v2 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] graph = new int[n+1][n+1];
		int INF = 100 * 100000;
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(i==j) graph[i][j] = 0;
				else graph[i][j] = INF;
			}
		}
		
		int a, b, c;
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			graph[a][b] = Math.min(graph[a][b], c);
		}
		
		for(int k=1; k<n+1; k++) {
			for(int i=1; i<n+1; i++) {
				for(int j=1; j<n+1; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(graph[i][j] == INF) System.out.print(0 + " ");
				else System.out.print(graph[i][j]+" ");
				
			}
			System.out.println();
		}
	}
}
