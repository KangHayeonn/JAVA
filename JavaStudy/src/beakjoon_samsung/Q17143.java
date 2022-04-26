// ���ÿ� (���� ���2)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q17143 {
	static Type2[][] map;
	static int R, C, M;
	static PriorityQueue<Type> shark;
	static int[] changeD = {1, 0, 3, 2};
	static int answer = 0;
	public static class Type implements Comparable<Type> {
		int r, c;
		int s; // �ӷ�
		int d; // �̵� ����
		int z; // ũ��
		public Type(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		@Override
		public int compareTo(Type t) {
			if(this.r == t.r && this.c == t.c) return t.z - this.z;
			else if(this.r == t.r) return this.c - t.c;
			else return this.r - t.r;
		}
	}
	public static class Type2 {
		int s; // �ӷ�
		int d; // �̵� ����
		int z; // ũ��
		public Type2(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Type2[R][C];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			
			if(d <= 1) s %= (R - 1) * 2; // ���ʿ��� ������ �ٿ��ֱ� ���� �ӵ� ������ �ּ�ȭ�� �ٿ��� (�ð��ʰ� ����)
			else s %= (C - 1) * 2;
			
			map[r][c] = new Type2(s, d, z);
		}
		
		int depth = -1;
		while(depth < C-1) { // C�� �������� Ż�� (C�� ��)
			// ���ÿ��� ���������� ��ĭ�̵�
			depth += 1;
			shark = new PriorityQueue<>();
			// ���ÿ��� �ִ� ���� �ִ� ��� �� ���� ���� ����� �� ���� (���� ���� �����ǿ��� �����)
			catchShark(depth);
			// ��� �̵� (�������� ���� �ݴ��)
			moveShark();
			// ��ĭ�� �� �θ��� �̻��� ���, ���� ū �� ������ �� ��� ��� ����
			map = new Type2[R][C];
			removeShark();
		}
		System.out.println(answer);
	}

	public static void catchShark(int depth) {
		for(int i=0; i<R; i++) {
			if(map[i][depth] != null) {
				answer += map[i][depth].z;
				map[i][depth] = null;
				break;
			}
		}
	}
	public static void moveShark() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] != null) {
					Type t = move(i, j, map[i][j].s, map[i][j].d, map[i][j].z, map[i][j].s);
					shark.offer(t);
				}
			}
		}
	}
	public static Type move(int r, int c, int s, int d, int z, int depth) {
		if(r < 0 || c < 0 || r >= R || c >= C) {
			d = changeD[d];
			if(r < 0) {
				return move(r+1, c, s, d, z, depth+1);
			}
			else if(c < 0) {
				return move(r, c+1, s, d, z, depth+1);
			}
			else if(r >= R) {
				return move(r-1, c, s, d, z, depth+1);
			}
			else if(c >= C) {
				return move(r, c-1, s, d, z, depth+1);
			}
		}
		
		if(depth==0) {
			Type t = new Type(r, c, s, d, z);
			return t;
		}
		
		if(d==0) {
			return move(r-1, c, s, d, z, depth-1);
		}
		else if(d==1) {
			return move(r+1, c, s, d, z, depth-1);
		}
		else if(d==2) {
			return move(r, c+1, s, d, z, depth-1);
		}
		else {
			return move(r, c-1, s, d, z, depth-1);
		}
	}
	public static void removeShark() {
		boolean[][] isVisited = new boolean[R][C];
		
		while(!shark.isEmpty()) {
			Type t = shark.poll();
			
			if(isVisited[t.r][t.c]) continue;
			
			map[t.r][t.c] = new Type2(t.s, t.d, t.z);
			isVisited[t.r][t.c] = true;
		}
	}
}
