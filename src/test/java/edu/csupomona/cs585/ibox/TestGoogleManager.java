package edu.csupomona.cs585.ibox;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;


import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.Drive.Files.*;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.client.http.AbstractInputStreamContent;

import edu.csupomona.cs585.ibox.sync.GoogleDriveFileSyncManager;
import junit.framework.TestCase;

public class TestGoogleManager extends TestCase {
	
	
	
	public Drive mock_drive = mock(Drive.class);
	public GoogleDriveFileSyncManager googleManager = new GoogleDriveFileSyncManager(mock_drive);
	File file_obj = new File();
	private Files files;
	private Insert insertFile;
	private java.io.File local;
	private File fileobj1;
	private File fileobj2;
	private Files files2;
	private Update updateFile;
	private List listobj;
	private FileList filelistobj;
	private Delete deletefileobj;
	private List listobj2;
	private FileList filelistobj2;
	
	@Test
	public void TestingAddFunctionInMainClass() throws Exception {
		java.io.File java_io_file_mock = mock(java.io.File.class);
		
		files = mock(Files.class);
		insertFile = mock(Insert.class);

		when(mock_drive.files()).thenReturn(files);
		when(files.insert(isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(insertFile);
		when(insertFile.execute()).thenReturn(file_obj);

		googleManager.addFile(java_io_file_mock);

		
		verify(mock_drive).files();
		verify(files).insert(isA(File.class), isA(AbstractInputStreamContent.class));
		verify(insertFile).execute();
	}

	@Test
	public void TestingUpdateFunctionInMainClass() throws Exception {
		local = mock(java.io.File.class);
		fileobj1 = new File();
		fileobj2 = new File();
		files2 = mock(Files.class);
		updateFile = mock(Update.class);
		listobj = mock(List.class);
		filelistobj = new FileList();

		fileobj1.setTitle("temp.txt");
		fileobj1.setId("temp2");

		ArrayList<File> fileArray = new ArrayList<File>();
		fileArray.add(fileobj1);

		filelistobj.setItems(fileArray);

		when(local.getName()).thenReturn("temp.txt");
		when(files2.list()).thenReturn(listobj);
		when(listobj.execute()).thenReturn(filelistobj);

		when(mock_drive.files()).thenReturn(files2);
		when(files2.update(isA(String.class), isA(File.class), isA(AbstractInputStreamContent.class))).thenReturn(updateFile);
		when(updateFile.execute()).thenReturn(fileobj2);
		
		googleManager.updateFile(local);
		
		
		verify(local, atLeast(1)).getName();
		verify(files2, atLeast(1)).list();
		verify(listobj, atLeast(1)).execute();
		
		verify(mock_drive, atLeast(1)).files();
		verify(files2, atLeast(1)).update(isA(String.class), isA(File.class), isA(AbstractInputStreamContent.class));
		verify(updateFile, atLeast(1)).execute();
	}
	
	@Test
	public void TestingDeleteFunctionInMainClass() throws Exception{
		java.io.File local = mock(java.io.File.class);
		
		
		Files files = mock(Files.class);
		deletefileobj = mock(Delete.class);
		listobj2 = mock(List.class);
		filelistobj2 = new FileList();

		fileobj1.setTitle("temp.txt");
		fileobj1.setId("temp2");

		ArrayList<File> fileArray = new ArrayList<File>();
		fileArray.add(fileobj1);

		filelistobj2.setItems(fileArray);

		when(local.getName()).thenReturn("temp.txt");
		when(files.list()).thenReturn(listobj2);
		when(listobj2.execute()).thenReturn(filelistobj2);

		when(mock_drive.files()).thenReturn(files);
		when(files.delete(isA(String.class))).thenReturn(deletefileobj);
		when(deletefileobj.execute()).thenReturn(null);
		
		googleManager.deleteFile(local);
		
		
		verify(listobj2, atLeast(1)).execute();

		verify(mock_drive, atLeast(1)).files();
		verify(files, atLeast(1)).delete(isA(String.class));
		verify(deletefileobj, atLeast(1)).execute();
		
		
	}

}
