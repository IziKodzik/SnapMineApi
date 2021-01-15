package com.snapmine.SnapMineApi.util;

public class PathParser {

    public static int[] findNSlash(String url,int n){
        if(n == 0)
            return new int[]{url.lastIndexOf('/'),url.length()};
        int[] result = new int[]{-1,-1};
        int count = 0;
        for(int i = url.length()-1; i > -1; --i){
            if(url.charAt(i) == '/'){
                if(count == n) {
                    result[0] = i;
                    return result;
                }
                result[1] = i;
                ++count;
            }
        }
        return new int[]{-1,-1};
    }

}
