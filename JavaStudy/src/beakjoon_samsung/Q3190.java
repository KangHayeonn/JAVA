// 뱀 (백준 골드5)

package beakjoon_samsung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3190 {
	public static class Type {
		int time;
		char direction;
		public Type (int time, char direction) {
			this.time = time;
			this.direction = direction;
		}
	}
	public static class Snake{
		int r;
		int c;
		public Snake(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine()); // 2<=N<=100
		int K = Integer.parseInt(br.readLine()); // 0<=K<=100
		StringTokenizer st = new StringTokenizer("");
		int[][] map = new int[N][N];
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row-1][col-1] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		Queue<Type> q = new LinkedList<>();
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char direction = (st.nextToken()).charAt(0);
			q.add(new Type(time, direction));
		}
		
		int answer = 0; // 시간
		boolean[][] isVisited = new boolean[N][N];
		Deque<Snake> snake = new ArrayDeque<>();
		isVisited[0][0] = true;
		snake.add(new Snake(0, 0));
		
		int[] dx = {1, 0, -1, 0}; // 동 남 서 북
		int[] dy = {0, 1, 0, -1};
		int idx = 0;
		
		while(true) {
			Snake s = snake.peekLast();
			
			if(!q.isEmpty()) {
				Type t = q.peek();
				if(t.time == answer) {
					idx = direction(idx, t.direction);
					q.poll();
				}
			}
			
			answer++;

			int nr = s.r + dy[idx];
			int nc = s.c + dx[idx];
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) break;
			if(isVisited[nr][nc]) break;
			
			if(map[nr][nc]==1) { // 사과일 경우
				snake.offerLast(new Snake(nr, nc));
				isVisited[nr][nc] = true;
				map[nr][nc] = 0; // 사과 없애기
			} else {
				Snake s2 = snake.pollFirst();
				snake.offerLast(new Snake(nr, nc));
				isVisited[s2.r][s2.c] = false;
				isVisited[nr][nc] = true;
			}
		}
		
		// System.out.print(answer);
		bw.write(answer+"\n");
		bw.flush();
		bw.close();
	}
	public static int direction(int idx, char c) {
		if(c=='D') {
			if(idx==3) {
				return 0;
			} else {
				return idx+1;
			}
		} else {
			if(idx==0) {
				return 3;
			} else {
				return idx-1;
			}
		}
	}
}
