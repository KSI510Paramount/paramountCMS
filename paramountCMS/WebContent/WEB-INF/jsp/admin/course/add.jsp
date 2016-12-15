<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<section>
	<form method="post" action="<c:url value="/course/postAdd.do"/>" enctype="multipart/form-data">
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="code">Course Code:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="code" name="code" required/>
			</div>
			<div class="2u 4u(small)">
				<label for="title">Title:*</label>
			</div>
			<div class="4u 8u(small)">
				<input type="text" id="title" name="title" required/>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="description">Description:</label>
			</div>
			<div class="10u 8u(small)">
				<input type="text" id="description" name="description" />
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="syllabus">Syllabus:</label>
			</div>
			<div class="10u 8u(small)">
				<input type="file" id="syllabus" name="syllabus" accept=".pdf,.doc,.docx" style="margin-top: 1.5em; width: 100%"/>
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
			window.location.href='<c:url value="/course/getList.do"/>';
		});
	} );
})(jQuery); 
</script>