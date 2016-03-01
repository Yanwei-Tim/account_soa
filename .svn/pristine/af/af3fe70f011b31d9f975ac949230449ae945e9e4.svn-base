package com.tianque.userAuth.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Permission;
import com.tianque.domain.Role;
import com.tianque.domain.User;
import com.tianque.domain.vo.UserCountVo;
import com.tianque.domain.vo.UserVo;
import com.tianque.init.Node;
import com.tianque.mobile.vo.PermissionVo;
import com.tianque.sysadmin.vo.RoleVo;

public interface PermissionDubboService {
	/**
	 * 新增用户
	 * 
	 * @param baseUser
	 */
	public User addUser(User user);

	/**
	 * 根据部门id和是否锁定分页查询用户
	 * 
	 * @param orgId
	 * @param locked
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<User> findUsersForPageByUserIdAndOrgIdAndLockState(
			Long userId, Long orgId, boolean findChild, Boolean locked,
			User user, String roleIds, int pageNum, int pageSize,
			String sortField, String order);

	/**
	 * 更新用户
	 * 
	 * @param baseUser
	 */
	public User updateUser(User user);

	/**
	 * 
	 * @Title: updateUserForMobile
	 * @Description: TODO手机端 更新用户信息
	 * @param @param user
	 * @param @return 设定文件
	 * @return User 返回类型
	 * @author wanggz
	 * @date 2014-8-6 下午03:57:28
	 */
	public User updateUserForMobile(User user);

	public User updateUserByShutdown(User user);

	/**
	 * 删除角色
	 * 
	 * @param roleId
	 */
	public void deleteRoleById(Long id);

	public List<PermissionVo> findUserAllPermissionEnameAndCnameByUserId(
			Long userId);

	/**
	 * 根据角色ID查询角色
	 * 
	 * @param roleId
	 * @return
	 */
	public Role findRoleById(Long id);

	/**
	 * 分页查询角色
	 * 
	 * @param sysRole
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Role> findAllRolesByUseInLevelForPage(int pageNum,
			int pageSize, Long id, String sortField, String order);

	public Role addRole(Role role, String[] names);

	public Role updateRole(Role role, String[] enames);

	public Role findRoleByRoleNameAndUserInLevel(String roleName, Long levelId);

	public List<Role> findRolesByUserId(Long userId);

	public List<Role> findRolesByUserIdAndUseInLevel(Long userId,
			Long useInLevel);

	public List<Role> findRolesByRoleNameAndUserIdAndUseInLevel(
			String roleName, Long userId, Long useInLevel);

	public List<Role> findRolesByUserInLevel(Long useInLevel);

	public List<Role> findRolesByUserInLevel(String roleName, Long useInLevel);

	public List<Role> findDirectlyChildRolesByUseInLevel(Long useInLevel);

	public List<Role> findAllChildRolesByUseInLevel(Long useInLevel);

	public List<Role> findAllRolesDownCurrentOrgLevel(Long useInLevel);

	/***
	 * 根据组织机构查询非admin之外的岗位
	 * 
	 * @param useInLevel
	 * @return
	 */
	public List<Role> findRolesByUserInLevelNoAdmin(Long useInLevel);

	/***
	 * 根据组织机构查询非admin之外的直辖子岗位
	 * 
	 * @param useInLevel
	 * @return
	 */
	public List<Role> findDirectlyChildRolesByUseInLevelNoAdmin(Long useInLevel);

	/***
	 * 根据组织机构查询非admin之外的所有子岗位
	 * 
	 * @param useInLevel
	 * @return
	 */
	public List<Role> findAllChildRolesByUseInLevelNoAdmin(Long useInLevel);

	/**
	 * 分页查询菜单
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Permission> findAllPermissionsForPage(int pageNum,
			int pageSize);

	/**
	 * 查询Role所有的菜单
	 * 
	 * @param PermissionAnnotation
	 * @return
	 */
	public List<Permission> findAllPermissionsByRoleId(Long roleId)
			throws Exception;

	/**
	 * 根据用户Id查询所有权限Ename.
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> findUserAllPermissionEnameByUserId(Long userId);

	/**
	 * 根据userId查询SysBaseUser
	 * 
	 * @param userId
	 * @return
	 */
	public User getSimpleUserById(Long id);

	public User getFullUserById(Long id);

	public User getFullUserByUerName(String userName);

	/**
	 * @param text
	 *            在autocomplete 中输入的字符
	 * @param locked
	 *            user是否被锁定，null表示无视
	 * @param rootOrgId
	 *            需要从那个网格中搜索
	 * @param searchChildOrg
	 *            是否需要在子网格中搜索
	 * @param size
	 *            最多返回记录条数
	 * @return 每行格式:{userId,userName,userFullName,orgDisplayName}
	 */
	public String[][] findUserForAutocomplete(Long parentOrgId, String text,
			Boolean locked, boolean searchChildOrg, int size);

