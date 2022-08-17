// ǥ���� (���α׷��ӽ� LEVEL3)
package programmers;

import java.util.*;

public class EditTable {
	public static void main(String args[]) {
		EditTable e = new EditTable();
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
		
		System.out.println(e.solution(n, k, cmd));
	}
	public String solution(int n, int k, String[] cmd) {
		int currIdx = k; // ���� ��ġ
        int lastIdx = n-1; // ������ �� �ε���
        Stack<Integer> deleteStack = new Stack<>();
        
        String command;
        int x = 0;
        
        for(int i=0; i<cmd.length; i++) {
        	String[] strArr = cmd[i].split(" ");
        	command = strArr[0];
        	
        	if("U".equals(command)) {
        		x = Integer.parseInt(strArr[1]);
        		currIdx -= x;
        	} else if("D".equals(command)) {
        		x = Integer.parseInt(strArr[1]);
        		currIdx += x;
        	} else if("C".equals(command)) {
        		deleteStack.add(currIdx);
        		
        		if(currIdx == lastIdx) currIdx -= 1;
                lastIdx -= 1;
        	} else {
        		if(deleteStack.pop() <= currIdx) currIdx += 1;
        		lastIdx += 1;
        	}
        }
        
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<=lastIdx; i++) {
        	sb.append("O");
        }
        while(!deleteStack.isEmpty()) {
        	sb.insert(deleteStack.pop(), "X");
        }
        		
		return sb.toString();
	}
}
