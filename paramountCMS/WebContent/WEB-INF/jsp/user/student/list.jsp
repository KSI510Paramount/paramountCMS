<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
<a class="addLink" href="<c:url value="/student/getAdd.do"/>">[Add]</a>
	<table id="studentTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Student ID</th>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Birth Date</th>
				<th>Gender</th>
				<th>Student Type</th>
				<th>Status</th>
				<th>Class Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${studentList}" var="student">
				<tr>
					<td><c:out value="${student.studentId }"></c:out></td>
					<td><c:out value="${student.firstName }"></c:out></td>
					<td><c:out value="${student.middleName }"></c:out></td>
					<td><c:out value="${student.lastName }"></c:out></td>
					<td><fmt:formatDate value="${student.birthdate}" /></td>
					<td><c:out value="${student.genderOid.shortDescription }"></c:out></td>
					<td><c:out value="${student.studentTypeOid.shortDescription }"></c:out></td>
					<td><c:out value="${student.studentStatusOid.shortDescription }"></c:out></td>
					<td><c:out value="${student.classStatusOid.shortDescription }"></c:out></td>
					<td>
						<a href="<c:url value="/student/getEdit.do?objectid=${student.objectid }"/>">[Edit]</a>&nbsp;
						<%-- <a href="<c:url value="/student/getDelete.do?objectid=${student.objectid }"/>">[Delete]</a> --%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<script>
(function($){
	$(document).ready(function() {
		$('#studentTable').DataTable();
	} );
})(jQuery); 

</script>