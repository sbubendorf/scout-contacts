package org.eclipse.scout.contacts.client.common;

import java.net.URL;
import java.util.Set;

import org.eclipse.scout.contacts.shared.common.AbstractUrlImageFieldData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.DefaultSubtypeSdkCommand;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.ImageFieldMenuType;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.platform.status.Status;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.IOUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;

@FormData(value = AbstractUrlImageFieldData.class, sdkCommand = SdkCommand.CREATE, defaultSubtypeSdkCommand = DefaultSubtypeSdkCommand.CREATE)
public class AbstractUrlImageField extends AbstractImageField {

	private String url;
	
	@FormData
	public String getUrl() {
		return this.url;
	}
	
	@FormData
	public void setUrl(String url) {
		this.url = url;
		//updateImage();
	}
	
	@Override
	protected boolean getConfiguredLabelVisible() {
		return false;
	}
	
	@Override
	protected int getConfiguredGridH() {
		return 5;
	}
	
	protected void updateImage() {
		clearErrorStatus();
		if (url == null) {
			setImage(null);
		}
		else {
			try {
				setImage(IOUtility.readFromUrl(new URL((String) url)));
				setAutoFit(true);
			}
			catch (Exception e) {
				addErrorStatus(new Status(TEXTS.get("FailedToAccessImageFromUrl"), IStatus.WARNING));
			}
		}
		getForm().touch();
	}

	@Order(1000)
	public class EditUrlMenu extends AbstractMenu {
		@Override
		protected String getConfiguredText() {
			return TEXTS.get("ChangeURL");
		}
		
		@Override
		protected Set<? extends IMenuType> getConfiguredMenuTypes() {
			return CollectionUtility.hashSet(ImageFieldMenuType.Image, ImageFieldMenuType.Null);
		}

		@Override
		protected void execAction() {
			PictureUrlForm form = new PictureUrlForm();
			String oldUrl = getUrl();
			if (StringUtility.hasText(oldUrl)) {
				form.getUrlField().setValue(oldUrl);
			}
			form.startModify();
			form.waitFor();		// Method waitFor makes the application wait until the user has closed the form.
			
			if (form.isFormStored()) {
				setUrl(form.getUrlField().getValue());
			}
		}
	}
}
