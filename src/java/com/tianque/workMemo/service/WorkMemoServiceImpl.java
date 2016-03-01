package com.tianque.workMemo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.workMemo.dao.WorkMemoDao;
import com.tianque.workMemo.domain.WorkMemo;

@Service("workMemoService")
@Transactional
public class WorkMemoServiceImpl implements WorkMemoService {
	@Autowired
	private WorkMemoDao workMemoDao;
	@Override
	public WorkMemo addWorkMemo(WorkMemo workMemo) {	
		if (workMemo.getAddMemoDate() == null
				|| workMemo.getAddMemoDate().before(
						DateUtil.parseDate(DateUtil.formateYMD(new Date()),
								"yyyy-MM-dd"))) {
			throw new ServiceException("不能在今天之前的日期添加备忘录");
		}
			if(workMemo.getMemoContents()==null||workMemo.getMemoContents().length()>60){
			throw new ServiceException("备忘录内容不能过多或没有内容");
		}
		workMemo.setUserId(ThreadVariable.getSession().getUserId());
		return workMemoDao.addWorkMemo(workMemo);
	}

	@Override
	public void deleteWorkMemoById(Long id) {
		workMemoDao.deleteWorkMemoById(id);
	}

	@Override
	public List<String> getDaysByUserIdAndDate(Long userId, String year,
			String month) {
		return workMemoDao.getDaysByUserIdAndDate(userId, year, month);
	}

	@Override
	public List<WorkMemo> getMemoContentsByUserIdAndAddMemoDate(Long userId,
			String date) {
		return workMemoDao.getMemoContentsByUserIdAndAddMemoDate(userId, date);
	}
}
