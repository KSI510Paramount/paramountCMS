<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
<form id="attendanceForm" method="post" action="<c:url value="/attendance/postAttendance.do"/>">
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
			<label for="attenTypeOid">Status:*</label>
		</div>
		<div class="4u 8u(small)">
			<code:codevalue id="attenTypeOid" name="attenTypeOid" codegroup="ATTTYPE" requried="true" ></code:codevalue>
		</div>
		<div class="2u 4u(small)">
			<label for="attendamceDate">Date:*</label>
		</div>
		<div class="4u 8u(small)">
			<input type="text" id="attendamceDate" name="attendamceDate" class="isDate" date="true" required/>
		</div>
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="comments">Comment:</label>
		</div>
		<div class="10u 8u(small)">
			<input type="text" id="comments" name="comments" />
		</div>
	</div>
	<ul class="actions">
		<li><input id="submitButton" type="button" value="Submit" class="special" /></li>
		<li><input type="button" id="cancel" value="Cancel" class="special"/></li>
	</ul>
	<table id="enrollmentTable" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Gender</th>
				<th>Type</th>
				<th>Status</th>
				<th>Class Status</th>
				<th></th>
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
					<td>
						<input style="margin-top: .8em;" class="attendanceChk" type="checkbox" name="enrollOids" id="enrollOids${enrollment.objectid }" value="${enrollment.objectid }"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
</section>
<script>
(function($){
	$(document).ready(function() {
		$(".isDate").datepicker();
		$('#enrollmentTable').DataTable({
			"bLengthChange": false,
		    "bFilter": false,
		    "responsive": true,
			"processing": true,
			"order": [[1,"asc"]],
			"columnDefs":[{"targets" : 0, "orderable" :false}]
		});
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/attendance/getList.do"/>';
		});
		$("#submitButton").click(function(){
			if($('.attendanceChk:checkbox:checked').length > 0){
				if(!$("#attendanceForm").validate()){
					return false;
				}else{
					$("#attendanceForm").submit();
				}		
			}else{
				alert("Please select at least one student.");
			}
		});
	} );
})(jQuery); 

</script>