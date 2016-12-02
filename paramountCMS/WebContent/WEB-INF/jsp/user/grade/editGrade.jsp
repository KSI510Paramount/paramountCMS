<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/tld/CodeValue.tld" prefix="code" %>
<section>
	<form id="gradeForm" method="post" action="<c:url value="/grade/postEditGrade.do?objectid=${grade.objectid }"/>">
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label>Course:</label>
		</div>
		<div class="10u 8u(small)">
			<label>${grade.enrollmentOid.courseOfferOid.courseOid.code} - ${grade.enrollmentOid.courseOfferOid.courseOid.title}</label>
		</div>
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="assignmentOid">Assignment:*</label>
		</div>
		<div class="10u 8u(small)">
			<code:codevalue id="assignmentOid" name="assignmentOid" codegroup="ASSIGNMENT" requried="true" value="${grade.assignmentOid.objectid }"></code:codevalue>
		</div>
		
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="extendedGrade">Extended Grade:</label>
		</div>
		<div class="4u 8u(small)">
			<input style="margin-top: .8em;" type="checkbox" name="extendedGrade" id="extendedGrade" value="Y"/>
		</div>
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="actualPoint">Actual Point:*</label>
		</div>
		<div class="4u 8u(small)">
			<input type="text" name="actualPoint" id="actualPoint" number="true" value="<fmt:formatNumber type="number" maxFractionDigits="2" value="${grade.actualPoint }" />"/>
		</div>
		<div class="2u 4u(small)">
			<label for="gradeTypeOid">Grade Type:*</label>
		</div>
		<div class="4u 8u(small)" id="gradeDivToggle">
			<code:codevalue id="gradeTypeOid" name="gradeTypeOid" codegroup="GRADETYPE" requried="true" longDescription="code" value="${grade.gradeTypeOid.objectid }"></code:codevalue>
		</div>
		<div class="4u 8u(small)" id="extendedGradeDivToggle" style="display: none;">
			<code:codevalue id="extendedGradeTypeOid" name="extendedGradeTypeOid" codegroup="EXTGRADETYPE" requried="true" longDescription="code" value="${grade.gradeTypeOid.objectid }"></code:codevalue>
		</div>
	</div>
	<div class="row 25%">
		<div class="2u 4u(small)">
			<label for="comments">Comment:</label>
		</div>
		<div class="10u 8u(small)">
			<input type="text" name="comments" id="comments" value="${grade.remarks }"/>
		</div>
	</div>
	<ul class="actions">
		<li><input type="submit" value="Update" class="special" /></li>
		<li><input type="button" id="cancel" value="Cancel" class="special"/></li>
	</ul>
</form>
</section>
<script>
var extendedChk = '${grade.extendedGrade}';
(function($){
	$(document).ready(function() {
		$("#gradeForm").validate({
			   ignore: ":hidden"
			});
		$(".isDate").datepicker();
		$( "#cancel" ).click(function() {
			window.location.href='<c:url value="/grade/getViewAssignments.do?objectid=${grade.enrollmentOid.courseOfferOid.objectid}"/>';
		});
		$('#extendedGrade').change(function() {
			var value = $("#actualPoint").val();
			if(this.checked){
				$('#extendedGradeDivToggle').toggle(true);
				$('#gradeDivToggle').toggle(false);
				resetExtendedGradeDD(value);
			}else{
				$('#gradeDivToggle').toggle(true);
				$('#extendedGradeDivToggle').toggle(false);
				resetGradeDD(value);
			}
		});
		
		$("#actualPoint").focusout(function(){
			var value = $(this).val();
			if(value.length > 0){
				if($("#extendedGrade").is(':checked')){
					resetExtendedGradeDD(value);
				}else{
					resetGradeDD(value);
				}
			}
		});

		$("#gradeTypeOid").change(function(){
			var value = $("#gradeTypeOid option:selected").attr("shortDescription");
			$("#actualPoint").val(value);
		});
		chk();
	} );
})(jQuery); 
function chk(){
	if(extendedChk === "Y"){
		$("#extendedGrade").attr('checked','checked');
		$('#extendedGradeDivToggle').toggle(true);
		$('#gradeDivToggle').toggle(false);
	}
}
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
function resetExtendedGradeDD(value){
	var selectedValue ;
	$("#extendedGradeTypeOid option").each(function(){
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
	$("#extendedGradeTypeOid").val(selectedValue);
} 
</script>