// ũ���� �����̱� ���� (���α׷��ӽ� LEVEL1)

// ver2 : ����
// solution �Լ��� ���� for�� ���� switch ���� ������ �ð� ���⵵�� ����
// �߰� �׽�Ʈ ���̽��� ����
// �˰��� ver1�� ����

package codingtest_9th_2nd;

import java.util.ArrayList;

public class Q3 {
	static public void main(String args[]) {
		Q3 s = new Q3();
		int[][] board = {{0,2,0},
						{1,2,0},
						{2,2,1}};
		int[] moves = {1,2,2,3};
		System.out.println(s.solution(board, moves));
		return;
	}
	
	public int solution(int[][] board, int[] moves) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
        int answer = 0;
   
        for(int i=0; i<moves.length; i++) {
    		for(int j=0; j< board.length; j++) {
    			if(board[j][moves[i]-1] != 0) {
    				arr.add(board[j][moves[i]-1]);
    				board[j][moves[i]-1] = 0;
    				break;
    			}
    			else continue;
    		}		
        }
        
        answer = comparison(arr, answer);
        return answer;
    }
	
	public int comparison(ArrayList<Integer> arr, int answer) {
		//arr = new ArrayList<Integer>(); // ���� ArrayList�� �ʱ�ȭ ������
		int index;
		while(arr.size()>1) {
			for(index=1; index<arr.size(); index++) {
				if(arr.get(index-1)==arr.get(index)) {
					arr.remove(index);
					arr.remove(index-1);
					answer = answer+2;
					return comparison(arr, answer);
				}
			}
			if(index==arr.size()) break; // �̺κ��� ��¥ ������� (1)
		}
		return answer; // �̺κ��� ��¥ ������� (2)
	}
}
