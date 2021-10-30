package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class test {
	public static class minHeap {
	      
	      private ArrayList<Integer> heap;
	      
	      public minHeap() {
	         heap = new ArrayList<>();
	         heap.add(0);   // 인덱스 1부터 시작위해
	      }
	      
	      public void swap(int i, int j) {
	         int tmp = heap.get(i);
	         heap.set(i, heap.get(j));
	         heap.set(j, tmp);
	      }
	      public void insert_minHeap(int x) {
	            heap.add(x);
	            int pointer = heap.size() - 1;      // 마지막 노드 포인터 역할

	            while( pointer > 1 && Math.abs(heap.get(pointer)) <= Math.abs(heap.get(pointer / 2)) ) {
	                if(Math.abs(heap.get(pointer)) < Math.abs(heap.get(pointer / 2))) {
	                    swap(pointer, pointer/2);
	                    pointer = pointer / 2;
	                }
	                else if(heap.get(pointer) < heap.get(pointer / 2)) {
	                    // 절댓값이 같으면서 마지막노드가 음수인 경우
	                    swap(pointer, pointer/2);
	                    pointer = pointer / 2;
	                }
	                else {      //갱신할 필요가 없으면 탈출
	                    break;
	                }
	            }
	      }
	      
	      /** 삭제 **/
	      // 루트 노드에 힙의 마지막 노드 넣기 --> 힙 마지막 노드 삭제 --> 힙 성질 맞도록 재구성
	      public int delete_minHeap() {
	         // 빈 힙일 경우
	         if (heap.size() - 1 < 1) {
	            return 0;
	         }
	         
	         // 삭제할 최소값 노드 = root 노트 (1부터 시작)
	         int del = heap.get(1); 
	         
	         // root 노드에 힙의 마지막 노드 넣기 --> 마지막 노드 제거
	         heap.set(1, heap.get(heap.size() - 1));
	         heap.remove(heap.size() - 1);
	         
	         for(int i = 1; i*2 < heap.size();) {
	
	            if ( i * 2 + 1 < heap.size() && Math.abs(heap.get(i*2)) > Math.abs(heap.get(i*2+1))) {
	                 if (Math.abs(heap.get(i)) <= Math.abs(heap.get(i*2+1))) {
	            	    // 이부분에 절댓값이 동일할 경우는 부모랑 우측좌식의 값을 비교하는 구문을 추가해서 break;
	                    if(Math.abs(heap.get(i)) == Math.abs(heap.get(i*2+1))) {
	      				    if(heap.get(i*2+1) > heap.get(i)) break;
	    				    else {
	    				   	   swap(i, i*2+1);
	    		               i = i * 2 +1;
	    				   }
	    			   }
	                   else break;
	                }
	                else {
	                   swap(i, i*2+1);
	 	               i = i * 2 +1;
	                }
	            }
	
	            else if(i * 2 + 1 < heap.size() && Math.abs(heap.get(i*2)) == Math.abs(heap.get(i*2+1))) {   
	               if(heap.get(i*2) > heap.get(i*2+1)) {
	            	   if (Math.abs(heap.get(i)) <= Math.abs(heap.get(i*2+1))) {
		            	    // 이부분에 절댓값이 동일할 경우는 부모랑 우측좌식의 값을 비교하는 구문을 추가해서 break;
		                    if(Math.abs(heap.get(i)) == Math.abs(heap.get(i*2+1))) {
		      				    if(heap.get(i*2+1) > heap.get(i)) break;
		    				    else {
		    				   	   swap(i, i*2+1);
		    		               i = i * 2 +1;
		    				   }
		    			    }
		                    else break;
	            	   }
	            	   else {
	            		  swap(i, i*2+1); 
	 	                  i = i * 2 +1;
	            	   }
	               }
	            // 왼쪽 부분도 추가
	               else {        
	            	   if (Math.abs(heap.get(i)) <= Math.abs(heap.get(i*2))) {
		            	    // 이부분에 절댓값이 동일할 경우는 부모랑 우측좌식의 값을 비교하는 구문을 추가해서 break;
		                    if(Math.abs(heap.get(i)) == Math.abs(heap.get(i*2))) {
		      				    if(heap.get(i*2) > heap.get(i)) break;
		    				    else {
		    				   	   swap(i, i*2);
		    		               i = i * 2;
		    				   }
		    			    }
		                    else break;
	            	   }
	            	   else {
	            		  swap(i, i*2); 
	 	                  i = i * 2;
	            	   }
	               }
	            }
	            else {   
	              
	            	// 이부분에 절댓값이 동일할 경우는 부모랑 좌측좌식의 값을 비교하는 구문을 추가해서 break;
	                
	               if (Math.abs(heap.get(i)) <= Math.abs(heap.get(i*2))) {
	            	    // 이부분에 절댓값이 동일할 경우는 부모랑 우측좌식의 값을 비교하는 구문을 추가해서 break;
	                    if(Math.abs(heap.get(i)) == Math.abs(heap.get(i*2))) {
	      				    if(heap.get(i*2) > heap.get(i)) break;
	    				    else {
	    				   	   swap(i, i*2);
	    		               i = i * 2;
	    				   }
	    			    }
	                    else break;
	           	   }
	           	   else {
	           		  swap(i, i*2); 
		                  i = i * 2;
	           	   }
	            }
	         }
	         return del;
	      }
	   }
	   
	   public static void main(String[] args) throws NumberFormatException, IOException {
	      
	      BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	      StringBuilder sb = new StringBuilder();
	      int N = Integer.parseInt(br.readLine());
	       
	      minHeap heap = new minHeap();
	      
	      for(int i = 0; i < N; i++) {
	         int input = Integer.parseInt(br.readLine());
	         
	         if (input != 0) {
	            heap.insert_minHeap(input);
	         }
	         else {
	            sb.append(heap.delete_minHeap() + "\n");
	         }
	      }
	      System.out.println(sb.toString());
	   }
}