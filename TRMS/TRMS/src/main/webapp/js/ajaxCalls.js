$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "AjaxServlet",
		action: "reimbursements_for_employee"
	});
});