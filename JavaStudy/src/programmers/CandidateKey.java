// 후보키 (프로그래머스 LEVEL2 - Kakao)

/* [ 알고리즘 ]
 * 
 * 1. 전체 조합에서 유일성을 만족하는 애들을 기준으로 map을 설정 (key: 속성 인덱스, value: true/false) - 유일성 만족하면 true
 * 2. 유일성을 만족하는 key 를 기준으로 조합을 구해 최소성을 체크
 * 3. 2번을 위해 key별로 그에 해당하는 조합값(key)이 이미 true이면 해당 부분은 최소성을 만족하지 않으므로 false로 변경
 * 4. 이후 map에서 value가 true인 key값을 count해서 answer로 출력 (유일성 & 최소성 모두 만족) 
 * 
 */

package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CandidateKey {
	static String[][] relation2;
	static Map<String, Boolean> map;
	
	public static void main(String args[]) {
		// String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","3"}};
		String[][] relation = {{"a","1","aaa","c","ng"},{"b","1","bbb","c","g"},{"c","1","aaa","d","ng"},{"d","2","bbb","d","ng"}};
		
		System.out.println(solution(relation));
	}
	public static int solution(String[][] relation) {
		int len = relation[0].length;
		int answer = 0;
		
		int[] numArr = new int[len];
		relation2 = new String[relation.length][len];

		map = new HashMap<>();
		
		long before = System.currentTimeMillis(); //코드 실행 전 시간
		
		// Garbage Collection으로 메모리 초기화
        System.gc();
		
		// 실행전 메모리 사용량 조회
        long before2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Used Memory : " + before);
		
		for(int i=1; i<=len; i++) {
			numArr[i-1] = i;
		}
		
		// 깊은 복사
		for(int i=0; i<relation.length; i++) {
			for(int j=0; j<relation[0].length; j++) {
				relation2[i][j] = relation[i][j];
			}
		}
		
		for(int i=1; i<=len; i++) {
			boolean[] visited = new boolean[len];
			combi(numArr, visited, 0, len, i);	
		}
		
		for(String key : map.keySet()) {
			if(map.get(key)) {
				for(int i=1; i<key.length(); i++) {
					boolean[] visited = new boolean[len];
					combination(key, visited, 0, key.length(), i);
				}
			}
		}
		
		for(String key : map.keySet()) {
			if(map.get(key)) {
				answer++;
			}
		}
		
		long after = System.currentTimeMillis(); // 코드 실행 후 시간
        long diff = (after - before); //두 시간에 차이
        System.out.println("시간차이(밀리세컨즈) : "+diff);
        
        // Garbage Collection으로 메모리 정리
        System.gc();
        
        // 실행 후 메모리 사용량 조회
        long after2  = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        
        // 메모리 사용량 측정
        long usedMemory = (before2 - after2)/1024;
        
        System.out.println("Used Memory(MB): " + usedMemory);

		return answer;
	}
	
	// 조합
	public static void combi(int[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
			String s = "";
			for(int i=0; i<n; i++) {
	    		if (visited[i]) {
	    			s += i;
	    		}
	    	}
	    	map.put(s, false);
	    	candidateKeyCheck(arr, visited, n);
	        return;
	    }
		

	    for(int i=start; i<n; i++) {
    		visited[i] = true;
    		combi(arr, visited, i + 1, n, r - 1);
    		visited[i] = false;
	    }
	}
	
	// 문자열 조합
	public static void combination(String str, boolean[] visited, int start, int n, int r) {
		if(r==0) {
			String s = "";
			for (int i = 0; i < n; i++) {
	            if (visited[i]) {
	                s += str.charAt(i);
	            }
	        }
			if(map.get(s)) { // 최소성
				map.put(str, false);
				return;
			}
			return;
		}
		
		for(int i=start; i<n; i++) {
			visited[i] = true;
			combination(str, visited, i+1, n, r-1);
			visited[i] = false;
		}
	}
	
	// 후보키 체크
    static void candidateKeyCheck(int[] arr, boolean[] visited, int n) {
    	Set<String> set = new HashSet<>();
    	Queue<Integer> q = new LinkedList<>();
    	
    	for (int i = 0; i < n; i++) {
            if (visited[i]) {
            	q.add(i);
            }
        }
    	
    	for(int j=0; j<relation2.length; j++) {
    		String str = "";
        	for (int i = 0; i < n; i++) {
                if (visited[i] && !map.get(Integer.toString(i))) {
                	str += relation2[j][i];
                }
            }
        	set.add(str);
        }
    	
    	if(set.size() == relation2.length) { // 유일성
    		String key = "";
    		
    		while(!q.isEmpty()) {
    			int num = q.poll();
    			key += num;
    		}
    		
    		map.put(key, true);
    	}
    }
}
