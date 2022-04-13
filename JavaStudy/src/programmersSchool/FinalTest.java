// programmers school Final Test Q2

package programmersSchool;

import java.util.*;

public class FinalTest {
	public static void main(String[] args) {
		String[] s1 = {"A", "B", "C", "F", "E", "D", "H", "H"};
		String[] s2 = {"G", "G", "B", "B", "C", "F", "F", "E"};
		System.out.println(Arrays.toString(solution(s1, s2, "G")));
	}
	public static class Type {
        private String alpa;
        private int level;
        
        public Type(String alpa, int level) {
            this.alpa = alpa;
            this.level = level;
        }
    }
	public static class Type2 implements Comparable<Type2>{
        private String alpa;
        private int level;
        
        public Type2(String alpa, int level) {
            this.alpa = alpa;
            this.level = level;
        }
        
        @Override
        public int compareTo(Type2 t) {
        	if(this.level == t.level) {
        		return (this.alpa).compareTo(t.alpa);
        	} else {
        		return t.level - this.level;
        	}
        }
    }
    public static String[] solution(String[] s1, String[] s2, String k) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        ArrayList<String> arr;

        for(int i=0; i<s1.length; i++) {
            String from = s2[i];
            String to = s1[i];

            if(!map.containsKey(from)) {
                arr = new ArrayList<String>();
                arr.add(to);
                map.put(from, arr);
            } else {
                map.get(from).add(to);
            }
        }

        Stack<Type> stack = new Stack<>();
        stack.push(new Type(k, 0));
        for(String s : map.get(k)) {
            stack.push(new Type(s, 1));
        }

        Map<String, Integer> map2 = new HashMap<>();
        Map<String, Integer> resultMap = new HashMap<>();
        Set<String> set = new HashSet<>();
        while(!stack.isEmpty()) {
            Type t = stack.peek();
            if(map.containsKey(t.alpa)) {
                stack.pop();
                for(String s : map.get(t.alpa)) {
                    if(!map2.containsKey(s)) {
                        map2.put(s, t.level+1);
                        stack.push(new Type(s, t.level+1));
                    } else{
                        map2.put(s, Math.min(map2.get(s), t.level+1));
                    }
                }
            } else {
            	set.add(t.alpa);
                stack.pop();
            }
        }

        for(String s : set) {
        	resultMap.put(s, 10000);
        }

        for(String s : map2.keySet()) {
            if(!resultMap.containsKey(s)) resultMap.put(s, map2.get(s));
        }
        
        PriorityQueue<Type2> pq = new PriorityQueue<>();
        
        for(String s3 : resultMap.keySet()) {
        	pq.add(new Type2(s3, resultMap.get(s3)));
        } 
        
        String[] answer = new String[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
        	answer[idx++] = pq.poll().alpa;
        }

        return answer;
    }
}
