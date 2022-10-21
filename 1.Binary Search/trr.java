import java.io.*;

public class trr {
    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(inp.readLine());
        for (int ti = 0; ti < t; ti++) {

            String input = inp.readLine();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'p') {
                    if (i + 4 <= input.length() - 1 && input.substring(i + 1, i + 5).equals("arty")) {
                        i += 4;
                        sb.append("pawri");
                    } else
                        sb.append("p");
                } else {
                    sb.append(input.charAt(i) + "");
                }
            }
            System.out.println(sb);
        }
    }
}
