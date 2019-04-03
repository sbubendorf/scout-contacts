package org.eclipse.scout.contacts.shared.person;

import java.security.BasicPermission;

public class ReadPersonPermission extends BasicPermission {

	private static final long serialVersionUID = 1L;

	public ReadPersonPermission() {
		super(ReadPersonPermission.class.getSimpleName());
	}
}
