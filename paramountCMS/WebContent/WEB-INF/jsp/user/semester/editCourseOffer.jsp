<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form method="post" action="<c:url value="/semester/postEditCourseOffer.do?objectid=${courseOffer.objectid }"/>">
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
				<label for="locationOid">Location:*</label>
			</div>
			<div class="4u 8u(small)">
				<code:codevalue id="locationOid" name="locationOid" codegroup="LOCATION" requried="true" value="${courseOffer.locationOid.objectid }"></code:codevalue>
			</div>
		</div>
		<div class="row 25%">
			<div class="2u 4u(small)">
				<label for="courseOids">Course:</label>
			</div>
			<div class="10u 8u(small)">
				<select id="courseOids" name="courseOids">
					<option value="">--Select--</option>
					<c:forEach items="${courseList }" var="course">
						<c:set value="" var="selected"></c:set>
						<c:if test="${course.objectid eq courseOffer.courseOid.objectid }">
							<c:set value="selected" var="selected"></c:set>
						</c:if>
						<option value="${course.objectid }" ${selected }><c:out value="${course.code } - ${course.title }"></c:out></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<ul class="actions">
			<li><input type="submit" value="Update" class="special" /></li>
			<li><input type="button" id="cancel" value="Cancel" class="special"/></li>
		</ul>
	</form>
</section>
<script>
(function($){
	$(document).ready(function() {
		$(".isDate").datepicker();
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/semester/getView.do?objectid=${courseOffer.semesterOid.objectid }"/>';
		});
	} );
})(jQuery); 
</script>