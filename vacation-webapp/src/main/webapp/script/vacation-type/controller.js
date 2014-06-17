// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers.controller('VacationTypeCtrl', function($scope, $location, vacationTypeService) {
	// Refresh the grid, calling the appropriate service method.
	$scope.save = function() {
		vacationTypeService
				.saveVacationType($scope.name, $scope.beginDay, $scope.beginMonth, $scope.endDay,$scope.endMonth, $scope.numberOfDays)
				.success(function(data) {
					$scope.name = null;
					$scope.beginDay = null;
					$scope.beginMonth = null;
					$scope.endDay = null;
					$scope.endMonth = null;
					$scope.numberOfDays = null;
				});
	};
	
	$scope.cancel = function() {
		$location.path( '/vacationTypes/list' );
	};
	
	$scope.getAll = function() {
		vacationTypeService.getVacationTypeList().success(function(data) {
			$scope.vacationTypes = data;
		});
	};
	
	$scope.sortBy = function(attribute) {
		
	};
	
	// sort
	$scope.sortOptions = {
		field : "type" ,
		direction : "asc" 
	};
	
	$scope.goToCreateVacationType = function() {
		$location.path( '/vacationTypes/create' );
	};
	
	$scope.getAll();

});

