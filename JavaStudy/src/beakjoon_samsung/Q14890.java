// 경사로 (백준 골드3)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14890 {
	static int[][] map;
	static int N, L;
	static int answer = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			if(rowSearch(i)) answer++;
			if(columnSearch(i)) answer++;
		}
		System.out.println(answer);
	}
	public static boolean rowSearch(int r) {
		boolean[] isVisited = new boolean[N];
		
		for(int i=0; i<N-1; i++) {
			int value = map[r][i] - map[r][i+1];
			
			if(value == 0) {
				continue;
			}
			else if(value < -1 || value > 1) return false;
			else {
				if(value == -1) {
					for(int j=0; j<L; j++) {
						if(i-j < 0 || isVisited[i-j]) return false;
						if(map[r][i] == map[r][i-j]) {
							isVisited[i-j] = true;
						}
					}
				} else if(value == 1) {
					for(int j=1; j<=L; j++) {
						if(i+j >= N) return false;
						if(map[r][i+1] == map[r][i+j]) {
							isVisited[i+j] = true;
						} else return false;
					}
				}
			}
		}
		return true;
	}
	public static boolean columnSearch(int c) {
		boolean[] isVisited = new boolean[N];
		
		for(int i=0; i<N-1; i++) {
			int value = map[i][c] - map[i+1][c];
			
			if(value == 0) {
				continue;
			}
			else if(value < -1 || value > 1) return false;
			else {
				if(value == -1) {
					for(int j=0; j<L; j++) {
						if(i-j < 0 || isVisited[i-j]) return false;
						if(map[i][c] == map[i-j][c]) {
							isVisited[i-j] = true;
						}
					}
				} else if(value == 1) {
					for(int j=1; j<=L; j++) {
						if(i+j >= N) return false;
						if(map[i+1][c] == map[i+j][c]) {
							isVisited[i+j] = true;
						} else return false;
					}
				}
			}
		}
		return true;
	}
}
