<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit - Training</title>
</head>
<body>
	<div id="main">
		<div class="header">
			<h3 class="header-text">Training</h3>
			<div class="header-br"></div>
		</div>
		<div class = "edit-container">
			<div class = "edit-container__header">
				<span class = "edit-container__headerText edit-container__headerText">Login > Search Customer > Edit Customer Info</span>
				<div class = "edit-container__wellcom">
				<div class = "edit-container__wellcomLeft edit-container__headerText">
				Welcome
				<logic:notEmpty name="namenamewellcom">
					<div class = "edit-container__wellcomname">
						<%="  "+session.getAttribute("namenamewellcom") %>
					</div>
				</logic:notEmpty>
				</div>
					<div class = "edit-container__wellcomRight edit-container__headerText">
						<a href ="/Struts-blank/pages/T001.jsp" onclick = "Logout() ">Log Out</a>
					</div>
				</div>
			
				<div class = "line-br">Add New</div>
			
			
		</div>
	<form action = "/StrutsExample/T003.do" method = "POST">
		<div class = "edit-container__content">
			<div class = "edit-container__content--error">
				
			</div>
			
			<div class = "edit-container__content-Id">
				<div class = "edit-container__content-IdText edit-container__headerText">Customser Id</div>
			
			<logic:empty name="idUser">
				<lable for="userId" class = "edit-container__content-IdLable edit-container__headerText" id = "lblCustomerID" name = "lblCustomerID"></lable>
				
			</logic:empty>
		</div>
			
			<div class = "edit-container__content-Name">
				<lable class = "edit-container__contentName-input edit-container__headerText">Customser Name</lable>
				<logic:empty name="nameUserEdit">
					<input id = "txtCustomerName" type="text" class = "form-with__input" name = "CUSTOMER_NAME" maxlength="50" value = ""/>
				</logic:empty>
				<logic:notEmpty name="nameUserEdit">
					<input id = "txtCustomerName" type="text" class = "form-with__input" name = "CUSTOMER_NAME" maxlength="50" value = "<%=request.getAttribute("nameUserEdit")%>"/>
				</logic:notEmpty>
				
			</div>
			<logic:empty name="sexUserEdit">
				<div class = "edit-container__content-Sex edit-container__btnContent-margin">
				<div class = "edit-container__content-Sexlable edit-container__headerText">Sex</div>
				<select  id="cboSex" name = "SEX">
				  <option class = "cbooption" value=""></option>
				  <option class = "cbooption" value="0" ${dto.SEX == '0' ? 'selected' : ''}>Male</option>
				  <option class = "cbooption" value="1" ${dto.SEX == '1' ? 'selected' : ''}>Female</option>
				</select>
			</div>
			</logic:empty>
			
			
			
			<logic:notEmpty name="sexUserEdit">
				<div class = "edit-container__content-Sex edit-container__btnContent-margin">
				<div class = "edit-container__content-Sexlable edit-container__headerText">Sex</div>
				  <select id="cboSex" id="SEX">
				    <option class = "cbooption" value=""></option>
				    <option class = "cbooption" value="0" <% if(request.getAttribute("sexUserEdit")!=null && request.getAttribute("sexUserEdit").equals("0")){ %>selected<% } %>>Male</option>
				    <option class = "cbooption" value="1" <% if(request.getAttribute("sexUserEdit")!=null && request.getAttribute("sexUserEdit").equals("1")){ %>selected<% } %>>Female</option>
				  </select>
			</div>
			</logic:notEmpty>
			
			
			<div class = "edit-container__content-Birthday edit-container__btnContent-margin">
				<lable class = "edit-container__contentBirthday-input edit-container__headerText">Birthday</lable>
				<logic:empty name="birthdayUserEdit">
					<input id = "txtCustomerBirthday"  class = "form-with__input" name = "BIRTHDAY" maxlenght ="10"/>
				</logic:empty>
				<logic:notEmpty name="birthdayUserEdit">
					<input id = "txtCustomerBirthday"  class = "form-with__input" name = "BIRTHDAY" maxlenght ="10" value= "<%=request.getAttribute("birthdayUserEdit")%>"/>
				</logic:notEmpty>
			</div>
			
			<div class = "edit-container__content-Email edit-container__btnContent-margin">
				<lable class = "edit-container__contentEmail-input edit-container__headerText">Email</lable>
				<logic:empty name="emailUserEdit">
					<input id = "txtCustomerEmail" type="text" class = "form-with__input" name ="EMAIL" maxlenght ="40" />
				</logic:empty>
				<logic:notEmpty name="emailUserEdit">
					<input id = "txtCustomerEmail" type="text" class = "form-with__input" name ="EMAIL" maxlenght ="40" value="<%=request.getAttribute("emailUserEdit")%>"/>
				</logic:notEmpty>
				
			</div>
			
			<div class = "edit-container__content-Address edit-container__btnContent-margin">
				<div class = "test-aline__editAddress">				
					<lable class = "edit-container__contentAddress-input edit-container__headerText">Address</lable>
				</div>
				<logic:empty name="addressUserEdit">
					<textarea id="txaAddress" type="text" maxlength="256"  name = "ADDRESS"></textarea>
				</logic:empty>
				
				<logic:notEmpty name="addressUserEdit">
					<textarea id="txaAddress" type="text" maxlength="256"  name = "ADDRESS"><%=request.getAttribute("addressUserEdit") %></textarea>
				</logic:notEmpty>
			</div>
			
			<div class = "edit-container__btnContent">
				<button type = "submit" class = "edit-container__btnContent-Save">Save</button>
				<button  id = "btnClearEdit" class = "edit-container__btnContent-Clear">Clear</button>
			</div>
		</div>
		<c:if test="${not empty errors.editerror}">
             <div class="form-group__error">
                 <c:out value="${errors.editerror}"/>
             </div>
         </c:if>
	</form>
		</div>
		<footer class="footer">
			<div class="header-br"></div>
			<div class="footer-text__copyright">Copyright (c) 2000-2008
			FUJINET, All Rights Reserved.</div>
		</footer>
	</div>
	<script>
		<%@include file ="/WEB-INF/js/T003.js"%>
	</script>
</body>
</html>