/**
 * 
 */
package org.srcm.pmp.service.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.srcm.pmp.config.ApplicationContextConfig;
import org.srcm.pmp.dao.SeekerDAO;
import org.srcm.pmp.domain.Program;
import org.srcm.pmp.services.ParticipantService;

/**
 * @author rudra
 *
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationContextConfig.class,loader=AnnotationConfigContextLoader.class)
@Transactional
public class PMPServicesTest {
	
	@Autowired
	private SeekerDAO dao;
	
	@Autowired
	private ParticipantService participantService;

	/**
	 * Test method for {@link org.srcm.pmp.services.ParticipantService#persistSeekerDetailsFromExcel(org.springframework.web.multipart.commons.CommonsMultipartFile)}.
	 */
	
	/*@Test
	
	public void testPersistSeekerDetailsFromExcel() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt = df.parse("2015-08-29");
			List<Program> program = dao.findProgram("HFN, 3-day Introduction", null);
			System.out.println("Programs::"+program.size());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
