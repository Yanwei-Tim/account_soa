package com.tianque.userAuth.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.PublicNoticeVo;
import com.tianque.publicNotice.domain.PublicNotice;
import com.tianque.publicNotice.domain.PublicNoticeAttachFiles;
import com.tianque.publicNotice.domain.PublicNoticeBenchVo;

public interface PublicNoticeDubboService {
	public PublicNotice addPublicNotice(PublicNotice publicNotice);

	public PageInfo<PublicNotice> findfindPublicNoticeForPageByOrgInternalCode(
			Long orgId, Integer publicNoticeLevel, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public PublicNotice getPublicNoticeById(Long id);

	public PublicNotice updatePublicNotice(PublicNotice publicNotice);

	public void deletePublicNotice(Long id);

	public void deletePublicNoticeByIds(Long[] ids);

	public PageInfo<PublicNotice> findByStartEndDateAndTitle(
			PublicNoticeVo publicNoticevo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public PageInfo<PublicNotice> searchPublicNotice(
			PublicNoticeVo publicNoticeVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public List<PublicNoticeAttachFiles> addAttachFileByPublicNoticeId(
			Long publicNoticeId, String[] attachFiles);

	public PublicNoticeAttachFiles getPublicNoticeAttachFileById(Long id);

	public void deletePublicNoticeAttachFileById(Long id);

	public void deletePublicNoticeAttachFileByPublicNoticeId(
			Long[] publicNoticeIds);

	public List<PublicNoticeAttachFiles> attachFileList(Long publicNoticeId);

	public List<PublicNoticeAttachFiles> updatePublicNoticeAttachFile(
			Long publicNoticeId, String[] attachFiles);

	public PageInfo getPublicNoticeReceiveList(PublicNoticeVo publicNoticeVo,
			Integer page, Integer rows, String sidx, String sord);

	public void updatePublicNoticeIsRead(Long id);

	/**
	 * 工作台获取通知通告（第一部分是：最新的通知通告；第二部分是：五条最新的未读的通知通告）
	 * 
	 * @param publicNoticeBenchVo
	 * @param num
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PublicNoticeBenchVo getPublicNoticeReceiveList(int num, String sidx,
			String sord);
}