	public void deleteUserMultiZoneByUserId(Long userId);

	public void deleteUserRoleRelasByUserId(Long userId);

	public void resetUserPasswordByUserId(Long userId, String password);

	// modify by FCY at 2011.12.26 start
	public String resetUserPasswordByUserName(String userName, String password);

	// modify by FCY at 2011.12.26 end

	public Long addUserMultiZone(Long userId, Long orgId);

	public User findUserByUserName(String userName);

	public List<User> findChildUsersByEnameAndOrgCode(String ename,
			String orgcode);

	public List<User> findChildUsersByEnameAndInternalCode(String ename,
			String internalCode);

	public Long addUserRoleRela(Long userId, Long roleId);

	public boolean updatePasswordSuccess(Long id, String oldPassword,
			String currentPassword, String validatePassword, String email);

	public boolean isRoleUsed(Long id);

	public void lockOperate(Long id, boolean lock);

	public boolean isUserHasPermission(Long userId, String ename);

	public void updateUserRoleRela(Long userId, Long[] roleIds);

	public void updateUserMultiZone(Long userId, Long[] zoneIds);

	public int countUsersByOrgInternalCode(String orgInternalCode);

	public void updateFailureTimesById(Long userId, Integer failureTimes);

	public boolean updateUserDetails(User user);

	public List<User> findUsersByOrgId(Long id);

	public List<Permission> getRootPermissions();

	public List<Permission> getPermissionByParentId(Long parentId);

	public List<Permission> getPermissionByParentId(Long parentId, Long userId);

	public PageInfo<Permission> getChildPermissions(Long parentId);

	public Permission updatePermissionName(Permission permission);

	public void setIndexIdPermission(Long parentId);

	public Permission getPermissionByPermissionId(Long id);

	public List<Permission> findPermissionsByPermissionName(String name,
			int pageNum, int pageSize);

	public List<Long> searchParentPermissionIds(Long id);

	public Permission findPermissionByEname(String ename);

	public boolean movePreviousOrNext(Permission p, String mode);

	public boolean moveFirstOrLast(Permission p, String mode);

	public List<Node> getAllNodes();

	public List<Node> getCheckedPermssionTree();

	public List<Node> getMenuPermissionTree();

	public List<Node> getMenuPermissionTree(String nodeId, Integer n_lv);

	public List<Node> checkPermissionTree(List<Permission> permissions,
			List<Node> permissionNodes);

	public Permission addPermission(Permission permission);

	public PageInfo<User> findUsersBylockStatus(String status, User user,
			Integer page, Integer rows, String sidx, String sord);

	public boolean oldPasswordIsRight(Long userId, String oldPassword);

	public boolean deleteUserById(Long[] ids);

	public void initWorkBench(Long id, Boolean isFristWorkBench);

	/**
	 * 根据normalUrl查找权限信息
	 * 
	 * @param normalUrl
	 * @return
	 */
	public Permission findPermissionByNormalUrl(String normalUrl);

	/**
	 * 根据roleId重置工作台
	 * 
	 * @param roleId
	 * @return
	 */
	public Long[] reSetPatelConfigByRoleId(Long roleId);

	/**
	 * 根据userId重置工作台
	 * 
	 * @param userId
	 */
	public void reSetPatelConfigByUserId(Long userId);

	public List<Long> findUserIdsByOrgIds(List<Long> orgIdList);

	public List<Long> findUserIdsByRoleIds(List<Long> roleIdList);

	/**
	 * 查找某个组织结构下的某个层级里所有的用户id
	 * 
	 * @param orgTypeInternalId
	 *            组织机构类型（行政区域还是职能部门等）
	 * @param orgLevelInternalId
	 *            组织机构层级（50省份，40市。。。）
	 * @param orgParentId
	 *            组织结构父节点
	 * @return UserIds
	 */
	public List<Long> findUserIdsByByOrgTypeAndOrgLevelAndOrgParentId(
			Integer orgTypeInternalId, Integer orgLevelInternalId,
			Long orgParentId);

	/**
	 * 查询在某网格下 有某个岗位的所有用户id
	 * 
	 * @param orgId
	 * @param roleId
	 * @return
	 */
	public List<Long> findUserIdsByOrgIdAndRoleId(Long orgId, Long roleId);

	/**
	 * 根据部门internalId和角色Id查询用户，并分页显示
	 * 
	 * @param orgId
	 * @param roleId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<User> findUsersForPageInfoListByOrgInternalCodeAndRoleId(
			String orgInternalCodeById, Long roleId, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 根据用户id统计岗位
	 * 
	 * @param userId
	 * @return
	 */
	public Integer countRolesByUserId(Long userId);

	public Integer countRolesByRoleNameAndUserInLevel(Long levelId);

	public Integer countRolesByUserIdAndUseInLevel(Long userId, Long useInLevel);

