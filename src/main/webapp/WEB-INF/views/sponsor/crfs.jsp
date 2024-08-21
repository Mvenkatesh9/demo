
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>CRF's</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">CRF's</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">

		<div class="l-spaced">
			<div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">List of CRF's</li>
					<li id="vscrollTable">Create CRF</li>
				</ul>
				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						<div class="l-row l-spaced-bottom">
							<div class="l-box">
								<div class="l-box-header">
									<h2 class="l-box-title">
										<span>CRF's</span> Table
									</h2>
								</div>
								<div class="l-box-body">
									<table id="dataTableId" cellspacing="0" width="100%"
										class="display">
										<thead>
											<tr>
												<th>Select</th>
												<th>Study Name</th>
												<th>Start Date</th>
												<th>End Date</th>
												<th>Therapeutic Names</th>
												<th>Created Date</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="crfsList" items="${crfsList}">
												<tr>
													<td>
														<div class="checkbox">
															<label><input type="radio" name="srado"
																value="${crfsList.crfId}"></label>
														</div>
													</td>
													<td>${crfsList.studyName}</td>
													<td>${crfsList.startDate}</td>
													<td>${crfsList.endDate}</td>
													<td>${crfsList.therapeuticNames}</td>
													<td>${crfsList.createdDate }</td>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
					</div>
					<!-- Vertical Scroll Table-->
					<div class="l-row l-spaced-bottom">
						<div class="l-box">
							<div class="l-box-header">
								<h2 class="l-box-title">
									<span>Create</span> CRF
								</h2>
							</div>
							<div class="l-box-body l-spaced">
								<form id="create" class="form-horizontal validate" role="form"
									action="../phcompanyHead/createCRF.do" method="post"
									enctype="multipart/form-data">

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Study
												Title<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="studyName" id="studyName">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Protocol
												Id<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="protocolId" id="protocolId">
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Study
												Short Title<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="studyShortName" id="studyShortName">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Principal
												Investigators<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<select name="investigatorDocId" id="investigatorDocId"
													size="5" tabindex="1" style="width: 18.6em;"
													required="required">
													<c:forEach var="doctorsList" items="${doctorsList}">
														<option value="${doctorsList.id}">${doctorsList.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Products<font
												color="red">*</font></label>
											<div class="col-sm-8">
												<select name="productId" id="productId" size="5"
													tabindex="1" style="width: 18.6em;" required="required">
													<c:forEach var="productsList" items="${productsList}">
														<option value="${productsList.id}">${productsList.productTitle}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Therapeutic
												Areas<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<select multiple="multiple" name="thAreaIds" id="thAreaIds"
													size="5" tabindex="1" style="width: 18.6em;"
													required="required">
													<c:forEach var="therapeuticList" items="${therapeuticList}">
														<option value="${therapeuticList.therapeuticId}">${therapeuticList.therapeuticName}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>
									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Start
												Date<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="date" class="form-control" required="required"
													name="startDate" id="startDate">
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">End
												Date<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="date" class="form-control" required="required"
													name="endDate" id="endDate">
											</div>
										</div>

									</div>

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="userName-v">Ethics
												Committee File</label>
											<div class="col-sm-8">
												<div class="input-group">
													<span class="input-group-btn"><span
														class="btn btn-primary btn-file">Upload File... <input
															type="file" id="ethics" name="ethics" size="50"></span></span>
													<input type="text" class="form-control" readonly
														name="ethics" placeholder="PDF size up to 450kb)">
												</div>
											</div>
										</div>
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="userName-v">Protocol
												File</label>
											<div class="col-sm-8">
												<div class="input-group">
													<span class="input-group-btn"><span
														class="btn btn-primary btn-file">Upload File... <input
															type="file" id="protocol" name="protocol" size="50"></span></span>
													<input type="text" class="form-control" readonly
														name="protocol" placeholder="PDF size up to 450kb)">
												</div>
											</div>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="userName-v">Trail
												Sites <font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<input type="text" class="form-control" required="required"
													name="trailSites" id="trailSites">
											</div>
										</div>

										<div class="col-sm-6">
											<label class="col-sm-4 control-label" for="title">Select
												Trail Sites<font color="red">*</font>
											</label>
											<div class="col-sm-8">
												<select multiple="multiple" name=trailSites id="trailSites"
													size="5" tabindex="1" style="width: 18.6em;"
													required="required">
													<c:forEach var="trailSiteList" items="${trailSiteList}">
														<option value="${trailSiteList.id}">${trailSiteList.siteName},
															${trailSiteList.location}</option>
													</c:forEach>
												</select>
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
