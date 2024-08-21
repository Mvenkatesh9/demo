
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Recruitment Status Report</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Recruitment Report</li>
	</ul>
</div>
<style>
.common-bg {
	padding: 10px;
	min-height:100px;
	text-align: center;
	background: #white;
	border: 1px solid #F5F1F1;
	-webkit-box-shadow: 0px 0px 5px -2px rgb(0 0 0/ 75%);
	-moz-box-shadow: 0px 0px 5px -2px rgba(0, 0, 0, 0.75);
	box-shadow: 0px 0px 5px -2px rgb(0 0 0/ 75%);
}


.pull-left {
	margin-right: 0px;
	font-size: 25px;
	margin-top: 6px; 
	height: 66px;
	text-align: center;
	line-height: 66px;
	-webkit-transition: 0.5s all;
	-moz-transition: 0.5s all;
	-o-transition: 0.5s all;
	-ms-transition: 0.5 sall;
	transition: 0.5s all;
	color: #ffffff;
	border-radius: 50px;
	width: 66px;
}

.demographic i {
	background-color: #7460ee;
}
.treatment i {
	background-color: #36215f;
}
.investigations i {
	background-color: #ffb22b;
}
.adverseEvents i {
	background-color: #1e88e5;
}
.documents i {
	background-color: #2fbf0b;
}
.completed i {
	background-color: #0b9ebf;
}
.sourcedocuments i {
	background-color: #dd2d10;
}


</style>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">

			<div class="resp-tabs-container">
				<div>
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Recruitment Report</span>
								</h2>
							</div>
							<div class="l-box-body l-spaced">
								<h4>Select the Site</h4>
								<select name="siteId" id="siteId"
									style="width: 100%; padding: 10px;" required="required" onchange="getSiteWiseData()">
									<option value="ALL">All</option>
									<c:forEach var="siteList" items="${siteList}" >
										<option value="${siteList.id}|${siteList.siteName}">${siteList.siteName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-12 l-box-body l-spaced">
							<h3>Patient Count</h3>
								<div class="col-sm-3 demographic common-bg">
									<i class="pull-left fa fa-users"></i>
									<p>Total Patients</p>
									<h3 id="totPatCount">${reportData.totalPatCount}</h3>
								</div>
								<div class="col-sm-3 investigations common-bg">
									<i class="pull-left fa fa-tasks"></i>
									<p>Screening Patients</p>
									<h3 id="subPatCount">${reportData.enrolledPatCount}</h3>
								</div>
								<div class="col-sm-3 adverseEvents common-bg">
									<i class="pull-left fa fa-list-alt"></i>
									<p>In-Treatment Patients</p>
									<h3 id="appPatCount">${reportData.completedPatCount}</h3>
								</div>
								<div class="col-sm-3 documents common-bg">
									<i class="pull-left fa fa-list-alt"></i>
									<p>Dropped Patients</p>
									<h3 id="comPatCount">${reportData.droppedPatCount}</h3>
								</div>
								</div>
								<%-- <div class="col-sm-12 l-box-body l-spaced">
								<h3>Source Document Count (For above Patients)</h3>
								<div class="col-sm-4 documents common-bg">
									<i class="pull-left fa fa-list-alt"></i>
									<p>History, Disease & Treatment Details</p>
									<h3 id="historyDocPatCount">${reportData.historyDocPatCount}</h3>
								</div>
								<div class="col-sm-4 documents common-bg">
									<i class="pull-left fa fa-list-alt"></i>
									<p>Investigation Reports</p>
									<h3 id="invDocPatCount">${reportData.invDocPatCount}</h3>
								</div>
								<div class="col-sm-4 sourcedocuments common-bg">
									<i class="pull-left fa fa-exclamation-triangle"></i>
									<p>No Source Documents Added !</p>
									<h3 id="totalSourceDocCount">${reportData.totalSourceDocCount}</h3>
								</div>
							</div> --%>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
function getSiteWiseData(){
	var select = document.getElementById("siteId").value;
	var siteId = 0;	
	if (select != "ALL"){
		var res = select.split("|");
		siteId = res[0];
		}
	 $.ajax({
		   url: 'siteReportFilter',
		   type: 'GET',
		   data: 'siteId='+siteId,    		   
		   success: function(data) {
			   document.getElementById("totPatCount").innerText = data.totalPatCount;
			   document.getElementById("subPatCount").innerText = data.enrolledPatCount;
			   document.getElementById("appPatCount").innerText = data.completedPatCount;
			   document.getElementById("comPatCount").innerText = data.droppedPatCount;
		   },
		   error: function(e) {
		   }
		 });  	 
}
	</script>
