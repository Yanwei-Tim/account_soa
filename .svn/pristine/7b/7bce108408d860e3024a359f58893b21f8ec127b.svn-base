package com.tianque.workMemo.dao;

import java.util.List;

import com.tianque.workMemo.domain.WorkMemo;

public interface WorkMemoDao {
	/**
	 * 新增工作备忘录
	 * @param workMemo
	 * @return
	 */
	public WorkMemo addWorkMemo(WorkMemo workMemo);
	/**
	 * 根据Id得到工作备忘录
	 * @param id
	 * @return
	 */
	public WorkMemo getWorkMemoById(Long id);
	/**
	 * 根据用户Id和新增时间得到忘录内容
	 * @param userid
	 * @param addMemoDate
	 * @return
	 */
	public List<WorkMemo> getMemoContentsByUserIdAndAddMemoDate(Long userId,String date);
	/**
	 * 根据Id删除工作备忘录
	 * @param id
	 * @param addMemoDate
	 */
	public void deleteWorkMemoById(Long id);
	/**
	 * 根据用户Id删除该用户的所用备忘录
	 * @param userId
	 */
	public void deleteAllWorkMemoByUserId(Long userId);
	/**
	 * 根据用户Id和所在年月得到有备忘录的日期
	 * @param userId
	 * @param date
	 * @return
	 */
	public List<String> getDaysByUserIdAndDate(Long userId,String year,String month);
}
