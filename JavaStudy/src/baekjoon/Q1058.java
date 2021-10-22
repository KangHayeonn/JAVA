// 친구 (백준 1058번)

// 둘이 양방향으로 칭구거나, 내 칭구의 친구 (ex, A<->B<->C : A와 C는 칭구)
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Q1058 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char Friends[][] = new char[N][N];
		Set<Integer> set = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<str.length(); j++) {
				Friends[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i<N; i++) {
			Set<Integer> count = new HashSet<>();
			for(int j=0; j<N; j++) {
				if(Friends[i][j]=='Y') {
					count.add(j);
					for(int k=0; k<N; k++) {
						if(Friends[j][k]=='Y') {
							count.add(k);
						}
					}
				}
			}
			if(count.size()==0) set.add(0);
			else set.add(count.size()-1); // 자기 자신 하나 빼기
		}
		System.out.println(Collections.max(set));
	}
}
