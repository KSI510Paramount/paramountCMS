<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
<a class="addLink" href="<c:url value="/semester/getAdd.do"/>">[Add Semester]</a>
	<table id="studentTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Semester Year</th>
				<th>Semester Type</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${semesterList}" var="semester">
				<tr>
					<td><c:out value="${semester.semesterYear }"></c:out></td>
					<td><c:out value="${semester.semesterTypeOid.shortDescription }"></c:out></td>
					<td><fmt:formatDate value="${semester.semStartDate}" /></td>
					<td><fmt:formatDate value="${semester.semEndDate}" /></td>
					<td>
						<a href="<c:url value="/semester/getEdit.do?objectid=${semester.objectid }"/>">[Edit Semester]</a>&nbsp;
						<a href="<c:url value="/semester/getView.do?objectid=${semester.objectid }"/>">[Add Course Offer]</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<script>
(function($){
	$(document).ready(function() {
		$('#studentTable').DataTable({
			"bLengthChange": false,
		    "bFilter": false,
		    "responsive": true,
			"processing": true,
		});
	} );
})(jQuery); 

</script>