import java.io.*;
import java.util.*;

public class Main {
	
	static int K,M,N;
	static long [] tree;
	static long answer;
	static int size, leafs;
	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] str = br.readLine().split(" ");
		
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		
		size = 1;
		while (size < N) {
			size *=2;
		}
		leafs = size;
		size *=2;
		tree = new long [size];                // tree 사이즈 결정
		
		for (int i = 1; i <= N; i++) {
			long value = Long.parseLong(br.readLine());
			insert (i, value);
			
		}
		
		for (int i = 0; i < M+K; i++) {
			String [] str2 = br.readLine().split(" ");
			int state = Integer.parseInt(str2[0]);
			
			if (state == 1) {
				int idxa = Integer.parseInt(str2[1]);
				long valuea = Long.parseLong(str2[2]);
				insert (idxa,valuea);
			}
			
			else {
				int a = Integer.parseInt(str2[1]);
				int b = Integer.parseInt(str2[2]);
				sb.append(sum(a,b) + "\n");
			}
			
			
			
		}
			System.out.println(sb);	
		
		
		
		
	}
	private static void insert (int idx, long val) {
		idx = leafs + idx-1;
		tree [idx] = val;
		idx /= 2; 
		while (idx > 0) {
			tree [idx]= tree[idx*2] + tree[idx*2+1];
			idx/=2;
		}
		
	}
	
	private static long sum (int from, int to) {
		long sum = 0;
		
		from = from+leafs-1;
		to = to+leafs-1;
		
		while (from <= to) {
			if (from%2 == 1) {
				sum += tree [from++];
			}
			if (to %2 == 0) {
				sum += tree [to--];
			}
			
			from/=2;
			to/=2;
			
		}
		
		
		
		
		return sum;
		
	}
}
