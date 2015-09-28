package org.srcm.pmp.fileupload.dao;

import org.srcm.pmp.domain.Program;
import org.srcm.pmp.domain.UploadFile;

public interface FileUploadDAO {
	void save(UploadFile uploadFile);
	void save(Program programData);
}
