
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Study Drugs &amp; Methodology</span> 
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Study Drugs &amp; Methodology</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">List of Study Drugs</li>
					<li id="vscrollTable">Methodology</li>

				</ul>


				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Study Drugs</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Title</th>
												<th>Composition</th>
												<th>Usage Description</th>
												<th>Created Date</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="productsList" items="${productsList}">
												<tr>
													<td>${productsList.productTitle}</td>
													<td>${productsList.composition}</td>
													<td>${productsList.usageDescription}</td>
													<td>${productsList.createdDate }</td>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>
					
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Study Drug</span> Methodology
								</h2>
							</div>
							<div class="l-box-body l-spaced">
							<div class="col-md-6">
									<h4>Remdesivir injection 100mg/vial </b></h4>
								</div>
								
							</div>

						</div>
					</div>

				</div>


			</div>
		</div>
	</div>
</div>

