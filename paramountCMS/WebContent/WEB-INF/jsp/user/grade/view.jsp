<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
<form id="gradeForm" method="post" action="<c:url value="/grade/postGrade.do?objectid=${courseOffer.objectid }"/>">
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
			<label for="assignmentOid">Assignment:*</label>
		</div>
		<div class="10u 8u(small)">
			<div class="row 25%">
				<div class="10u 8u(small)">
					<code:codevalue id="assignmentOid" name="assignmentOid" codegroup="ASSIGNMENT" requried="true" ></code:codevalue>
				</div>
				<div class="2u 4u(small)">
					<a href="<c:url value="/codeValue/assignment/getAdd.do?objectid=${courseOffer.objectid }"/>">[ Add Assignment ]</a>
				</div>
			</div>
		</div>
		
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="actualPoint">Actual Point:*</label>
		</div>
		<div class="4u 8u(small)">
			<input type="text" name="actualPoint" id="actualPoint" number="true"/>
		</div>
		<div class="2u 4u(small)">
			<label for="gradeTypeOid">Grade Type:*</label>
		</div>
		<div class="4u 8u(small)">
			<code:codevalue id="gradeTypeOid" name="gradeTypeOid" codegroup="GRADETYPE" requried="true" longDescription="code"></code:codevalue>
		</div>
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="comments">Comment:</label>
		</div>
		<div class="10u 8u(small)">
			<input type="text" name="comments" id="comments" />
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
		</tbody>
	</table>
</form>
</section>
<script>
var courseOfferOid = '${courseOffer.objectid }';
(function($){
	$(document).ready(function() {
		
		var dt = $('#enrollmentTable').DataTable({
		    "bLengthChange": false,
		    "bFilter": false,
		    "responsive": true,
			"processing": true,
			"deferLoading": 0,
			"order": [[1,"asc"]],
			"columns": [
				{"data":"firstName"},
				{"data":"lastName"},
				{"data":"gender"},
				{"data":"type"},
				{"data":"status"},
				{"data":"classStatus"},
			],
			"columnDefs":[
			  	{
				  	"targets": 6,
				  	"data": "objectid",
				  	"orderable": false,
				  	"render": function(data){
					  	var span = $("<span/>");
					  	var inp = $('<input/>').attr({ type: 'checkbox', id: 'enrollOids', name: 'enrollOids', value:data});
					  	inp.attr("class","gradechks");
					  	inp.attr("style","margin-top: .8em;");
					  	span.append(inp);
					  	return span.html();
					 }
				}
			],
			"ajax":{
				"url":contextPath+"/grade/getResultList.do",
				"data":function ( d ) {
					var value = $("#assignmentOid option:selected").attr("value");
					d.assignmentOid = value;
					d.courseOfferOid = courseOfferOid;
		      		return 
				}
			}
		});

		$("#assignmentOid").change(function(){
			var value = $("#assignmentOid option:selected").attr("value");
			if(value.length > 0){
				dt.ajax.reload();
			}
		});

		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/grade/getList.do"/>';
		});
		
		$("#submitButton").click(function(){
			if($('.gradechks:checkbox:checked').length > 0){
				if(!$("#gradeForm").validate()){
					return false;
				}else{
					$("#gradeForm").submit();
				}		
			}else{
				alert("Please select at least one student.");
			}
		});
		$("#actualPoint").focusout(function(){
			var value = $(this).val();
			if(value.length > 0){
				resetGradeDD(value);
			}
		});

		$("#gradeTypeOid").change(function(){
			var value = $("#gradeTypeOid option:selected").attr("shortDescription");
			$("#actualPoint").val(value);
		});
	} );
})(jQuery); 
function resetGradeDD(value){
	var selectedValue ;
	$("#gradeTypeOid option").each(function(){
		var selectValue = $(this).attr("value");
		if(selectValue.length > 0){
			var lowerBound = Math.round(parseFloat($(this).attr("shortDescription")));
		    var upperBound = Math.round(parseFloat($(this).attr("longDescription")));
		    value = Math.ceil(parseFloat(value));
		    if(value >= lowerBound && value <= upperBound){
		    	selectedValue = selectValue;
		    	return false;
			}
		}
	    
	});
	$("#gradeTypeOid").val(selectedValue);
}
</script>