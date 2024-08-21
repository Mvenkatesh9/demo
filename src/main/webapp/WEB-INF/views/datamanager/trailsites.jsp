
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Trial Sites</span> 
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Trial Sites</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">
		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">List of Trial Sites</li>
				</ul>
				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Trial Sites</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Trial Site Name</th>
												<th>Location</th>
												<th>Created Date</th>
												<th>View Patients</th>
												
											</tr>
										</thead>
										<tbody>
											<c:forEach var="trailSiteList" items="${trailSiteList}">
												<tr>
													<td>${trailSiteList.siteName}</td>
													<td>${trailSiteList.location}</td>
													<td>${trailSiteList.createdDate }</td>
													<td><input type="button" class="sitePatients"
														id="${trailSiteList.id}|${trailSiteList.siteName}"
														class="btn " value="VIEW"></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<form action="../datamanager/subjectsBySite" id="sitePatients" method="get"
						enctype="multipart/form-data">
						<input type="hidden" id="siteIdS" name="siteIdS" value="" /> <input
							type="hidden" id="siteNameS" name="siteNameS" value="" />
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<script>
	$(document).ready(function() {
		$('#dataTableId').dataTable({
			"order" : []
		});
	});
</script>
	<script>
		$(".sitePatients").click(function() {
			var id = $(this).attr("id");
			if (id == '' || id === undefined) {
				alert("Please select a Section Id");
			} else {
				var res = id.split("|");
				var siteId = res[0];
				var siteName = res[1];
				$("#siteIdS").val(siteId);
				$("#siteNameS").val(siteName);
				$("#sitePatients").submit();
		}

		})
		
	</script>