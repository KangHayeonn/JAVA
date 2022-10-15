// 청소년 상어 (백준 골드2) - 삼성

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q19236 {
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	static int max = 0;
	static Queue<Type2> q;
	static int shark_nr, shark_nc;
	static int[][] tmpArr;
	static Map<Integer, Type> tmpMap;
	public static class Type {
		int d; // 방향
		int r, c; // 좌표
		public Type(int d, int r, int c) {
			this.d = d;
			this.r = r;
			this.c = c;
		}
	}
	public static class Type2 {
		int num; // 물고기 번호
		int d; // 방향
		int r, c; // 상어 좌표
		public Type2(int num, int d, int r, int c) {
			this.num = num;
			this.d = d;
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		int[][] map = new int[4][4];
		Map<Integer, Type> fishMap = new HashMap<>(); // 물고기 번호 : key
		
		int num, d;
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				num = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken())-1;
				map[i][j] = num; // 물고기의 번호를 저장한 map
				fishMap.put(num, new Type(d, i, j));
			}
		}
		
		int start = map[0][0];
		Type shark = new Type(fishMap.get(start).d, 0, 0); // 상어 번호 -1
		map[0][0] = -1; // -1 일 경우 상어 위치
		fishMap.remove(start);
		max += start;
		
		move(map, fishMap, shark, max);
		
		System.out.println(max);
	}
	public static void move(int[][] map, Map<Integer, Type> fishMap, Type shark, int sum) {
		if(shark.r < 0 || shark.c < 0 || shark.r >=4 || shark.c >= 4) {
			max = Math.max(max, sum);
			return;
		}
		
		q = new LinkedList<>();
		
		int nr=0, nc=0, nd=0;
		for(int i=1; i<=16; i++) {
			if(!fishMap.containsKey(i)) continue; // 해당 칸에 물고기가 없음
			Type t = fishMap.get(i);
			
			for(int j=0; j<8; j++) {
				nd = (t.d + j) % 8;
				nr = t.r + dr[nd];
				nc = t.c + dc[nd];
				
				if(nr < 0 || nc < 0 || nr >= 4 || nc >= 4) continue; // 공간의 경계를 넘는 칸
				if(map[nr][nc] == -1) continue; // 상어가 있는 칸
				
				if(map[nr][nc] > 0) {
					int tmpN = map[nr][nc];
					map[nr][nc] = i;
					map[t.r][t.c] = tmpN;
					fishMap.put(i, new Type(nd, nr, nc));
					fishMap.put(tmpN, new Type(fishMap.get(tmpN).d, t.r, t.c));
				} else {
					// 빈칸에 들어감
					map[nr][nc] = i;
					map[t.r][t.c] = 0;
					fishMap.put(i, new Type(nd, nr, nc));
				}
				break;
			}
		}
		
		for(int i=1; i<=3; i++) {
			tmpArr = copy(map);
			tmpMap = copyMap(fishMap);
			shark_nr = shark.r + dr[shark.d] * i;
			shark_nc = shark.c + dc[shark.d] * i;
			
			if(shark_nr < 0 || shark_nc < 0 || shark_nr >= 4 || shark_nc >= 4) {
				Type tmpShark = new Type(shark.d, shark_nr, shark_nc);
				move(tmpArr, tmpMap, tmpShark, sum);
				continue;
			}
			
			if(tmpArr[shark_nr][shark_nc] > 0) {
				int fish = tmpArr[shark_nr][shark_nc];
				sum += fish;
				Type t = tmpMap.get(fish);
				tmpArr[shark.r][shark.c] = 0; // 빈칸
				tmpArr[shark_nr][shark_nc] = -1;
				tmpMap.remove(fish);
				Type tmpShark = new Type(t.d, shark_nr, shark_nc);
				move(tmpArr, tmpMap, tmpShark, sum);
				sum -= fish;
			}
		}
	}
	public static int[][] copy(int[][] map) {
		int[][] tmpMap = new int[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}
		return tmpMap;
	}
	public static Map<Integer, Type> copyMap(Map<Integer, Type> map) {
		Map<Integer, Type> tmpMap = new HashMap<>();
		for(Map.Entry<Integer, Type> entry: map.entrySet()) {
			tmpMap.put(entry.getKey(), entry.getValue());
		}
		return tmpMap;
	}
}
