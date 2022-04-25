// 나무 재테크 (백준 골드4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q16235 {
	static int N, M, K;
	static int[][] A; // A[r][c] 기본 양분 배열
	static int[][] nutriment; // 영양분
	static PriorityQueue<Type> treeQ;
	static Queue<Type> deleteQ;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	public static class Type implements Comparable<Type>{
		int r, c;
		int age;
		public Type(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
		
		@Override
		public int compareTo(Type t) {
			/* ver1
			if(this.r == t.r && this.c == t.c) return this.age - t.age;
			else if(this.r == t.r) return this.c - t.c; 
			// c의 정렬기준이 없으면 내부로직에서 꼬여서 age의 조건문 기준이 먹히지 않음
			// c에서 우선순위가 없게 됨
			else return this.r - t.r;
			*/
			
			// ver2
			return this.age - t.age;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		treeQ = new PriorityQueue<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			treeQ.offer(new Type(x-1, y-1, z));
		}
		
		nutriment = new int[N][N];
		for(int i=0; i<N; i++) {
			Arrays.fill(nutriment[i], 5);
		}
		
		while(K-- > 0) {
			deleteQ = new LinkedList<>();
			Spring();
			Summer();
			Fall();
			Winter();
		}

		System.out.println(treeQ.size());
	}
	public static void Spring() {
		Queue<Type> tempQ = new LinkedList<>();
		while(!treeQ.isEmpty()) {
			Type t = treeQ.poll();
			int r = t.r;
			int c = t.c;
			int treeAge = t.age;
			if(nutriment[r][c] - treeAge < 0) {
				deleteQ.offer(new Type(r, c, treeAge));
			} else {
				nutriment[r][c] -= treeAge;
				treeAge++;
				tempQ.offer(new Type(r, c, treeAge));
			}
		}
		
		while(!tempQ.isEmpty()) {
			treeQ.offer(tempQ.poll());
		}
		// 나무가 자신의 나이만큼 양분을 먹음
		// 나무의 나이가 1증가
		// 하나의 칸에 여러개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
		// 만약, 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
	}
	public static void Summer() {
		while(!deleteQ.isEmpty()) {
			Type t = deleteQ.poll();
			int n = t.age/2;
			nutriment[t.r][t.c] += n;
		}
		// 봄에 죽은 나무가 양분으로 변하게 됨
		// 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가됨 (소수점 아래는 버림)
	}
	public static void Fall() {
		Queue<Type> tempQ = new LinkedList<>();
		while(!treeQ.isEmpty()) {
			Type t = treeQ.poll();
			int treeAge = t.age;
			int r = t.r;
			int c = t.c;
			if(treeAge % 5 == 0) {
				tempQ.offer(new Type(r, c, treeAge));
				
				// 번식 (인접한 8개의 칸)
				for(int i=0; i<8; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if(nr < 0 || nc < 0 || nr >=N || nc >= N) continue;
					
					tempQ.offer(new Type(nr, nc, 1));	
				}
			} else {
				tempQ.offer(new Type(r, c, treeAge));
			}
		}
		
		while(!tempQ.isEmpty()) {
			treeQ.offer(tempQ.poll());
		}
		// 나무가 번식함
		// 번식하는 나무는 나이가 5의 배수이어야 함
		// 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 어떤 칸 (r,c)와 인접한 칸은 ~
		// 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
	}
	public static void Winter() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				nutriment[i][j] += A[i][j];
			}
		}
		// S2D2가 땅을 돌아다니면서 땅에 양분을 추가
		// 각 칸에 추가되는 양분의 양은 A[r][c]임
	}
}
