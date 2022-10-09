// 마법사 상어와 토네이도 (백준 골드3)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q20057 {
	static int N;
	static int[][] map;
	static int centerR, centerC;
	static int result = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		centerR = N/2;
		centerC = N/2;
		
		tornado();
		System.out.println(result);
	}
	public static void tornado() {
		int[] dr = {0, 1, 0, -1}; // ← ↓ → ↑
		int[] dc = {-1, 0, 1, 0};
		
		int getIdx = 0;
		int nr = centerR, nc = centerC;
		while(true) {
			for(int i=0; i<getIdx/2+1; i++) {
				nr = nr + dr[getIdx%4];
				nc = nc + dc[getIdx%4];
				
				spreadSand(nr, nc, getIdx%4);
				if(nr == 0 && nc == 0) break;
			}
			if(nr == 0 && nc == 0) break;
			getIdx += 1;
		}
	}
	public static void spreadSand(int r, int c, int d) {
		int[][] dr = {
				{-1, -1, -1, -1, 0, 1, 1, 1, 1}, 
				{-1, 0, 0, 1, 1, 1, 0, 0, -1}, 
				{1, 1, 1, 1, 0, -1, -1, -1, -1}, 
				{1, 0, 0, -1, -1, -1, 0, 0, 1}};
		int[][] dc = {
				{1, 0, 0, -1, -1, -1, 0, 0, 1}, 
				{-1, -1, -1, -1, 0, 1, 1, 1, 1}, 
				{-1, 0, 0, 1, 1, 1, 0, 0, -1}, 
				{1, 1, 1, 1, 0, -1, -1, -1, -1}};
		int[] s = {1, 2, 1, 1, 2, 1, 1, 2, 1};
		int[] percent = {1, 2, 7, 10, 5, 10, 7, 2, 1};
		int[] a_dr = {0, 1, 0, -1};
		int[] a_dc = {-1, 0, 1, 0};
		
		int nr, nc;
		int total = 0;
		int val = map[r][c];
		int n;
		for(int i=0; i<9; i++) {
			nr = r + dr[d][i]*s[i];
			nc = c + dc[d][i]*s[i];
			n = val * percent[i] / 100;
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
				result += n;
				total += n;
				continue;
			}
			
			map[nr][nc] += n;
			total += n;
		}
		int a = val - total;
		nr = r + a_dr[d];
		nc = c + a_dc[d];
		
		if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
			result += a;
		} else {
			map[nr][nc] += a;
		}
	}
}
