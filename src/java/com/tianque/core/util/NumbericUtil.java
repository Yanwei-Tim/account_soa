package com.tianque.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumbericUtil {
//	private int DEFAULT_NUMBERIC_SCALE=2;
	
	public static final double plus(double d1, double d2){
        BigDecimal one = new BigDecimal(String.valueOf(d1));
        BigDecimal two = new BigDecimal(String.valueOf(d2));
        return one.add(two).doubleValue();
    }

	public static final double plus(double d1, double d2,int scale){
        return round(plus(d1,d2),scale);
    }

    public static final double minus(double d1, double d2){
        BigDecimal one = new BigDecimal(String.valueOf(d1));
        BigDecimal two = new BigDecimal(String.valueOf(d2));
        return one.subtract(two).doubleValue();
    }

	public static final double minus(double d1, double d2,int scale){
        return round(minus(d1,d2),scale);
    }

    public static final double multiply(double d1,double d2){
		BigDecimal bd1=new BigDecimal(String.valueOf(d1));
		BigDecimal bd2=new BigDecimal(String.valueOf(d2));
		return bd1.multiply(bd2).doubleValue();
	}

	public static final double multiply(double d1,double d2,int scale){
		BigDecimal bd1=new BigDecimal(String.valueOf(d1));
		BigDecimal bd2=new BigDecimal(String.valueOf(d2));
		return round(bd1.multiply(bd2).doubleValue(),scale);
	}
	
	public static final double round(String numString,int scale){
		BigDecimal decimal=new BigDecimal(numString);
       return divide(decimal.doubleValue(), 1.0d, scale);
	}
	
	public static final double round(double d1,int scale){
       return divide(d1, 1.0d, scale);
	}
	//d1/d2
	public static final double divide(double d1, double d2, int scale){
		if (isZero(d1)) {
			return 0;
		}
        BigDecimal one = new BigDecimal(String.valueOf(d1));
        BigDecimal two = new BigDecimal(String.valueOf(d2));
        return one.divide(two, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

	//d1/d2
	public static final double divide(double d1, double d2){
		if (isZero(d1)) {
			return 0;
		}
        BigDecimal one = new BigDecimal(String.valueOf(d1));
        BigDecimal two = new BigDecimal(String.valueOf(d2));
        return one.divide(two, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static final int compare(double d1, double d2, int scale){
        BigDecimal one = new BigDecimal(String.valueOf(round(d1, scale)));
        BigDecimal two = new BigDecimal(String.valueOf(round(d2, scale)));
        return one.compareTo(two);
    }
    
    public static final int compare(double d1, double d2){
        BigDecimal one = new BigDecimal(String.valueOf(d1));
        BigDecimal two = new BigDecimal(String.valueOf(d2));
        return one.compareTo(two);
    }
    
    public static final String toString(double d, int scale){
        double r = round(d, scale);
        return (new DecimalFormat(getFormatPattern(scale))).format(r);
    }
    
    private static final boolean isZero(double value){
    	return value==0;
    }
    
    private static String getFormatPattern(int scale) {
        String result = "0";
        if(scale > 0)
            result = result + ".";
        for(int i = 0; i < scale; i++)
            result = result + "0";

        return result;
    }



}
