package Day52.sqrtdecomposition;

public class Main {
    static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 7, 6, 3, 1, 4, 8};
        int n = arr.length; // n = 10

// Block size = floor(√n)
        int sqrt = (int) Math.sqrt(n); // sqrt = 3

// Number of blocks = √n + 1 (extra for remainder)
        int[] blocks = new int[sqrt + 1]; // blocks[0..3]

        int blockId = -1;

        for (int i = 0; i < n; i++) {
            // New block starts every sqrt elements
            if (i % sqrt == 0) {
                blockId++;
            }
            blocks[blockId] += arr[i];
        }

    }
/*
TRACE — n=10, sqrt=3:
i=0: 0%3==0 → blockId=0, blocks[0]+=arr[0]=1  → blocks[0]=1
i=1: 1%3!=0          blocks[0]+=arr[1]=3  → blocks[0]=4
i=2: 2%3!=0          blocks[0]+=arr[2]=5  → blocks[0]=9
i=3: 3%3==0 → blockId=1, blocks[1]+=arr[3]=2  → blocks[1]=2
i=4: 4%3!=0          blocks[1]+=arr[4]=7  → blocks[1]=9
i=5: 5%3!=0          blocks[1]+=arr[5]=6  → blocks[1]=15
i=6: 6%3==0 → blockId=2, blocks[2]+=arr[6]=3  → blocks[2]=3
i=7: 7%3!=0          blocks[2]+=arr[7]=1  → blocks[2]=4
i=8: 8%3!=0          blocks[2]+=arr[8]=4  → blocks[2]=8
i=9: 9%3==0 → blockId=3, blocks[3]+=arr[9]=8  → blocks[3]=8

Array:  [1,  3,  5,  |  2,  7,  6,  |  3,  1,  4,  |  8]
Block:  [-------9----]  [-------15---]  [-------8----]  [-8-]
BlockId:      0               1               2           3
*/

    public static int query(int[] blocks, int[] arr, int left, int right, int sqrt) {
        int ans = 0;

        // LEFT PARTIAL BLOCK:
        // Process elements from left until we hit a block boundary
        // Condition: left is not at start of a block (left%sqrt != 0)
        //            AND left hasn't passed right
        //            AND left is not 0 (0 is always block start)
        while (left % sqrt != 0 && left <= right && left != 0) {
            ans += arr[left];
            left++;
        }

        // MIDDLE COMPLETE BLOCKS:
        // Add entire block if block fits completely within [left, right]
        // left+sqrt-1 <= right means next sqrt elements are within range
        while (left + sqrt <= right) {
            ans += blocks[left / sqrt]; // blockId = left/sqrt
            left += sqrt;               // Jump entire block
        }

        // RIGHT PARTIAL BLOCK:
        // Process remaining elements one by one
        while (left <= right) {
            ans += arr[left];
            left++;
        }

        return ans;
    }

    public void update(int[] blocks, int[] arr, int i, int val, int sqrt) {
    /*
    Point update: change arr[i] to val

    Step 1: Find which block index i belongs to
    Step 2: Update block aggregate:
            blocks[blockId] += (val - arr[i])
            Remove old value, add new value
    Step 3: Update arr[i] = val

    TIME: O(1) — direct index operations

    Example:
    arr = [1,3,5,2,7,6,3,1,4,8], blocks=[9,15,8,8], sqrt=3
    Update: i=4, val=10 (arr[4]=7 → arr[4]=10)

    blockId = 4/3 = 1
    blocks[1] += (10 - 7) = 15 + 3 = 18
    arr[4] = 10

    After: arr=[1,3,5,2,10,6,3,1,4,8], blocks=[9,18,8,8]

    Verify: query [3,5] = arr[3]+arr[4]+arr[5] = 2+10+6 = 18
    blocks[1] = 18 ✓
    */
        int blockId = i / sqrt;
        blocks[blockId] += (val - arr[i]); // Adjust block sum
        arr[i] = val;                       // Update element
    }
}
