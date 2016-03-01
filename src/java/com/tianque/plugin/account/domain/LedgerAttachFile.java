package com.tianque.plugin.account.domain;

import java.io.Serializable;

/*** 附件 */
public class LedgerAttachFile implements Serializable {
	/** 附件名称 */
	private String name;
	/** 附件的物理路径 */
	private String physicsFullFileName;
	/** 附件所属模块 */
	private String moduleKey;
	/** 模块附件关联表中的id */
	private String moduleObjectId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhysicsFullFileName() {
		return physicsFullFileName;
	}

	public void setPhysicsFullFileName(String physicsFullFileName) {
		this.physicsFullFileName = physicsFullFileName;
	}

	public String getModuleKey() {
		return moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

	public String getModuleObjectId() {
		return moduleObjectId;
	}

	public void setModuleObjectId(String moduleObjectId) {
		this.moduleObjectId = moduleObjectId;
	}

	@Override
	public int hashCode() {
		int result = getClass().hashCode();
		final int prime = 31;
		if (moduleKey != null) {
			result = prime * result + moduleKey.hashCode();
		}
		if (moduleObjectId != null) {
			result = prime * result + moduleObjectId.hashCode();
		}
		if (physicsFullFileName != null) {
			result = prime * result + physicsFullFileName.hashCode();
		}
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || !obj.getClass().equals(getClass())) {
			return false;
		}

		LedgerAttachFile other = (LedgerAttachFile) obj;
		if (getModuleKey() != null) {
			if (other.getModuleKey() == null || !getModuleKey().equals(other.getModuleKey())) {
				return false;
			}
		} else {
			if (other.getModuleKey() != null) {
				return false;
			}
		}
		if (getModuleObjectId() != null) {
			if (other.getModuleObjectId() == null
					|| !getModuleObjectId().equals(other.getModuleObjectId())) {
				return false;
			}
		} else {
			if (other.getModuleObjectId() != null) {
				return false;
			}
		}
		if (getPhysicsFullFileName() != null) {
			if (other.getPhysicsFullFileName() == null
					|| !getPhysicsFullFileName().equals(other.getPhysicsFullFileName())) {
				return false;
			}
		} else {
			if (other.getPhysicsFullFileName() != null) {
				return false;
			}
		}
		return true;
	}

}
