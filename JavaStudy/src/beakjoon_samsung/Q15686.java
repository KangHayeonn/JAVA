//  치킨 배달 (백준 골드5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q15686 {
	static Type[] chicken;
	static Type[] house;
	static int answer = Integer.MAX_VALUE;
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
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		int chickenCount = 0;
		int houseCount = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) houseCount++;
				if(map[i][j]==2) chickenCount++;
			}
		}
		
		chicken = new Type[chickenCount];
		house = new Type[houseCount];
		int idx_c = 0;
		int idx_h = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1) house[idx_h++] = new Type(i, j);
				else if(map[i][j]==2) chicken[idx_c++] = new Type(i, j);
			}
		}
		
		boolean[] isVisited = new boolean[chickenCount];
		combi(isVisited, 0, 0, M);
		
		System.out.println(answer);
	}
	public static void combi(boolean[] isVisited, int start, int depth, int M) {
		if(depth==M) {
			setDistance(isVisited);
		}
		for(int i=start; i<chicken.length; i++) {
			isVisited[i] = true;
			combi(isVisited, i+1, depth+1, M);
			isVisited[i] = false;
		}
	}
	public static void setDistance(boolean[] isVisited) {
		int total = 0;
		for(int i=0; i<house.length; i++) {
			Type t1 = house[i];
			int min = Integer.MAX_VALUE;
			for(int j=0; j<isVisited.length; j++) {
				if(isVisited[j]) {
					Type t2 = chicken[j];
					int distance = Math.abs(t1.r-t2.r) + Math.abs(t1.c-t2.c);
					min = Math.min(min, distance);
				}
			}
			total += min;
		}
		answer = Math.min(answer, total);
	}
}
