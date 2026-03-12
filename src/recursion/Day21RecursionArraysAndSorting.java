package recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class Day21RecursionArraysAndSorting {

    static boolean isSorted(int[] arr,int i){
        if(i == arr.length - 1){
            return true;
        }
        return arr[i] < arr[i+1] && isSorted(arr,i+1);
    }

    static boolean exist(int[] arr,int i,int t){
        if(i == arr.length){
            return false;
        }
        return arr[i] == t || exist(arr,i+1,t);
    }

    static int exist2(int[] arr,int i,int t){
        if(i == arr.length){
            return -1;
        }
        return arr[i] == t ? i : exist2(arr,i+1,t);
    }

    static ArrayList<Integer> allOccurence(int[] arr, int i, int t,ArrayList<Integer> list){
        if(i == arr.length){
            return list;
        }
        if(arr[i] == t){
            list.add(i);
            return allOccurence(arr,i+1,t,list);
        }
        return allOccurence(arr,i+1,t,list);
    }

    //Not optimal as new object is created everytime
    static ArrayList<Integer> allOccurence2(int[] arr, int i, int t){
        ArrayList<Integer> list = new ArrayList<>();
        if(i == arr.length){
            return list;
        }
        if(arr[i] == t){
            list.add(i);
        }
        ArrayList<Integer> ansFromBelowCalls = allOccurence2(arr,i+1,t);
        list.addAll(ansFromBelowCalls);

        return list;
    }

    static int rotatedBS(int[] arr,int t,int s,int e){
        if(s>e){
            return -1;
        }
        int m = s + (e - s) / 2;
        if(arr[m] == t){
            return m;
        }

        if(arr[s] <= arr[m]){
            if(arr[s] <= t && arr[m] >= t ){
                return rotatedBS(arr,t,s,m-1);
            }else{
                return rotatedBS(arr,t,m+1,e);
            }
        }
        if(t >= arr[m] && t <= arr[e]){
            return rotatedBS(arr,t,m+1,e);
        }else{
            return rotatedBS(arr,t,s,m-1);
        }
    }

    static void pattern1(int r, int c){
        if(r == 0){
            return;
        }
        if(c<r){
            System.out.print("*");
            pattern1(r,c+1);
        }else {
            System.out.println();
            pattern1(r - 1, 0);
        }
    }

    static void pattern2(int r, int c){
        if(r == 0){
            return;
        }
        if(c<r){
            pattern2(r,c+1);
            System.out.print("*");
        }else {
            pattern2(r - 1, 0);
            System.out.println();
        }
    }

    static void bubbleSort(int[] arr,int r,int c){
        if(r==0){
            return;
        }
        if(c<r){
            if(arr[c]>arr[c+1]){
                int temp = arr[c];
                arr[c] = arr[c+1];
                arr[c+1] = temp;
            }
            bubbleSort(arr,r,c+1);
        }
        bubbleSort(arr,r-1,0);
    }

    static void selectionSort(int[] arr,int r,int c,int max){
        if(r==0){
            return;
        }
        if(c<r){
            if(arr[c]>arr[max]){
                selectionSort(arr,r,c+1,c);
            }else{
                selectionSort(arr,r,c+1,max);
            }
        }else{
            int temp = arr[max];
            arr[max] = arr[r-1];
            arr[r-1] = temp;
        selectionSort(arr,r-1,0,0);
        }
    }


    public static void main(String[] args) {
        int[] arr = {8,1,5,7,3,2,4,6};
//        System.out.println(isSorted(arr,0));
//        System.out.println(exist(arr,0,7));
//        System.out.println(exist2(arr,0,8));
//        System.out.println(allOccurence(arr,0,5,new ArrayList<>()));
//        System.out.println(allOccurence2(arr,0,4));
//        System.out.println(rotatedBS(arr,3,0, arr.length-1));
//        pattern1(4,0);
//        pattern2(4,0);
//        bubbleSort(arr, arr.length - 1, 0);
//        selectionSort(arr, arr.length,0,0);
//        System.out.println(Arrays.toString(arr));
    }
}
