
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty  deviceMsg}">
	<div class="notificationmsg">${deviceMsg}</div>
</c:if>
<script src="http://code.highcharts.com/highcharts.js"></script>

<div class="l-page-header">
	<h2 class="l-page-title">
		<span>Monitoring Reports/Study Status</span>
	</h2>
	<!--BREADCRUMB-->
	<ul class="breadcrumb t-breadcrumb-page">
		<li><a href="javascript:void(0)">Report Analytics</a></li>
		<li class="active">Monitoring Reports/Study Status</li>
	</ul>
</div>
<div class="l-box no-border">
	<!-- Vertical Scroll Table-->
	<div class="l-row l-spaced-bottom">
		<div class="l-box">
			<div class="l-box-body l-spaced">
				<div class="row">
					<div class="col-sm-6" style="display:none">
						<label for="userName-v" class="col-sm-12 control-label">
							Please Select Study<font color="red">*</font>
						</label>
						<div class="col-sm-12 custom-select">
							<select name="studyIds" id="studyIds" size="8" tabindex="1"
								style="width: 100%;" required="required" onchange="getAllData()">
								<c:forEach var="studyList" items="${studyList}">
									<option value="${studyList.studyId}"
										style="padding: 8px 14px; background: lightgray; cursor: pointer; border-bottom: 1px solid #d8d8d8;">${studyList.studyName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-sm-12 grid-margin stretch-card" id="displayData"
						style="display: none">
						<div class="card">
							<div class="p-4 border-bottom bg-light">
								<h4 class="card-title mb-0 text-center">Study Patients</h4>
							</div>
							<div class="card-body">
								<div
									class="d-flex justify-content-between align-items-center pb-4">
									<h4 class="card-title mb-0">Visit Status</h4>
									<div id="stacked-bar-traffic-legend"></div>
								</div>
								<canvas id="stackedbarChart" style="min-height: 250px"></canvas>
							</div>
						</div>
					</div>

				</div>
			</div>

		</div>
	</div>



</div>

<script>
$(document).ready(function(){	
	getAllData()
});
</script>

<script>
	function getAllData() {
		var selectedValues = '1';
/* 		var selectedValues = $('#studyIds').val();
 */		var type = 'ALL';
		$("#studyIds").val(selectedValues);
		
		$.ajax({
			url : 'visitReportsByStudy.do',
			type : 'GET',
			data : 'studyId=' + selectedValues + '&type=' + type,
			success : function(data) {
				$('#displayData').css('display', 'block');
				$('#stackedbarChart').append(data);
				loadGraph(data);
			},
			error : function(e) {
			}
		});

	}
	function loadGraph(sData){
		if ($("#stackedbarChart").length) {
			
		    var stackedbarChartCanvas = $("#stackedbarChart").get(0).getContext("2d");
		    var stackedbarChart = new Chart(stackedbarChartCanvas, {
		      type: 'bar',
		      data: {
		        labels: sData.visitNames,
		        datasets: [{
		            label: "INCOMPLETE",
		            backgroundColor: ChartColor[0],
		            borderColor: ChartColor[0],
		            borderWidth: 1,
		            data: sData.openCountValues
		          },
		          {
		            label: "ONGOING",
		            backgroundColor: ChartColor[1],
		            borderColor: ChartColor[1],
		            borderWidth: 1,
		            data: sData.ongoingCountValues
		          },
		          {
		            label: "COMPLETED",
		            backgroundColor: ChartColor[2],
		            borderColor: ChartColor[2],
		            borderWidth: 1,
		            data: sData.completedCountValues
		          }
		        ]
		      },
		      options: {
		        responsive: true,
		        maintainAspectRatio: true,
		        legend: false,
		        categoryPercentage: 0.5,
		        stacked: true,
		        layout: {
		          padding: {
		            left: 0,
		            right: 0,
		            top: 0,
		            bottom: 0
		          }
		        },
		        scales: {
		          xAxes: [{
		            display: true,
		            scaleLabel: {
		              display: true,
		              labelString: 'List Of Visits',
		              fontSize: 12,
		              lineHeight: 2
		            },
		            ticks: {
		              fontColor: '#bfccda',
		              stepSize: 50,
		              min: 0,
		              max: sData.maxCount,
		              autoSkip: true,
		              autoSkipPadding: 15,
		              maxRotation: 0,
		              maxTicksLimit: 10
		            },
		            gridLines: {
		              display: false,
		              drawBorder: false,
		              color: 'transparent',
		              zeroLineColor: '#eeeeee'
		            }
		          }],
		          yAxes: [{
		            display: true,
		            scaleLabel: {
		              display: true,
		              labelString: 'Number of users',
		              fontSize: 12,
		              lineHeight: 2
		            },
		            ticks: {
		              fontColor: '#bfccda',
		              stepSize: 50,
		              min: 0,
		              max: sData.maxCount,
		              autoSkip: true,
		              autoSkipPadding: 15,
		              maxRotation: 0,
		              maxTicksLimit: 10
		            },
		            gridLines: {
		              drawBorder: false
		            }
		          }]
		        },
		        legend: {
		          display: false
		        },
		        legendCallback: function (chart) {
		          var text = [];
		          text.push('<div class="chartjs-legend"><ul>');
		          for (var i = 0; i < chart.data.datasets.length; i++) {
		            console.log(chart.data.datasets[i]); // see what's inside the obj.
		            text.push('<li>');
		            text.push('<span style="background-color:' + chart.data.datasets[i].backgroundColor + '">' + '</span>');
		            text.push(chart.data.datasets[i].label);
		            text.push('</li>');
		          }
		          text.push('</ul></div>');
		          return text.join("");
		        },
		        elements: {
		          point: {
		            radius: 0
		          }
		        }
		      }
		    });
		    document.getElementById('stacked-bar-traffic-legend').innerHTML = stackedbarChart.generateLegend();
		  }
		}
	
	 
</script>


