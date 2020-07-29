import edu.neu.titan.titanApp.common.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JodaTest {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<Integer>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        combinationSum(candidates, target, candidates.length, 0, path, res);

        return res;
    }

    public void combinationSum(int[] candidates, int target, int length, int begin, List<Integer> path, List<List<Integer>> res) {

        if (target==0) {
            ArrayList<Integer> p = new ArrayList<Integer>(path);
            res.add(p);
            return;
        }

        for (int i=begin; i<length ;i++) {

            if (candidates[i]>target){
                continue;
            }
            path.add(candidates[i]);
            combinationSum(candidates, target-candidates[i], length, i+1, path, res);
            path.remove(path.size()-1);
        }
    }

    public static boolean isPrimitive(Class<?> type) {
        try {
            return ((Class<?>) type.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }


    public static void main(String[] args) throws IllegalAccessException {
//        System.out.println(new JodaTest().combinationSum(new int[]{10,2,7,6,1,5},7));
//        System.out.println(new JodaTest().combinationSum(new int[]{7,3,2,7},7));
//        System.out.println(Arrays.toString(DateUtils.getDefaultDays(30)));
//        System.out.println(Arrays.deepToString(ArrayUtils.stackShift(Integer.class, new Integer[]{1, 3, 5, 7, 8, 9, 4, 5, 7}, 3, 1)));
        System.out.println(Arrays.toString(ArrayUtils.sumByRow(ArrayUtils.stackShift(Integer.class, new Integer[]{1, 3, 5, 7, 8, 9, 4, 5, 7}, 3, 1))));
        System.out.println();
//        System.out.println(ArrayUtils.);
    }

}

