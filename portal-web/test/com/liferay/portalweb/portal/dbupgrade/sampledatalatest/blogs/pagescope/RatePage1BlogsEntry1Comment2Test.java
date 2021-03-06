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

package com.liferay.portalweb.portal.dbupgrade.sampledatalatest.blogs.pagescope;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class RatePage1BlogsEntry1Comment2Test extends BaseTestCase {
	public void testRatePage1BlogsEntry1Comment2() throws Exception {
		selenium.open("/web/blogs-page-scope-community/");

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (selenium.isVisible("link=Blogs Test Page1")) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.saveScreenShotAndSource();
		selenium.clickAt("link=Blogs Test Page1",
			RuntimeVariables.replace("Blogs Test Page1"));
		selenium.waitForPageToLoad("30000");
		selenium.saveScreenShotAndSource();
		assertEquals(RuntimeVariables.replace("Blogs"),
			selenium.getText("//span[@class='portlet-title-text']"));
		assertEquals(RuntimeVariables.replace("Blogs Entry1 Title"),
			selenium.getText("//div[@class='entry-title']/a"));
		assertEquals(RuntimeVariables.replace("Blogs Entry1 Content"),
			selenium.getText("//div[@class='entry-body']/p"));
		assertEquals(RuntimeVariables.replace("2 Comments"),
			selenium.getText("//span[@class='comments']/a"));
		selenium.clickAt("//span[@class='comments']/a",
			RuntimeVariables.replace("2 Comments"));
		selenium.waitForPageToLoad("30000");
		selenium.saveScreenShotAndSource();
		assertEquals(RuntimeVariables.replace("Blogs Entry1 Comment2 Body"),
			selenium.getText("//form/div/div/div[3]/div/div[3]/div/div[1]"));
		assertTrue(selenium.isPartialText(
				"xPath=(//div[@class='aui-rating-label-element'])[4]", "0 Votes"));
		assertEquals(RuntimeVariables.replace("Rate this as good."),
			selenium.getText(
				"//div[3]/div/div[3]/div/div[3]/div/div[1]/div/div/div/div/a[1]"));
		assertEquals(RuntimeVariables.replace("Rate this as bad."),
			selenium.getText(
				"//div[3]/div/div[3]/div/div[3]/div/div[1]/div/div/div/div/a[2]"));
		selenium.clickAt("//div[3]/div/div[3]/div/div[3]/div/div[1]/div/div/div/div/a[2]",
			RuntimeVariables.replace("Rate this as bad."));

		for (int second = 0;; second++) {
			if (second >= 60) {
				fail("timeout");
			}

			try {
				if (RuntimeVariables.replace("-1 (1 Vote)")
										.equals(selenium.getText(
								"xPath=(//div[@class='aui-rating-label-element'])[4]"))) {
					break;
				}
			}
			catch (Exception e) {
			}

			Thread.sleep(1000);
		}

		selenium.saveScreenShotAndSource();
		assertEquals(RuntimeVariables.replace("-1 (1 Vote)"),
			selenium.getText(
				"xPath=(//div[@class='aui-rating-label-element'])[4]"));
	}
}