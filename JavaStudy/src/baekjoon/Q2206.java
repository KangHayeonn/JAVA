// 벽 부수고 이동하기

/* [ 알고리즘 ]
 * 
 * 1. 먼저 전체 맵을 받아 이차원 배열에 저장
 * 2. 처음 (0,0) 칸부터 시작해서 상하좌우로 이동 (현재 위치에서 4가지 방향으로 가는 반복문) - 현재 위치 & distance & 벽을 부순 횟수를 추가.
 * 3. 만약 맵을 벗어나는 경우 continue
 * 4. 만약 이미 드릴을 사용했는데 또 벽일 경우 continue
 * 5. 3번과 4번이 모두 아닐 경우 두가지 케이스로 나눠서 이동
 * 6. (1) 이동한 좌표가 0일 경우 : 이동거리 증가, 드릴횟수 그대로
 * 7. (2) 이동한 좌표가 1일 경우 : 이동거리 증가, 드릴횟수 증가
 * 8. 만약 이동한 좌표가 (N-1,M-1) 이 될 경우 그때의 이동거리 return
 * 9. 모든 경우가 끝났을 경우 해당 이동거리를 출력, 이동거리가 0이면 -1을 출력 
 * 10. N과 M이 모두 1일 경우는 바로 0을 출력
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2206 {
	static int[] dx = {0, 0, -1, 1}; // 상하좌우 
	static int[] dy = {1, -1, 0, 0};
	
	public static class type {
		private int x;
		private int y;
		private int distance;
		private int drill;
		
		public type(int x, int y, int distance, int drill) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.drill = drill;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		//int[] check = new int[N*M];
		int[][] check = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
				//check[M*(i%M) + j] = Integer.MAX_VALUE;
				check[i][j] = Integer.MAX_VALUE;
			}
		}
		System.out.println(Arrays.toString(check));
		BFS(map, check, N, M);
	}
	
	public static void BFS(int[][] map, int[][] check, int N, int M) {
		Queue<type> move = new LinkedList<>(); // 이동
		move.add(new type(0, 0, 1, 0));
		check[0][0] = 0;
		
		while(!move.isEmpty()) {
			type temp = move.poll();
			
			if(temp.x == M-1 && temp.y == N-1) {
				System.out.print(temp.distance);
				System.out.println("들어오나?");
				return;
			}
			
			for(int i=0; i<4; i++) {
				int now_x = temp.x + dx[i];
				int now_y = temp.y + dy[i];
				
				if(now_x < 0 || now_y < 0 || now_x > M-1 || now_y > N-1 ) continue;
				if(check[now_y][now_x] <= temp.drill) continue;
				//if(check[now_x*(now_x%M) + now_y] <= temp.drill) continue; // 이미 한번은 뚫은 곳이기 때문에 pass
				System.out.println(Arrays.toString(check));
				if(map[now_y][now_x] == 0) {
					move.add(new type(now_x, now_y, temp.distance+1, temp.drill));
					//check[now_x*(now_x%M) + now_y] = temp.drill;
					check[now_y][now_x] = temp.drill;
				}
				else {
					if(temp.drill == 0) {
						move.add(new type(now_x, now_y, temp.distance+1, temp.drill+1));
						//check[now_x*(now_x%M) + now_y] = temp.drill+1;
						check[now_y][now_x] = temp.drill+1;
					}
				}
			}
		}
		if(move.isEmpty()) {
			System.out.print(-1);
			return;
		}
	}
}
