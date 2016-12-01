<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
	<table id="courseEnrollmentTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Semester</th>
				<th>Course Number</th>
				<th>Title</th>
				<th>Description</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Location</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courseOfferList}" var="courseOffer">
				<tr>
					<td><c:out value="${courseOffer.semesterOid.semesterTypeOid.shortDescription} - ${courseOffer.semesterOid.semesterYear}"></c:out></td>
					<td><c:out value="${courseOffer.courseOid.code }"></c:out></td>
					<td><c:out value="${courseOffer.courseOid.title }"></c:out></td>
					<td><c:out value="${courseOffer.courseOid.description }"></c:out></td>
					<td><fmt:formatDate value="${courseOffer.startDate}" /></td>
					<td><fmt:formatDate value="${courseOffer.endDate}" /></td>
					<td><c:out value="${courseOffer.locationOid.shortDescription }"></c:out></td>
					<td>
						<a href="<c:url value="/attendance/getView.do?objectid=${courseOffer.objectid }"/>">[Attendance]</a>&nbsp;
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<script>
(function($){
	$(document).ready(function() {
		$('#courseEnrollmentTable').DataTable({
			"bLengthChange": false,
		    "bFilter": false,
		    "responsive": true,
			"processing": true,
		});
	} );
})(jQuery); 

</script>