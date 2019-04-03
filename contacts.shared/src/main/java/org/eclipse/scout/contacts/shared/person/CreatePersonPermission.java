package org.eclipse.scout.contacts.shared.person;

import java.security.BasicPermission;

public class CreatePersonPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public CreatePersonPermission() {
		super(CreatePersonPermission.class.getSimpleName());
	}
}
