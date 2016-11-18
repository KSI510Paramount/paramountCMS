<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/student/postEdit.do?objectid=${student.objectid }"/>">
		<div class="field">
			<label for="sid">First Name:*</label>
			<input type="text" id="sid" name="sid" value="${student.studentId }"/>
		</div>
		<div class="field half first">
			<label for="firstName">First Name:*</label>
			<input type="text" id="firstName" name="firstName" required value="${student.firstName }"/>
		</div>
		<div class="field half">
			<label for="lastName">Last Name:*</label>
			<input type="text" id="lastName" name="lastName" required value="${student.lastName }"/>
		</div>
		<div class="field half first">
			<label for="middleName">Middle Name:</label>
			<input type="text" id="middleName" name="middleName" value="${student.middleName }"/>
		</div>
		<div class="field half">
			<label for="birthdate">Birth Date:</label>
			<input type="text" id="birthdate" name="birthdate" class="isDate" date="true" value="<fmt:formatDate value="${student.birthdate}" />"/>
		</div>
		<div class="field half first">
			<label for="genderOid">Gender:</label>
			<code:codevalue id="genderOid" name="genderOid" codegroup="GENDER" requried="false" value="${student.genderOid.objectid }"></code:codevalue>
		</div>
		<div class="field half">
			<label for="studentTypeOid">Student Type:</label>
			<code:codevalue id="studentTypeOid" name="studentTypeOid" codegroup="STUDENT_TYPE" requried="false" value="${student.studentTypeOid.objectid }"></code:codevalue>
		</div>
		<div class="field half first">
			<label for="studentStatusOid">Student Status:</label>
			<code:codevalue id="studentStatusOid" name="studentStatusOid" codegroup="STUDENT_STATUS" requried="false" value="${student.studentStatusOid.objectid }"></code:codevalue>
		</div>
		<div class="field half">
			<label for="classStatusOid">Class Status:</label>
			<code:codevalue id="classStatusOid" name="classStatusOid" codegroup="CLASS_STATUS" requried="false" value="${student.classStatusOid.objectid }"></code:codevalue>
		</div>
		<ul class="actions">
			<li><input type="submit" value="Update" class="special" /></li>
			<li><input type="button" id="cancel" value="Cancel" class="special"/></li>
		</ul>
	</form>
</section>
<script>
(function($){
	$(document).ready(function() {
		$(".isDate").datepicker();
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/student/getList.do"/>';
		});
	} );
})(jQuery); 
</script>