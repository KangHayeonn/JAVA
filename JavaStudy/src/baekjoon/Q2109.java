// 순회 공연 (백준 2109번)

// 단순하게 순서대로 탐색해서 자리에 매치 시켜줌 ( 하지만 완전탐색이라 시간이 오래걸림)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Q2109 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // n 개의 대학에서 강연 요청
		StringTokenizer st = new StringTokenizer("");
		int[][] list = new int[n][2];
		int Max = 0;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()); // 강연료
			int d = Integer.parseInt(st.nextToken()); // 기한
			list[i][0] = p;
			list[i][1] = d;
			Max = Math.max(Max, list[i][1]);
		}
		
		Arrays.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] x, int[] y) {
				if(x[0]==y[0]) return y[1]-x[1];
				else return y[0]-x[0]; // 강연료에 따라 오름차순
			}
		});
		
		boolean[] isVisited = new boolean[Max];
		int answer = 0;
		
		for(int i=0; i<list.length; i++) {
			int len = list[i][1]-1;
			for(int j=len; j>=0; j--) {
				if(!isVisited[j]) {
					isVisited[j] = true;
					answer += list[i][0];
					break;
				}
			}
		}
		
		System.out.print(answer);
	}
}
