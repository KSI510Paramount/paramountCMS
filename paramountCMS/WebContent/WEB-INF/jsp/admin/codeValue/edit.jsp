<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
	<form method="post" action="<c:url value="/codeValue/postEdit.do?objectid=${codeValue.objectid}"/>">
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="codeGroup">Code Group:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="codeGroup" name="codeGroup" required value="${codeValue.codeGroup }"/>
			</div>
			<div class="2u 4u(small)">
				<label for="code">Code:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="code" name="code" required value="${codeValue.code }"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="sequence">Seq:</label>
			</div>
			<div class="10u 8u(small)">
				<input type="text" id="seq" name="seq" digit="true" value="${codeValue.seq }"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="shortDescription">Short Description:</label>
			</div>
			<div class="10u 8u(small)">
				<input type="text" id="shortDescription" name="shortDescription" value="${codeValue.shortDescription }"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="longDescription">Long Description:</label>
			</div>
			<div class="10u 8u(small)">
				<input type="text" id="longDescription" name="longDescription" value="${codeValue.longDescription }"/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="effectiveDateTime">Effective Date:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" class="isDate" id="effectiveDateTime" name="effectiveDateTime" date="true" value="<fmt:formatDate value="${codeValue.effectiveDateTime}" />"/>
			</div>
			<div class="2u 4u(small)">
				<label for="effectiveThruDateTime">Effective Thru Date:</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" class="isDate" id="effectiveThruDateTime" name="effectiveThruDateTime" date="true" value="<fmt:formatDate value="${codeValue.effectiveThruDateTime}" />"/>
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
			window.location.href='<c:url value="/codeValue/getList.do"/>';
		});
	} );
})(jQuery); 
</script>