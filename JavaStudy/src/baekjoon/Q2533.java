// 사회망 서비스(SNS)

/* DP? 메모이제이션?
 * 
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2533 {
	
	static ArrayList<Integer> arr[];
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 정점의 개수
		
		arr = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
		}
		
		// 트리 확인
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<arr[i].size(); j++) {
				System.out.print(i + " -> " +arr[i].get(j)+ " ");
			}
			System.out.print("\n");
		}
	}
}
