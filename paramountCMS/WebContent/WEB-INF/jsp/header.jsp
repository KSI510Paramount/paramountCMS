<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<header id="header" class="container 125%">
	<div >

		<!-- Logo -->
			<a href="#" class="logo">
				<span class="symbol"><img src="<c:url value="/assets/images/logo.png"/>" alt="" /></span><span class="title">Course Management System</span>
			</a>
			<a href="#" class="logo">
				<span class="paramount"><img src="<c:url value="/assets/images/paramount.png"/>" alt="" /></span>
			</a>

		<!-- Nav -->
			<nav>
				<ul>
					<li><a href="#menu">Menu</a></li>
				</ul>
			</nav>

	</div>
</header>

<!-- Menu -->
<nav id="menu">
	<h2>Menu</h2>
	<ul>
		<li><a href="<c:url value="/login/getHome.do"/>">Home</a></li>
		<!-- <li><a href="generic.html">Ipsum veroeros</a></li>
		<li><a href="generic.html">Tempus etiam</a></li>
		<li><a href="generic.html">Consequat dolor</a></li>
		<li><a href="elements.html">Elements</a></li> -->
	</ul>
</nav>