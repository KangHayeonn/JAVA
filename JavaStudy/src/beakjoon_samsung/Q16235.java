// ���� ����ũ (���� ���4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q16235 {
	static int N, M, K;
	static int[][] A; // A[r][c] �⺻ ��� �迭
	static int[][] nutriment; // �����
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
			// c�� ���ı����� ������ ���η������� ������ age�� ���ǹ� ������ ������ ����
			// c���� �켱������ ���� ��
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
		// ������ �ڽ��� ���̸�ŭ ����� ����
		// ������ ���̰� 1����
		// �ϳ��� ĭ�� �������� ������ �ִٸ�, ���̰� � �������� ����� �Դ´�.
		// ����, ����� ������ �ڽ��� ���̸�ŭ ����� ���� �� ���� ������ ����� ���� ���ϰ� ��� �״´�.
	}
	public static void Summer() {
		while(!deleteQ.isEmpty()) {
			Type t = deleteQ.poll();
			int n = t.age/2;
			nutriment[t.r][t.c] += n;
		}
		// ���� ���� ������ ������� ���ϰ� ��
		// ������ ���� �������� ���̸� 2�� ���� ���� ������ �ִ� ĭ�� ������� �߰��� (�Ҽ��� �Ʒ��� ����)
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
				
				// ���� (������ 8���� ĭ)
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
		// ������ ������
		// �����ϴ� ������ ���̰� 5�� ����̾�� ��
		// ������ 8���� ĭ�� ���̰� 1�� ������ �����. � ĭ (r,c)�� ������ ĭ�� ~
		// ���� ���� ����� ĭ���� ������ ������ �ʴ´�.
	}
	public static void Winter() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				nutriment[i][j] += A[i][j];
			}
		}
		// S2D2�� ���� ���ƴٴϸ鼭 ���� ����� �߰�
		// �� ĭ�� �߰��Ǵ� ����� ���� A[r][c]��
	}
}
