<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/semester/postEdit.do?objectid=${semester.objectid }"/>">
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="semesterYear">Year:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="semesterYear" name="semesterYear" required maxlength="4" digit="true" value="${semester.semesterYear }"/>
			</div>
			<div class="2u 4u(small)">
				<label for="semesterTypeOid">Semester Type:*</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="semesterTypeOid" name="semesterTypeOid" codegroup="SEMESTER" requried="true" value="${semester.semesterTypeOid.objectid }"></code:codevalue>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="semStartDate">Start Date:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="semStartDate" name="semStartDate" class="isDate" date="true" value="${semester.semStartDate }"/>
			</div>
			<div class="2u 4u(small)">
				<label for="semEndDate">End Date:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="semEndDate" name="semEndDate" class="isDate" date="true" value="${semester.semEndDate }"/>
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
			window.location.href='<c:url value="/semester/getList.do"/>';
		});
	} );
})(jQuery); 
</script>