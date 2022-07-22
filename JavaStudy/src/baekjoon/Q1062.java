// 가르침 (백준 골드4)
package baekjoon;

import java.io.*;
import java.util.*;

public class Q1062 {
	static int N, K;
	static int max = 0;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		String[] word = new String[N];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			s = s.replace("anta", "");
			s = s.replace("tica", "");
			word[i] = s;
		}
		
		if(K < 5) {
			System.out.println(0);
			return;
		} else if(K == 26) {
			System.out.println(N);
			return;
		} else {
			boolean[] isVisited = new boolean[26];
			isVisited['a'-'a'] = true;
			isVisited['n'-'a'] = true;
			isVisited['t'-'a'] = true;
			isVisited['i'-'a'] = true;
			isVisited['c'-'a'] = true;
	
			dfs(word, isVisited, 0, 0);
		}
		
		System.out.println(max);
	}
	public static void dfs(String[] word, boolean[] isVisited, int start, int len) {
		if(K-5 == len) {
			int count = 0;
			for(int i=0; i<N; i++) {
				boolean check = true;
				for(int j=0; j<word[i].length(); j++) {
					char c = word[i].charAt(j);
					int idx = c - 'a';
					if(!isVisited[idx]) {
						check = false;
						break;
					}
				}
				if(check) count++;
			}
			max = Math.max(max, count);
			return;
		}
		
		/*
		for(int i=0; i<N; i++) {
			for(int j=0; j<word[i].length(); j++) {
				char c = word[i].charAt(j);
				int idx = c - 'a';
				if(!isVisited[idx]) {
					isVisited[idx] = true;
					dfs(word, isVisited, len+1);
					isVisited[idx] = false;
				}
			}
		} */
		for(int i=start; i<26; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				dfs(word, isVisited, i+1, len+1);
				isVisited[i] = false;
			}
		}
	}
}
