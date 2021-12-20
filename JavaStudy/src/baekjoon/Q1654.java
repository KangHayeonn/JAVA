// 랜선 자르기 (백준 1654번)

// 제일 큰값과 제일 작은 값을 기준으로 이분탐색 -> mid : (제일 큰값 + 제일 작은 값) / 2
// 만약 해당 값을 나머지 원소들에 나눴을 때 몫의 합이 N보다 작으면 최댓값을 줄여줌
// 만약 해당 값을 나머지 원소들에 나눴을 때 몫의 합이 N보다 클 경우 최솟값을 늘려줌
// 만약 N값과 같아지는 경우가 생기면 그 때의 해당 값을 출력

package baekjoon;
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1654 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Integer[] arr = new Integer[K];
		
		long max = 1;
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > max ) max = arr[i];
		}
		
		long min = 1;
		long mid = 0;
		int count = 0;
		
		* 경우의 수 
		 * 
		 * case 1 : while(min <= max)  ->  else min = mid +1   -> 출력 max   (정답)
		 * case 2 : while(min < max)   ->  else min = mid      -> 출력 min   (실패)
		 * case 3 : while(min < max)   ->  else min = mid+1    -> 출력 min-1 (실패)
		 *
		
		while(min <= max) {   // 왜 < 이건 틀리고 <= 이건 맞는 걸까? (해당 조건 주의)
			mid = (min+max)/2;
			
			count = 0;
			
			for(int i=0; i<K; i++) {
				count += arr[i]/mid;
			}
			
			if(count < N) max = mid-1;
			else min = mid+1;
			
		}
		System.out.print(max);
		//System.out.println(Arrays.toString(arr));
		
	}
	
	 잘못된 코드
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
}*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1654 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Integer[] arr = new Integer[K];
		
		long max = 1;
		
		for(int i=0; i<K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(arr[i] > max ) max = arr[i];
		}
		
		max++; // 나눗셈에서 뭉개질 수 있으므로
		
		long min = 1;
		long mid = 0;
		int count = 0;
        long answer = 1;
		
		while(min < max) {
			mid = (min+max)/2;
			
			count = 0;
			
			for(int i=0; i<K; i++) {
				count += arr[i]/mid;
			}
			
			if(count < N) max = mid;
			else {
                min = mid+1;
                answer = mid;
            }
		}
		System.out.print(answer);
	}
}

