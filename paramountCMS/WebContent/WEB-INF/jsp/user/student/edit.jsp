<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/student/postEdit.do?objectid=${student.objectid }"/>">
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="sid">Student ID:</label>
			</div>
			<div class="10u 8u(small)">
				<input type="text" id="sid" name="sid" value="${student.studentId }" disabled="disabled"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="firstName">First Name:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="firstName" name="firstName" required value="${student.firstName }"/>
			</div>
			<div class="2u 4u(small)">
				<label for="lastName">Last Name:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="lastName" name="lastName" required value="${student.lastName }"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="middleName">Middle Name:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="middleName" name="middleName" value="${student.middleName }"/>
			</div>
			<div class="2u 4u(small)">
				<label for="birthdate">Birth Date:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="birthdate" name="birthdate" class="isDate" date="true" value="<fmt:formatDate value="${student.birthdate}" />"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="genderOid">Gender:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="genderOid" name="genderOid" codegroup="GENDER" requried="false" value="${student.genderOid.objectid }"></code:codevalue>
			</div>
			<div class="2u 4u(small)">
				<label for="studentTypeOid">Student Type:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="studentTypeOid" name="studentTypeOid" codegroup="STUDENT_TYPE" requried="false" value="${student.studentTypeOid.objectid }"></code:codevalue>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="studentStatusOid">Student Status:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="studentStatusOid" name="studentStatusOid" codegroup="STUDENT_STATUS" requried="false" value="${student.studentStatusOid.objectid }"></code:codevalue>
			</div>
			<div class="2u 4u(small)">
				<label for="classStatusOid">Class Status:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="classStatusOid" name="classStatusOid" codegroup="CLASS_STATUS" requried="false" value="${student.classStatusOid.objectid }"></code:codevalue>
			</div>
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