// ��ǳ�� �ȳ�! (���� �÷�Ƽ��5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q23289 {
	static int[][] keyMap;
	static int R, C, K; // K : ��ǥ �µ�
	static int W; // ���� ����
	static int[][][] wall; // ���� ���� (3���� �κ� : �� �� �� ��)
	static int chocolate; // ���ݸ� ����
	static int[][] room; // ���� �µ� ����
	static ArrayList<Type> heater;
	static ArrayList<Node> checkArr;
	static boolean[][] isVisited;
	public static class Type {
		int r, c;
		int type;
		public Type(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	public static class Node {
		int r, c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		keyMap = new int[R][C];
		room = new int[R][C];
		heater = new ArrayList<>();
		checkArr = new ArrayList<>();
		int idx = 0;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				keyMap[i][j] = idx++;
				int num = Integer.parseInt(st.nextToken());
				if(num == 5) checkArr.add(new Node(i, j));
				else if(num > 0 && num < 5) heater.add(new Type(i, j, num));
			}
		}
		
		W = Integer.parseInt(br.readLine());
		wall = new int[R][C][4];
		for(int i=0; i<W; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken()); // 0 or 1
			
			if(t == 0) {
				wall[r][c][0] = 1;
				wall[r-1][c][2] = 1;
			} else { // t == 1
				wall[r][c][1] = 1;
				wall[r][c+1][3] = 1;
			}
		}
		
		while(true) {
			if(chocolate > 100) break;
			// ���� �ִ� ��� ��ǳ�⿡�� �ٶ��� �ѹ� ����
			wind();
			// �µ��� ������
			control();
			// �µ��� 1�̻��� ���� �ٱ��� ĭ�� �µ��� 1�� ����
			decrease();
			// ���ݸ��� �ϳ� �Դ´�.
			chocolate++;
			// �����ϴ� ��� ĭ�� �µ��� K �̻��� �Ǿ����� �˻�. ��� ĭ�� �µ��� K�̻��̸� �׽�Ʈ�� �ߴ��ϰ�, �ƴϸ� 1���� �ٽ� ����
			if(inspect()) break;
		}
		
		System.out.println(chocolate > 100 ? 101 : chocolate);
	}
	public static void wind() {
		for(Type t : heater) {
			isVisited = new boolean[R][C];
			if(t.type == 1) { // ������
				int x = t.r;
				int y = t.c+1;
				spread(x, y, 5, 5, 1);
			} else if(t.type == 2) { // ����
				int x = t.r;
				int y = t.c-1;
				spread(x, y, 5, 5, 2);
			} else if(t.type == 3) { // ��
				int x = t.r-1;
				int y = t.c;
				spread(x, y, 5, 5, 3);
			} else { // �Ʒ�
				int x = t.r+1;
				int y = t.c;
				spread(x, y, 5, 5, 4);
			}
		}
	}
	public static void spread(int x, int y, int k, int depth, int type) {
		if(depth == 0) return;
		if(x < 0 || y < 0 || x >= R || y >= C) return;
		if(!isVisited[x][y]) {
			room[x][y] += k;
			isVisited[x][y] = true;
		}

		// ����������
		if(type == 1) {
			if(wall[x][y][0] != 1) {
				if(x-1 >= 0) {
					if(wall[x-1][y][1] != 1) spread(x-1, y+1, k-1, depth-1, 1);
				}
			}
			if(wall[x][y][1] != 1) {
				spread(x, y+1, k-1, depth-1, 1);
			}
			if(wall[x][y][2] != 1) {
				if(x+1 >= R) return;
				else {
					if(wall[x+1][y][1] != 1) spread(x+1, y+1, k-1, depth-1, 1);
				}
			}
		}
		
		// ��������
		if(type == 2) {
			if(wall[x][y][0] != 1) {
				if(x-1 >= 0) {
					if(wall[x-1][y][3] != 1) spread(x-1, y-1, k-1, depth-1, 2);
				}
			}
			if(wall[x][y][3] != 1) {
				spread(x, y-1, k-1, depth-1, 2);
			}
			if(wall[x][y][2] != 1) {
				if(x+1 >= R) return;
				else {
					if(wall[x+1][y][3] != 1) spread(x+1, y-1, k-1, depth-1, 2);
				}
			}
		}
		
		// ��������
		if(type == 3) {
			if(wall[x][y][3] != 1) {
				if(y-1 >= 0) {
					if(wall[x][y-1][0] != 1) spread(x-1, y-1, k-1, depth-1, 3);
				}
			}
			if(wall[x][y][0] != 1) {
				spread(x-1, y, k-1, depth-1, 3);
			}
			if(wall[x][y][1] != 1) {
				if(y+1 >= C) return;
				else {
					if(wall[x][y+1][0] != 1) spread(x-1, y+1, k-1, depth-1, 3);
				}
			}
		}
		
		// �Ʒ�������
		if(type == 4) {
			if(wall[x][y][3] != 1) {
				if(y-1 >= 0) {
					if(wall[x][y-1][2] != 1) spread(x+1, y-1, k-1, depth-1, 4);
				}
			}
			if(wall[x][y][2] != 1) {
				spread(x+1, y, k-1, depth-1, 4);
			}
			if(wall[x][y][1] != 1) {
				if(y+1 >= C) return;
				else {
					if(wall[x][y+1][2] != 1) spread(x+1, y+1, k-1, depth-1, 4);
				}
			}
		}
	}
	public static void control() {
		int[][] temp = new int[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				int mainValue = room[i][j];
				
				// ����
				if(wall[i][j][1] != 1) {
					int nr = i;
					int nc = j+1;
					if(nr >= 0 && nc >= 0 && nr < R && nc < C) {
						int value = Math.abs(mainValue - room[nr][nc]) / 4;
						
						if(mainValue > room[nr][nc]) {
							temp[i][j] -= value;
							temp[nr][nc] += value;
						} 
					}
				}
				
				// ����
				if(wall[i][j][3] != 1) {
					int nr = i;
					int nc = j-1;
					if(nr >= 0 && nc >= 0 && nr < R && nc < C) {
						int value = Math.abs(mainValue - room[nr][nc]) / 4;
						
						if(mainValue > room[nr][nc]) {
							temp[i][j] -= value;
							temp[nr][nc] += value;
						}
					}
				}
				
				// ����
				if(wall[i][j][2] != 1) {
					int nr = i+1;
					int nc = j;
					
					if(nr >= 0 && nc >= 0 && nr < R && nc < C) {
						int value = Math.abs(mainValue - room[nr][nc]) / 4;
						
						if(mainValue > room[nr][nc]) {
							temp[i][j] -= value;
							temp[nr][nc] += value;
						}
					}
				}
				
				// ����
				if(wall[i][j][0] != 1) {
					int nr = i-1;
					int nc = j;
					if(nr >= 0 && nc >= 0 && nr < R && nc < C) {
						int value = Math.abs(mainValue - room[nr][nc]) / 4;
						
						if(mainValue > room[nr][nc]) {
							temp[i][j] -= value;
							temp[nr][nc] += value;
						}
					}
				}
			}
		}
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				room[i][j] += temp[i][j];
			}
		}
	}
	public static void decrease() {
		if(room[0][0] > 0) room[0][0] -= 1;
		if(room[R-1][0] > 0) room[R-1][0] -= 1;
		if(room[0][C-1] > 0) room[0][C-1] -= 1;
		if(room[R-1][C-1] > 0) room[R-1][C-1] -= 1;

		for(int r=1; r<R-1; r++) { // �𼭸� ����
			if(room[r][0] > 0) room[r][0] -= 1;
			if(room[r][C-1] > 0) room[r][C-1] -= 1;
		}
		for(int c=1; c<C-1; c++) {
			if(room[0][c] > 0) room[0][c] -= 1;
			if(room[R-1][c] > 0) room[R-1][c] -= 1;
		}
	}
	public static boolean inspect() {
		boolean check = true;
		for(Node n : checkArr) {
			if(room[n.r][n.c] >= K) continue;
			else {
				check = false;
				break;
			}
		}
		if(!check) return false;
		return true;
	}
}
