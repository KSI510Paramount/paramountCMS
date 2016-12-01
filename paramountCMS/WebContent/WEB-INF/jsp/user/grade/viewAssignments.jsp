<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/enroll/postEditEnrollment.do?objectid=${enrolled.objectid }"/>">
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label>Semester Year:</label>
			</div>
			<div class="4u 8u(small)">
				<label>${courseOffer.semesterOid.semesterYear }</label>
			</div>
			<div class="2u 4u(small)">
				<label>Semester Type:</label>
			</div>
			<div class="4u 8u(small)">
				<label>${courseOffer.semesterOid.semesterTypeOid.shortDescription }</label>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label>Course:</label>
			</div>
			<div class="10u 8u(small)">
				<label>${courseOffer.courseOid.code} - ${courseOffer.courseOid.title}</label>
			</div>
		</div>
	</form>
	<br />
	<table id="courseEnrollmentTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th style="width: 90%">Assignments</th>
				<th style="width: 10%">Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${assignmentList}" var="assignment">
				<tr>
					<td><c:out value="${assignment.shortDescription}"></c:out></td>
					<td>
						<a href="<c:url value="/grade/getViewStudentGrade.do?objectid=${assignment.objectid }"/>">[View Grade]</a>&nbsp;
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="actions">
		<li><input type="button" id="cancel" value="Cancel" class="special"/></li>
	</ul>
</section>
<script>
(function($){
	$(document).ready(function() {
		$(".isDate").datepicker();
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/grade/getList.do"/>';
		});
	} );
})(jQuery); 
</script>