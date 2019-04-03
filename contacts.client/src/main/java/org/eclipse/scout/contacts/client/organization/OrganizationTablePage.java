package org.eclipse.scout.contacts.client.organization;

import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.organization.OrganizationTablePage.Table;
import org.eclipse.scout.contacts.shared.organization.IOrganizationService;
import org.eclipse.scout.contacts.shared.organization.OrganizationTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(OrganizationTablePageData.class)
public class OrganizationTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		// TODO [B040910] verify translation
		return TEXTS.get("Organizations");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		IOrganizationService bean = BEANS.get(IOrganizationService.class);
		OrganizationTablePageData orgTable = bean.getOrganizationTableData(filter);
		importPageData(orgTable);
	}

	public class Table extends AbstractTable {

		public CityColumn getCityColumn() {
			return getColumnSet().getColumnByClass(CityColumn.class);
		}

		public CountryColumn getCountryColumn() {
			return getColumnSet().getColumnByClass(CountryColumn.class);
		}

		public HomepageColumn getHomepageColumn() {
			return getColumnSet().getColumnByClass(HomepageColumn.class);
		}

		public NameColumn getNameColumn() {
			return getColumnSet().getColumnByClass(NameColumn.class);
		}

		public OrganizationIdColumn getOrganizationIdColumn() {
			return getColumnSet().getColumnByClass(OrganizationIdColumn.class);
		}

		@Order(1000)
		public class OrganizationIdColumn extends AbstractStringColumn {
			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
			@Override
			protected boolean getConfiguredPrimaryKey() {
				return true;
			}
		}

		@Order(2000)
		public class NameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Name");
			}

			@Override
			protected int getConfiguredWidth() {
				return 250;
			}
		}

		@Order(3000)
		public class CityColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("City");
			}

			@Override
			protected int getConfiguredWidth() {
				return 150;
			}
		}

		@Order(4000)
		public class CountryColumn extends AbstractSmartColumn<String> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Country");
			}

			@Override
			protected int getConfiguredWidth() {
				return 150;
			}
			
			@Override
			protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
				return CountryLookupCall.class;
			}
		}

		@Order(5000)
		public class HomepageColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Homepage");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
			
			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
		}
		
		
		
	}
	
}
