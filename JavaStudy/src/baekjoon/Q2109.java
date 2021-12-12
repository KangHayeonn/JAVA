// 순회 공연 (백준 2109번)

// 최소힙을 이용해 날짜가 큰거에서 작은 순으로 최소값을 체크해주면서 확인
// 만약 최대 일수가 2일 경우 힙크기는 2
// 이때 최소값보다 들어오는 값이 클경우 최소값을 삭제해주고 해당 값을 추가
// 마지막에 힙안에 있는 모든 수를 더해줌

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2109 {
	public static class type implements Comparable<type>{
		int pay;
		int date;
		public type(int pay, int date) {
			this.pay = pay;
			this.date = date;
		}
		@Override
		public int compareTo(type o1) {
			return o1.pay - this.pay;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // n 개의 대학에서 강연 요청
		StringTokenizer st = new StringTokenizer("");
		int[][] list = new int[n][2];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken()); // 강연료
			int d = Integer.parseInt(st.nextToken()); // 기한
			list[i][0] = p;
			list[i][1] = d;
		}
		
		Arrays.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] x, int[] y) {
				if(x[1]==y[1]) return y[0]-x[0];
				else return y[1]-x[1]; // 기한에 따라 오름차순
			}
		});
		
		PriorityQueue<type> pQ = new PriorityQueue<>();
		int answer = 0; // 이전 강의료
		
		int size = list[0][1]; // 최대 일수
		
		for(int i= size; i>0; i--) {
			System.out.println(i);
			for(int j=0; j<list.length && list[j][1] == i; j++) {
				System.out.println("tetst " + j);
				pQ.add(new type(list[j][0], list[j][1]));
				System.out.println("들어오나?" + list[j][0]);
			}
		}
		while(!pQ.isEmpty()) {
			type p = pQ.remove();
			
			System.out.println(p.pay + " " +p.date);
		}
		//System.out.println(answer);
	}
}
