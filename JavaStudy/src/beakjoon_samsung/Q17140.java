// 이차원 배열과 연산 (백준 골드4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q17140 {
	static int r, c, k;
	static int[][] map;
	static PriorityQueue<Type> pq;
	public static class Type implements Comparable<Type>{
		int idx;
		int num;
		int count;
		public Type(int idx, int num, int count) {
			this.idx = idx;
			this.num = num;
			this.count = count;
		}
		@Override
		public int compareTo(Type t) {
			if(this.idx == t.idx && this.count == t.count) return this.num - t.num;
			else if(this.idx == t.idx) return this.count - t.count;
			else return this.idx - t.idx;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken())-1;
		c = Integer.parseInt(st.nextToken())-1;
		k = Integer.parseInt(st.nextToken());
		
		map = new int[3][3];
		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(time <= 100) { // 100 을 넘어갈 때까지 확인해야함
			if(r < map.length && c < map[0].length) {
				if(map[r][c] == k) break;
			}
			
			int[][] temp = copy(map);
			int R = temp.length;
			int C = temp[0].length;
			
			if(R >= C) {
				// R 연산
				map = R_Calc(R, C, temp);
			} else {
				// C 연산
				map = C_Calc(R, C, temp);
			}
			
			time += 1;
		}
		System.out.println( time > 100 ? -1 : time );
	}
	public static int[][] R_Calc(int R, int C, int[][] map) {
		Map<Integer, Integer> mapNum = new HashMap<>();
		pq = new PriorityQueue<>();

		int max = 0;
		for(int i=0; i<R; i++) {
			mapNum = new HashMap<>();
			for(int j=0; j<C; j++) {
				if(map[i][j] == 0) continue;
				mapNum.put(map[i][j], mapNum.getOrDefault(map[i][j], 0)+1);
			}
			for(int key : mapNum.keySet()) {
				pq.offer(new Type(i, key, mapNum.get(key)));
			}
			max = Math.max(max, mapNum.size());
		}
		
		int[][] temp = new int[R][max*2];
		int r = 0;
		int idx = 0;
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			if(t.idx == r) {
				temp[t.idx][idx++] = t.num;
				temp[t.idx][idx++] = t.count;
			} else {
				idx = 0;
				temp[t.idx][idx++] = t.num;
				temp[t.idx][idx++] = t.count;
				r = t.idx;
			}
		}
		
		int[][] temp2 = new int[100][100];
		if(R > 100 || max*2 > 100) {
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					temp2[i][j] = temp[i][j];
				}
			}
		}
		
		return (R > 100 || max*2 > 100) ? temp2 : temp;
	}
	public static int[][] C_Calc(int R, int C, int[][] map) {
		Map<Integer, Integer> mapNum = new HashMap<>();
		pq = new PriorityQueue<>();

		int max = 0;
		for(int i=0; i<C; i++) {
			mapNum = new HashMap<>();
			for(int j=0; j<R; j++) {
				if(map[j][i] == 0) continue;
				mapNum.put(map[j][i], mapNum.getOrDefault(map[j][i], 0)+1);
			}
			for(int key : mapNum.keySet()) {
				pq.offer(new Type(i, key, mapNum.get(key)));
			}
			max = Math.max(max, mapNum.size());
		}
		
		int[][] temp = new int[max*2][C];
		int c = 0;
		int idx = 0;
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			if(t.idx == c) {
				temp[idx++][t.idx] = t.num;
				temp[idx++][t.idx] = t.count;
			} else {
				idx = 0;
				temp[idx++][t.idx] = t.num;
				temp[idx++][t.idx] = t.count;
				c = t.idx;
			}
		}
		
		int[][] temp2 = new int[100][100];
		if(R > 100 || max*2 > 100) {
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					temp2[i][j] = temp[i][j];
				}
			}
		}
		
		return (R > 100 || max*2 > 100) ? temp2 : temp;
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