	public Role addRolePermissionRelasByRoleVos(Role role,
			List<RoleVo> roelVoList);

	public Role updateRolePermissionRelasByRoleVos(Role role,
			List<RoleVo> roelVoList);

	public Role copyRole(Role role);

	/**
	 * 新增手机账号
	 * 
	 * @param user
	 * @return
	 * */
	public User addMobileUser(User user, Boolean isImport);

	/**
	 * @param userName
	 *            根据用户名查找user（用于手机账号库验证用户名是否重复等）
	 * */
	public User findUserByMobileUserName(String userName);

	/**
	 * @param user
	 *            根据userVo查找(列表显示，查询等)
	 * */
	public PageInfo<User> findMobileUsers(UserVo userVo, int pageNum,
			int pageSize, String sortField, String sord);

	/**
	 * @param user
	 *            修改手机账号用户
	 * */
	public User updateMobileUser(User user);

	/**
	 * 
	 * @Title: updateMobileUserVersion
	 * @Description: TODO手机端登录，修改版本号和内部版本号参数
	 * @param @param user
	 * @param @return 设定文件
	 * @return User 返回类型
	 * @author wanggz
	 * @date 2014-7-23 上午10:38:30
	 */
	public User updateMobileUserVersion(User user);

	/**
	 * @param ids
	 * @param organization
	 *            手机账号用户匹配网格
	 * */
	public void matchupOrganizationMobileUserByIds(Long[] ids,
			Organization organization);

	/**
	 * @param vpdnTemp
	 * @param ids
	 *            验证vpdn号是否正确
	 * */
	public Boolean validateMobileUserVpdn(String vpdnTemp, Long[] ids);

	/**
	 * @param ids
	 *            账号回收
	 * */
	public void recycleMobileUser(Long[] ids);

	public String[][] getExportPopertyArray();

	public int countMobileUsers(UserVo userVo, int pageNum, int pageSize,
			String sortField, String sord);

	/**
	 * @param imsi
	 *            根据imsi查找user（用于账号验证imsi是否重复等）
	 * */
	public User findUserByImsi(String imsi);

	/**
	 * 
	 * @param organization
	 *            根据org统计所以的四级平台账号
	 */
	public int countFourthAccountUserByOrg(Organization organization);

	/**
	 * 根据用户的ID和权限中文名称查询权限
	 */
	public boolean findUserAllPermissionByUserIdAndPermissionEname(Long userId,
			String ename);

	public List<Node> getMenuLeaderPermissionTree(String nodeId, Integer n_lv);

	/**
	 * 设置自定义排序
	 * 
	 * @param id
	 * @param seq
	 */
	public Boolean setPermissionSeq(Long id, Long seq);

	/**
	 * 验证seq的值是否可以用于排序自定义设置排序
	 * 
	 * @param id
	 * @param seq
	 * @return
	 */
	public boolean isCanSeq(Long id, Long seq);

	/**
	 * 开启关闭GPS
	 * 
	 * @param ids
	 * @param mode
	 */
	public void openOrCloseGpsByUserId(Long[] ids, String mode);

	/**
	 * 开启关闭四支队伍
	 * 
	 * @param ids
	 * @param mode
	 */
	public void openOrCloseFourTeamsByUserId(Long[] ids, String mode);

	/** 根据roleID查询出该岗位的所有权限ID */
	public List<Long> getRolePermissionByRoleId(Long roleId);

	/**
	 * 根据用户id获取用户的所有权限
	 * 
	 * @param id
	 * @return
	 */
	public List<String> findPermissionsEnameByUserId(Long id);

	/**
	 * 获取当前用户的所有权限
	 * 
	 * @param userId
	 * @param permissiontype
	 *            （权限的类型）
	 * @return
	 * @throws Exception
	 */
	public List<Permission> findAllPermissionsByCurrentUserRoleId(Long userId,
			Integer permissiontype) throws Exception;

	/**
	 * 激活、停用账号
	 * 
	 * @param ids
	 * @param state
	 * @return
	 */
	public boolean updateUserStateByUserIds(Long[] ids, Long state);

	/**
	 * 账号统计
	 * 
	 * @param orgLevel
	 * @param orgCode
	 * @param accountType
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<UserCountVo> findUserCount(Long orgId, Long accountType,
			Integer page, Integer rows, String sidx, String sord);

	/**
	 * 获得账号统计的导出模板
	 * 
	 * @return
	 */
	public String[][] getUserCountExportPopertyArray();

	/******************************* 组织机构迁移合并方法 ********************************/
	/***
	 * 根据权限ID查询所有子权限
	 */
	public List<Permission> getAllChildPermissionsByParentId(Long permissionId);

	public Permission getSimplePermissionById(Long id);

	List<Permission> getChildMenuByEname(String string);

	List<Permission> getChildMenuByEnameAndExcludeButtons(String string);
}
