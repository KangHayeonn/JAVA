// �̼����� �ȳ�! (���� ���4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q17144 {
	static int[][] map;
	static int[][] temp;
	static int R, C, T;
	static int[] airRefresh1 = new int[2]; // ����û���� ���� ��ǥ -> �ݽð���� Ž��
	static int[] airRefresh2 = new int[2]; // ����û���� �Ʒ��� ��ǥ -> �ð���� Ž��
	static Queue<Type> spreadQ;
	static int[] dr = {0, 0, 1, -1}; // ��������
	static int[] dc = {1, -1, 0, 0};
	public static class Type {
		int r, c;
		int value;
		public Type(int r, int c, int value) {
			this.r = r;
			this.c = c;
			this.value = value; // Ȯ��Ǵ� ��
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1) {
					if(airRefresh1[0] < 2) {
						airRefresh1[0] = i;
						airRefresh2[1] = j;
					} else {
						airRefresh2[0] = i;
						airRefresh2[1] = j;
					}
				}
			}
		}
		
		while(T-- > 0) {
			spreadQ = new LinkedList<>();
			
			// �̼����� Ȯ�� �˰��� (�̼������� �ִ� ��� ĭ���� ���ÿ� �Ͼ)
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j] > 0) {
						bfs_spread(i, j);
					}
				}
			}
			
			while(!spreadQ.isEmpty()) {
				Type t = spreadQ.poll();
				map[t.r][t.c] += t.value;
			}

			// ����û���� �۵� �˰���
			temp = copy(map);
			bfs_airRefresh();
			
			map = copy(temp);
		}
		
		int count = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(temp[i][j] > 0) count += temp[i][j];
			}
		}
		System.out.println(count);
	}
	public static void bfs_spread(int r, int c) {
		int value = map[r][c]/5;
		int count = 0;
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
			if(map[nr][nc] >= 0) {
				spreadQ.offer(new Type(nr, nc, value));
				count++;
			}
		}
		
		if(count > 0) {
			value *= count;
			spreadQ.offer(new Type(r, c, (-1) * value));
		}
	}
	public static void bfs_airRefresh() {
		// �ݽð����
		temp[airRefresh1[0]][airRefresh1[1]+1] = 0;
		for(int i=airRefresh1[1]+1; i<C-1; i++) {
			temp[airRefresh1[0]][i+1] = map[airRefresh1[0]][i];
		}
		for(int i=airRefresh1[0]; i>0; i--) {
			temp[i-1][C-1] = map[i][C-1];
		}
		for(int i=C-1; i>0; i--) {
			temp[0][i-1] = map[0][i];
		}
		for(int i=0; i<airRefresh1[0]-1; i++) {
			temp[i+1][0] = map[i][0];
		}
		
		// �ð����
		temp[airRefresh2[0]][airRefresh2[1]+1] = 0;
		for(int i=airRefresh2[1]+1; i<C-1; i++) {
			temp[airRefresh2[0]][i+1] = map[airRefresh2[0]][i];
		}
		for(int i=airRefresh2[0]; i<R-1; i++) {
			temp[i+1][C-1] = map[i][C-1];
		}
		for(int i=C-1; i>0; i--) {
			temp[R-1][i-1] = map[R-1][i];
		}
		for(int i=R-1; i>airRefresh2[0]+1; i--) {
			temp[i-1][0] = map[i][0];
		}
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
