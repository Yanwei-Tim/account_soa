package com.tianque.userAuth.api;

import java.util.List;

import com.tianque.domain.UserSign;

public interface UserSignDubboService {
	public UserSign addUserSign(UserSign userSign);

	public UserSign getUserSignById(Long id);

	/**
	 * 根据当前用户的id和当前日期，判断登录用户是否已经签到
	 * 
	 * @param userId
	 * @param date
	 * @return
	 */
	public boolean getUserSignByUserId(Long userId, String date);

	/**
	 * 根据当前用户id和当前年月，获取用户本月的签到情况
	 * 
	 * @param userId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<String> getDaysByUserIdAndDate(Long userId, String year,
			String month);

}
