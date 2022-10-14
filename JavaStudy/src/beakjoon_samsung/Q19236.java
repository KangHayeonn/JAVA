// û�ҳ� ��� (���� ���2) - �Ｚ

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q19236 {
	static int[][] map;
	static Map<Integer, Type> sharkMap; // ��� ��ȣ : key
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	static Type shark;
	static int max = 0;
	public static class Type {
		int d; // ����
		int r, c; // ��� ��ǥ
		public Type(int d, int r, int c) {
			this.d = d;
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		int num, d;
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				num = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken())-1;
				map[i][j] = num; // ����� ��ȣ�� ������ map
				sharkMap.put(num, new Type(d, i, j));
			}
		}
		
		int start = map[0][0];
		shark = new Type(sharkMap.get(start).d, 0, 0); // ��� ��ȣ -1
		map[0][0] = -1;
		sharkMap.remove(start);
		max += start;
		
		
	}
}
