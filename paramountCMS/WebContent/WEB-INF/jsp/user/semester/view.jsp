<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
<a class="addLink" href="<c:url value="/semester/getAddCourseOffer.do?objectid=${semester.objectid }"/>">[Add Course Offer]</a>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label>Semester Year:</label>
		</div>
		<div class="4u 8u(small)">
			<label>${semester.semesterYear }</label>
		</div>
		<div class="2u 4u(small)">
			<label>Semester Type:</label>
		</div>
		<div class="4u 8u(small)">
			<label>${semester.semesterTypeOid.shortDescription }</label>
		</div>
	</div>
	<table id="courseOfferTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
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
					<td><c:out value="${courseOffer.courseOid.code }"></c:out></td>
					<td><c:out value="${courseOffer.courseOid.title }"></c:out></td>
					<td><c:out value="${courseOffer.courseOid.description }"></c:out></td>
					<td><fmt:formatDate value="${courseOffer.startDate}" /></td>
					<td><fmt:formatDate value="${courseOffer.endDate}" /></td>
					<td><c:out value="${courseOffer.locationOid.shortDescription }"></c:out></td>
					<td>
						<a href="<c:url value="/semester/getEditCourseOffer.do?objectid=${courseOffer.objectid }"/>">[Edit Course Offer]</a>&nbsp;
						<a href="<c:url value="/semester/getDeleteCourseOffer.do?objectid=${courseOffer.objectid }"/>">[Delete Course Offer]</a>&nbsp;
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<script>
(function($){
	$(document).ready(function() {
		$('#courseOfferTable').DataTable();
	} );
})(jQuery); 

</script>