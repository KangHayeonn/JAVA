// 마법사 상어와 복제 (백준 골드1)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q23290 {
	static int M, S;
	static int shark_r, shark_c; // 상어의 위치
	static Type[][] infoMap;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] drS = {-1, 0, 1, 0}; // 상좌하우 (상어) <- (디버깅0) **
	static int[] dcS = {0, -1, 0, 1};
	static Type[][] tmpInfoMap; // 복제 마법 저장
	static ArrayList<Cood> tmpArr; // 임시 배열
	static Map<Integer, Integer> tmpMap; // 임시 맵
	static ArrayList<Type2> sharkMoveList;
	static int[][] smellMap;
	public static class Type { // 물고기의 정보를 담은 map을 위한 type
		int cnt; // 물고기의 수
		Map<Integer, Integer> fishMap; // key : 방향, value : 물고기 개수 (동일한 방향의 물고기는 하나로 묶임)
		public Type(int cnt, Map<Integer, Integer> fishMap) {
			this.cnt = cnt;
			this.fishMap = fishMap;
		}
	}
	public static class Type2 {
		int first;
		int second;
		int third;
		public Type2(int first, int second, int third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}
	}
	public static class Cood {
		int r, c, d;
		public Cood(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static class Type3 {
		int r, c;
		int cnt; // 물고기의 수
		Map<Integer, Integer> fishMap; // key : 방향, value : 물고기 개수 (동일한 방향의 물고기는 하나로 묶임)
		public Type3(int r, int c, int cnt, Map<Integer, Integer> fishMap) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.fishMap = fishMap;
		}
	}
	public static class PQType { 
		int sum;
		String s;
		int nr1, nr2, nr3, nc1, nc2, nc3;
		public PQType(int sum, String s, int nr1, int nr2, int nr3, int nc1, int nc2, int nc3) {
			this.sum = sum;
			this.s = s;
			this.nr1 = nr1;
			this.nr2 = nr2;
			this.nr3 = nr3;
			this.nc1 = nc1;
			this.nc2 = nc2;
			this.nc3 = nc3;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		infoMap = new Type[4][4];
		smellMap = new int[4][4];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				tmpMap = new HashMap<>();
				infoMap[i][j] = new Type(0, tmpMap);
			}
		}
		
		int fx, fy, d;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			fx = Integer.parseInt(st.nextToken()) - 1;
			fy = Integer.parseInt(st.nextToken()) - 1;
			d = Integer.parseInt(st.nextToken()) - 1;
			
			Type t = infoMap[fx][fy];
			t.fishMap.put(d,  t.fishMap.getOrDefault(d, 0) + 1); // map 초기화 입력 잘못 받음 (디버깅1) **
			t.cnt += 1;
			
			infoMap[fx][fy] = t;
		}
		
		st = new StringTokenizer(br.readLine());
		shark_r = Integer.parseInt(st.nextToken()) - 1; // 상어 좌표 잘못 받음 (디버깅2) **
		shark_c = Integer.parseInt(st.nextToken()) - 1;
		
		sharkMoveList = new ArrayList<>();
		int[] arr = {0, 1, 2, 3};
		int[] tempArr = new int[3];
		permutation(arr, tempArr, 0);
		
		while(S-- > 0) {
			// (1번) 복제마법
			tmpInfoMap = new Type[4][4];
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					// tmpInfoMap[i][j] = infoMap[i][j]; <- 깊은복사 안됨(얕은복사됨) // 디버깅 이슈***
					Type t = infoMap[i][j];
					tmpMap = new HashMap<>();
					
					// 깊은 복사 (Map, ArrayList등등) *** (디버깅 핵심 부분!!!)
					for(Map.Entry<Integer, Integer> entry : t.fishMap.entrySet()) {
						tmpMap.put(entry.getKey(), entry.getValue());
					}
					tmpInfoMap[i][j] = new Type(t.cnt, tmpMap);
				}
			}
			
			// (2번) 모든 물고기가 한 칸 이동
			moveFish();
			
			// (3번) 상어가 연속해서 3칸
			moveShark();
			
			// (4번) 두 번 전 연습에서 생긴 물고기의 냄새가 격자에서 사라짐
			deleteSmell();
			
			// (5번) 1에서 사용한 복제 마법 완료됨
			completeCopy();
		}
		
		int result = 0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				result += infoMap[i][j].cnt;
			}
		}
		
		System.out.println(result);
	}
	public static void completeCopy() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				Type t = infoMap[i][j];
				Type copyT = tmpInfoMap[i][j];
				
				t.cnt += copyT.cnt;
				for(Integer key : copyT.fishMap.keySet()) {
					t.fishMap.put(key, t.fishMap.getOrDefault(key, 0) + copyT.fishMap.get(key));
				}
			}
		}
	}
	public static void deleteSmell() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(smellMap[i][j] > 0) smellMap[i][j] -= 1;
			}
		}
	}
	public static void moveShark() {
		PriorityQueue<PQType> pq = new PriorityQueue<>((a, b) -> {
			if(a.sum != b.sum) return b.sum - a.sum;
			else return (a.s).compareTo(b.s);
		});
		
		int nr1, nr2, nr3, nc1, nc2, nc3;
		int sum;
		String s;
		boolean[][] isVisited;
		
		for(Type2 t : sharkMoveList) { // first: 첫번째 이동 (...)
			sum = 0;
			isVisited = new boolean[4][4];
			s = "";
			
			nr1 = shark_r + drS[t.first];
			nc1 = shark_c + dcS[t.first];
			
			if(nr1 < 0 || nc1 < 0 || nr1 >= 4 || nc1 >= 4) continue;
			sum += infoMap[nr1][nc1].cnt;
			isVisited[nr1][nc1] = true;
			
			nr2 =  nr1 + drS[t.second];
			nc2 =  nc1 + dcS[t.second];
			
			if(nr2 < 0 || nc2 < 0 || nr2 >= 4 || nc2 >= 4) continue;
			sum += infoMap[nr2][nc2].cnt;
			isVisited[nr2][nc2] = true;
			
			nr3 = nr2 + drS[t.third];
			nc3 = nc2 + dcS[t.third];
			
			if(nr3 < 0 || nc3 < 0 || nr3 >= 4 || nc3 >= 4) continue;
			if(!isVisited[nr3][nc3]) {
				sum += infoMap[nr3][nc3].cnt;
			}
			
			s += t.first;
			s += t.second;
			s += t.third;
			
			pq.offer(new PQType(sum, s, nr1, nr2, nr3, nc1, nc2, nc3));
		}
		
		PQType t = pq.poll();
		
		if(infoMap[t.nr1][t.nc1].cnt > 0) { // 조건문 누락 (디버깅3) **
			tmpMap = new HashMap<>();
			infoMap[t.nr1][t.nc1] = new Type(0, tmpMap);
			smellMap[t.nr1][t.nc1] = 3;
		}
		
		if(infoMap[t.nr2][t.nc2].cnt > 0) {
			tmpMap = new HashMap<>();
			infoMap[t.nr2][t.nc2] = new Type(0, tmpMap);
			smellMap[t.nr2][t.nc2] = 3;
		}
		
		if(infoMap[t.nr3][t.nc3].cnt > 0) {
			tmpMap = new HashMap<>();
			infoMap[t.nr3][t.nc3] = new Type(0, tmpMap);
			smellMap[t.nr3][t.nc3] = 3;
		}
		
		shark_r = t.nr3; // 상어좌표 갱신 안해줌 (디버깅4) **
		shark_c = t.nc3;
	}
	public static void moveFish() {
		Queue<Type3> q = new LinkedList<>();
		Type[][] tmpInfoArr = new Type[4][4];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				Type t = infoMap[i][j];
				tmpInfoArr[i][j] = new Type(t.cnt, t.fishMap);
			}
		}
		
		int nr, nc, nd, count;
		boolean moveChk;
		for(int r=0; r<4; r++) {
			for(int c=0; c<4; c++) {
				Type t = infoMap[r][c];
				moveChk = false; // 물고기가 움직이지 않았을 경우 체크 안해줌 (디버깅5) **
				
				for(Integer d : t.fishMap.keySet()) {
					count = t.fishMap.get(d);
					
					for(int i=0; i<8; i++) {
						nd = (d - i + 8) % 8;
						nr = r + dr[nd];
						nc = c + dc[nd];
						
						if(nr < 0 || nc < 0 || nr >= 4 || nc >= 4) continue;
						if(smellMap[nr][nc] > 0) continue;
						if(nr == shark_r && nc == shark_c) continue;
						
						tmpMap = new HashMap<>();
						tmpMap.put(nd, count);
						q.offer(new Type3(nr, nc, count, tmpMap));
						moveChk = true; // 이동함
						
						break;
					}
				}
				
				if(moveChk) {
					tmpMap = new HashMap<>();
					tmpInfoArr[r][c] = new Type(0, tmpMap);
				}
				/*
				if(!moveChk) { // 이동하지 않은 물고기들 저장
					q.offer(new Type3(r, c, t.cnt, t.fishMap));
				}*/
			}
		}
		
		while(!q.isEmpty()) {
			Type3 t = q.poll();
			Type originT = tmpInfoArr[t.r][t.c];
			
			originT.cnt += t.cnt;
			for(Integer key : t.fishMap.keySet()) {
				originT.fishMap.put(key, originT.fishMap.getOrDefault(key, 0) + t.fishMap.get(key));
			}
		}
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				Type t = tmpInfoArr[i][j];
				infoMap[i][j] = new Type(t.cnt, t.fishMap);
			}
		}
	}
	public static void permutation(int[] arr, int[] tempArr, int depth) {
		if(depth == 3) {
			Type2 t = new Type2(tempArr[0], tempArr[1], tempArr[2]);
			sharkMoveList.add(t);
			return;
		}
		
		for(int i=0; i<4; i++) {
			tempArr[depth] = arr[i];
			permutation(arr, tempArr, depth+1);
		}
	}
}
