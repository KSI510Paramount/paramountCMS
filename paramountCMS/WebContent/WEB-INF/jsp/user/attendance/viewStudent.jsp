<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
<form id="gradeForm" method="post" >
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label>Course:</label>
		</div>
		<div class="10u 8u(small)">
			<label>${courseOffer.courseOid.code} - ${courseOffer.courseOid.title}</label>
		</div>
	</div>
	<br />
	<table id="attendanceTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Gender</th>
				<th>Type</th>
				<th>Student Status</th>
				<th>Class Status</th>
				<th>Class Date</th>
				<th>Attendance</th>
				<th>Comments</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${attendanceList}" var="attendace">
				<tr>
					<td><c:out value="${attendace.enrollmentOid.studentOid.firstName}"></c:out></td>
					<td><c:out value="${attendace.enrollmentOid.studentOid.lastName }"></c:out></td>
					<td><c:out value="${attendace.enrollmentOid.studentOid.genderOid.shortDescription }"></c:out></td>
					<td><c:out value="${attendace.enrollmentOid.studentOid.studentTypeOid.shortDescription }"></c:out></td>
					<td><c:out value="${attendace.enrollmentOid.studentOid.studentStatusOid.shortDescription }"></c:out></td>
					<td><c:out value="${attendace.enrollmentOid.studentOid.classStatusOid.shortDescription }"></c:out></td>
					<td><fmt:formatDate value="${attendace.attendanceDate}" /></td>
					<td><c:out value="${attendace.attenTypeOid.shortDescription }"></c:out></td>
					<td><c:out value="${attendace.comments }"></c:out></td>
					<td>
						<a href="<c:url value="/attendance/getEditAttendance.do?objectid=${attendace.objectid }"/>">[Edit Attendance]</a>&nbsp;
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="actions">
		<li><input type="button" id="cancel" value="Cancel" class="special"/></li>
	</ul>
</form>
</section>
<script>
(function($){
	$(document).ready(function() {
		

		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/attendance/getList.do"/>';
		});
		
	} );
})(jQuery); 
</script>