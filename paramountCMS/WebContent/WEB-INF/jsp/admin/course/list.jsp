<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<section>
<a class="addLink" href="<c:url value="/course/getAdd.do"/>">[Add]</a>
	<table id="courseTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Course Number</th>
				<th>Title</th>
				<th>Description</th>
				<th>Syllabus</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courseList}" var="course">
				<tr>
					<td><c:out value="${course.code }"></c:out></td>
					<td><c:out value="${course.title }"></c:out></td>
					<td><c:out value="${course.description }"></c:out></td>
					<td>
						<c:if test="${not empty course.syllabus }">
							<c:url value="/assets/images/word.png" var="src"/>
							<c:if test="${course.fileExtension eq 'pdf' }">
								<c:url value="/assets/images/pdf.jpg" var="src"/>
							</c:if>
							<a href="<c:url value="/course/getDocView.do?objectid=${course.objectid }" />">
								<img src='<c:out value="${src }" />' style="height: 30px; width: 30px; cursor: pointer;" />
							</a>
							
						</c:if>
					</td>
					<td>
						<a href="<c:url value="/course/getEdit.do?objectid=${course.objectid }"/>">[Edit]</a>&nbsp;
						<%-- <a href="<c:url value="/course/getDelete.do?objectid=${course.objectid }"/>">[Delete]</a> --%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<script>
(function($){
	$(document).ready(function() {
		$('#courseTable').DataTable();
	} );
})(jQuery); 

</script>