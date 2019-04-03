package org.eclipse.scout.contacts.shared.person;

import java.security.BasicPermission;

public class UpdatePersonPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public UpdatePersonPermission() {
		super(UpdatePersonPermission.class.getSimpleName());
	}
}
