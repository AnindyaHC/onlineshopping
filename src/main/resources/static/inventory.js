var mainApp = angular.module("inventoryApp", []);

mainApp.service('customerService', function() {
    this.customer = function(customerInfo) {
       alert("inventoryapp customerservice");
       this.customer.customerName = customerInfo.name;
       alert(this.customer.customerName);
    }
 });

mainApp.controller('inventoryController', function($scope, $http) {

	$scope.submit = function() {

		var request = $http({
			method : "post",
			url : "http://localhost:8080/addInventory/",
			data : {
				newProduct : {
					productName : $scope.productName,
					productType : $scope.productType,
					manufacturer : $scope.productManufaturer,
					unitPrice : $scope.unitPrice,
					margin : $scope.margin,
					// stockBatchDate: $scope.stockBatchDate
				},				
				quantity : $scope.quantity,
			
			}
		}).then(function(response) {
			alert("Data Saved");
			window.location.href = 'http://localhost:8080/inventory.html';
		});

	}

	$http.get("http://localhost:8080/getInventories/").then(function(response) {
		$scope.inventories = response.data;

	});

	$scope.addProduct = function() {
		alert("Adding Product");
		window.location.href = 'http://localhost:8080/addInventory.html';

	}

	$scope.updateInventory = function(inventory) {
		var request = $http({
			method : "post",
			url : "http://localhost:8080/addInventory/",
			data : {
				//  alert(inventory.newProduct.productId);
				existingProductId : inventory.newProduct.productId,
				newQuantity : inventory.newQuantity				
			}
		}).then(
				function(response) {
					alert("Data Saved");
					$http.get("http://localhost:8080/getInventories/").then(
							function(response) {
								$scope.inventories = response.data;

							});
				});

	}

	
	$scope.transactionList = [];
	$scope.addTransaction = function(inventory) {
		var quantity = 1;
		if(inventory.quantity > 0){
			inventory.quantity = inventory.quantity - 1;
			//var totalPrice = 0;
			var transaction = {
					productId: inventory.newProduct.productId,
					productName: inventory.newProduct.productName,
					productManufacturer: inventory.newProduct.manufacturer,
					quantity: quantity,
					unitPrice: inventory.newProduct.unitPrice,
					costPrice: inventory.newProduct.unitPrice * quantity
			}
			for(var i = 0; i < $scope.transactionList.length; i++){
				if($scope.transactionList[i].productId == inventory.newProduct.productId){
					$scope.transactionList[i].quantity++; 
					$scope.transactionList[i].costPrice = $scope.transactionList[i].unitPrice * $scope.transactionList[i].quantity;
					summarizeTransaction();
					calculateMarginPrice();
					return;
				}
			}
			
			$scope.transactionList.push(transaction);
		}else{
			alert("NO Stock");
		}
		summarizeTransaction();
		calculateMarginPrice();
	}
	
	$scope.totalPayment = 0;
	summarizeTransaction = function(){
		//alert("Inside");
		$scope.totalPayment = 0;
			for(var i = 0; i < $scope.transactionList.length; i++){
				$scope.totalPayment = $scope.totalPayment + $scope.transactionList[i].costPrice;
			}
		};
		
	$scope.marginPrice = 0;
	calculateMarginPrice = function(){
		$scope.marginPrice = 0;
		for(var i = 0; i < $scope.transactionList.length; i++){
			for(var k = 0; k < $scope.inventories.length; k++){
				if($scope.transactionList[i].productId == $scope.inventories[k].newProduct.productId){
					$scope.marginPrice = $scope.marginPrice + ($scope.transactionList[i].unitPrice * $scope.inventories[k].newProduct.margin) / 100;
					break;
				}
			}
		}
		$scope.marginPrice = $scope.totalPayment - $scope.marginPrice;
	};
	
	$scope.saveTransaction = function(){
		if($scope.finalPayment > $scope.marginPrice){
			alert("Saving");
			var request = $http({
				method : "post",
				url : "http://localhost:8080/createTransaction/",
				data : {
					checkedOutProductList : $scope.transactionList,
					finalPayment : $scope.finalPayment,
					customerInfo : $scope.customerInfo,
					loan : $scope.loan
				}
			}).then(function(response) {
				alert("Data Saved");
				window.location.href = 'http://localhost:8080/inventory.html';
			});

		}else{
			alert("Final Payment should be larger than marginPrice");
		}
	};
	

	
	$scope.findCustomerByContactNumber = function(){
		alert("Finding Customer");
		$http.get("http://localhost:8080/getCustomersByContactNumber/" + $scope.customerInfo.contactNumber).then(function(response) {
			$scope.customerInfo = response.data;

		});
	};

});