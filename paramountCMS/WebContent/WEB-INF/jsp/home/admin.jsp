<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<section class="tiles">
	<article class="style1">
		<span class="image">
			<img src='<c:url value="/assets/images/pic01.jpg" />' alt="" />
		</span>
		
		<a href="<c:url value="/codeValue/getList.do"/>">
			<h2>Code Value</h2>
			<div class="content">
				<p>Master Records for Drop Down</p>
			</div>
		</a>
	</article>
	<article class="style2">
		<span class="image">
			<img src='<c:url value="/assets/images/enroll.jpg" />' alt="" />
		</span>
		
		<a href="<c:url value="/faculty/getList.do"/>">
			<h2>Manage Faculty</h2>
			<div class="content">
				<p>Add Faculty Memebers with User Id</p>
			</div>
		</a>
	</article>
	<article class="style5">
		<span class="image" style="height: 258px;">
			<img src='<c:url value="/assets/images/attend.jpg" />' alt="" />
		</span>
		
		<a href="<c:url value="/student/getList.do"/>">
			<h2>Manage Student</h2>
			<div class="content">
				<p>Manage Student Records</p>
			</div>
		</a>
	</article>
</section>
