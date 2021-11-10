// 결혼식 (백준 5567번)

/* [ 알고리즘 ]
 * 
 * 1. BFS를 이용해서 품
 * 2. 나 -> 친구 -> 친구의 친구 ( 딱 LEVEL 2까지만 돌게 설정 )
 * 
 */

// 배열로만 성공 -> 왜 LinkedList는 똑같이 했는데 안될까...
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q5567 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c_number = Integer.parseInt(br.readLine());
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st;
		boolean[] isVisited = new boolean[c_number+1];
		
		int[][] arr = new int[c_number+1][c_number+1];
		
		for(int i=0; i<count; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향
			arr[from][to] = 1;
			arr[to][from] = 1; // 단방향일 경우 이부분 삭제
		}
		
		BFS_Array(arr, isVisited, c_number, 1);			
	}
	
	// BFS 구현 : 인접행렬 & 큐
	public static void BFS_Array(int[][] arr, boolean[] isVisited, int N, int V) {
		isVisited[V] = true;
		int count =0; // 초대 동기 수
		
		for(int i=1; i<N+1; i++) {
			if(arr[V][i]==1) {
				if(!isVisited[i]) {
					count++;
					isVisited[i] = true;
				}
				for(int j=1; j<N+1; j++) {
					if(arr[i][j]==1) {
						if(!isVisited[j]) {
							count++;
							isVisited[j] = true;
						}
					}
				}
			}
		}
		System.out.print(count);
	}
}
