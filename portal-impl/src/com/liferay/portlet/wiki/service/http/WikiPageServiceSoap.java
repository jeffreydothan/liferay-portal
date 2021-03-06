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

package com.liferay.portlet.wiki.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import com.liferay.portlet.wiki.service.WikiPageServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.portlet.wiki.service.WikiPageServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portlet.wiki.model.WikiPageSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portlet.wiki.model.WikiPage}, that is translated to a
 * {@link com.liferay.portlet.wiki.model.WikiPageSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/tunnel-web/secure/axis. Set the property
 * <b>tunnel.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WikiPageServiceHttp
 * @see       com.liferay.portlet.wiki.model.WikiPageSoap
 * @see       com.liferay.portlet.wiki.service.WikiPageServiceUtil
 * @generated
 */
public class WikiPageServiceSoap {
	public static com.liferay.portlet.wiki.model.WikiPageSoap addPage(
		long nodeId, java.lang.String title, java.lang.String content,
		java.lang.String summary, boolean minorEdit,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portlet.wiki.model.WikiPage returnValue = WikiPageServiceUtil.addPage(nodeId,
					title, content, summary, minorEdit, serviceContext);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.wiki.model.WikiPageSoap addPage(
		long nodeId, java.lang.String title, java.lang.String content,
		java.lang.String summary, boolean minorEdit, java.lang.String format,
		java.lang.String parentTitle, java.lang.String redirectTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portlet.wiki.model.WikiPage returnValue = WikiPageServiceUtil.addPage(nodeId,
					title, content, summary, minorEdit, format, parentTitle,
					redirectTitle, serviceContext);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addPageAttachments(long nodeId, java.lang.String title,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, byte[]>> files)
		throws RemoteException {
		try {
			WikiPageServiceUtil.addPageAttachments(nodeId, title, files);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addPageAttachment(long nodeId, java.lang.String title,
		java.lang.String fileName, byte[] bytes) throws RemoteException {
		try {
			WikiPageServiceUtil.addPageAttachment(nodeId, title, fileName, bytes);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void changeParent(long nodeId, java.lang.String title,
		java.lang.String newParentTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			WikiPageServiceUtil.changeParent(nodeId, title, newParentTitle,
				serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deletePage(long nodeId, java.lang.String title)
		throws RemoteException {
		try {
			WikiPageServiceUtil.deletePage(nodeId, title);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deletePage(long nodeId, java.lang.String title,
		double version) throws RemoteException {
		try {
			WikiPageServiceUtil.deletePage(nodeId, title, version);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deletePageAttachment(long nodeId,
		java.lang.String title, java.lang.String fileName)
		throws RemoteException {
		try {
			WikiPageServiceUtil.deletePageAttachment(nodeId, title, fileName);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteTempPageAttachment(long nodeId,
		java.lang.String fileName, java.lang.String tempFolderName)
		throws RemoteException {
		try {
			WikiPageServiceUtil.deleteTempPageAttachment(nodeId, fileName,
				tempFolderName);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.wiki.model.WikiPageSoap getDraftPage(
		long nodeId, java.lang.String title) throws RemoteException {
		try {
			com.liferay.portlet.wiki.model.WikiPage returnValue = WikiPageServiceUtil.getDraftPage(nodeId,
					title);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.wiki.model.WikiPageSoap[] getNodePages(
		long nodeId, int max) throws RemoteException {
		try {
			java.util.List<com.liferay.portlet.wiki.model.WikiPage> returnValue = WikiPageServiceUtil.getNodePages(nodeId,
					max);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getNodePagesRSS(long nodeId, int max,
		java.lang.String type, double version, java.lang.String displayStyle,
		java.lang.String feedURL, java.lang.String entryURL)
		throws RemoteException {
		try {
			java.lang.String returnValue = WikiPageServiceUtil.getNodePagesRSS(nodeId,
					max, type, version, displayStyle, feedURL, entryURL);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.wiki.model.WikiPageSoap getPage(
		long nodeId, java.lang.String title) throws RemoteException {
		try {
			com.liferay.portlet.wiki.model.WikiPage returnValue = WikiPageServiceUtil.getPage(nodeId,
					title);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.wiki.model.WikiPageSoap getPage(
		long nodeId, java.lang.String title, java.lang.Boolean head)
		throws RemoteException {
		try {
			com.liferay.portlet.wiki.model.WikiPage returnValue = WikiPageServiceUtil.getPage(nodeId,
					title, head);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.wiki.model.WikiPageSoap getPage(
		long nodeId, java.lang.String title, double version)
		throws RemoteException {
		try {
			com.liferay.portlet.wiki.model.WikiPage returnValue = WikiPageServiceUtil.getPage(nodeId,
					title, version);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPagesRSS(long companyId, long nodeId,
		java.lang.String title, int max, java.lang.String type, double version,
		java.lang.String displayStyle, java.lang.String feedURL,
		java.lang.String entryURL, String locale) throws RemoteException {
		try {
			java.lang.String returnValue = WikiPageServiceUtil.getPagesRSS(companyId,
					nodeId, title, max, type, version, displayStyle, feedURL,
					entryURL, LocaleUtil.fromLanguageId(locale));

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String[] getTempPageAttachmentNames(long nodeId,
		java.lang.String tempFolderName) throws RemoteException {
		try {
			java.lang.String[] returnValue = WikiPageServiceUtil.getTempPageAttachmentNames(nodeId,
					tempFolderName);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void movePage(long nodeId, java.lang.String title,
		java.lang.String newTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			WikiPageServiceUtil.movePage(nodeId, title, newTitle, serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.wiki.model.WikiPageSoap revertPage(
		long nodeId, java.lang.String title, double version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portlet.wiki.model.WikiPage returnValue = WikiPageServiceUtil.revertPage(nodeId,
					title, version, serviceContext);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void subscribePage(long nodeId, java.lang.String title)
		throws RemoteException {
		try {
			WikiPageServiceUtil.subscribePage(nodeId, title);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsubscribePage(long nodeId, java.lang.String title)
		throws RemoteException {
		try {
			WikiPageServiceUtil.unsubscribePage(nodeId, title);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.wiki.model.WikiPageSoap updatePage(
		long nodeId, java.lang.String title, double version,
		java.lang.String content, java.lang.String summary, boolean minorEdit,
		java.lang.String format, java.lang.String parentTitle,
		java.lang.String redirectTitle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portlet.wiki.model.WikiPage returnValue = WikiPageServiceUtil.updatePage(nodeId,
					title, version, content, summary, minorEdit, format,
					parentTitle, redirectTitle, serviceContext);

			return com.liferay.portlet.wiki.model.WikiPageSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(WikiPageServiceSoap.class);
}