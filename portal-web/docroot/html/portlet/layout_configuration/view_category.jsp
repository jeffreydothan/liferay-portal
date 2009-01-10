<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/layout_configuration/init.jsp" %>

<%
PortletCategory portletCategory = (PortletCategory)request.getAttribute(WebKeys.PORTLET_CATEGORY);

String oldCategoryPath = (String)request.getAttribute(WebKeys.PORTLET_CATEGORY_PATH);

String newCategoryPath = LanguageUtil.get(pageContext, portletCategory.getName());

Pattern pattern = Pattern.compile("[:,a-z,A-Z,0-9]+");

Matcher matcher = pattern.matcher(newCategoryPath);

StringBuilder divId = new StringBuilder();

while (matcher.find()) {
	divId.append(matcher.group());
}

newCategoryPath = divId.toString();

if (Validator.isNotNull(oldCategoryPath)) {
	newCategoryPath = oldCategoryPath + ":" + newCategoryPath;
}

List categories = ListUtil.fromCollection(portletCategory.getCategories());

categories = ListUtil.sort(categories, new PortletCategoryComparator(company.getCompanyId(), locale));

List portlets = new ArrayList();

Iterator itr = portletCategory.getPortletIds().iterator();

String externalPortletCategory = null;

while (itr.hasNext()) {
	String portletId = (String)itr.next();

	Portlet portlet = PortletLocalServiceUtil.getPortletById(user.getCompanyId(), portletId);

	if (portlet != null) {
		portlets.add(portlet);

		PortletApp portletApp = portlet.getPortletApp();

		if (portletApp.isWARFile() && Validator.isNull(externalPortletCategory)) {
			PortletConfig curPortletConfig = PortletConfigFactory.create(portlet, application);

			ResourceBundle resourceBundle = curPortletConfig.getResourceBundle(locale);

			try {
				externalPortletCategory = resourceBundle.getString(portletCategory.getName());
			}
			catch (MissingResourceException mre) {
			}
		}
	}
}

portlets = ListUtil.sort(portlets, new PortletTitleComparator(application, locale));

