<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
	<form method="post" action="<c:url value="/codeValue/postEdit.do?objectid=${codeValue.objectid}"/>">
		<div class="field half first">
			<label for="codeGroup">Code Group:*</label>
			<input type="text" id="codeGroup" name="codeGroup" required value="${codeValue.codeGroup }"/>
		</div>
		<div class="field half">
			<label for="code">Code:*</label>
			<input type="text" id="code" name="code" required value="${codeValue.code }"/>
		</div>
		<div class="field">
			<label for="sequence">Seq:</label>
			<input type="text" id="seq" name="seq" digit="true" value="${codeValue.seq }"/>
		</div>
		<div class="field">
			<label for="shortDescription">Short Description:</label>
			<input type="text" id="shortDescription" name="shortDescription" value="${codeValue.shortDescription }"/>
		</div>
		<div class="field">
			<label for="longDescription">Long Description:</label>
			<input type="text" id="longDescription" name="longDescription" value="${codeValue.longDescription }"/>
		</div>
		<div class="field half first">
			<label for="effectiveDateTime">Effective Date:</label>
			<input type="text" class="isDate" id="effectiveDateTime" name="effectiveDateTime" date="true" value="<fmt:formatDate value="${codeValue.effectiveDateTime}" />"/>
		</div>
		<div class="field half">
			<label for="effectiveThruDateTime">Effective Thru Date:</label>
			<input type="text" class="isDate" id="effectiveThruDateTime" name="effectiveThruDateTime" date="true" value="<fmt:formatDate value="${codeValue.effectiveThruDateTime}" />"/>
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