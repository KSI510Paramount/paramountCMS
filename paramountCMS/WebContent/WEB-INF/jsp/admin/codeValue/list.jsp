<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<section>
<a class="addLink" href="<c:url value="/codeValue/getAdd.do"/>">[Add]</a>
	<table id="codeValueTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Code Group</th>
				<th>Code</th>
				<th>Short Description</th>
				<th>Long Description</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${codeValueList}" var="codeValue">
				<tr>
					<td><c:out value="${codeValue.codeGroup }"></c:out></td>
					<td><c:out value="${codeValue.code }"></c:out></td>
					<td><c:out value="${codeValue.shortDescription }"></c:out></td>
					<td><c:out value="${codeValue.longDescription }"></c:out></td>
					<td>
						<a href="<c:url value="/codeValue/getEdit.do?objectid=${codeValue.objectid }"/>">[Edit]</a>&nbsp;
						<a href="<c:url value="/codeValue/getDelete.do?objectid=${codeValue.objectid }"/>">[Delete]</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</section>
<script>
(function($){
	$(document).ready(function() {
		$('#codeValueTable').DataTable();
	} );
})(jQuery); 

</script>