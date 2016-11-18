<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
	<form method="post" action="<c:url value="/course/postEdit.do?objectid=${course.objectid }"/>" enctype="multipart/form-data">
		<div class="field half first">
			<label for="code">Course Code:*</label>
			<input type="text" id="code" name="code" disabled="disabled" value="${course.code }"/>
		</div>
		<div class="field half">
			<label for="title">Title:*</label>
			<input type="text" id="title" name="title" required value="${course.title }"/>
		</div>
		<div class="field">
			<label for="description">Description:</label>
			<textarea id="description" name="description" cols="15" rows="4">${course.description}</textarea>
		</div>
		<div class="field">
			<label for="syllabus">Syllabus:</label>
			<input type="file" id="syllabus" name="syllabus" accept=".pdf,.doc,.docx"/>
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
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/course/getList.do"/>';
		});
	} );
})(jQuery); 
</script>