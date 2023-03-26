package fjs.com.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import fjs.com.Dao.T002Dao;
import fjs.com.Dto.T002Dto;

public class T002 extends Action{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String indexPage = request.getParameter("index");
			if (indexPage == null) {
				indexPage = "1";
			}
			String pages = request.getParameter("page");
			if (pages == null) {
				pages= "1";
			}
			
			//get data
			List<T002Dto> list = null;
			T002Dao dao = new T002Dao();
			int count = dao.getDataPage();
			int endpage = count/5;
			if (count % 5 != 0) {
				endpage++;
			}
			
			int index = Integer.parseInt(indexPage);

			List<T002Dto> listPage = dao.pagingData(index);

			request.setAttribute("tag", index);
			request.setAttribute("ListData", listPage);
			request.setAttribute("endP", endpage);
			deleteData(request, mapping);
			
			T002Dto searchBean = (T002Dto)form;
			String name = searchBean.getCUSTOMER_NAME();
			String sex = searchBean.getSEX();
			String birthdayFrom = searchBean.getBIRTHDAYFROM();
			String birthdayTo = searchBean.getBIRTHDAYTO();
			
			int page = Integer.parseInt(pages);
			
			String page2 = request.getParameter("pagepage");
	        HttpSession session = request.getSession();

	        if (page2 != null && !page2.isEmpty()) {
	            String sex1 = (String) session.getAttribute("sex");
	            String birthdayFrom1 = (String) session.getAttribute("birthdayFrom");
	            String birthdayTo1 = (String) session.getAttribute("birthdayTo");
	            processSearch(request, response, name, sex1, birthdayFrom1, birthdayTo1, page2);
	        } else {
	        	page2 = "1";
	            request.setAttribute("nameSearch", name);
	            request.setAttribute("sexSearch", sex);
	            request.setAttribute("birthdayFromSearch", birthdayFrom);
	            request.setAttribute("birthdayToSearch", birthdayTo);
	            processSearch(request, response, name, sex, birthdayFrom, birthdayTo, page2);
	            session.setAttribute("sex", sex);
	            session.setAttribute("birthdayFrom", birthdayFrom);
	            session.setAttribute("birthdayTo", birthdayTo);
	            
	        }
	        
	        return (mapping.findForward("T002"));
		}catch(Exception e) {
			e.printStackTrace();
			return (mapping.findForward("T002"));
		}
		
	}
	
	private void processSearch(HttpServletRequest request, HttpServletResponse response, String name, String sex, String birthdayFrom, String birthdayTo, String page) {
	    T002Dao daoSearch = new T002Dao();
	    List<T002Dto> resultSearch = daoSearch.getDataSearch(name, sex, birthdayFrom, birthdayTo);
	    int recordsPerPage = 15;
	    int startIndex = (Integer.parseInt(page) - 1) * recordsPerPage;
	    int endIndex = startIndex + recordsPerPage;
	    int totalRecords = resultSearch.size();
	    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
	    List<T002Dto> resultPaginated = resultSearch.subList(startIndex, Math.min(endIndex, totalRecords));
	    if (resultPaginated.size() == 0) {
	    	request.setAttribute("buttonDelete", resultPaginated);
	    }
	    request.setAttribute("ListSearch", resultPaginated);
	    request.setAttribute("currentPage", page);
	    request.setAttribute("recordsPerPage", recordsPerPage);
	    request.setAttribute("totalRecords", totalRecords);
	    request.setAttribute("totalPages", totalPages);  
	}
	
	private ActionErrors deleteData(HttpServletRequest request, ActionMapping mapping)  {
	    T002Dao daoSearch = new T002Dao();
	    ActionErrors errors = new ActionErrors();
	    String[] ids = request.getParameterValues("selectedValues");
	    if (ids == null) {
	    	
	    	errors.add("logerError", new ActionMessage("MESSAGE_CHECKOFF"));
	    	return errors;
	    }
	    if (ids != null && ids.length > 0) {
	        String idList = String.join(",", ids);
	        idList = idList.replace("[\"", "").replace("\"]", "");
	        String result = idList.replaceAll("[^\\d,]", "").replace("\"", "");
	        String[] result2 = result.split(",");
	        daoSearch.deleteData(result2);
	    }
	    return errors;
	}
}
