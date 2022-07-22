// �������� (���� Q9077)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q9077 {
	static int x, y;
	static int[][] map;
	public static class Type {
		int x, y;
		public Type(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer("");
		ArrayList<Type> arr;
		ArrayList<Type> reset; // ������ ��ġ
		map = new int[10001][10001];
		int N;
		
		while(T-- > 0) {
			N = Integer.parseInt(br.readLine()); // ���� ����
			arr = new ArrayList<>();
			reset = new ArrayList<>();
			// map = new int[10001][10001]; // �޸� �ʰ�?
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken()); // ���� x-��ǥ
				y = Integer.parseInt(st.nextToken()); // ���� y-��ǥ
				
				arr.add(new Type(x, y));
				map[y][x] = 1;
				reset.add(new Type(x, y));
			}
			
			int max = 0;
			for(Type t : arr) {
				int n = check(t.x, t.y);
				if(max < n) max = n;
			}
			
			int count = lastCheck();
			if(max < count) max = count;
			
			for(Type t : reset) {
				map[t.y][t.x] = 0;
			}
			
			sb.append(max).append("\n");
		}
		System.out.println(sb);
	}
	public static int check(int x, int y) {
		int[] result = new int[4];
		
		// ���簢���� '���� ���'�� �������� ���ڰ� ��ġ�� ���
		for(int r=y; r<=y+10; r++) {
			for(int c=x; c<=x+10; c++) {
				if(r > 10000 || c > 10000) continue;
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		// ���簢���� '���� �ϴ�'�� �������� ���ڰ� ��ġ�� ���
		for(int r=y; r>=y-10; r--) {
			for(int c=x; c<=x+10; c++) {
				if(r < 0 || c > 10000) continue;
				if(map[r][c] == 1) result[1] += 1;
			}
		}
		// ���簢���� '���� ���'�� �������� ���ڰ� ��ġ�� ���
		for(int r=y; r<=y+10; r++) {
			for(int c=x; c>=x-10; c--) {
				if(r > 10000 || c < 0) continue;
				if(map[r][c] == 1) result[2] += 1;
			}
		}
		// ���簢���� '���� �ϴ�'�� �������� ���ڰ� ��ġ�� ���
		for(int r=y; r>=y-10; r--) {
			for(int c=x; c>=x-10; c--) {
				if(r < 0 || c < 0) continue;
				if(map[r][c] == 1) result[3] += 1;
			}
		}
		
		Arrays.sort(result); // �ϳ��� ���ڸ� �������� ���簢���� ����� ��� �ִ밪�� ����
		
		return result[3];
	}
	// 0�� 10000 �κп� �������� ��ġ�� ����� ����ó��
	public static int lastCheck() {
		int[] result = new int[4];
		
		for(int r=0; r<=10; r++) {
			for(int c=0; c<=10; c++) {
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		for(int r=9990; r<=10000; r++) {
			for(int c=9990; c<=10000; c++) {
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		for(int r=0; r<=10; r++) {
			for(int c=9990; c<=10000; c++) {
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		for(int r=9990; r<=10000; r++) {
			for(int c=0; c<=10; c++) {
				if(map[r][c] == 1) result[0] += 1;
			}
		}
		
		Arrays.sort(result);
		
		return result[3];
	}
	/* �ð��ʰ�
	public static int delete(int r, int c) {
		for(Type t : map) {
			if(t.x == c && t.y == r) return 1;
			else continue;
		}
		return 0;
	}*/
}
