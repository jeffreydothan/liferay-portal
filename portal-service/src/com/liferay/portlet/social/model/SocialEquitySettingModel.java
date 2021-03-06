/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.social.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the SocialEquitySetting service. Represents a row in the &quot;SocialEquitySetting&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.social.model.impl.SocialEquitySettingModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.social.model.impl.SocialEquitySettingImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialEquitySetting
 * @see com.liferay.portlet.social.model.impl.SocialEquitySettingImpl
 * @see com.liferay.portlet.social.model.impl.SocialEquitySettingModelImpl
 * @generated
 */
public interface SocialEquitySettingModel extends BaseModel<SocialEquitySetting> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a social equity setting model instance should use the {@link SocialEquitySetting} interface instead.
	 */

	/**
	 * Returns the primary key of this social equity setting.
	 *
	 * @return the primary key of this social equity setting
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this social equity setting.
	 *
	 * @param primaryKey the primary key of this social equity setting
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the equity setting ID of this social equity setting.
	 *
	 * @return the equity setting ID of this social equity setting
	 */
	public long getEquitySettingId();

	/**
	 * Sets the equity setting ID of this social equity setting.
	 *
	 * @param equitySettingId the equity setting ID of this social equity setting
	 */
	public void setEquitySettingId(long equitySettingId);

	/**
	 * Returns the group ID of this social equity setting.
	 *
	 * @return the group ID of this social equity setting
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this social equity setting.
	 *
	 * @param groupId the group ID of this social equity setting
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this social equity setting.
	 *
	 * @return the company ID of this social equity setting
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this social equity setting.
	 *
	 * @param companyId the company ID of this social equity setting
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the fully qualified class name of this social equity setting.
	 *
	 * @return the fully qualified class name of this social equity setting
	 */
	public String getClassName();

	/**
	 * Returns the class name ID of this social equity setting.
	 *
	 * @return the class name ID of this social equity setting
	 */
	public long getClassNameId();

	/**
	 * Sets the class name ID of this social equity setting.
	 *
	 * @param classNameId the class name ID of this social equity setting
	 */
	public void setClassNameId(long classNameId);

	/**
	 * Returns the action ID of this social equity setting.
	 *
	 * @return the action ID of this social equity setting
	 */
	@AutoEscape
	public String getActionId();

	/**
	 * Sets the action ID of this social equity setting.
	 *
	 * @param actionId the action ID of this social equity setting
	 */
	public void setActionId(String actionId);

	/**
	 * Returns the daily limit of this social equity setting.
	 *
	 * @return the daily limit of this social equity setting
	 */
	public int getDailyLimit();

	/**
	 * Sets the daily limit of this social equity setting.
	 *
	 * @param dailyLimit the daily limit of this social equity setting
	 */
	public void setDailyLimit(int dailyLimit);

	/**
	 * Returns the lifespan of this social equity setting.
	 *
	 * @return the lifespan of this social equity setting
	 */
	public int getLifespan();

	/**
	 * Sets the lifespan of this social equity setting.
	 *
	 * @param lifespan the lifespan of this social equity setting
	 */
	public void setLifespan(int lifespan);

	/**
	 * Returns the type of this social equity setting.
	 *
	 * @return the type of this social equity setting
	 */
	public int getType();

	/**
	 * Sets the type of this social equity setting.
	 *
	 * @param type the type of this social equity setting
	 */
	public void setType(int type);

	/**
	 * Returns the unique entry of this social equity setting.
	 *
	 * @return the unique entry of this social equity setting
	 */
	public boolean getUniqueEntry();

	/**
	 * Returns <code>true</code> if this social equity setting is unique entry.
	 *
	 * @return <code>true</code> if this social equity setting is unique entry; <code>false</code> otherwise
	 */
	public boolean isUniqueEntry();

	/**
	 * Sets whether this social equity setting is unique entry.
	 *
	 * @param uniqueEntry the unique entry of this social equity setting
	 */
	public void setUniqueEntry(boolean uniqueEntry);

	/**
	 * Returns the value of this social equity setting.
	 *
	 * @return the value of this social equity setting
	 */
	public int getValue();

	/**
	 * Sets the value of this social equity setting.
	 *
	 * @param value the value of this social equity setting
	 */
	public void setValue(int value);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(SocialEquitySetting socialEquitySetting);

	public int hashCode();

	public CacheModel<SocialEquitySetting> toCacheModel();

	public SocialEquitySetting toEscapedModel();

	public String toString();

	public String toXmlString();
}