// ������ ���� ���� (���� ���1)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q23290 {
	static int M, S;
	static MapType[][] map = new MapType[4][4];
	static SmellType[][] smellChkMap = new SmellType[4][4];
	static int fishIdx = 1;
	static Map<Integer, Type> fishMap;
	static int shark_r, shark_c;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] shark_dr = {-1, 1, 0, 0}; // �� �� �� �� 
	static int[] shark_dc = {0, 0, -1, 1};
	static ArrayList<String> sharkMoveDict;
	public static class Type {
		int r, c;
		int d;
		public Type(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static class MapType {
		int cnt;
		ArrayList<Integer> arr;
		public MapType(int cnt, ArrayList<Integer> arr) {
			this.cnt = cnt;
			this.arr = arr;
		}
	}
	public static class SmellType {
		boolean chk; // true: ����� ���� ��, false: ����� ���� ��
		int cnt; // ��� ����������
		public SmellType(boolean chk, int cnt) {
			this.chk = chk;
			this.cnt = cnt;
		}
	}
	public static class SharkType { 
		int rFirst, cFirst;
		int rSecond, cSecond;
		int rThird, cThird;
		int cnt;
		String s;
		public SharkType(int rFirst, int cFirst, int rSecond, int cSecond, int rThird, int cThird, int cnt, String s) {
			this.rFirst = rFirst;
			this.cFirst = cFirst;
			this.rSecond = rSecond;
			this.cSecond = cSecond;
			this.rThird = rThird;
			this.cThird = cThird;
			this.cnt = cnt;
			this.s = s;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		fishMap = new HashMap<>();
		int r, c, d;
		ArrayList<Integer> tmpArr;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			d = Integer.parseInt(st.nextToken())-1;
			fishMap.put(fishIdx, new Type(r, c, d));
			
			if(map[r][c] == null) {
				tmpArr = new ArrayList<Integer>();
				tmpArr.add(fishIdx);
				map[r][c] = new MapType(1, tmpArr);
			} else {
				MapType t = map[r][c];
				t.cnt += 1;
				t.arr.add(fishIdx);
				map[r][c] = t; 
			}
			fishIdx += 1;
		}
		
		st = new StringTokenizer(br.readLine());
		shark_r = Integer.parseInt(st.nextToken())-1;
		shark_c = Integer.parseInt(st.nextToken())-1;
		
		int[] arr = {0, 1, 2, 3};
		int[] pickArr = new int[3];
		sharkMoveDict = new ArrayList<>();
		
		getSharkMoveDict(arr, pickArr, 0);
		
		// Collections.sort(sharkMoveDict);
		
		Queue<Type> tmpQ;
		int idx = 1;
		while(S-- > 0) {
			// �� ��� ����⿡�� ���� ������ ���� (1��)
			tmpQ = new LinkedList<>();
			for(Integer key : fishMap.keySet()) {
				Type t = fishMap.get(key);
				tmpQ.offer(new Type(t.r, t.c, t.d));
			}
			/*
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(map[i][j] == null) continue;
					System.out.println("r: " + i + ", c : " + j + " -> " + map[i][j].cnt);
					for(Integer a: map[i][j].arr) {
						System.out.println(a + ", ");
					}
					System.out.println();
				}
			}
			
			*/System.out.println("\n-------------------�̵��� -------------");
			
			// ��� ����Ⱑ ��ĭ �̵� (2��)
			moveFish();
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(map[i][j] == null) continue;
					System.out.println("r: " + i + ", c : " + j + " -> " + map[i][j].cnt);
					for(Integer a: map[i][j].arr) {
						System.out.println(a + ", ");
					}
					System.out.println();
				}
			}

			// �� �����ؼ� 3ĭ �̵� (3��)
			moveShark();
			
			System.out.println("\n-------------------�� ��Ƹ��� �� -------------");
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(map[i][j] == null) continue;
					System.out.println("r: " + i + ", c : " + j + " -> " + map[i][j].cnt);
					for(Integer a: map[i][j].arr) {
						System.out.println(a + ", ");
					}
					System.out.println();
				}
			}
			
			// �� �� �� �������� ���� ������� ������ ���ڿ��� ����� (4��)
			deleteSmell();
			
			// ���� ���� �Ϸ�� (5��)
			while(!tmpQ.isEmpty()) {
				Type t = tmpQ.poll();
				
				fishMap.put(fishIdx, new Type(t.r, t.c, t.d));
				
				if(map[t.r][t.c] == null) {
					tmpArr = new ArrayList<Integer>();
					tmpArr.add(fishIdx);
					map[t.r][t.c] = new MapType(1, tmpArr);
				} else {
					MapType m = map[t.r][t.c];
					m.cnt += 1;
					m.arr.add(fishIdx);
					map[t.r][t.c] = m; 
				}
				fishIdx += 1;
			}
			
			System.out.println(idx + " ��° -----------");
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(smellChkMap[i][j] == null) continue;
					System.out.println("r: " + i + ", c : " + j + " -> " + smellChkMap[i][j].chk + " , cnt : " + smellChkMap[i][j].cnt);
				}
			}
			System.out.println();
			idx += 1;
			/*
			System.out.println("\n-------------------���� �� -------------");
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(map[i][j] == null) continue;
					System.out.println("r: " + i + ", c : " + j + " -> " + map[i][j].cnt);
					for(Integer a: map[i][j].arr) {
						System.out.println(a + ", ");
					}
					System.out.println();
				}
			}
			System.out.println("\n-------------------Finish -------------");*/
			
		}
		
		int sumFish = 0;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(map[i][j] != null) {
					/*
					System.out.println("cnt : " + map[i][j].cnt);
					for(Integer a: map[i][j].arr) {
						System.out.println(a + ", ");
					}*/
					sumFish += map[i][j].cnt;
				}
			}
		}
		
		System.out.println(sumFish);
	}
	public static void moveFish() {
		int nr=0, nc=0, nd=0;
		boolean moveChk;
		ArrayList<Integer> tmpArr;
		for(Integer key : fishMap.keySet()) {
			Type t = fishMap.get(key);
			
			moveChk = false;
			for(int i=0; i<8; i++) {
				nd = (t.d + -i + 8) % 8;
				nr = t.r + dr[nd];
				nc = t.c + dc[nd];
				
				if(nr < 0 || nc < 0 || nr >= 4 || nc >= 4) continue;
				
				if(nr == shark_r && nc == shark_c) continue;
				
				if(smellChkMap[nr][nc] != null && smellChkMap[nr][nc].chk) continue;
				
				moveChk = true;
				break;
			}
			
			if(moveChk) { // �̵�
				MapType m = map[t.r][t.c];
				m.cnt -= 1;
				m.arr.remove(key);
				map[t.r][t.c] = m;
				
				if(map[nr][nc] == null) {
					tmpArr = new ArrayList<Integer>();
					tmpArr.add(key);
					map[nr][nc] = new MapType(1, tmpArr);
				} else {
					MapType a = map[nr][nc];
					a.cnt += 1;
					a.arr.add(key);
					map[nr][nc] = a; 
				}
				
				/*
				System.out.println(">>> �̵�" + " , key : "+ key);
				System.out.println("nr : " + nr + " nc : " + nc + " -> d: " + nd);
				System.out.println("<<<< \n");*/
				fishMap.put(key, new Type(nr, nc, nd));
			}
		}
	}
	public static void getSharkMoveDict(int[] arr, int[] pickArr, int depth) {
		if(depth == 3) {
			String s = "";
			for(int i=0; i<3; i++) {
				s += pickArr[i];
			}
			sharkMoveDict.add(s);
			return;
		}
		
		// ����
		for(int i=0; i<4; i++) {
			pickArr[depth] = arr[i];
			getSharkMoveDict(arr, pickArr, depth+1);
		}
	}
	public static void moveShark() {
		PriorityQueue<SharkType> pq = new PriorityQueue<>((a, b) -> {
			if(a.cnt != b.cnt) return b.cnt - a.cnt;
			else {
				return (a.s).compareTo(b.s);
			}
		});
		int first, second, third;
		int nrFirst, ncFirst, nrSecond, ncSecond, nrThird, ncThird;
		boolean[][] isVisited;
		
		int sum = 0;
		for(String s : sharkMoveDict) {
			isVisited = new boolean[4][4];
			first = s.charAt(0) - '0';
			second = s.charAt(1) - '0';
			third = s.charAt(2) - '0';
			System.out.println(first + " " + second + " " + third);
			System.out.println("(��� ��ǥ) r: " + shark_r + ", c: " + shark_c);
			
			sum = 0;
			
			nrFirst = shark_r + shark_dr[first];
			ncFirst = shark_c + shark_dc[first];
			System.out.println("(ù��° �̵�) r: " + nrFirst + ", c: " + ncFirst);
			
			if(nrFirst < 0 || ncFirst < 0 || nrFirst >= 4 || ncFirst >= 4) continue;
			if(map[nrFirst][ncFirst] != null && !isVisited[nrFirst][ncFirst]) {
				sum += map[nrFirst][ncFirst].cnt;
			}
			isVisited[nrFirst][ncFirst] = true;
			
			nrSecond = nrFirst + shark_dr[second];
			ncSecond = ncFirst + shark_dc[second];
			System.out.println("(�ι�° �̵�) r: " + nrSecond + ", c: " + ncSecond);
			
			if(nrSecond < 0 || ncSecond < 0 || nrSecond >= 4 || ncSecond >= 4) continue;
			if(map[nrSecond][ncSecond] != null && !isVisited[nrSecond][ncSecond]) {
				sum += map[nrSecond][ncSecond].cnt;
			}
			isVisited[nrSecond][ncSecond] = true;
			
			nrThird = nrSecond + shark_dr[third];
			ncThird = ncSecond + shark_dc[third];
			System.out.println("(����° �̵�) r: " + nrThird + ", c: " + ncThird);
			
			if(nrThird < 0 || ncThird < 0 || nrThird >= 4 || ncThird >= 4) continue;
			if(map[nrThird][ncThird] != null && !isVisited[nrThird][ncThird]) {
				sum += map[nrThird][ncThird].cnt;
			}
			
			pq.offer(new SharkType(nrFirst, ncFirst, nrSecond, ncSecond, nrThird, ncThird, sum, s));
		}
		
		SharkType s = pq.poll();
		System.out.println(s.s + " -> " + s.cnt);
		
		// ù��° �̵����� ����
		if(map[s.rFirst][s.cFirst] != null && map[s.rFirst][s.cFirst].cnt > 0) {
			// map���� ����
			MapType m = map[s.rFirst][s.cFirst];
			for(Integer i : m.arr) {
				fishMap.remove(i); // fishMap���� ����
			}
			m.cnt = 0;
			m.arr.clear();
			map[s.rFirst][s.cFirst] = m;
			
			// smellMap�� �߰�
			smellChkMap[s.rFirst][s.cFirst] = new SmellType(true, 3);
		}
		
		// �ι�° �̵����� ����
		if(map[s.rSecond][s.cSecond] != null && map[s.rSecond][s.cSecond].cnt > 0) {
			// map���� ����
			MapType m = map[s.rSecond][s.cSecond];
			for(Integer i : m.arr) {
				fishMap.remove(i); // fishMap���� ����
			}
			m.cnt = 0;
			m.arr.clear();
			map[s.rSecond][s.cSecond] = m;
			
			// smellMap�� �߰�
			smellChkMap[s.rSecond][s.cSecond] = new SmellType(true, 3);
		}
		
		// ����° �̵����� ����
		if(map[s.rThird][s.cThird] != null && map[s.rThird][s.cThird].cnt > 0) {
			// map���� ����
			MapType m = map[s.rThird][s.cThird];
			for(Integer i : m.arr) {
				fishMap.remove(i); // fishMap���� ����
			}
			m.cnt = 0;
			m.arr.clear();
			map[s.rThird][s.cThird] = m;
			
			// smellMap�� �߰�
			smellChkMap[s.rThird][s.cThird] = new SmellType(true, 3);
		}
		
		shark_r = s.rThird;
		shark_c = s.cThird;
	}
	public static void deleteSmell() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(smellChkMap[i][j] != null) {
					SmellType t = smellChkMap[i][j];
					t.cnt -= 1;
					
					if(t.cnt == 0) {
						smellChkMap[i][j] = null;
					} else {
						smellChkMap[i][j] = t;
					}
				}
			}
		}
	}
}
