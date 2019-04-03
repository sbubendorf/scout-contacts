package org.eclipse.scout.contacts.client.person;

import java.util.regex.Pattern;

import org.eclipse.scout.contacts.client.common.AbstractUrlImageField;
import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.CancelButton;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.EmailField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CityField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CountryField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.MobileField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.PhoneField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.StreetField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.NotesBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.NotesBox.NotesField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.EmailWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.OrganizationField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PhoneWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PositionField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.DateOfBirthField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.FirstNameField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.GenderGroup;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.LastNameField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.OkButton;
import org.eclipse.scout.contacts.shared.person.GenderCodeType;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {
	
	// Represents the person's primary key
	private String personId;

	@FormData
	public String getPersonId() {
		return personId;
	}

	@FormData
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Person");
	}
	
	@Override
	public Object computeExclusiveKey() {
		return getPersonId();
	}
	
	@Override
	protected int getConfiguredDisplayHint() {
		return IForm.DISPLAY_HINT_VIEW;
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GeneralBox getGeneralBox() {
		return getFieldByClass(GeneralBox.class);
	}

	public DetailsBox getDetailsBox() {
		return getFieldByClass(DetailsBox.class);
	}

	public ContactInfoBox getContactInfoBox() {
		return getFieldByClass(ContactInfoBox.class);
	}

	public AddressBox getAddressBox() {
		return getFieldByClass(AddressBox.class);
	}

	public WorkBox getWorkBox() {
		return getFieldByClass(WorkBox.class);
	}

	public FirstNameField getFirstNameField() {
		return getFieldByClass(FirstNameField.class);
	}

	public LastNameField getLastNameField() {
		return getFieldByClass(LastNameField.class);
	}

	public DateOfBirthField getDateOfBirthField() {
		return getFieldByClass(DateOfBirthField.class);
	}

	public GenderGroup getGenderGroup() {
		return getFieldByClass(GenderGroup.class);
	}

	public PositionField getPositionField() {
		return getFieldByClass(PositionField.class);
	}

	public OrganizationField getOrganizationField() {
		return getFieldByClass(OrganizationField.class);
	}

	public PhoneWorkField getPhoneWorkField() {
		return getFieldByClass(PhoneWorkField.class);
	}

	public EmailWorkField getEmailWorkField() {
		return getFieldByClass(EmailWorkField.class);
	}

	public NotesField getNotesField() {
		return getFieldByClass(NotesField.class);
	}

	public StreetField getStreetField() {
		return getFieldByClass(StreetField.class);
	}

	public LocationBox getLocationBox() {
		return getFieldByClass(LocationBox.class);
	}

	public CityField getCityField() {
		return getFieldByClass(CityField.class);
	}

	public CountryField getCountryField() {
		return getFieldByClass(CountryField.class);
	}

	public PhoneField getPhoneField() {
		return getFieldByClass(PhoneField.class);
	}

	public MobileField getMobileField() {
		return getFieldByClass(MobileField.class);
	}

	public EmailField getEmailField() {
		return getFieldByClass(EmailField.class);
	}

	public NotesBox getNotesBox() {
		return getFieldByClass(NotesBox.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class GeneralBox extends AbstractGroupBox {

			@Order(10)
			public class PictureField extends AbstractUrlImageField {
			}

			@Order(20)
			public class PictureUrlField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("PictureURL");
				}
				@Override
				protected boolean getConfiguredVisible() {
					return true;
				}
				@Override
				protected boolean getConfiguredEnabled() {
					return false;
				}
				@Override
				protected byte getConfiguredLabelPosition() {
					return LABEL_POSITION_TOP;
				}
			}
			
			@Order(2000)
			public class FirstNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("FirstName");
				}
			}

			@Order(3000)
			public class LastNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("LastName");
				}
			}

			@Order(4000)
			public class DateOfBirthField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("DateOfBirth");
				}
			}

			@Order(5000)
			public class GenderGroup extends AbstractRadioButtonGroup<String> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Gender");
				}
				@Override
				protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
					return GenderCodeType.class;
				}
			}
			
		}

		@Order(2000)
		public class DetailsBox extends AbstractTabBox {

			@Order(1000)
			public class ContactInfoBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ContactInformation");
				}

				@Order(1000)
				public class AddressBox extends AbstractGroupBox {
					@Override
					protected boolean getConfiguredBorderVisible() {
						return false;
					}
					@Override
					protected int getConfiguredGridH() {
						return 3;
					}
					@Override
					protected int getConfiguredGridW() {
						return 1;
					}
					@Override
					protected int getConfiguredGridColumnCount() {
						return 1;
					}
					@Order(1000)
					public class StreetField extends AbstractStringField {
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Street");
						}
						@Override
						protected void execChangedValue() {
							validateAddressFields();
						}
					}
					// Use a sequence box for horizontal layout
					@Order(2000)
					public class LocationBox extends AbstractSequenceBox {
						// Extending a Scout sequence box will place the inner fields of the LocationBox on a single row.
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Location");
						}

						@Override
						protected boolean getConfiguredAutoCheckFromTo() {
							return false;
						}

						@Order(1000)
						public class CityField extends AbstractStringField {
							@Override
							protected String getConfiguredLabel() {
								return TEXTS.get("City");
							}
							@Override
							protected byte getConfiguredLabelPosition() {
								return LABEL_POSITION_ON_FIELD;
							}
							@Override
							protected void execChangedValue() {
								validateAddressFields();
							}
						}

						@Order(2000)
						public class CountryField extends AbstractSmartField<String> {
							@Override
							protected String getConfiguredLabel() {
								return TEXTS.get("Country");
							}
							@Override
							protected byte getConfiguredLabelPosition() {
								return LABEL_POSITION_ON_FIELD;
							}
							@Override
							protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
								return CountryLookupCall.class;
							}
							@Override
							protected void execChangedValue() {
								validateAddressFields();
							}
						}
						
					}
					
					protected void validateAddressFields() {
						boolean hasStreet = StringUtility.hasText(getStreetField().getValue());
						boolean hasCity = StringUtility.hasText(getCityField().getValue());
						// The city becomes mandatory if the street field is not empty. 
						getCityField().setMandatory(hasStreet);
						// The country is mandatory if the street or the city is not empty.
						getCountryField().setMandatory(hasStreet || hasCity);
					}
					
					@Order(3000)
					public class PhoneField extends AbstractStringField {
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Phone");
						}
					}
					@Order(4000)
					public class MobileField extends AbstractStringField {
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Mobile");
						}
					}
					@Order(5000)
					public class EmailField extends AbstractStringField {
						
						private static final String EMAIL_PATTERN =
								"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
								"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
						
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Email");
						}
						
						@Override
						protected int getConfiguredMaxLength() {
							return 64;
						}
						
						@Override
						protected String execValidateValue(String rawValue) {
							if ( rawValue != null && !Pattern.matches(EMAIL_PATTERN, rawValue)) {
								throw new VetoException(TEXTS.get("BadEmailAddress"));
							}
							return rawValue;
						}
					}
					
				}
				
			}

			@Order(2000)
			public class WorkBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Work");
				}

				@Order(1000)
				public class PositionField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Position");
					}
				}

				@Order(2000)
				public class OrganizationField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Organization");
					}
				}

				@Order(3000)
				public class PhoneWorkField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("PhoneWork");
					}
				}

				@Order(4000)
				public class EmailWorkField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("EmailWork");
					}
				}
			}

			@Order(3000)
			public class NotesBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Notes");
				}

				@Order(1000)
				public class NotesField extends AbstractStringField {
					@Override
					protected int getConfiguredGridH() {
						return 4;
					}
					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}
					@Override
					protected boolean getConfiguredMultilineText() {
						return true;
					}
				}
			}
		}

		@Order(100000)
		public class OkButton extends AbstractOkButton {
		}

		@Order(101000)
		public class CancelButton extends AbstractCancelButton {
		}
	}
	
	@Override
	protected boolean execValidate() {
		
		boolean hasFirstName = StringUtility.hasText(getFirstNameField().getValue());
		boolean hasLastName = StringUtility.hasText(getLastNameField().getValue());

		getGeneralBox().clearErrorStatus();
		
		if (!hasFirstName && !hasLastName) {
			String message = TEXTS.get("MissingName");
			getGeneralBox().addErrorStatus(message);
			getFirstNameField().requestFocus();
			throw new VetoException(message);
		}
		
		return true;
	}
	
	public class ModifyHandler extends AbstractFormHandler {
		
		@Override
		protected void execLoad() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);
			getForm().setSubTitle(calculateSubTitle());
		}
		
		@Override
		protected void execStore() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}
		
	public class NewHandler extends AbstractFormHandler {
	
		@Override
		protected void execStore() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = service.create(formData);
			importFormData(formData);
		}
	}
	
	private String calculateSubTitle() {
		return StringUtility.join(" ", getFirstNameField().getValue(),
		getLastNameField().getValue());
	}	

}
