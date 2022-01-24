// 종이의 개수 (백준 1780번)

/* [ 알고리즘 ]
 * 
 * 1. 완전탐색으로 종이가 모두 같은 수로 되어있는지 체크
 * 2. 같은 수로 되어있으면 각각 -1, 0, 1 로만 이루어진 종이의 개수를 카운트해줌
 * 3. 만약, 같은 수로 되어있지 않다면 3^k*1, 3^k*2, 3^k*3 으로 (가로, 세로) 분할
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780 {
	static int zeroCount = 0;  // 0
	static int minusCount = 0; // -1
	static int plusCount = 0;  // 1
	static int[][] map;
	public static void main(String args[]) throws IOException{
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
		
		System.out.print(minusCount+"\n"+zeroCount+"\n"+plusCount);
 	}
	
	// 시작점, 가로끝, 세로끝
	public static void partition(int w, int l, int size) {

		if(check(w, l, size)) {
			switch(map[l][w]) {
				case 0: zeroCount++; break;
				case -1: minusCount++; break;
				case 1: plusCount++;  break;
				default: break;
			}
		} else {
			/*  
			 *  A B C
			 *  D E F
			 *  G H I
			 */
			int resize = size/3;
			
			// A
			partition(w, l, resize);
			// B
			partition(w+resize, l, resize);
			// C
			partition(w+resize*2, l, resize);
			// D
			partition(w, l+resize, resize);
			// E
			partition(w+resize, l+resize, resize);
			// F
			partition(w+resize*2, l+resize, resize);
			// G
			partition(w, l+resize*2, resize);
			// H
			partition(w+resize, l+resize*2, resize);
			// I
			partition(w+resize*2, l+resize*2, resize);
		}	
		return;
	}
	public static boolean check(int startWidth, int startLength, int size) {
		int color = map[startLength][startWidth];
		for(int i=startLength; i<startLength+size; i++) {
			for(int j=startWidth; j<startWidth+size; j++) {
				if(map[i][j]!=color) return false;
			}
		}
		return true;
	}
}
