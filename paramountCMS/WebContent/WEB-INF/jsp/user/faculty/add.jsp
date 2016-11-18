<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/student/postAdd.do"/>">
		<div class="field half first">
			<label for="firstName">First Name:*</label>
			<input type="text" id="firstName" name="firstName" required/>
		</div>
		<div class="field half">
			<label for="lastName">Last Name:*</label>
			<input type="text" id="lastName" name="lastName" required/>
		</div>
		<div class="field half first">
			<label for="middleName">Middle Name:</label>
			<input type="text" id="middleName" name="middleName" />
		</div>
		<div class="field half">
			<label for="birthdate">Birth Date:</label>
			<input type="text" id="birthdate" name="birthdate" class="isDate" date="true"/>
		</div>
		<div class="field half first">
			<label for="genderOid">Gender:</label>
			<code:codevalue id="genderOid" name="genderOid" codegroup="GENDER" requried="false" ></code:codevalue>
		</div>
		<div class="field half">
			<label for="studentTypeOid">Student Type:</label>
			<code:codevalue id="studentTypeOid" name="studentTypeOid" codegroup="STUDENT_TYPE" requried="false"></code:codevalue>
		</div>
		<div class="field half first">
			<label for="studentStatusOid">Student Status:</label>
			<code:codevalue id="studentStatusOid" name="studentStatusOid" codegroup="STUDENT_STATUS" requried="false" ></code:codevalue>
		</div>
		<div class="field half">
			<label for="classStatusOid">Class Status:</label>
			<code:codevalue id="classStatusOid" name="classStatusOid" codegroup="CLASS_STATUS" requried="false"></code:codevalue>
		</div>
		<ul class="actions">
			<li><input type="submit" value="Add" class="special" /></li>
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