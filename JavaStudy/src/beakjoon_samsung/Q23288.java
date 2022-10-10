// 주사위 굴리기 2 (백준 골드3)
package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q23288 {
	static int N, M, K;
	static int top=1, down=6, left=4, right=3, front=5, back=2;
	static int[][] map;
	static int box_r=0, box_c=0; // 주사위 좌표
	static int[] d = {0, 1, 2, 3}; // 이동방향 (0: 동쪽, 2: 서쪽, 1: 남쪽, 3: 북쪽)
	static int nd=0; // 현재 이동방향
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0}; // 동 서 남 북
	public static class Type {
		int r, c;
		public Type(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score;
		int result = 0;
		while(K-- > 0) {
			// 주사위가 이동 방향으로 한 칸 굴러감
			roll();
			
			// 도착한 칸에 대한 점수 획득
			score = map[box_r][box_c];
			
			// 주사위의 아랫면과 칸 (x,y)의 정수를 비교해 이동 방향을 결정
			compare();
			
			int C = getScore(score); // bfs
			
			result += score * C;
		}
		
		System.out.println(result);
	}
	public static void roll() {
		int tmpVal;
		int nr, nc;
		
		nr = box_r + dr[nd];
		nc = box_c + dc[nd];
		
		if(nr < 0 || nc < 0 || nr >= N || nc >= M) {
			nd = (nd + 2) % 4; // 방향을 반대로 한 다음 굴러간다.
			roll();
			return;
		}
		
		box_r = nr;
		box_c = nc;
		
		if(nd == 0) { // 동쪽	
			tmpVal = right;
			right = top;
			top = left;
			left = down;
			down = tmpVal;
		} else if(nd == 1) { // 남쪽
			tmpVal = down;
			down = front;
			front = top;
			top = back;
			back = tmpVal;
		} else if(nd == 2) { // 서쪽
			tmpVal = left;
			left = top;
			top = right;
			right = down;
			down = tmpVal;
		} else { // 북쪽
			tmpVal = back;
			back = top;
			top = front;
			front = down;
			down = tmpVal;
		}
	}
	public static void compare() {
		int A = down; // 주사위의 아랫면
		int B = map[box_r][box_c]; // 칸
		
		if(A > B) {
			// 90도 시계방향 회전
			nd = (nd + 1) % 4;
		} else if(A < B) {
			// 90도 반시계방향 회전
			nd = (nd -1 + 4) % 4;
		} else return;
	}
	public static int getScore(int score) {
		Queue<Type> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][M];
		q.offer(new Type(box_r, box_c));
		isVisited[box_r][box_c] = true;
		
		int totalC = 0;
		int nr, nc;
		while(!q.isEmpty()) {
			Type t = q.poll();
			
			totalC += 1;
	
			for(int i=0; i<4; i++) {
				nr = t.r + dr[i];
				nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				if(!isVisited[nr][nc] && map[nr][nc] == score) {
					q.offer(new Type(nr, nc));
					isVisited[nr][nc] = true;	
				}
			}
		}
		
		return totalC;
	}
}
