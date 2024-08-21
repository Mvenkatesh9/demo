
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Study Doctors</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Report Analytics</a></li>
		<li class="active">Study Doctors</li>
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
									<span>Doctors</span> Table
								</h2>
							</div>
							<div class="l-box-body">
								<table id="dataTableId" cellspacing="0" width="100%"
									class="display">
									<thead>
										<tr>
											<th>Name</th>
											<th>Mobile</th>
											<th>Hospital</th>
											<th>Place</th>
											<th>Total Patients</th>
											<th>Enrolled Date</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="doctorsList" items="${doctorsList}">
											<tr>
												<td>${doctorsList.fullName}</td>
												<td>${doctorsList.mobile}</td>
												<td>${doctorsList.hospital}</td>
												<td>${doctorsList.place}</td>
												<td>${doctorsList.totalPatients}</td>
												<td>${doctorsList.enrolledDate }</td>
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
