<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/student/postAdd.do"/>">
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="firstName">First Name:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="firstName" name="firstName" required/>
			</div>
			<div class="2u 4u(small)">
				<label for="lastName">Last Name:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="lastName" name="lastName" required/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="middleName">Middle Name:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="middleName" name="middleName" />
			</div>
			<div class="2u 4u(small)">
				<label for="birthdate">Birth Date:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="birthdate" name="birthdate" class="isDate" date="true"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="genderOid">Gender:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="genderOid" name="genderOid" codegroup="GENDER" requried="false" ></code:codevalue>
			</div>
			<div class="2u 4u(small)">
				<label for="studentTypeOid">Student Type:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="studentTypeOid" name="studentTypeOid" codegroup="STUDENT_TYPE" requried="false"></code:codevalue>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="studentStatusOid">Student Status:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="studentStatusOid" name="studentStatusOid" codegroup="STUDENT_STATUS" requried="false" ></code:codevalue>
			</div>
			<div class="2u 4u(small)">
				<label for="classStatusOid">Class Status:</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="classStatusOid" name="classStatusOid" codegroup="CLASS_STATUS" requried="false"></code:codevalue>
			</div>
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