<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/faculty/postEdit.do?objectid=${faculty.objectid }"/>">
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="facultyPrefixOid">Prefix:</label>
			</div>
			<div class="10u 8u(small)">
				<code:codevalue id="facultyPrefixOid" name="facultyPrefixOid" codegroup="PREFIX" requried="false" value="${faculty.objectid }"></code:codevalue>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="firstName">First Name:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="firstName" name="firstName" required value="${faculty.firstName }"/>
			</div>
			<div class="2u 4u(small)">
				<label for="lastName">Last Name:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="lastName" name="lastName" required value="${faculty.lastName }"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="middleName">Middle Name:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="middleName" name="middleName" value="${faculty.middleName }"/>
			</div>
			<div class="2u 4u(small)">
				<label for="birthdate">Birth Date:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="birthdate" name="birthdate" class="isDate" date="true" value="<fmt:formatDate value="${faculty.birthdate}" />"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="genderOid">Gender:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="genderOid" name="genderOid" codegroup="GENDER" requried="false" value="${faculty.genderOid.objectid }"></code:codevalue>
			</div>
			<div class="2u 4u(small)">
				<label for="facultyTypeOid">Faculty Type:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="facultyTypeOid" name="facultyTypeOid" codegroup="STUDENT_TYPE" requried="false" value="${faculty.facultyTypeOid.objectid }"></code:codevalue>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="facultyStatusOid">Faculty Status:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="facultyStatusOid" name="facultyStatusOid" codegroup="STUDENT_STATUS" requried="false" value="${faculty.facultyStatusOid.objectid }"></code:codevalue>
			</div>
			<div class="2u 4u(small)">
				<label for="classStatusOid">Class Status:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="classStatusOid" name="classStatusOid" codegroup="CLASS_STATUS" requried="false" value="${faculty.classStatusOid.objectid }"></code:codevalue>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="username">Username:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="username" name="username" disabled="disabled"/>
			</div>
			<div class="2u 4u(small)">
				<label for="activeFlag">Active Flag:</label>
			</div>
			<div class="4u 8u(small)">
				<c:set value="" var="selected"></c:set>
				<c:if test="${faculty.userOid.activeFlag eq 'Y' }">
					<c:set value="checked" var="selected"></c:set>
				</c:if>
				<input type="checkbox" id="activeFlag" name="activeFlag" ${selected }/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="roleOid">Role:*</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="roleOid" name="roleOid" codegroup="ROLE" requried="true" value="${faculty.userOid.roleOid.objectid }"></code:codevalue>
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
			window.location.href='<c:url value="/faculty/getList.do"/>';
		});
	} );
})(jQuery); 
</script>