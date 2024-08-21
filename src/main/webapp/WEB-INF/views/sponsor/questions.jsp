
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Form Fields - ${section}</span>
	</h2>
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Form Fields</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">${section}FormFields</li>
				</ul>

				<div class="resp-tabs-container">
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Form Field</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Field Name</th>
												<th>Type</th>
												<th>Length</th>
												<th>SDTM</th>
												<th>CDASH</th>
												<th>Description</th>
												<th>Options</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="questionsList" items="${questionsList}">
												<tr>
													<td>${questionsList.questionTitle}</td>
													<td>${questionsList.questionType}</td>
													<td>${questionsList.length}</td>
													<td>${questionsList.sdtm}</td>
													<td>${questionsList.cdash}</td>
													<td>${questionsList.questionComments}</td>
													<td>${questionsList.options}</td>
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

</div>

