package org.srcm.pmp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.srcm.pmp.domain.UploadFile;
import org.srcm.pmp.fileupload.dao.FileUploadDAO;
import org.srcm.pmp.services.ParticipantService;

/**
 * Handles requests for the file upload page.
 */
@Controller
public class HomeController {
	@Autowired
	private FileUploadDAO fileUploadDao;
	@Autowired
	private ParticipantService participantService;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showUploadForm(HttpServletRequest request) {
		return "Upload";
	}
	
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request,
            @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
         
        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload){
                 
                System.out.println("Saving file: " + aFile.getFileItem().getName());
                
                UploadFile uploadFile = new UploadFile();
                uploadFile.setFileName(aFile.getOriginalFilename());
                uploadFile.setData(aFile.getBytes());
             //   fileUploadDao.save(uploadFile);     
                if((aFile.getFileItem().getName().endsWith("xlsx")||
                		aFile.getFileItem().getName().endsWith("xls"))
                		&& aFile.getBytes()!=null && aFile.getBytes().length>0){
                	participantService.persistSeekerDetailsFromExcel(aFile);
                }
            }
        }
 
        return "Success";
    }	
}
