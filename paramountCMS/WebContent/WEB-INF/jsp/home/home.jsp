<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<section class="tiles">
	<article class="style6">
		<span class="image">
			<img src='<c:url value="/assets/images/course.jpg" />' alt="" />
		</span>
		
		<a href="<c:url value="/semester/getList.do"/>">
			<h2>Course Offer</h2>
			<div class="content">
				<p>Select Course Offered</p>
			</div>
		</a>
	</article>
	<article class="style2">
		<span class="image">
			<img src='<c:url value="/assets/images/enroll.jpg" />' alt="" />
		</span>
		
		<a href="<c:url value="/enroll/getList.do"/>">
			<h2>Enroll Course</h2>
			<div class="content">
				<p>Enroll in Course</p>
			</div>
		</a>
	</article>
	<article class="style5">
		<span class="image" style="height: 258px;">
			<img src='<c:url value="/assets/images/attend.jpg" />' alt="" />
		</span>
		
		<a href="<c:url value="/attendance/getList.do"/>">
			<h2>Attendance</h2>
			<div class="content">
				<p>Add Attendance</p>
			</div>
		</a>
	</article>
	<article class="style3">
		<span class="image" >
			<img src='<c:url value="/assets/images/grade.jpg" />' alt="" />
		</span>
		
		<a href="<c:url value="/grade/getList.do"/>">
			<h2>Grade</h2>
			<div class="content">
				<p>Add Grades</p>
			</div>
		</a>
	</article>
</section>
