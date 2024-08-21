
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Representatives</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">Representatives</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">Representatives List</li>
					<li id="vscrollTable">Create Representative</li>
				</ul>
				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>Representatives</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Select</th>
												<!-- <th>Drug Id</th> -->
												<th>Name</th>
												<th>Managers</th>
												<th>Mobile Number</th>
												<th>Location</th>
												<th>Created Date</th>
												<th>Status</th>
												<!-- <th>View Details</th> -->
											</tr>
										</thead>
										<tbody>
											<c:forEach var="repsList" items="${repsList}">
												<tr>
													<td>
														<div class="checkbox">
															<label><input type="radio" name="srado"
																value="${repsList.id}"></label>
														</div>
													</td>

													<%-- <td>${allDrugStockList.drugId}</td> --%>
													<td>${repsList.fullName}</td>
													<td>${repsList.managerName}</td>
													<td>${repsList.mobileNumber}</td>
													<td>${repsList.location}</td>
													<td>${repsList.createdDate }</td>
													<td><a data-toggle="modal" id="new"
														data-target="#LargeModelInprogress" class="btn-link"
														onClick="viewModel(${repsList.id})"> Update</a></td>
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
														Representative Status</h4>
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
																		<form action="../phcompanyHead/updateRep.do"
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
					<!-- Vertical Scroll Table-->
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Create</span> Representative
								</h2>
							</div>
							<div class="l-box-body l-spaced">
								<form id="create" class="form-horizontal validate" role="form"
									action="../phcompanyHead/createRep.do" method="post"
									enctype="multipart/form-data">

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">First
												Name<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control required"
													name="firstName" id="firstName">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Last
												Name<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control required"
													name="lastName" id="lastName">
											</div>
										</div>

									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Title<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<input type="text" class="form-control required"
													name="title" id="title">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Gender<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<input type="text" class="form-control required"
													name="gender" id="gender">
											</div>
										</div>

									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Date
												of Birth<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control required" name="dob"
													id="dob">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="basicMultiSelect">Assigned
												Manager<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<select multiple="multiple" name="assManager"
													id="assManager" size="5" tabindex="1"
													style="width: 18.6em;">
													<c:forEach var="managerList" items="${managerList}">
														<option value="${managerList.id}">${managerList.fullName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Mobile
												Number<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control required"
													name="mobileNumber" id="mobileNumber">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Email
												Id<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control required"
													name="emailId" id="emailId">
											</div>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Location<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<input type="text" class="form-control required"
													name="location" id="location">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Therapeutic
												Areas<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control required"
													name="therapeutic" id="therapeutic">
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="userName-v">Profile
												Picture<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<div class="input-group">
													<span class="input-group-btn"><span
														class="btn btn-primary btn-file">Upload File... <input
															type="file" required="required" id="image" name="image"
															size="50"></span></span> <input type="text"
														class="form-control" readonly name="image"
														placeholder="(JPG size up to 250kb)">
												</div>
											</div>
										</div>
									</div>


									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="basicSelect"></label>
											<div class="col-sm-8">
												<button type="submit" class="btn btn-green"
													name="buttonName" value="Create"
													onclick="return fnSubmit(this);" form="create">Create</button>
											</div>
										</div>
									</div>


								</form>

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
</script>
