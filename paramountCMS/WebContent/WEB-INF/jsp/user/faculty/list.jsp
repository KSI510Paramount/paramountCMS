<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section>
<a class="addLink" href="<c:url value="/faculty/getAdd.do"/>">[Add]</a>
	<table id="studentTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>Birth Date</th>
				<th>Gender</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${facultyList}" var="faculty">
				<tr>
					<td><c:out value="${faculty.firstName }"></c:out></td>
					<td><c:out value="${faculty.middleName }"></c:out></td>
					<td><c:out value="${faculty.lastName }"></c:out></td>
					<td><fmt:formatDate value="${faculty.birthdate}" /></td>
					<td><c:out value="${faculty.genderOid.shortDescription }"></c:out></td>
					<td>
						<a href="<c:url value="/faculty/getEdit.do?objectid=${faculty.objectid }"/>">[Edit]</a>&nbsp;
						<%-- <a href="<c:url value="/faculty/getDelete.do?objectid=${faculty.objectid }"/>">[Delete]</a> --%>
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