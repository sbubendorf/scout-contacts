package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonFormData;
import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class PersonFormTest {

	@BeanMock
	private IPersonService m_mockSvc;

	@Before
	public void setup() {
		PersonFormData answer = new PersonFormData();
		Mockito.when(m_mockSvc.prepareCreate(ArgumentMatchers.any())).thenReturn(answer);
		Mockito.when(m_mockSvc.create(ArgumentMatchers.any())).thenReturn(answer);
		Mockito.when(m_mockSvc.load(ArgumentMatchers.any())).thenReturn(answer);
		Mockito.when(m_mockSvc.store(ArgumentMatchers.any())).thenReturn(answer);
	}

	// TODO [B040910] add test cases
}
