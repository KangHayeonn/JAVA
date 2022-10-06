// 마법사 상어와 블리자드 (백준 골드1)
package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q21611 {
	static int N, M;
	static int[][] map;
	static int[] dr_magic = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dc_magic = {0, 0, -1, 1};
	static ArrayList<Type> magicArr;
	static int shark_r, shark_c;
	public static class Type {
		int d; // 방향
		int s; // 거리
		public Type(int d, int s) {
			this.d = d;
			this.s = s;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		magicArr = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			magicArr.add(new Type(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		
		shark_r = N/2;
		shark_c = N/2;
		
		mapRotation();
		
		System.out.println(shark_r + " : " + shark_c);
	}
	public static void mapRotation() {
		int[][] tmpArr = new int[N][N];
		
		int nr = shark_r;
		int nc = shark_c;
		for(int i=1; i<N; i++) {
			// ←
			nr = nr + 0;
			nc = nc - 1;
			
			// ↓
			
			// →
			
			// ↑
		}
	}
}
