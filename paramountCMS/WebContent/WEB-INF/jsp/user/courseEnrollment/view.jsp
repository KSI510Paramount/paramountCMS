<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
		<ul class="actions">
			<li><input type="button" id="back" value="Back" class="special" /></li>
		</ul>
<a class="addLink" href="<c:url value="/enroll/getEnroll.do?objectid=${courseOffer.objectid }"/>">[Enroll Student]</a>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label>Course:</label>
		</div>
		<div class="10u 8u(small)">
			<label>${courseOffer.courseOid.code} - ${courseOffer.courseOid.title}</label>
		</div>
	</div>
	<table id="enrollmentTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Gender</th>
				<th>Type</th>
				<th>Status</th>
				<th>Class Status</th>
				<th>Completion Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${enrollmentList}" var="enrollment">
				<tr>
					<td><c:out value="${enrollment.studentOid.firstName }"></c:out></td>
					<td><c:out value="${enrollment.studentOid.lastName }"></c:out></td>
					<td><c:out value="${enrollment.studentOid.genderOid.shortDescription }"></c:out></td>
					<td><c:out value="${enrollment.studentOid.studentTypeOid.shortDescription}" /></td>
					<td><c:out value="${enrollment.studentOid.studentStatusOid.shortDescription}" /></td>
					<td><c:out value="${enrollment.studentOid.classStatusOid.shortDescription}" /></td>
					<td><c:out value="${enrollment.completionStatusOid.shortDescription}" /></td>
					<td>
						<a href="<c:url value="/enroll/getEditEnrollment.do?objectid=${enrollment.objectid }"/>">[Update]</a>&nbsp;
						<a href="<c:url value="/enroll/getDeletEnrollment.do?objectid=${enrollment.objectid }"/>">[Remove]</a>
						<c:if test="${enrollment.completionStatusOid.code eq 'INPROGRESS' }">
							<a href="<c:url value="/enroll/getWithdrawEnrollment.do?objectid=${enrollment.objectid }"/>">[Withdraw]</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<script>
(function($){
	$(document).ready(function() {
		$('#enrollmentTable').DataTable({
			"bLengthChange": false,
		    "bFilter": false,
		    "responsive": true,
			"processing": true,
		});
		$( "#back" ).click(function() {
			window.location.href='<c:url value="/enroll/getList.do"/>';
		});
	} );
})(jQuery); 

</script>