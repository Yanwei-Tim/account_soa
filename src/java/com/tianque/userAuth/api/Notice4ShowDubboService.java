package com.tianque.userAuth.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.publicNotice.domain.PublicNotice;
import com.tianque.publicNotice.domain.PublicNoticeAttachFiles;

public interface Notice4ShowDubboService {
	public List<PublicNotice> findNoticeByOrgCode(String orgCode);

	public PublicNotice findNoticeById(Long id);

	public PublicNoticeAttachFiles getPublicNoticeAttachFilesById(Long id);

	public PageInfo findNoticeForHistoryByOrgCode(String orgInternalCode, Integer page,
			Integer rows, String sidx, String sord);
}
