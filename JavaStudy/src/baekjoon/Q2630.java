// ������ ����� (���� 2630��)

/* [ �˰��� ]
 * 
 * 1. ����Ž������ ���̰� ��� ���� ���� �Ǿ��ִ��� üũ
 * 2. ���� ���� �Ǿ������� ���� blue, white �θ� �̷���� ������ ������ ī��Ʈ����
 * 3. ����, ���� ���� �Ǿ����� �ʴٸ� 4���� ���� (N/2)
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2630 {
	static int[][] map;
	static int white = 0; // �Ͼ������ ĥ���� ĭ 0�� �־���
	static int blue = 0;  // �Ķ������� ĥ���� ĭ 1�� �־���
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer("");
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		partition(0, 0, N);
		
		System.out.print(white + "\n" + blue);
	}
	public static void partition(int w, int l, int size) {
		
		if(check(w, l, size)) {
			switch(map[l][w]) {
				case 0 : white++; break;
				case 1 : blue++; break;
				default: break;
			}
			
		} else {
			
			/*
			 * A B
			 * C D
			 */

			int resize = size/2;
			
			// A
			partition(w, l, resize);
			// B
			partition(w+resize, l, resize);
			// C
			partition(w, l+resize, resize);
			// D
			partition(w+resize, l+resize, resize);
		}
		return;
	}
	public static boolean check(int w, int l, int size) {
		int color = map[l][w];
		
		for(int i=l; i<l+size; i++) {
			for(int j=w; j<w+size; j++) {
				if(map[i][j] != color) return false;
			}
		}
		return true;
	}
}
