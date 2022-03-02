import java.util.*;
import java.io.*;

public class Main {

	static int N,M,K;
	static long[] nums, tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 숫자 개수
		M = Integer.parseInt(st.nextToken()); // 숫자 변경 횟수
		K = Integer.parseInt(st.nextToken()); // 구간 합 계산 횟수
		
		nums = new long[N+1]; // 0번은 비워둔다.
		
		int h = (int)Math.ceil(Math.log(N)/Math.log(2));
		tree = new long[1<<(h+1)]; // 세그먼트 트리
		
		for(int i=1; i<=N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		
		// 세그먼트 트리 생성
		init(1,N,1);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int num1 = Integer.parseInt(st.nextToken());
			long num2 = Long.parseLong(st.nextToken());
			
			if(order==1) {
				// num1번째 수를 num2로 바꾼다.
				long diff = num2 - nums[num1];
				nums[num1] = num2;
				update(1,N,1,diff,num1);
			} else {
				// num1~num2 구간합을 출력한다.
				sb.append(sum(num1,(int)num2,1,1,N)).append("\n");
			}
			
		}
		System.out.println(sb.toString());
		
	} // main end
	
	// current : 트리의 인덱스 , start,end : 배열의 인덱스
	static long init(int start, int end, int current) {
		if(start==end) return tree[current]=nums[start];
		
		int mid = (start+end)/2;
		return tree[current] = init(start, mid, current*2) + init(mid+1, end, current*2+1);
	}
	
	// 수정
	static void update(int start, int end, int current, long diff, int changeIdx) {
		// 구간에 수정 원소가 없으면 리턴
		if(changeIdx < start || changeIdx > end) return;
		// 수정
		tree[current] += diff;
		
		// 분기
		// 마지막까지 수정했으면 종료
		if(start==end) return;
		
		int mid = (start+end)/2;
		update(start,mid,current*2,diff,changeIdx);
		update(mid+1,end,current*2+1,diff,changeIdx);
	}
	
	// 구간합 구하기
	static long sum(int start, int end, int current, int left, int right) {
		// 만약 구간이 아예 벗어 났으면 0을 리턴
		if(left>end || right<start) return 0;
		
		// 완전히 구간 내라면 해당 구간의 합을 반환
		if(left>=start && right<=end) return tree[current];
		
		// 구간에 걸쳐있다면 분기
		int mid = (left+right)/2;
		return sum(start,end,current*2,left,mid) + sum(start,end,current*2+1,mid+1,right);
	}
}
