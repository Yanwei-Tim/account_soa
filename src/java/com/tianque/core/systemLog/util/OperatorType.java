package com.tianque.core.systemLog.util;

public class OperatorType {
   public static final Integer  ADD=1;
   public static final Integer UPDATE=2;
   public static final Integer DELETE=3;
   public static final Integer COPE=4;
   public static final Integer IPORT=5;
   public static final Integer RESET=6;
   public static final Integer LOCK=7;
   public static final Integer UNLOCK=8;
   public static final Integer SHUT=9;
   public static final Integer UNSHUT=10;

   public static final Integer LOGIN=11;
   public static final Integer LOGINUP=12;
   public static final Integer EXPORT=13;

   public static final Integer EMPHASISE=14;
   public static final Integer CANCELEMPHASISE=15;
   public static final Integer LOGOUT=16;
   public static final Integer CANCELLOGOUT=17;
   public static final Integer UNCLAIM=18;
   public static String toString(int typeValue){
		switch (typeValue){
			case 1:
				return "新增";
			case 2:
				return "修改";
			case 3:
				return "删除";
			case 4:
				return "复制";
			case 5:
				return "导入";
			case 6:
				return "重置";
			case 7:
				return "锁定";
			case 8:
				return "解锁";
			case 9:
				return "启用";
			case 10:
				return "禁用";
			case 11:
				return "登录";
			case 12:
				return "登出";
			case 13:
				return "导出";
			case 14:
				return "关注";
			case 15:
				return "取消关注";
			case 16:
				return "注销";
			case 17:
				return "取消注销";
			default:
				return "";
		}
	}
}
