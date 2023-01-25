package Algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IndexTree_2 {
	static int[] tree;
	public static void main(String args[]) throws IOException {
		/*
		 * 8
		 * 3 7 1 5 2 4 1 11
		 */
		System.setIn(new FileInputStream("input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int tempN = getTempN(N);
		tree = new int[tempN * 2];

		int n, idx;
		
		for(int i=0; i<N; i++) {
			idx = tempN + i;
			n = arr[i];
			
			while(idx > 0) {
				tree[idx] += n;
				idx /= 2;
			}
			
		}
		
		System.out.println(getSum(2, 3, 0, tempN-1, 1));
		
		System.out.println(Arrays.toString(tree));
		getDiff(tempN, 3, -10);
		System.out.println(Arrays.toString(tree));
	}
	public static int getTempN(int n) {
		int x = 0;
		int sum = 1;
		while(sum < n) {
			sum *= 2;
			x += 1;
		}
		
		return sum;
	}
	public static int getSum(int left, int right, int s_left, int s_right, int currIdx) {
		if(left <= s_left && right >= s_right) { // 범위 주의!
			return tree[currIdx];
		} else if(right < s_left || left > s_right) {
			return 0;
		} else {
			int idx = (s_left+s_right)/2;
			return getSum(left, right, s_left, idx, currIdx*2) + getSum(left, right, idx+1, s_right, currIdx*2+1);
		}
	}
	public static void getDiff(int tempN, int idx, int value) {
		int n = idx + tempN;
		
		while(n > 0) {
			tree[n] += value;
			n /= 2;
		}
	}
}
