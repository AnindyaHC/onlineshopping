
var mainApp = angular.module("customerApp", []);


mainApp.controller('customerController', function($scope, $http) {

	
	

	$http.get("http://localhost:8080/getCustomers/").then(function(response) {
		$scope.customerList = response.data;

	});

});