
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Adverse Events - ${patientId}</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Adverse Events</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">

			<div class="resp-tabs-container">
				<!-- Default Table-->
				<div>
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Adverse Events</span> Table
								</h2>
							</div>
							<div class="l-box-body">
								<table id="dataTableId" cellspacing="0" width="100%"
									class="display">
									<thead>
										<tr>
											<th>Subject Id</th>
											<th>AE Field Name</th>
											<th>Answer</th>
											<th>CreatedDate</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="advList" items="${advList}">
											<tr>
												<td>${advList.patientId}</td>
												<td>${advList.remarks}</td>
												<td>${advList.answer}</td>
												<td>${advList.createdDate}</td>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
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
});</script>
