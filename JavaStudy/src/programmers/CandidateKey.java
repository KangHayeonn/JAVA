// 후보키 (프로그래머스 LEVEL2 - Kakao)

package programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class CandidateKey {
	static boolean[] check;
	static int[] result;
	static String[][] relation2;
	static int answer;
	static Set<Integer> setCheck;
	
	public static void main(String args[]) {
		// String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","3"}};
		String[][] relation = {{"a","1","aaa","c","ng"},{"b","1","bbb","c","g"},{"c","1","aaa","d","ng"},{"d","2","bbb","d","ng"}};
		
		System.out.println(solution(relation));
	}
	public static int solution(String[][] relation) {
		int len = relation[0].length;
		
		check = new boolean[len];
		int[] numArr = new int[len];
		relation2 = new String[relation.length][len];
		answer = 0;
		
		for(int i=1; i<=len; i++) {
			numArr[i-1] = i;
		}
		
		// 깊은 복사
		for(int i=0; i<relation.length; i++) {
			for(int j=0; j<relation[0].length; j++) {
				relation2[i][j] = relation[i][j];
			}
		}
		setCheck = new HashSet<>();
		
		for(int i=1; i<=len; i++) {
			boolean[] visited = new boolean[len];
			combination(numArr, visited, 0, len, i);
			// System.out.println("왜 안들어옴?");
			
			Iterator<Integer> iter = setCheck.iterator();
			
			while(iter.hasNext()) {
				int num = iter.next();
				check[num] = true;
				// System.out.println("-> " + num);
			}
			setCheck = new HashSet<>();
		}

		return answer;
	}
	
	// 조합
	public static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
		if(r == 0) {
	        candidateKeyCheck(arr, visited, n);
	        return;
	    }
		

	    for(int i=start; i<n; i++) {
	    	if(!check[i]) {
	    		visited[i] = true;
	    		combination(arr, visited, i + 1, n, r - 1);
	    		visited[i] = false;
	    	}
	    }
	}
	
	// 후보키 체크
    static void candidateKeyCheck(int[] arr, boolean[] visited, int n) {
    	Set<String> set = new HashSet<>();
    	Stack<Integer> stack = new Stack<>();
	
    	for(int j=0; j<relation2.length; j++) {
    		String str = "";
    		// System.out.println(Arrays.toString(check));
        	for (int i = 0; i < n; i++) {
                if (visited[i] && !check[i]) {
                	// System.out.println("i : " + i);
                	stack.add(i);
                	str += relation2[j][i];
                }
            }
        	set.add(str);
        }
    	
    	if(set.size() == relation2.length) {
    		answer++;
    		while(!stack.isEmpty()) {
    			int num = stack.pop();
    			setCheck.add(num);
    			// System.out.print(num +" ");
    			// check[num] = true;
    		}
    	}
    }
}
