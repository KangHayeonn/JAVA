// �ֻ��� ������ 2 (���� ���3)
package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q23288 {
	static int N, M, K;
	static int top=1, down=6, left=4, right=3, front=5, back=2;
	static int[][] map;
	static int box_r=0, box_c=0; // �ֻ��� ��ǥ
	static int[] d = {0, 1, 2, 3}; // �̵����� (0: ����, 2: ����, 1: ����, 3: ����)
	static int nd=0; // ���� �̵�����
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0}; // �� �� �� ��
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
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score;
		int result = 0;
		while(K-- > 0) {
			// �ֻ����� �̵� �������� �� ĭ ������
			roll();
			
			// ������ ĭ�� ���� ���� ȹ��
			score = map[box_r][box_c];
			
			// �ֻ����� �Ʒ���� ĭ (x,y)�� ������ ���� �̵� ������ ����
			compare();
			
			int C = getScore(score); // bfs
			
			result += score * C;
		}
		
		System.out.println(result);
	}
	public static void roll() {
		int tmpVal;
		int nr, nc;
		
		nr = box_r + dr[nd];
		nc = box_c + dc[nd];
		
		if(nr < 0 || nc < 0 || nr >= N || nc >= M) {
			nd = (nd + 2) % 4; // ������ �ݴ�� �� ���� ��������.
			roll();
			return;
		}
		
		box_r = nr;
		box_c = nc;
		
		if(nd == 0) { // ����	
			tmpVal = right;
			right = top;
			top = left;
			left = down;
			down = tmpVal;
		} else if(nd == 1) { // ����
			tmpVal = down;
			down = front;
			front = top;
			top = back;
			back = tmpVal;
		} else if(nd == 2) { // ����
			tmpVal = left;
			left = top;
			top = right;
			right = down;
			down = tmpVal;
		} else { // ����
			tmpVal = back;
			back = top;
			top = front;
			front = down;
			down = tmpVal;
		}
	}
	public static void compare() {
		int A = down; // �ֻ����� �Ʒ���
		int B = map[box_r][box_c]; // ĭ
		
		if(A > B) {
			// 90�� �ð���� ȸ��
			nd = (nd + 1) % 4;
		} else if(A < B) {
			// 90�� �ݽð���� ȸ��
			nd = (nd -1 + 4) % 4;
		} else return;
	}
	public static int getScore(int score) {
		Queue<Type> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][M];
		q.offer(new Type(box_r, box_c));
		isVisited[box_r][box_c] = true;
		
		int totalC = 0;
		int nr, nc;
		while(!q.isEmpty()) {
			Type t = q.poll();
			
			totalC += 1;
	
			for(int i=0; i<4; i++) {
				nr = t.r + dr[i];
				nc = t.c + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
				
				if(!isVisited[nr][nc] && map[nr][nc] == score) {
					q.offer(new Type(nr, nc));
					isVisited[nr][nc] = true;	
				}
			}
		}
		
		return totalC;
	}
}
