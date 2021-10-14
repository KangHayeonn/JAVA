// 단어 정렬 (백준 1181번)

// 이차원배열, 정렬
// (1) 길이가 짧은 것부터 , (2) 길이가 같으면 사전 순으로
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Q1181 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		String[][] arr = new String[count][2];
		
		for(int i=0; i<count; i++) {
			String str = br.readLine();
			arr[i][0] = String.valueOf(str.length());
			arr[i][1] = str;
		}
	
		Arrays.sort(arr, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				int len1 = Integer.parseInt(o1[0]);
				int len2 = Integer.parseInt(o2[0]);
				
				if(len1<len2) return -1; // 음수값이 나오면 o1부터 출력
				else if(len1>len2) return 1; // 양수값이 나오면 o2부터 출력
				else return o1[1].compareTo(o2[1]); // o1[1]이 o2[1]보다 사전 순으로 더 앞에 있으면 먼저 출력
			}
			
		});
		
		String before = " ";
		
		for(int i=0; i<count; i++) {
			if(arr[i][1].compareTo(before)==0) {
				continue;
			}
			else System.out.println(arr[i][1]);
			
			before = arr[i][1];
		}
	}
}
