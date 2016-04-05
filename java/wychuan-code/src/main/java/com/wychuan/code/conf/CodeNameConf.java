package com.wychuan.code.conf;

import com.wychuan.code.common.CodeCommon;
import com.wychuan.util.StringUtils;

/**
 * 命名配置类
 * 
 * @author wychuan
 * @date 2016-03-10
 *
 */
public class CodeNameConf {

	/**
	 * service 层命名空间
	 */
	private String nameSpaceOfService;

	/**
	 * 获取service命名空间
	 * 
	 * @return
	 */
	public String getNameSpaceOfService() {
		return nameSpaceOfService;
	}

	/**
	 * 设置service命名空间
	 * 
	 * @param nameSpaceOfService
	 */
	public void setNameSpaceOfService(String nameSpaceOfService) {
		this.nameSpaceOfService = nameSpaceOfService;
	}

	/**
	 * service 名称
	 */
	private String nameOfService;

	/**
	 * 获取 service 名称
	 * 
	 * @return
	 */
	public String getNameOfService() {
		if (!StringUtils.isEmpty(nameOfService)) {
			return nameOfService;
		}
		return "I" + getNameOfModel() + "Service";
	}

	/**
	 * 设置 service 名称
	 * 
	 * @param nameOfService
	 */
	public void setNameOfService(String nameOfService) {
		this.nameOfService = nameOfService;
	}

	/**
	 * 获取 service 全路径名称：package+.+servicename
	 * 
	 * @return
	 */
	public String getFullNameOfService() {
		return getNameSpaceOfService() + "." + getNameOfService();
	}

	/**
	 * service impl 层命名空间
	 */
	private String nameSpaceOfServiceImpl;

	/**
	 * 获取 serviceImpl命名空间
	 * 
	 * @return
	 */
	public String getNameSpaceOfServiceImpl() {
		return nameSpaceOfServiceImpl;
	}

	/**
	 * 设置 serviceImpl 命名空间
	 * 
	 * @param nameSpaceOfServiceImpl
	 */
	public void setNameSpaceOfServiceImpl(String nameSpaceOfServiceImpl) {
		this.nameSpaceOfServiceImpl = nameSpaceOfServiceImpl;
	}

	/**
	 * service impl 类名称
	 */
	private String nameOfServiceImpl;

	/**
	 * 获取 service impl 类名称
	 * 
	 * @return
	 */
	public String getNameOfServiceImpl() {
		if (!StringUtils.isEmpty(nameOfServiceImpl)) {
			return nameOfServiceImpl;
		}
		return getNameOfModel() + "Service";
	}

	/**
	 * 设置 service impl 类名称
	 * 
	 * @param nameOfServiceImpl
	 */
	public void setNameOfServiceImpl(String nameOfServiceImpl) {
		this.nameOfServiceImpl = nameOfServiceImpl;
	}

	/**
	 * 获取 service impl 全名称
	 * 
	 * @return
	 */
	public String getFullNameOfServiceImpl() {
		return nameSpaceOfServiceImpl + "." + nameOfServiceImpl;
	}

	/**
	 * model 命名空间
	 */
	private String nameSpaceOfModel;

	/**
	 * 获取 model 命名空间
	 * 
	 * @return
	 */
	public String getNameSpaceOfModel() {
		return nameSpaceOfModel;
	}

	/**
	 * 设置 model 命名空间
	 * 
	 * @param nameSpaceOfModel
	 */
	public void setNameSpaceOfModel(String nameSpaceOfModel) {
		this.nameSpaceOfModel = nameSpaceOfModel;
	}

	/**
	 * model 类名称
	 */
	private String nameOfModel;

	/**
	 * 获取 model 类名
	 * 
	 * @return
	 */
	public String getNameOfModel() {
		return nameOfModel;
	}

	/**
	 * 设置 model 类名
	 * 
	 * @param nameOfModel
	 */
	public void setNameOfModel(String nameOfModel) {
		this.nameOfModel = nameOfModel;
	}

	/**
	 * 获取 model fullname
	 * 
	 * @return
	 */
	public String getFullNameOfModel() {
		return getNameSpaceOfModel() + "." + getNameOfModel();
	}

	/**
	 * 获取 model 参数名
	 * <p>
	 * 如：{@code user}
	 * 
	 * @return
	 */
	public String getParam() {
		return CodeCommon.setFirstCharLower(getNameOfModel());
	}

	/**
	 * 获取 model 参数名，带有类型。
	 * <p>
	 * 如：{@code User user}
	 * 
	 * @return
	 */
	public String getParamWithType() {
		return getNameOfModel() + " " + getParam();
	}

	private String nameSpaceOfDao;

	/**
	 * @return the nameSpaceOfDao
	 */
	public String getNameSpaceOfDao() {
		return nameSpaceOfDao;
	}

	/**
	 * @param nameSpaceOfDao
	 *            the nameSpaceOfDao to set
	 */
	public void setNameSpaceOfDao(String nameSpaceOfDao) {
		this.nameSpaceOfDao = nameSpaceOfDao;
	}

	private String nameOfDao;

	/**
	 * @return the nameOfDao
	 */
	public String getNameOfDao() {
		if (!StringUtils.isEmpty(nameOfDao)) {
			return nameOfDao;
		}
		return getNameOfModel() + "Dao";
	}

	/**
	 * @param nameOfDao
	 *            the nameOfDao to set
	 */
	public void setNameOfDao(String nameOfDao) {
		this.nameOfDao = nameOfDao;
	}

	/**
	 * get dao full name
	 * 
	 * @return
	 */
	public String getFullNameOfDao() {
		return getNameSpaceOfDao() + "." + getNameOfDao();
	}

	private String nameSpaceOfDomain;

	/**
	 * @return the nameSpaceOfDomain
	 */
	public String getNameSpaceOfDomain() {
		return nameSpaceOfDomain;
	}

	/**
	 * @param nameSpaceOfDomain
	 *            the nameSpaceOfDomain to set
	 */
	public void setNameSpaceOfDomain(String nameSpaceOfDomain) {
		this.nameSpaceOfDomain = nameSpaceOfDomain;
	}

	private String nameOfDomain;

	/**
	 * @return the nameOfDomain
	 */
	public String getNameOfDomain() {
		if (!StringUtils.isEmpty(nameOfDomain)) {
			return nameOfDomain;
		}
		return "I" + getNameOfModel() + "Dao";
	}

	/**
	 * @param nameOfDomain
	 *            the nameOfDomain to set
	 */
	public void setNameOfDomain(String nameOfDomain) {
		this.nameOfDomain = nameOfDomain;
	}
	public String getFullNameOfDomain(){
		return getNameSpaceOfDomain()+"."+getNameOfDomain();
	}
	/**
	 * 获取ServiceImpl层依赖的Dao对象
	 * @return
	 */
	public String getServiceImplDepedencyObj(){
		String daoName = getNameOfDao();
		return CodeCommon.setFirstCharLower(daoName);
	}
}
