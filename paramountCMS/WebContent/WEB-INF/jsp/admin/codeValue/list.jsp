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
		</tbody>
	</table>
</section>
<script>
(function($){
	$(document).ready(function() {
		$('#codeValueTable').DataTable({
			"responsive": true,
			"processing": true,
			"columns": [
				{"data":"codeGroup"},
				{"data":"code"},
				{"data":"shortDescription"},
				{"data":"longDescription"},
			],
			"columnDefs":[
			  	{
				  	"targets": 4,
				  	"data": "objectid",
				  	"orderable": false,
				  	"render": function(data){
					  	var span = $("<span/>");
					  	var href = $("<a/>")
					  	href.attr("href", contextPath + "/codeValue/getEdit.do?objectid=" + data);
					  	href.append("[Edit]");
					  	span.append(href);

					  	href = $("<a/>")
					  	href.attr("href", contextPath + "/codeValue/getDelete.do?objectid" + data);
					  	href.append("[Delete]");
					  	span.append(href);
					  	
					  	return span.html();
					 }
				}
			],
			"ajax":{
				"url":contextPath+"/rest/codeValue/getResultList.do"
			}
		});
	} );
})(jQuery); 

</script>