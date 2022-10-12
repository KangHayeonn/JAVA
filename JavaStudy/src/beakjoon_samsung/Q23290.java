// ������ ���� ���� (���� ���1)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q23290 {
	static int M, S;
	static int shark_r, shark_c; // ����� ��ġ
	static Type[][] infoMap;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] drS = {-1, 0, 1, 0}; // �����Ͽ� (���) <- (�����0) **
	static int[] dcS = {0, -1, 0, 1};
	static Type[][] tmpInfoMap; // ���� ���� ����
	static ArrayList<Cood> tmpArr; // �ӽ� �迭
	static Map<Integer, Integer> tmpMap; // �ӽ� ��
	static ArrayList<Type2> sharkMoveList;
	static int[][] smellMap;
	public static class Type { // ������� ������ ���� map�� ���� type
		int cnt; // ������� ��
		Map<Integer, Integer> fishMap; // key : ����, value : ����� ���� (������ ������ ������ �ϳ��� ����)
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
		int cnt; // ������� ��
		Map<Integer, Integer> fishMap; // key : ����, value : ����� ���� (������ ������ ������ �ϳ��� ����)
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
			t.fishMap.put(d,  t.fishMap.getOrDefault(d, 0) + 1); // map �ʱ�ȭ �Է� �߸� ���� (�����1) **
			t.cnt += 1;
			
			infoMap[fx][fy] = t;
		}
		
		st = new StringTokenizer(br.readLine());
		shark_r = Integer.parseInt(st.nextToken()) - 1; // ��� ��ǥ �߸� ���� (�����2) **
		shark_c = Integer.parseInt(st.nextToken()) - 1;
		
		sharkMoveList = new ArrayList<>();
		int[] arr = {0, 1, 2, 3};
		int[] tempArr = new int[3];
		permutation(arr, tempArr, 0);
		
		while(S-- > 0) {
			// (1��) ��������
			tmpInfoMap = new Type[4][4];
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					// tmpInfoMap[i][j] = infoMap[i][j]; <- �������� �ȵ�(���������) // ����� �̽�***
					Type t = infoMap[i][j];
					tmpMap = new HashMap<>();
					
					// ���� ���� (Map, ArrayList���) *** (����� �ٽ� �κ�!!!)
					for(Map.Entry<Integer, Integer> entry : t.fishMap.entrySet()) {
						tmpMap.put(entry.getKey(), entry.getValue());
					}
					tmpInfoMap[i][j] = new Type(t.cnt, tmpMap);
				}
			}
			
			// (2��) ��� ����Ⱑ �� ĭ �̵�
			moveFish();
			
			// (3��) �� �����ؼ� 3ĭ
			moveShark();
			
			// (4��) �� �� �� �������� ���� ������� ������ ���ڿ��� �����
			deleteSmell();
			
			// (5��) 1���� ����� ���� ���� �Ϸ��
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
		
		for(Type2 t : sharkMoveList) { // first: ù��° �̵� (...)
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
		
		if(infoMap[t.nr1][t.nc1].cnt > 0) { // ���ǹ� ���� (�����3) **
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
		
		shark_r = t.nr3; // �����ǥ ���� ������ (�����4) **
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
				moveChk = false; // ����Ⱑ �������� �ʾ��� ��� üũ ������ (�����5) **
				
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
						moveChk = true; // �̵���
						
						break;
					}
				}
				
				if(moveChk) {
					tmpMap = new HashMap<>();
					tmpInfoArr[r][c] = new Type(0, tmpMap);
				}
				/*
				if(!moveChk) { // �̵����� ���� ������ ����
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
