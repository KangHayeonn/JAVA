// 감시 (백준 골드4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q15683 {
	static int N, M;
	static ArrayList<Type> arr;
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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList<>();
		int[][] map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					arr.add(new Type(i, j));
				}
			}
		}

		dfs(map, 0, arr.size());
		System.out.println(answer);
	}
	public static void dfs(int[][] map, int start, int depth) {
		if(depth==0) {
			int sum =0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) sum+=1;
				}
			}
			answer = Math.min(answer, sum);
			return;
		}
		
		Type t = arr.get(start);
		if(map[t.r][t.c] == 1) {
			int[][] temp = copy(map);
			temp = checkUp(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkDown(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkLeft(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkRight(temp, start, depth);
			dfs(temp, start+1, depth-1);
		}
		if(map[t.r][t.c] == 2) {
			int[][] temp = copy(map);
			temp = checkUp(temp, start, depth);
			temp = checkDown(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkLeft(temp, start, depth);
			temp = checkRight(temp, start, depth);
			dfs(temp, start+1, depth-1);
		}
		if(map[t.r][t.c] == 3) {
			int[][] temp = copy(map);
			temp = checkUp(temp, start, depth);
			temp = checkRight(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkRight(temp, start, depth);
			temp = checkDown(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkLeft(temp, start, depth);
			temp = checkDown(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkLeft(temp, start, depth);
			temp = checkUp(temp, start, depth);
			dfs(temp, start+1, depth-1);
		}
		if(map[t.r][t.c] == 4) {
			int[][] temp = copy(map);
			temp = checkLeft(temp, start, depth);
			temp = checkUp(temp, start, depth);
			temp = checkRight(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkUp(temp, start, depth);
			temp = checkRight(temp, start, depth);
			temp = checkDown(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkLeft(temp, start, depth);
			temp = checkRight(temp, start, depth);
			temp = checkDown(temp, start, depth);
			dfs(temp, start+1, depth-1);
			
			temp = copy(map);
			temp = checkLeft(temp, start, depth);
			temp = checkUp(temp, start, depth);
			temp = checkDown(temp, start, depth);
			dfs(temp, start+1, depth-1);
		}
		if(map[t.r][t.c] == 5) {
			int[][] temp = copy(map);
			temp = checkLeft(temp, start, depth);
			temp = checkRight(temp, start, depth);
			temp = checkUp(temp, start, depth);
			temp = checkDown(temp, start, depth);
			dfs(temp, start+1, depth-1);
		}
	}
	public static int[][] checkLeft(int[][] map, int start, int depth) {
		Type t = arr.get(start);
		for(int i=t.c; i>=0; i--) {
			if(map[t.r][i] == 6) return map;
			else {
				if(map[t.r][i] == 0) {
					map[t.r][i] = -1;
				}
			}
		}
		return map;
	}
	public static int[][] checkRight(int[][] map, int start, int depth) {
		Type t = arr.get(start);
		for(int i=t.c; i<M; i++) {
			if(map[t.r][i] == 6) return map;
			else {
				if(map[t.r][i] == 0) {
					map[t.r][i] = -1;
				}
			}
		}
		return map;
	}
	public static int[][] checkUp(int[][] map, int start, int depth) {
		Type t = arr.get(start);
		for(int i=t.r; i>=0; i--) {
			if(map[i][t.c] == 6) return map;
			else {
				if(map[i][t.c] == 0) {
					map[i][t.c] = -1;
				}
			}
		}
		return map;
	}
	public static int[][] checkDown(int[][] map, int start, int depth) {
		Type t = arr.get(start);
		for(int i=t.r; i<N; i++) {
			if(map[i][t.c] == 6) return map;
			else {
				if(map[i][t.c] == 0) {
					map[i][t.c] = -1;
				}
			}
		}
		return map;
	}
	public static int[][] copy(int[][] map) {
		int[][] temp = new int[map.length][map[0].length];
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		return temp;
	}
}
