package org.eclipse.scout.contacts.client.person;

import java.util.Set;

import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.person.PersonTablePage.Table;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(PersonTablePageData.class)
public class PersonTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		// TODO [B040910] verify translation
		return TEXTS.get("Persons");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IPersonService.class).getPersonTableData(filter));
	}
	
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	public class Table extends AbstractTable {
		
		@Override
		protected Class<? extends IMenu> getConfiguredDefaultMenu() {
			// Defines the action to be performed when Enter is pressed on a table row
			return EditMenu.class;
		}

		@Order(1000)
		public class EditMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Edit");
			}

			@Override
			protected void execAction() {
				PersonForm form = new PersonForm();
				form.setPersonId(getPersonIdColumn().getSelectedValue());
				form.addFormListener(new PersonFormListener());
				// Start the form using its modify handler
				form.startModify();
			}
		}

		@Order(2000)
		public class NewMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("New");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.EmptySpace, TableMenuType.SingleSelection);
			}

			@Override
			protected void execAction() {
				PersonForm form = new PersonForm();
				form.addFormListener(new PersonFormListener());
				// Start the form using its new handler
				form.startNew();
			}
		}

		public FirstNameColumn getFirstNameColumn() {
			return getColumnSet().getColumnByClass(FirstNameColumn.class);
		}

		public LastNameColumn getLastNameColumn() {
			return getColumnSet().getColumnByClass(LastNameColumn.class);
		}

		public CityColumn getCityColumn() {
			return getColumnSet().getColumnByClass(CityColumn.class);
		}

		public CountryColumn getCountryColumn() {
			return getColumnSet().getColumnByClass(CountryColumn.class);
		}

		public PhoneColumn getPhoneColumn() {
			return getColumnSet().getColumnByClass(PhoneColumn.class);
		}

		public PictureUrlColumn getPictureUrlColumn() {
			return getColumnSet().getColumnByClass(PictureUrlColumn.class);
		}

		public OrganizationColumn getOrganizationColumn() {
			return getColumnSet().getColumnByClass(OrganizationColumn.class);
		}

		public PersonIdColumn getPersonIdColumn() {
			return getColumnSet().getColumnByClass(PersonIdColumn.class);
		}

		@Order(1000)
		public class PersonIdColumn extends AbstractStringColumn {

			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}

			@Override
			protected boolean getConfiguredPrimaryKey() {
				// TODO Auto-generated method stub
				return true;
			}

		}

		@Order(2000)
		public class FirstNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("FirstName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}
		}

		@Order(3000)
		public class LastNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("LastName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}
		}

		@Order(4000)
		public class CityColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("City");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}
		}

		@Order(5000)
		public class CountryColumn extends AbstractSmartColumn<String> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Country");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

			@Override
			protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
				return CountryLookupCall.class;
			}
		}

		@Order(6000)
		public class PhoneColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Phone");
			}
			
			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

		}
		
		@Order(7000)
		public class MobileColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Mobile");
			}
			
			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

		}
		
		@Order(8000)
		public class EmailColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Email");
			}
			
			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

		}

		@Order(9000)
		public class OrganizationColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Organization");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(10000)
		public class PictureUrlColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("PictureURL");
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

		}
		
		private class PersonFormListener implements FormListener {

			@Override
			public void formChanged(FormEvent e) {
				
				// Reload page to reflect new/changed data after saving any chnages
				
				if ( FormEvent.TYPE_CLOSED == e.getType() && e.getForm().isFormStored() ) {
					reloadPage();
				}
				
			}
			
		}
		
	}
	
}
