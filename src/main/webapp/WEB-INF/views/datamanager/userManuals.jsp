       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- Page Summary Widget-->
    <c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<div class="l-page-header">
	<h2 class="l-page-title">
		<span>User Manuals</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Home</a></li>
		<li class="active">User Manuals</li>
	</ul>
</div>
<div class="l-box no-border">
	<div class="l-box l-spaced-bottom">
    
    <div id="tables" class="resp-tabs-skin-1">
				<ul class="resp-tabs-list">
					<li id="nlist">User Manuals</li>
				</ul>

				<div class="resp-tabs-container">
					<!-- Default Table-->
					<div>
						      <!-- slider -->
				          <div class="l-row l-spaced">
				            <div class="row">
				            <div class="col-md-12" style="text-align:center">
				            	<div class="col-md-4">
				            		<h3> Sponsor Version - <a href="http://trials.cliniv.in/data/userManual/user-man.pdf" target="_blank"> VIEW </a></h3>
				            	</div>
				            	<div class="col-md-4">
				            		<h3> Investigator Version - <a href="http://trials.cliniv.in/data/userManual/user-man.pdf" target="_blank"> VIEW </a></h3>
				            	</div>
				            	<div class="col-md-4">
				            		<h3> Patient Version - <a href="http://trials.cliniv.in/data/userManual/user-man.pdf" target="_blank"> VIEW </a></h3>
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
          $('#id').on('click', function() {
        	    $('#image').trigger('click');
        	   
        	});
          </script>
        <!-- slider -->
      <!-- Row 5 - Gender, Age-->
        

