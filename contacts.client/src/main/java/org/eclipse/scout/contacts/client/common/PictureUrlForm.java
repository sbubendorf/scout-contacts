package org.eclipse.scout.contacts.client.common;

import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.CancelButton;
import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.OkButton;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.UrlBox;
import org.eclipse.scout.contacts.client.common.PictureUrlForm.MainBox.UrlBox.UrlField;

public class PictureUrlForm extends AbstractForm {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("PictureURL");
	}

	public void startModify() {
		startInternal(new ModifyHandler());
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public UrlBox getUrlBox() {
		return getFieldByClass(UrlBox.class);
	}

	public UrlField getUrlField() {
		return getFieldByClass(UrlField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
		
		

		@Order(1000)
		public class UrlBox extends AbstractGroupBox {

			@Order(1000)
			public class UrlField extends AbstractStringField {

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
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

	public class ModifyHandler extends AbstractFormHandler {

	}

}
