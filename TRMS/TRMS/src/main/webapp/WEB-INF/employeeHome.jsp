<%@page import="daoObjects.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="js/angular.min.js"></script>
<script>
	var app = angular.module('myApp', []);
	
	function MyController($scope, $http) {
		$scope.getDataFromServer = function() {
			$http({
				method : 'GET',
				url : 'http://localhost:8085/trms/ReimbursementServlet'
			}).success(function(data, status, headers, config) {
				console.log("success");
				$scope.reimbursements = data;
			}).error(function(data, status, headers, config) {
				console.log("error");
				location.href = "http://localhost:8085/trms/login";
			});
		};
	};
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<h1 id="welcome">Welcome {{employee}}</h1>
	</div>
	<div ng-app="myApp">
		<div ng-controller="MyController" ng-init="getDataFromServer()")>
			<!-- <button ng-click="getDataFromServer()">Fetch data from server</button> -->
			<table border="1">
				<tr>
					<th>Date Submitted</th>
					<th>Event Name</th>
					<th>Work Time Missed</th>
					<th>Justification</th>
					<th>Projected Amount</th>
					<th>Status</th>
					<th>Amount Awarded</th>
				</tr>
				<tr ng-repeat="reimbursement in reimbursements">
					<td>{{reimbursement.dateSubmitted}}</td>
					<td></td>
					<td>{{reimbursement.workTimeMissed}}</td>
					<td>{{reimbursement.justification}}</td>
					<td>{{reimbursement.projectedAmount | currency}}</td>
					<td>{{reimbursement.reimbursementStatus}}</td>
					<td>{{reimbursement.amountAwarded | currency}}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>