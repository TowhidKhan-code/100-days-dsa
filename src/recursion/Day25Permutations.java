package recursion;

import java.util.ArrayList;

public class Day25Permutations {

    static void permutation(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for(int i=0;i<=p.length();i++){
            String f = p.substring(0,i); // i is exclusive
            String s = p.substring(i,p.length());
            permutation(f+ch+s,up.substring(1));
        }
    }

    static ArrayList<String> permutationList(String p,String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> ans = new ArrayList<>();
        char ch = up.charAt(0);
        for(int i=0;i<=p.length();i++){
            String f = p.substring(0,i); // i is exclusive
            String s = p.substring(i,p.length());
            ans.addAll(permutationList(f+ch+s,up.substring(1)));
        }
        return ans;
    }

    static int permutationCount(String p,String up){
        if(up.isEmpty()){
            return 1;
        }
        int count = 0;
        char ch = up.charAt(0);
        for(int i=0;i<=p.length();i++){
            String f = p.substring(0,i); // i is exclusive
            String s = p.substring(i,p.length());
            count += permutationCount(f+ch+s,up.substring(1));
        }
        return count;
    }

    static void letterCom(String p,String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }

        int digit = up.charAt(0) - '0';
        for(int i=(digit-1)*3; i<digit*3;i++){
            char ch = (char) ('a' + i);
            letterCom(p + ch,up.substring(1));
        }
    }

    static void dice(String p,int target){
        if(target == 0){
            System.out.println(p);
            return;
        }
        for (int i = 1; i <= target; i++) {
            dice(p+i,target - i);
        }
    }

    static void diceCustomFace(String p,int target,int face){
        if(target == 0){
            System.out.println(p);
            return;
        }

        for (int i = 1; i <= face && i<=target; i++) {
            diceCustomFace(p+i,target - i,face);
        }
    }




    public static void main(String[] args) {
//        permutation("","abc");
//        System.out.println(permutationList("","abc"));
//        System.out.println(permutationCount("","abc"));
//        letterCom("","12");
//        dice("",4);
        diceCustomFace("",7,10);
    }
}
