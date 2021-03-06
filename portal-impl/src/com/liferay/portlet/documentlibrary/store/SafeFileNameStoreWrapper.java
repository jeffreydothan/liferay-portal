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

package com.liferay.portlet.documentlibrary.store;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;

import java.io.File;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class SafeFileNameStoreWrapper implements Store {

	public SafeFileNameStoreWrapper(Store store) {
		_store = store;
	}

	public void addDirectory(long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		String safeDirName = FileUtil.encodeSafeFileName(dirName);

		if (!safeDirName.equals(dirName)) {
			try {
				_store.move(dirName, safeDirName);
			}
			catch (Exception e) {
			}
		}

		_store.addDirectory(companyId, repositoryId, safeDirName);
	}

	public void addFile(
			long companyId, long repositoryId, String fileName, byte[] bytes)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		_store.addFile(
			companyId, repositoryId, safeFileName, bytes);
	}

	public void addFile(
			long companyId, long repositoryId, String fileName, File file)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		_store.addFile(companyId, repositoryId, safeFileName, file);
	}

	public void addFile(
			long companyId, long repositoryId, String fileName, InputStream is)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		_store.addFile(companyId, repositoryId, safeFileName, is);
	}

	public void checkRoot(long companyId) throws SystemException {
		_store.checkRoot(companyId);
	}

	public void copyFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionNumber, String toVersionNumber,
			String sourceFileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);
		String safeSourceFileName = FileUtil.encodeSafeFileName(sourceFileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);
		renameUnsafeFile(
			companyId, repositoryId, sourceFileName, safeSourceFileName);

		_store.copyFileVersion(
			companyId, repositoryId, safeFileName, fromVersionNumber,
			toVersionNumber, safeSourceFileName);
	}

	public void deleteDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		String safeDirName = FileUtil.encodeSafeFileName(dirName);

		if (!safeDirName.equals(dirName)) {
			try {
				_store.deleteDirectory(companyId, repositoryId, dirName);

				return;
			}
			catch (Exception e) {
			}
		}

		_store.deleteDirectory(companyId, repositoryId, safeDirName);
	}

	public void deleteFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			_store.hasFile(
				companyId, repositoryId, fileName,
				DLFileEntryConstants.DEFAULT_VERSION)) {

			_store.deleteFile(companyId, repositoryId, fileName);

			return;
		}

		_store.deleteFile(companyId, repositoryId, safeFileName);
	}

	public void deleteFile(
			long companyId, long repositoryId, String fileName,
			String versionNumber)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			_store.hasFile(companyId, repositoryId, fileName, versionNumber)) {

			_store.deleteFile(companyId, repositoryId, fileName, versionNumber);

			return;
		}

		_store.deleteFile(companyId, repositoryId, safeFileName, versionNumber);
	}

	public byte[] getFile(long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			_store.hasFile(
				companyId, repositoryId, fileName,
				DLFileEntryConstants.DEFAULT_VERSION)) {

			return _store.getFile(companyId, repositoryId, fileName);
		}

		return _store.getFile(companyId, repositoryId, safeFileName);
	}

	public byte[] getFile(
			long companyId, long repositoryId, String fileName,
			String versionNumber)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			_store.hasFile(companyId, repositoryId, fileName, versionNumber)) {

			return _store.getFile(
				companyId, repositoryId, fileName, versionNumber);
		}

		return _store.getFile(
			companyId, repositoryId, safeFileName, versionNumber);
	}

	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			_store.hasFile(
				companyId, repositoryId, fileName,
				DLFileEntryConstants.DEFAULT_VERSION)) {

			return _store.getFileAsStream(companyId, repositoryId, fileName);
		}

		return _store.getFileAsStream(companyId, repositoryId, safeFileName);
	}

	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionNumber)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			_store.hasFile(companyId, repositoryId, fileName, versionNumber)) {

			return _store.getFileAsStream(
				companyId, repositoryId, fileName, versionNumber);
		}

		return _store.getFileAsStream(
			companyId, repositoryId, safeFileName, versionNumber);
	}

	public String[] getFileNames(long companyId, long repositoryId)
		throws SystemException {

		String[] fileNames = _store.getFileNames(
			companyId, repositoryId);

		String[] decodedFileNames = new String[fileNames.length];

		for (int i = 0; i < fileNames.length; i++) {
			decodedFileNames[i] = FileUtil.decodeSafeFileName(fileNames[i]);
		}

		return decodedFileNames;
	}

	public String[] getFileNames(
			long companyId, long repositoryId, String dirName)
		throws PortalException, SystemException {

		String safeDirName = FileUtil.encodeSafeFileName(dirName);

		if (!safeDirName.equals(dirName)) {
			try {
				_store.move(dirName, safeDirName);
			}
			catch (Exception e) {
			}
		}

		String[] fileNames = _store.getFileNames(
			companyId, repositoryId, safeDirName);

		String[] decodedFileNames = new String[fileNames.length];

		for (int i = 0; i < fileNames.length; i++) {
			decodedFileNames[i] = FileUtil.decodeSafeFileName(fileNames[i]);
		}

		return decodedFileNames;
	}

	public long getFileSize(
			long companyId, long repositoryId, String fileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			_store.hasFile(
				companyId, repositoryId, fileName,
				DLFileEntryConstants.DEFAULT_VERSION)) {

			return _store.getFileSize(companyId, repositoryId, fileName);
		}

		return _store.getFileSize(companyId, repositoryId, safeFileName);
	}

	public boolean hasFile(
			long companyId, long repositoryId, String fileName,
			String versionNumber)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		if (!safeFileName.equals(fileName) &&
			_store.hasFile(companyId, repositoryId, fileName, versionNumber)) {

			return true;
		}

		return _store.hasFile(
			companyId, repositoryId, safeFileName, versionNumber);
	}

	public void move(String srcDir, String destDir) throws SystemException {
		_store.move(srcDir, destDir);
	}

	public void updateFile(
			long companyId, long repositoryId, long newRepositoryId,
			String fileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		_store.updateFile(
			companyId, repositoryId, newRepositoryId, safeFileName);
	}

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String newFileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);
		String safeNewFileName = FileUtil.encodeSafeFileName(newFileName);

		if (!safeFileName.equals(fileName)) {
			if (_store.hasFile(
					companyId, repositoryId, fileName,
					DLFileEntryConstants.DEFAULT_VERSION)) {

				safeFileName = fileName;
			}
		}

		_store.updateFile(
			companyId, repositoryId, safeFileName, safeNewFileName);
	}

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionNumber, String sourceFileName, byte[] bytes)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);
		String safeSourceFileName = FileUtil.encodeSafeFileName(sourceFileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);
		renameUnsafeFile(
			companyId, repositoryId, sourceFileName, safeSourceFileName);

		_store.updateFile(
			companyId, repositoryId, safeFileName, versionNumber,
			safeSourceFileName, bytes);
	}

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionNumber, String sourceFileName, File file)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);
		String safeSourceFileName = FileUtil.encodeSafeFileName(sourceFileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);
		renameUnsafeFile(
			companyId, repositoryId, sourceFileName, safeSourceFileName);

		_store.updateFile(
			companyId, repositoryId, safeFileName, versionNumber,
			safeSourceFileName, file);
	}

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionNumber, String sourceFileName, InputStream is)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);
		String safeSourceFileName = FileUtil.encodeSafeFileName(sourceFileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);
		renameUnsafeFile(
			companyId, repositoryId, sourceFileName, safeSourceFileName);

		_store.updateFile(
			companyId, repositoryId, safeFileName, versionNumber,
			safeSourceFileName, is);
	}

	public void updateFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionNumber, String toVersionNumber,
			String sourceFileName)
		throws PortalException, SystemException {

		String safeFileName = FileUtil.encodeSafeFileName(fileName);

		renameUnsafeFile(companyId, repositoryId, fileName, safeFileName);

		_store.updateFileVersion(
			companyId, repositoryId, safeFileName, fromVersionNumber,
			toVersionNumber, sourceFileName);
	}

	protected void renameUnsafeFile(
			long companyId, long repositoryId, String fileName,
			String safeFileName)
		throws PortalException, SystemException {

		if (!safeFileName.equals(fileName)) {
			if (_store.hasFile(
					companyId, repositoryId, fileName,
					DLFileEntryConstants.DEFAULT_VERSION)) {

				_store.updateFile(
					companyId, repositoryId, fileName, safeFileName);
			}
		}
	}

	private Store _store;

}