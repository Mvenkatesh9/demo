
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Recruitment Status</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Report Analytics</a></li>
		<li class="active">Recruitment Status</li>
	</ul>
</div>
<div class="l-box no-border">
	<!-- Vertical Scroll Table-->
	<div class="l-row l-spaced-bottom">
		<div class="l-box">

			<div id="displayData">
				<div class="col-sm-12">
					<!-- 	<div class="col-md-6 text-center">
						<h3>SCREENED REPORT</h3>
					</div> -->
					<div class="col-md-12 text-center">
						<h3>REPORT COUNT</h3>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="row clearfix">
						<div class="col-sm-6 col-xs-12">
							<div class="info-box bg-cyan hover-expand-effect">
								<div class="icon">
									<i class="fa fa-paper-plane-o"></i>
									<!-- <a href="#" class="doctorsAll"> VIEW</a> -->
									<!-- <a href="#" class="sites"> VIEW</a> -->

								</div>
								<div class="content">
									<div class="text">Total Sites</div>
									<div id="site" class="number count-to" data-from="0"
										data-to="${reportModel.siteCount}" data-speed="1000"
										data-fresh-interval="20">${reportModel.siteCount}</div>

								</div>
							</div>
						</div>
						<div class="col-sm-6 col-xs-12">
							<div class="info-box bg-pink hover-expand-effect">
								<div class="icon">
									<i class="fa fa-sign-in"></i> 
									<!-- <a href="#" class="subjects">
										VIEW</a> -->

								</div>
								<div class="content">
									<div class="text">Total Subjects</div>
									<div id="subject" class="number count-to" data-from="0"
										data-to="${reportModel.patCount}" data-speed="1000"
										data-fresh-interval="20">${reportModel.patCount}</div>
								</div>
							</div>
						</div>

					</div>

				</div>
				<form action="../datamanager/trailSites" id="sites" method="get"
					target="blank_" enctype="multipart/form-data"></form>
				<form action="../datamanager/patients" id="subjects" method="get"
					target="blank_" enctype="multipart/form-data"></form>

			</div>


		</div>
	</div>

</div>


<script>
	$(".sites").click(function() {
		$("#sites").submit();
	})
	$(".subjects").click(function() {

		$("#subjects").submit();

	})
	$(document).ready(function() {
		$('#dataTableId').dataTable({
			"order" : []
		});
	});
</script>


