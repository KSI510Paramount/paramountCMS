<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/enroll/postEnroll.do?objectid=${courseOffer.objectid }"/>">
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
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="studentOid">Student:*</label>
			</div>
			<div class="10u 8u(small)">
				<select id="studentOid" name="studentOid" required>
					<option value="">--Select--</option>
					<c:forEach items="${studentList }" var="student">
						<option value="${student.objectid }"><c:out value="${student.firstName } ${student.lastName }"></c:out></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<ul class="actions">
			<li><input type="submit" value="Enroll" class="special" /></li>
			<li><input type="button" id="cancel" value="Cancel" class="special"/></li>
		</ul>
	</form>
</section>
<script>
(function($){
	$(document).ready(function() {
		$(".isDate").datepicker();
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/enroll/getView.do?objectid=${courseOffer.objectid}"/>';
		});
	} );
})(jQuery); 
</script>