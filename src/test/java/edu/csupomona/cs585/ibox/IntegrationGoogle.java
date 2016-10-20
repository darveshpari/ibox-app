package edu.csupomona.cs585.ibox;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.lang.*;
import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.api.services.drive.Drive;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import edu.csupomona.cs585.ibox.sync.GoogleDriveServiceProvider;
import junit.framework.TestCase;

public class IntegrationGoogle {

	
	public GoogleDriveFileSyncManager googleObj = new GoogleDriveFileSyncManager(GoogleDriveServiceProvider.get().getGoogleDriveClient());
	File fileObj = new File("/Users/darveshparisingh/Desktop/585/darvesh.rtf");

	@Test
	public void testAdd() throws Exception {
		System.out.println("This is the function for testing Add File");
		googleObj.addFile(fileObj);
		String value;
		value = googleObj.getFileId("darvesh.rtf");
		if (value != null) {
			assertTrue(true);

		} else {
			assertFalse(false);
			System.out.println("no such file found");
		}
	}
	
	@Test
	public void testUpdate() throws Exception {
		System.out.println("This is the function for testing update File");
		googleObj.updateFile(fileObj);
		String value;
		value = googleObj.getFileId("darvehsh.rtf");
		if (value != null) {
			assertTrue(true);

		} else {
			assertFalse(false);
			System.out.println("no such file found");
		}
	}

	@Test
	public void testDelete() throws Exception {
		System.out.println("This is the function for testing delete File");
		googleObj.deleteFile(fileObj);
		String value;
		value = googleObj.getFileId("darvesh.rtf");
		if (value != null) {
			assertTrue(true);

		} else {
			assertFalse(false);
			System.out.println("no such file found");
		}
	}

}
