// 랜선 자르기 (백준 1654번)

// 내림차순으로 정렬
// 제일 큰값을 이분탐색 -> mid : 해당 값
// 만약 해당 값을 나머지 원소들에 나눴을 때 몫의 합이 N보다 작으면 한번더 이분탐색
// 만약 해당 값을 나머지 원소들에 나눴을 때 몫의 합이 N보다 클 경우 해당 값을 +1
// 만약 N값과 같아지는 경우가 생기면 그 때의 해당 값을 출력

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1654 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Integer[] arr = new Integer[K];
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr, (a,b) -> b-a); // 내림차순 (Comparator로 비교하고 싶을 땐 int 대신 Integer 사용해야함)
		
		int answer = cutting(arr, K, N);
		
		System.out.print(answer);
		//System.out.println(Arrays.toString(arr));
	}
	
	public static int cutting(Integer[] arr, int K, int N) {
		int left = 0;
		int right = arr[0];
		boolean check = false;
		
		if(N==K) return arr[K-1];
		
		int mid = (left + right) /2;
		
		while(left <= right) {
			int sum = 0;
			for(int i=0; i<K; i++) {
				sum += arr[i] / mid;
			}
			
			if(sum < N) {
				if(check == true) return mid - 1;
				mid = (left + mid) / 2;
				continue;
			}
			else if(sum > N) {
				mid = mid + 1;
				check = true;
				continue;
			}
			else return mid;
		}
		
		return mid; 
	}
}
