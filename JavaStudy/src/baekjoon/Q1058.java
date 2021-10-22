// ģ�� (���� 1058��)

// ���� ��������� Ī���ų�, �� Ī���� ģ�� (ex, A<->B<->C : A�� C�� Ī��)
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
			else set.add(count.size()-1); // �ڱ� �ڽ� �ϳ� ����
		}
		System.out.println(Collections.max(set));
	}
}
