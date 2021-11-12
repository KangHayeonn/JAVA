// 도서관 (백준 1461번)

/* [ 알고리즘 ]
 * 
 * 1. 0보다 작은거 A 우선순위 큐에 저장 , 0보다 큰 값 B 스택에 저장  (A일 경우 절댓값이 큰게 앞부분에 있고 B의 경우 뒤에 있기 때문)
 * 2. A의 경우 최소값을 빼주면서 배열에 저장, B의 경우 오름차순으로 정렬 후 제일 뒤에 있는 값을 제거
 * 3. 들어온 갯수 만큼 빼주고 제일 앞 값만 새로운 Distance 배열에 저장
 * 4. A와 B 에 더이상 값이 없으면 탈출 혹은 중간에 끊기면 젤 앞값만 저장 후 탈출
 * 5. Distance 배열에서 제일 큰 수를 제외하고 모든 원소*2 해서 더해줌
 * 6. 마지막에 제일 큰 수 또한 더해준 뒤 그때의 합을 출력
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q1461 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		//Queue<Integer> A = new LinkedList<>();
		PriorityQueue<Integer> A = new PriorityQueue<>(); // 오름차순으로 정렬 (루트 노드가 제일 작은 수)
		Stack<Integer> B = new Stack<>();
		
		while(N-->0) {
			int a = Integer.parseInt(st.nextToken());
			
			if(a<0) A.add(a);
			else B.push(a);
		}
		
		Collections.sort(B); // 오름차순으로 정렬
		
		ArrayList<Integer> Distance = new ArrayList<>();
		
		int check = 0;
		while(!A.isEmpty()) {
			if(check==0) Distance.add((-1)*A.poll());
			else A.poll(); 
			check++; 
			if(check==M) check = 0;
		}
		
		check = 0;
		while(!B.isEmpty()) {
			if(check==0) Distance.add(B.pop());
			else B.pop(); 
			check++; 
			if(check==M) check = 0;
		}
		
		Collections.sort(Distance);
		
		int answer = 0;
		
		for(int i =0; i < Distance.size() ; i++) {
			if(i==Distance.size()-1) {
				answer += Distance.get(i);
				break;
			}
			
			answer += 2*Distance.get(i);
		}
		
		System.out.print(answer);
		
	}
}
