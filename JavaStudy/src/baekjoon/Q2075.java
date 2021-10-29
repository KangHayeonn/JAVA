// N번째 큰 수(백준 2075번)

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2075 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//Integer arr[] = new Integer[N*N];
		int arr[] = new int[N*N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[5*i+j] = Integer.parseInt(st.nextToken());
				System.out.println(5*i+j+" : " +arr[5*i+j]);
			}
		}
		//Arrays.sort(arr, (a,b)-> b-a);
		//System.out.print(Arrays.toString(arr));
		//System.out.print(arr[N-1]);
		//Arrays.sort(arr, (a,b)-> b-a);
		//System.out.print(Arrays.toString(arr));
		Arrays.sort(arr);
		System.out.print(arr[N*N-N]);
	}
}
