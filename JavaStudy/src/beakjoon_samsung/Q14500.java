// 테트로미노 (백준 골드5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14500 {
	static int[][] map;
	static int answer = Integer.MIN_VALUE;
	static int N, M;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				Tetro1_v1(i, j); // vertical
				Tetro1_v2(i, j); // horizontal
				Tetro2_v1(i, j); // square
				Tetro3_v1(i, j); // L
				Tetro3_v2(i, j); // 시계방향으로 90도 회전
				Tetro3_v3(i, j); // 시계방향으로 180도 회전
				Tetro3_v4(i, j); // 시계방향으로 270도 회전
				Tetro3_v5(i, j); // L을 대칭
				Tetro3_v6(i, j); // L을 대칭한 것을 시계방향으로 90도 회전
				Tetro3_v7(i, j); // L을 대칭한 것을 시계방향으로 180도 회전
				Tetro3_v8(i, j); // L을 대칭한 것을 시계방향으로 270도 회전
				Tetro4_v1(i, j);
				Tetro4_v2(i, j);
				Tetro4_v3(i, j);
				Tetro4_v4(i, j);
				Tetro5_v1(i, j);
				Tetro5_v2(i, j);
				Tetro5_v3(i, j);
				Tetro5_v4(i, j);
			}
		}
		
		System.out.println(answer);
	}
	public static void Tetro1_v1(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		if(r+2 < 0 || c < 0 || r+2 >= N || c >= M) return;
		if(r+3 < 0 || c < 0 || r+3 >= N || c >= M) return;
		
		int value = map[r][c] + map[r+1][c] + map[r+2][c] + map[r+3][c];
		answer = Math.max(answer, value);
	}
	public static void Tetro1_v2(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r < 0 || c+2 < 0 || r >= N || c+2 >= M) return;
		if(r < 0 || c+3 < 0 || r >= N || c+3 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r][c+2] + map[r][c+3];
		answer = Math.max(answer, value);
	}
	public static void Tetro2_v1(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		if(r+1 < 0 || c+1 < 0 || r+1 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r+1][c] + map[r+1][c+1];
		answer = Math.max(answer, value);
	}
	public static void Tetro3_v1(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		if(r+2 < 0 || c < 0 || r+2 >= N || c >= M) return;
		if(r+2 < 0 || c+1 < 0 || r+2 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r+1][c] + map[r+2][c] + map[r+2][c+1];
		answer = Math.max(answer, value);
	}
	public static void Tetro3_v2(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r < 0 || c+2 < 0 || r >= N || c+2 >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c];
		answer = Math.max(answer, value);
	}
	public static void Tetro3_v3(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r+1 < 0 || c+1 < 0 || r+1 >= N || c+1 >= M) return;
		if(r+2 < 0 || c+1 < 0 || r+2 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r+1][c+1] + map[r+2][c+1];
		answer = Math.max(answer, value);
	}
	public static void Tetro3_v4(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r < 0 || c+2 < 0 || r >= N || c+2 >= M) return;
		if(r-1 < 0 || c+2 < 0 || r-1 >= N || c+2 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r][c+2] + map[r-1][c+2];
		answer = Math.max(answer, value);
	}
	public static void Tetro3_v5(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r-1 < 0 || c+1 < 0 || r-1 >= N || c+1 >= M) return;
		if(r-2 < 0 || c+1 < 0 || r-2 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r-1][c+1] + map[r-2][c+1];
		answer = Math.max(answer, value);
	}
	public static void Tetro3_v6(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		if(r+1 < 0 || c+1 < 0 || r+1 >= N || c+1 >= M) return;
		if(r+1 < 0 || c+2 < 0 || r+1 >= N || c+2 >= M) return;
		
		int value = map[r][c] + map[r+1][c] + map[r+1][c+1] + map[r+1][c+2];
		answer = Math.max(answer, value);
	}
	public static void Tetro3_v7(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		if(r+2 < 0 || c < 0 || r+2 >= N || c >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r+1][c] + map[r+2][c];
		answer = Math.max(answer, value);
	}
	public static void Tetro3_v8(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r < 0 || c+2 < 0 || r >= N || c+2 >= M) return;
		if(r+1 < 0 || c+2 < 0 || r+1 >= N || c+2 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+2];
		answer = Math.max(answer, value);
	}
	public static void Tetro4_v1(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		if(r+1 < 0 || c+1 < 0 || r+1 >= N || c+1 >= M) return;
		if(r+2 < 0 || c+1 < 0 || r+2 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r+1][c] + map[r+1][c+1] + map[r+2][c+1];
		answer = Math.max(answer, value);
	}
	public static void Tetro4_v2(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r+1 < 0 || c+1 < 0 || r+1 >= N || c+1 >= M) return;
		if(r+1 < 0 || c+2 < 0 || r+1 >= N || c+2 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r+1][c+1] + map[r+1][c+2];
		answer = Math.max(answer, value);
	}
	public static void Tetro4_v3(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r-1 < 0 || c+1 < 0 || r-1 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r+1][c] + map[r][c+1] + map[r-1][c+1];
		answer = Math.max(answer, value);
	}
	public static void Tetro4_v4(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r-1 < 0 || c+1 < 0 || r-1 >= N || c+1 >= M) return;
		if(r-1 < 0 || c+2 < 0 || r-1 >= N || c+2 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r-1][c+1] + map[r-1][c+2];
		answer = Math.max(answer, value);
	}
	public static void Tetro5_v1(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r < 0 || c+2 < 0 || r >= N || c+2 >= M) return;
		if(r+1 < 0 || c+1 < 0 || r+1 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r][c+2] + map[r+1][c+1];
		answer = Math.max(answer, value);
	}
	public static void Tetro5_v2(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r-1 < 0 || c+1 < 0 || r-1 >= N || c+1 >= M) return;
		if(r+1 < 0 || c+1 < 0 || r+1 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r-1][c+1] + map[r+1][c+1];
		answer = Math.max(answer, value);
	}
	public static void Tetro5_v3(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r < 0 || c+1 < 0 || r >= N || c+1 >= M) return;
		if(r-1 < 0 || c+1 < 0 || r-1 >= N || c+1 >= M) return;
		if(r < 0 || c+2 < 0 || r >= N || c+2 >= M) return;
		
		int value = map[r][c] + map[r][c+1] + map[r-1][c+1] + map[r][c+2];
		answer = Math.max(answer, value);
	}
	public static void Tetro5_v4(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return;
		if(r+1 < 0 || c < 0 || r+1 >= N || c >= M) return;
		if(r+2 < 0 || c < 0 || r+2 >= N || c >= M) return;
		if(r+1 < 0 || c+1 < 0 || r+1 >= N || c+1 >= M) return;
		
		int value = map[r][c] + map[r+1][c] + map[r+2][c] + map[r+1][c+1];
		answer = Math.max(answer, value);
	}
}
