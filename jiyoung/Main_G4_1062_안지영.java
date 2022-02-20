package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_1062_안지영 {
	static int N;
	static int num;
	static int max=0;
	static int[] word;

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		num = K-5;
		if(num<0) {
			System.out.println(0);
			return;
		}
		
		//0~25까지 비트로 사용알파벳 표기 
		//a,n,t,i,c
		int flag=0;
		flag=flag|1<<((int)'a'-97);
		flag=flag|1<<((int)'n'-97);
		flag=flag|1<<((int)'t'-97);
		flag=flag|1<<((int)'i'-97);
		flag=flag|1<<((int)'c'-97);
		
		word = new int[N];
		
		for(int i=0;i<N;i++) {
			char[]tmp=br.readLine().toCharArray();
			for(int j=0;j<tmp.length;j++) {
				word[i]= word[i]|(1<<(tmp[j]-97));
			}
			
		}
		combi(0,flag,0);
	
		System.out.println(max);
		
	}
	private static void combi(int cnt, int flag,int start) {
		if(cnt==num) { //조합 완성 
			int count=0;
			
			for(int i=0,end=N;i<end;i++) { //각 단어 마다 읽을 수 있는지 체크 
				if((flag&word[i])==word[i])count++;
				
			}
			max=Math.max(max, count);
			return;
			
		}
		for(int i=start,end=26;i<end;i++) {
			if((flag&(1<<i))>0) continue;
			combi(cnt+1,flag|(1<<i),i+1);
		}
		
	}
}
