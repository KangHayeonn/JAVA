// 다리 만들기 (백준 2146번)

/* [ 알고리즘 ]
 * 
 * 1. 같은 섬 별로 번호를 다르게 지정해주어 섬을 분리 (BFS)
 * 2. 섬을 분리한 뒤 기존 섬에서 다른 섬을 만날 때까지 거리를 저장해주면서 다른 섬을 만날 경우 최솟값을 갱신 (BFS)
 * 3. 2번을 위해 큐에 좌표랑 거리 변수를 모두 저장 
 * 
 * - BFS : 동서남북으로 이동하면서 BFS 돌려줌 (전형적인 BFS 문제)
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2146 {
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dx = {0, 0, -1, 1}; // 상 하 좌 우
	static int[] dy = {1, -1, 0, 0};
	
	public static class type {
		int x;
		int y;
		int distance;
		public type(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
	public static void main (String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 지도의 크기
		StringTokenizer st = new StringTokenizer("");
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int x = Integer.parseInt(st.nextToken());
				map[i][j] = x;
			}
		}
		
		isVisited = new boolean[N][N];
		int count = 2;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 1) {
					count = mapMake(i, j, N, count);
				}
			}
		}
		
		int answer = 20000;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				isVisited = new boolean[N][N];
				if(map[i][j] >= 2) {
					answer = BFS(i, j, N, answer);
				}
			}
		}
		
		/*
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}*/
		
		System.out.print(answer);
	}
	
	// 같은 섬끼리 분리
	public static int mapMake(int x, int y, int N, int count) {
		Queue<type> q = new LinkedList<>();
		q.add(new type(x, y, 0));
		isVisited[x][y] = true;
		map[x][y] = count;
		
		while(!q.isEmpty()) {
			type t = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				
				if( nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
				
				if(map[nx][ny] == 1 && !isVisited[nx][ny]) {
					map[nx][ny] = count;
					q.add(new type(nx, ny, 0));
				}
			}
		}
		return count+1;
	}
	
	// 섬끼리 최소 거리 구하기
	public static int BFS(int x, int y, int N, int answer) {
		Queue<type> q = new LinkedList<>();
		q.add(new type(x, y, 0));
		isVisited[x][y] = true;
		
		while(!q.isEmpty()) {
			type t= q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = t.x + dx[i];
				int ny = t.y + dy[i];
				
				if( nx < 0 || ny < 0 || nx >= N || ny >= N ) continue;
				
				// 다른 섬이거나 바다일 경우 해당 조건문에 들어감
				if(map[nx][ny] != map[x][y] && !isVisited[nx][ny]) {
					if(map[nx][ny] == 0) { // 바다일 경우
						q.add(new type(nx, ny, t.distance+1));
					} else { // 다른 섬일 경우
						answer = Math.min(answer, t.distance);
					}
					isVisited[nx][ny] = true; // 이 부분의 위치가 중요 (조건문 한곳에만 넣어줄 경우 -> OutOfMemoryError: Java heap space 이런 에러뜸)
				}
			}
		}
		
		return answer;
	}
}
