<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
<form id="attendanceForm" method="post" action="<c:url value="/attendance/postEditAttendance.do?objectid=${attendance.objectid }"/>">
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label>Course:</label>
		</div>
		<div class="10u 8u(small)">
			<label>${courseOffer.courseOid.code} - ${courseOffer.courseOid.title}</label>
		</div>
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="attenTypeOid">Status:*</label>
		</div>
		<div class="4u 8u(small)">
			<code:codevalue id="attenTypeOid" name="attenTypeOid" codegroup="ATTTYPE" requried="true" value="${attendance.attenTypeOid.objectid }" ></code:codevalue>
		</div>
		<div class="2u 4u(small)">
			<label for="attendamceDate">Date:*</label>
		</div>
		<div class="4u 8u(small)">
			<input type="text" id="attendanceDate" name="attendanceDate" value="${attendance.attendanceDate}" class="isDate" date="true" required/>
		</div>
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="comments">Comment:</label>
		</div>
		<div class="10u 8u(small)">
			<input type="text" id="comments" name="comments" value="${attendance.comments}" />
		</div>
	</div>
	<ul class="actions">
		<li><input id="submitButton" type="submit" value="Submit" class="special" /></li>
		<li><input type="button" id="cancel" value="Cancel" class="special"/></li>
	</ul>
</form>
</section>
<script>
(function($){
	$(document).ready(function() {
		$(".isDate").datepicker();
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/attendance/getViewStudent.do?objectid=${attendance.enrollmentOid.courseOfferOid.objectid}"/>';
		});
	} );
})(jQuery); 

</script>