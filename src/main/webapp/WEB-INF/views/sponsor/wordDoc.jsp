					
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
   <div class="notificationmsg">${deviceMsg}</div> 
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Trail Sites</span> PAGE
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
	<li class="active">Trail Sites</li>
	</ul>
</div>
<div class="l-box no-border" style="height:100%">
	
	<script>
	
	// Set up connection details
	var starDocs = new Gnostice.StarDocs(
	  new Gnostice.ConnectionInfo(
	    'https://api.gnostice.com/stardocs/v1', 
	    '3bd0bc57247441578fd939875f1ed27b', 
	    'dff6d8cfc6034ee290a9e52878a7cfd8'),
	  new Preferences(
	    // Force full permissions on PDF files protected 
	    // with an permissions/owner/master password
	    new DocPasswordSettings(true))
	);

	// Authenticate
	starDocs.auth.loginApp()
	  .done(function(response) { /* Success */ })
	  .fail(function(httpStatusCode, httpErrorMessage, response) { /* Handle error */ });
	
	
	// Upload file
	var selectedFile = document.getElementById('input').files[0];
	starDocs.storage.upload(selectedFile, "password") 
	  .done(function(response) {
	    // Store document URL
	    documentUrl = response.documents[0].url;
	  })
	  .fail(function(errorThrown, errorCode, errorMessage) { /* Handle error */ });
	
	starDocs.docOperations.getDocInfo(documentUrl, "password")
	  .done(function(response) {
	    /*
	    response has the following properties:
	      url: URL of the document
	      fileName: Filename of the document
	      fileSize: The size of the file in bytes
	      fileExpiry: The number of seconds since epoch when file will be 
	        deleted from the server. Null if there is no expiry set 
	      mimeType: MIME type of the document
	      unsupportedMimeTypeOrCorrupt: True if the document is either 
	        corrupted or if it is of a type not currently supported by 
	        StarDocs. The remaining properties shown below of the response are 
	        valid only if this property is set to false
	      passwordProtected: True if the document requires a password to be 
	        opened
	      passwordCorrect: True if StarDocs has the correct password to open 
	        the document. Valid only if passwordProtected is true. The other 
	        properties shown below of the response are valid only if 
	        passwordProtected is false or this property is set to true
	      pageCount: Number of pages contained in the document. Note that this 
	        property may not be valid for certain types of documents (ex. 
	        flow-based documents such as DOCX)
	    */
	  })
	  .fail(function(httpStatusCode, httpErrorMessage, response) { /* Handle error */ });
	
	
	
	starDocs.storage.download(documentUrl)
	  .done(function() {
	    /* Success */
	  })
	  .fail(function(httpStatusCode, httpErrorMessage, response) {
	    /* Handle error */ 
	  });
	</script>
	
	<a href="${docUrl}" >Open A Word Document</a>
			<iframe style="height:100%" src='https://view.officeapps.live.com/op/embed.aspx?src=http%3A%2F%2Fieee802%2Eorg%3A80%2Fsecmail%2FdocIZSEwEqHFr%2Edoc' width='100%' height='100%' frameborder='0'>This is an embedded <a target='_blank' href='http://office.com'>Microsoft Office</a> document, powered by <a target='_blank' href='http://office.com/webapps'>Office Online</a>.</iframe>
	
	
</div>
