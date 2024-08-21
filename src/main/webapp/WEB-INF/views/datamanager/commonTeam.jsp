
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span> ${type}</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">${type}</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">${type}List</li>
				</ul>

				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>${type}</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Designation</th>
												<th>Mobile Number</th>
												<th>Email Id</th>
												<th>Location</th>
												<th>Created Date</th>
												<th>Status</th>
												<!-- <th>View Details</th> -->
											</tr>
										</thead>
										<tbody>
											<c:forEach var="managerList" items="${managerList}">
												<tr>
													<td>${managerList.designation}</td>
													<td>${managerList.mobileNumber}</td>
													<td>${managerList.emailId}</td>
													<td>${managerList.location}</td>
													<td>${managerList.createdDate }</td>
													<td>${managerList.status }</td>
											</c:forEach>
										</tbody>
									</table>
									<div id="LargeModelInprogress" tabindex="-1" role="dialog"
										aria-labelledby="largeModalLabel" aria-hidden="true"
										class="modal fade">
										<div class="modal-dialog modal-lg">
											<div class="modal-content">
												<div class="modal-header">
													<h4 id="largeModalLabel" class="modal-title">View
														Manager Status</h4>
													<button type="button" data-dismiss="modal" class="close">
														<span aria-hidden="true">X</span><span class="sr-only">Close</span>
													</button>
												</div>

												<div class="form-group row">

													<div class="col-md-6">
														<div class="modal-body">
															<dl class="dl-horizontal">

																<dt style="margin-top: 9px;">Choose Status</dt>
																<dd>
																	<center>
																		<form action="../phcompanyHead/updateManager.do"
																			method="post" id="formInprogress">
																			<select name="statusDropDown"
																				class="l-spaced form-control">
																				<option value="New">------ select Choose
																					Status -----</option>
																				<option value="Active">Active</option>
																				<option value="Inactive">Inactive</option>
																			</select> <input name="id" id="directorIdsChangeStatus"
																				type="hidden" value="" />
																		</form>
																	</center>
																</dd>
																<div class="l-spaced l-box-body l-spaced">
																	<dt>&nbsp;</dt>
																	<dd>
																		<center>
																			<button type="submit" class="btn btn-green"
																				value="Submit" form="formInprogress">Update</button>
																		</center>
																	</dd>
																</div>
															</dl>
														</div>
													</div>

												</div>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
function viewModel(id){
	createEmptyDataModel();
	   $("#directorIdsChangeStatus").attr("value",id);

}
function createEmptyDataModel(){
	   $("#directorIdsChangeStatus").attr("value",'');

}
$(document).ready( function () {
	  $('#dataTableId').dataTable({
	    "order": []
	  });
	});
</script>
