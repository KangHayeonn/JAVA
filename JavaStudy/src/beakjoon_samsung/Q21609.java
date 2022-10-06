// ��� ���б� (���� ���2)
package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q21609 {
	static int[][] map;
	static boolean[][] isVisited;
	static int N, M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static ArrayList<Cood> arr;
	static PriorityQueue<Type> pq;
	public static class Type {
		int r, c; // ���� ��� ��ǥ
		int cnt, rainbowCnt; // ����� ��, ������ ����� ��
		ArrayList<Cood> arr;
		public Type(int r, int c, int cnt, int rainbowCnt, ArrayList<Cood> arr) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.rainbowCnt = rainbowCnt;
			this.arr = arr;
		}
	}
	public static class Cood {
		int r;
		int c;
		public Cood(int r, int c) {
			this.r = r;
			this.c = c;
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
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int totalCnt = 0, cnt;
		while(true) {
			cnt = autoPlay();
			
			if(cnt < 2) break;
			else {
				totalCnt += cnt*cnt;
				autoPlayStep3();
				autoPlayStep4();
				autoPlayStep3();
			}
		}
		
		System.out.print(totalCnt);
	}
	public static int autoPlay() {
		int num;
		pq = new PriorityQueue<>((a, b) -> {
			if(a.cnt != b.cnt) return b.cnt - a.cnt;
			else {
				if(a.rainbowCnt != b.rainbowCnt) return b.rainbowCnt - a.rainbowCnt;
				else {
					if(a.r != b.r) return b.r - a.r;
					else return b.c - a.c;
				}
			}
		});
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				num = map[i][j];
				if(num >= 1 && num <= M) {
					isVisited = new boolean[N][N];
					autoPlayStep1(i, j, num);
				}
			}
		}
		
		while(!pq.isEmpty()) {
			Type t = pq.poll();
			
			for(Cood m : t.arr) {
				map[m.r][m.c] = -2;
			}
			
			return t.cnt;
		}
		return 0;
	}
	public static void autoPlayStep1(int r, int c, int K) {
		Queue<Cood> q = new LinkedList<>();
		ArrayList<Cood> tmpArr = new ArrayList<>();
		tmpArr.add(new Cood(r, c));
		isVisited[r][c] = true;
		q.offer(new Cood(r, c));
		
		while(!q.isEmpty()) {
			Cood t = q.poll();
			
			for(int i=0; i<4; i++) {
				int nr = t.r + dr[i];
				int nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				
				if(!isVisited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == K)) {
					isVisited[nr][nc] = true;
					tmpArr.add(new Cood(nr, nc));
					q.offer(new Cood(nr, nc));
				}
			}
		}
		
		int originR = r, originC = c;
		int rainbowCnt = 0;
		for(Cood t : tmpArr) {
			if(map[t.r][t.c] == 0) rainbowCnt += 1;
			else { // �ݷ� ���� : ���� ��� ������ ������ ����� �������� �ʾҾ��� (else �߰��ؾ���)
				if(originR > t.r) {
					originR = t.r;
					originC = t.c;
				} else if(originR == t.r && originC > t.c) {
					originR = t.r;
					originC = t.c;
				} else continue;
			}
		}
		
		pq.offer(new Type(originR, originC, tmpArr.size(), rainbowCnt, tmpArr));
	}
	public static void autoPlayStep3() {
		// �߷��� ����
		int[][] cntArr = new int[N][N];
		int[][] tmpMap = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				tmpMap[i][j] = -5; // -5�� �ʱ�ȭ
			}
		}
		
		for(int c=0; c<N; c++) {     // ��
			int cntDown = 0; // �������� ĭ��
			for(int r=N-1; r>=0; r--) { // ��
				if(map[r][c] == -2) cntDown += 1;
				else if(map[r][c] == -1) {
					cntDown = 0;
					cntArr[r][c] = -1;
				}
				else cntArr[r][c] = cntDown;
			}
		}
		
		int num;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				num = cntArr[i][j];
				
				if(num > 0) {
					tmpMap[i+num][j] = map[i][j];
					map[i][j] = -2;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(tmpMap[i][j] >= 0) map[i][j] = tmpMap[i][j];
			}
		}
	}
	public static void autoPlayStep4() {
		// 90�� �ݽð�������� ȸ��
		int K;
		if(N%2 == 0) K = N/2;
		else K = N/2 + 1;
		
		int T = N;
		for(int i=0; i<K; i++) {
			counterClockWise(i, i, T);
			T -= 2;
		}
	}
	public static void counterClockWise(int r, int c, int T) {
		int R = r + T;
		int C = c + T;
		
		// ��
		int idx = 0;
		int[] Left = new int[T];
		for(int i=r; i<R; i++) {
			Left[idx] = map[i][c];
			idx += 1;
		}
		
		// ��
		idx = 0;
		int[] Down = new int[T];
		for(int i=c; i<C; i++) {
			Down[idx] = map[R-1][i];
			idx += 1;
		}
		
		// ��
		idx = 0;
		int[] Right = new int[T];
		for(int i=R-1; i>=r; i--) {
			Right[idx] = map[i][C-1];
			idx += 1;
		}
		
		// ��
		idx = 0;
		int[] Top = new int[T];
		for(int i=C-1; i>=c; i--) {
			Top[idx] = map[r][i];
			idx += 1;
		}
		
		///////////////////////////////
		
		// �� (Top ����)
		idx = 0;
		for(int i=r; i<R; i++) {
			map[i][c] = Top[idx];
			idx += 1;
		}
		
		// �� (Left ����)
		idx = 0;
		for(int i=c; i<C; i++) {
			map[R-1][i] = Left[idx];
			idx += 1;
		}
		
		// �� (Down ����)
		idx = 0;
		for(int i=R-1; i>=r; i--) {
			map[i][C-1] = Down[idx];
			idx += 1;
		}
		
		// �� (Right ����)
		idx = 0;
		for(int i=C-1; i>=c; i--) {
			map[r][i] = Right[idx];
			idx += 1;
		}
	}
}
