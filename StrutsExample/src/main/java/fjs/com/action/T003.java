package fjs.com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import fjs.com.Dao.T003Dao;
import fjs.com.Dto.T003Dto;

public class T003 extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		T003Dto editBean = (T003Dto)form;
		int id = editBean.getCUSTOMER_ID();
		String name = editBean.getCUSTOMER_NAME();
		String sex = editBean.getSEX();
		String birthday = editBean.getBIRTHDAY();
		String email = editBean.getEMAIL();
		String address = editBean.getADDRESS();
		
		try {
			HttpSession session = request.getSession();
	        /**
	         * Kiểm tra nếu id = rỗng thì là save
	         * Ngoài ra id khác rỗng thì là update
	         */
			String idFoword = request.getParameter("id");
			if (idFoword != null) {
				
				editBean = T003Dao.getCustomerById(Integer.parseInt(idFoword));
				request.setAttribute("dto", editBean);
				return mapping.findForward("T003");
			}
			
			
	        int status = 0;
	        T003Dao dao = new T003Dao();
	        if (id == 0) {
	        	T003Dto dtosave = new T003Dto();
	        	dtosave.setCUSTOMER_NAME(name);
	        	dtosave.setSEX(sex);
	        	dtosave.setBIRTHDAY(birthday);
	        	dtosave.setEMAIL(email);
	        	dtosave.setADDRESS(address);
	            status = dao.save(dtosave, session);
	            
	        }else if (id != 0) {	 
	        	T003Dto dto = new T003Dto();
		        dto.setCUSTOMER_ID(id);
		        dto.setCUSTOMER_NAME(name);
		        dto.setSEX(sex);
		        dto.setBIRTHDAY(birthday);
		        dto.setEMAIL(email);
		        dto.setADDRESS(address);
	        	status = dao.update(dto, session);
	        }
	        if (status > 0) {
	        	return mapping.findForward("T002");
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("T002");
	}
}
