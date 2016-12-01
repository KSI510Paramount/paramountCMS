<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
<form id="gradeForm" method="post" >
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label>Assignment:</label>
		</div>
		<div class="10u 8u(small)">
			<label>${assignemt.shortDescription }</label>
		</div>
	</div>
	<br />
	<table id="enrollmentTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Gender</th>
				<th>Type</th>
				<th>Status</th>
				<th>Class Status</th>
				<th>Actual Point</th>
				<th>Grade</th>
				<!-- <th></th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${gradeList}" var="grade">
				<tr>
					<td><c:out value="${grade.enrollmentOid.studentOid.firstName}"></c:out></td>
					<td><c:out value="${grade.enrollmentOid.studentOid.lastName }"></c:out></td>
					<td><c:out value="${grade.enrollmentOid.studentOid.genderOid.shortDescription }"></c:out></td>
					<td><c:out value="${grade.enrollmentOid.studentOid.studentTypeOid.shortDescription }"></c:out></td>
					<td><c:out value="${grade.enrollmentOid.studentOid.studentStatusOid.shortDescription }"></c:out></td>
					<td><c:out value="${grade.enrollmentOid.studentOid.classStatusOid.shortDescription }"></c:out></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${grade.actualPoint }" /></td>
					<td><c:out value="${grade.letterGrade }"></c:out></td>
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
			window.location.href='<c:url value="/grade/getList.do"/>';
		});
		
	} );
})(jQuery); 
</script>