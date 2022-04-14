import java.util.Scanner;

public class matrix {

    public static void main(String [] args) {
        int N, value;
        int arr[][], dp[][];

        Scanner scan= new Scanner(System.in);

        N= scan.nextInt();
        arr= new int[N][2]; //0~(N-1)
        dp= new int[N][N];

        for(int i=0; i<N; i++) {
            arr[i][0]= scan.nextInt();
            arr[i][1]= scan.nextInt();
        }


        for(int i=1; i<N; i++) {
            for(int j=0; j<N-i; j++) {
                dp[j][j+i]= Integer.MAX_VALUE;
                for(int k=0; k<i; k++) {
                    value= dp[j][j+k]+dp[j+k+1][j+i]+arr[j][0]*arr[j+k][1]*arr[j+i][1];
                    dp[j][j+i]= Math.min(dp[j][j+i], value);
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}