if ((categories.size() > 0) || (portlets.size() > 0)) {
%>

	<div class="lfr-add-content <%= (layout.getType().equals(LayoutConstants.TYPE_PORTLET)) ? "collapsed" : "" %>" id="<%= newCategoryPath.replace(':', '-') %>">
		<h2>
			<span><%= Validator.isNotNull(externalPortletCategory) ? externalPortletCategory : LanguageUtil.get(pageContext, portletCategory.getName()) %></span>
		</h2>

		<div class="lfr-content-category <%= (layout.getType().equals(LayoutConstants.TYPE_PORTLET)) ? "hidden" : "" %>">

			<%
			itr = categories.iterator();

			while (itr.hasNext()) {
				request.setAttribute(WebKeys.PORTLET_CATEGORY, itr.next());
				request.setAttribute(WebKeys.PORTLET_CATEGORY_PATH, newCategoryPath);
			%>

				<liferay-util:include page="/html/portlet/layout_configuration/view_category.jsp" />

			<%
				request.setAttribute(WebKeys.PORTLET_CATEGORY_PATH, oldCategoryPath);
			}

			itr = portlets.iterator();

			while (itr.hasNext()) {
				Portlet portlet = (Portlet)itr.next();

				divId = new StringBuilder();

				divId.append(newCategoryPath);
				divId.append(":");

				matcher = pattern.matcher(PortalUtil.getPortletTitle(portlet, application, locale));

				while (matcher.find()) {
					divId.append(matcher.group());
				}

				boolean portletInstanceable = portlet.isInstanceable();
				boolean portletUsed = layoutTypePortlet.hasPortletId(portlet.getPortletId());
				boolean portletLocked = (!portletInstanceable && portletUsed);

				if (portletInstanceable && layout.getType().equals(LayoutConstants.TYPE_PANEL)) {
					continue;
				}
			%>

				<c:choose>
					<c:when test="<%= layout.getType().equals(LayoutConstants.TYPE_PORTLET) %>">

						<%
						Set<String> headerPortalCssPaths = new LinkedHashSet<String>();

						List<String> headerPortalCssList = portlet.getHeaderPortalCss();

						for (String headerPortalCss : headerPortalCssList) {
							String headerPortalCssPath = request.getContextPath() + headerPortalCss;

							if (!headerPortalCssPaths.contains(headerPortalCssPath)) {
								headerPortalCssPaths.add(headerPortalCssPath);

								if (headerPortalCssPath.endsWith(".jsp")) {
									headerPortalCssPath += "?themeId=" + theme.getThemeId() + "&amp;colorSchemeId=" + colorScheme.getColorSchemeId() + "&amp;t=" + portlet.getTimestamp();
								}
								else {
									headerPortalCssPath += "?t=" + portlet.getTimestamp();
								}

								if (themeDisplay.isThemeCssFastLoad()) {
									headerPortalCssPath += "&amp;minifierType=css";
								}
							}
						}

						Set<String> headerPortletCssPaths = new LinkedHashSet<String>();

						List<String> headerPortletCssList = portlet.getHeaderPortletCss();

						for (String headerPortletCss : headerPortletCssList) {
							String headerPortletCssPath = portlet.getContextPath() + headerPortletCss;

							if (!headerPortletCssPaths.contains(headerPortletCssPath)) {
								headerPortletCssPaths.add(headerPortletCssPath);

								if (headerPortletCssPath.endsWith(".jsp")) {
									headerPortletCssPath += "?themeId=" + theme.getThemeId() + "&amp;colorSchemeId=" + colorScheme.getColorSchemeId() + "&amp;t=" + portlet.getTimestamp();
								}
								else {
									headerPortletCssPath += "?t=" + portlet.getTimestamp();
								}

								if (themeDisplay.isThemeCssFastLoad()) {
									headerPortletCssPath += "&amp;minifierType=css";
								}
							}
						}

						Set<String> footerPortalCssPaths = new LinkedHashSet<String>();

						List<String> footerPortalCssList = portlet.getFooterPortalCss();

						for (String footerPortalCss : footerPortalCssList) {
							String footerPortalCssPath = request.getContextPath() + footerPortalCss;

							if (!footerPortalCssPaths.contains(footerPortalCssPath)) {
								footerPortalCssPaths.add(footerPortalCssPath);

								if (footerPortalCssPath.endsWith(".jsp")) {
									footerPortalCssPath += "?themeId=" + theme.getThemeId() + "&amp;colorSchemeId=" + colorScheme.getColorSchemeId() + "&amp;t=" + portlet.getTimestamp();
								}
								else {
									footerPortalCssPath += "?t=" + portlet.getTimestamp();
								}

								if (themeDisplay.isThemeCssFastLoad()) {
									footerPortalCssPath += "&amp;minifierType=css";
								}
							}
						}

						Set<String> footerPortletCssPaths = new LinkedHashSet<String>();

						List<String> footerPortletCssList = portlet.getFooterPortletCss();

						for (String footerPortletCss : footerPortletCssList) {
							String footerPortletCssPath = portlet.getContextPath() + footerPortletCss;

							if (!footerPortletCssPaths.contains(footerPortletCssPath)) {
								footerPortletCssPaths.add(footerPortletCssPath);

								if (footerPortletCssPath.endsWith(".jsp")) {
									footerPortletCssPath += "?themeId=" + theme.getThemeId() + "&amp;colorSchemeId=" + colorScheme.getColorSchemeId() + "&amp;t=" + portlet.getTimestamp();
								}
								else {
									footerPortletCssPath += "?t=" + portlet.getTimestamp();
								}

								if (themeDisplay.isThemeCssFastLoad()) {
									footerPortletCssPath += "&amp;minifierType=css";
								}
							}
						}
						%>

						<div
							class="lfr-portlet-item <c:if test="<%= portletLocked %>">lfr-portlet-used</c:if> <c:if test="<%= portletInstanceable %>">lfr-instanceable</c:if>"
							footerPortalCssPaths="<%= StringUtil.merge(footerPortalCssPaths) %>"
							footerPortletCssPaths="<%= StringUtil.merge(footerPortletCssPaths) %>"
							headerPortalCssPaths="<%= StringUtil.merge(headerPortalCssPaths) %>"
							headerPortletCssPaths="<%= StringUtil.merge(headerPortletCssPaths) %>"
							id="<%= divId.toString().replace(':', '-') %>"
							instanceable="<%= portletInstanceable %>"
							plid="<%= plid %>"
							portletId="<%= portlet.getPortletId() %>"
							title="<%= PortalUtil.getPortletTitle(portlet, application, locale) %>"
						>
							<p><%= PortalUtil.getPortletTitle(portlet, application, locale) %> <a href="javascript: ;"><liferay-ui:message key="add" /></a></p>
						</div>
					</c:when>
					<c:otherwise>
						<div>
							<a href="<liferay-portlet:renderURL portletName="<%= portlet.getRootPortletId() %>" windowState="<%= WindowState.MAXIMIZED.toString() %>"></liferay-portlet:renderURL>"><%= PortalUtil.getPortletTitle(portlet, application, locale) %></a>
						</div>
					</c:otherwise>
				</c:choose>

			<%
			}
			%>

		</div>
	</div>

<%
}
%>