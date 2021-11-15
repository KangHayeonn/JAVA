// 숨바꼭질 (백준 1697번)

/* [ 알고리즘 ]
 * 
 * 1. X<K , X==K, X>K 일 경우로 나눠서 풀어줌
 * 2. X<K 일 경우 X+1, X-1, X*2 에 따라서 각각 해당 위치에 왔던 경우를 check하며 for문을 돔 (BFS, 큐 이용)
 * 3. X==K 일 경우, 0 출력
 * 4. X>K 일 경우 X-K를 출력
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1697 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		int K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		
		if(X<K) FIND(X, K); // BFS이용 , 큐이용
		else if(X>K) {
			System.out.print(X-K);
		}
		else {
			System.out.print(0);
		}
	}
	
	public static void FIND(int X, int K) {
		Queue<Integer> q = new LinkedList<>();
		int[] check = new int[100001];
		
		q.add(X);

		while(!q.isEmpty()) {
			int now = q.poll(); // 현재위치
			for(int i=0; i<3; i++) {
				int next = 0;
				switch(i) {
					case 0: 
						next = now+1;
						break;
					case 1: 
						next = now-1;
						break;
					case 2: 
						next = now*2;
						break;
				}
				if(next >= 0 && next < check.length && check[next] ==0) {
					q.add(next);
					check[next] = check[now] +1;
				}
                
                if(next == K) {
					System.out.println(check[next]);
					return;
				}
			}
		}
	}
}