import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ArrayList<Long> water = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            water.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(water);

        int start = 0;
        int end = N - 1;
        long plus = Math.abs(water.get(start) + water.get(end) + water.get((start + end) / 2));
        long x = 0;
        long y = 0;
        long z = 0;

        while (start < end-1) {
            int mid = (start + end) / 2;
            long temp = water.get(start) + water.get(end) + water.get(mid);
            if (Math.abs(temp) <= plus) {
                x = water.get(start);
                y = water.get(mid);
                z = water.get(end);
                plus = Math.abs(temp);
                if (plus == 0) {
                    System.out.println(x + " " + y + " " + z);
                    System.exit(0);
                }
            }

            if (temp > 0) {
                end -= 1;
            } else {
                start += 1;
            }
        }
        System.out.println(x + " " + y + " " + z);
    }

}
