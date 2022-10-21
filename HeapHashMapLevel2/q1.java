import java.util.*;

public class q1 {
      /*
       * 1. You are given number N and 2*N number of strings that contains mapping of
       * the employee and his manager.
       * 2. An employee directly reports to only one manager.
       * 3. All managers are employees but the reverse is not true.
       * 4. An employee reporting to himself is the CEO of the company.
       * 5. You have to find the number of employees under each manager in the
       * hierarchy not just their direct reports.
       * Sample Input
       * 6
       * A C
       * B C
       * C F
       * D E
       * E F
       * F F
       * Sample Output
       * A 0
       * B 0
       * C 2
       * D 0
       * E 1
       * F 5
       */
      public static void main(String[] args) {
            Scanner scn = new Scanner(System.in);
            int n = Integer.parseInt(scn.nextLine());
            String[][] allParts = new String[n][2];
            for (int i = 0; i < n; i++) {
                  String[] parts = scn.nextLine().split(" ");
                  allParts[i] = parts;
                  // allParts[i][0] -> emp
                  // allParts[i][1]] -> his manager.
            }

            findEmpUnderEachManager(allParts);
      }

      public static int size(HashMap<String, HashSet<String>> map, String man, HashMap<String, Integer> ans) {
            if (map.containsKey(man) == false) {
                  ans.put(man, 0);
                  return 1;
            }

            int sz = 0;
            for (String emp : map.get(man)) {
                  int cs = size(map, emp, ans);
                  sz += cs;
            }
            ans.put(man, sz);
            return sz + 1;
      }

      public static void findEmpUnderEachManager(String[][] allParts) {
            String ceo = "";

            HashMap<String, HashSet<String>> tree = new HashMap<>(); // manager -> { employees }
            for (String parts[] : allParts) {
                  String emp = parts[0];
                  String man = parts[1];

                  tree.putIfAbsent(man, new HashSet<>());
                  tree.putIfAbsent(emp, new HashSet<>());

                  if (man.equals(emp)) {
                        ceo = man;
                  } else { // we don't want any cycles in this tree.....
                        tree.get(man).add(emp);
                  }
            }
            // tree built

            HashMap<String, Integer> sizes = new HashMap<>();
            size(ceo, tree, sizes);

            for (String s : sizes.keySet()) {
                  System.out.println(s + " : " + sizes.get(s));
            }
      }

      public static int size(String man, HashMap<String, HashSet<String>> tree, HashMap<String, Integer> sizes) {

            int mySize = 0;
            for (String emp : tree.get(man)) {
                  mySize += size(emp, tree, sizes);
            }

            sizes.put(man, mySize); // don't use gtetOrDefault ..  not needed.

            return mySize + 1;
      }
}
