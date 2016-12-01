<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/codeValue/assignment/postAdd.do?objectid=${courseOfferOid }"/>">
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="assignmentTitle">Assignment Title:*</label>
			</div>
			<div class="10u 8u(small)">
				<input type="text" name="assignmentTitle" id="assignmentTitle" required/>
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
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/grade/getView.do?objectid=${courseOfferOid }"/>';
		});
	} );
})(jQuery); 
</script>