// 스타트와 링크

// 3명이 팀일때는
// (1,2) + (2,1) + (1,3) + (3,1) + (2,3) + (3,2) 이렇게 팀별 능력치의 합을 구해야함
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import java.util.StringTokenizer;

public class Q14889 {
	
	static int min = Integer.MAX_VALUE;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int[][] arr = new int[count][count];
		for(int i=0; i<count; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int j=0; j<count; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean visited[] = new boolean[count];
		combi(arr, visited, 0, count, 0);
		
		System.out.println(min);
	}
	
	public static void combi(int[][] arr, boolean visited[], int idx, int n, int count) {
		if(count == n/2) {
			//System.out.println(Arrays.toString(visited));
			abilityDistance(arr, visited, n);
			return;
		}
		
		for(int i=idx; i<n; i++) {
			visited[i] = true;
			combi(arr, visited, i + 1, n, count+1);
			visited[i] = false;
		}
	}
	
	public static void abilityDistance(int[][] arr, boolean visited[], int n) {
		int start = 0;
		int link = 0;
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				// 스타트팀
				if (visited[i] == true && visited[j] == true) {
					start += arr[i][j];
					start += arr[j][i];
				}
				// 링크팀
				else if (visited[i] == false && visited[j] == false) {
					link += arr[i][j];
					link += arr[j][i];
				}
			}
		}
	
		int val = Math.abs(start - link); // 두 팀의 점수 차이 (절댓값)
		
		if (val == 0) {
			System.out.println(val);
			System.exit(0); //종료
		}
		
		min = Math.min(val, min);
	}
}
