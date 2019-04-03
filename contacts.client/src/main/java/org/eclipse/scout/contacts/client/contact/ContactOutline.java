package org.eclipse.scout.contacts.client.contact;

import java.util.List;

import org.eclipse.scout.contacts.client.organization.OrganizationTablePage;
import org.eclipse.scout.contacts.client.person.PersonTablePage;
import org.eclipse.scout.contacts.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

/**
 * @author B040910
 */
@Order(1000)
public class ContactOutline extends AbstractOutline {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Contacts");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.CategoryBold;
	}
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		// pages to be shown in the navigation area of this outline
		pageList.add(new PersonTablePage());
		pageList.add(new OrganizationTablePage());
	}
}
