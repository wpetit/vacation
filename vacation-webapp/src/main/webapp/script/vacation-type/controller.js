// Create a controller with name VacationsCtrl to bind to the html page.
vacationAppControllers.controller('VacationTypeCtrl', function($scope,
		$location, vacationTypeService) {

	// initialization
	$scope.beginDate = new Date();
	$scope.endDate = new Date();
	$scope.dateFormat = 'dd/MM';

	$scope.openEndDatePicker = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.endDatePickerOpened = true;
	};

	$scope.openBeginDatePicker = function($event) {
		$event.preventDefault();
		$event.stopPropagation();
		$scope.beginDatePickerOpened = true;
	};

	// Refresh the grid, calling the appropriate service method.
	$scope.save = function() {
		vacationTypeService.saveVacationType($scope.name,
				$scope.beginDate.getDate(), $scope.beginDate.getMonth() + 1,
				$scope.endDate.getDate(), $scope.endDate.getMonth() + 1,
				$scope.numberOfDays).success(function(data) {
			$scope.name = null;
			$scope.beginDate = new Date();
			$scope.endDate = new Date();
			$scope.numberOfDays = null;
		});
	};

	$scope.cancel = function() {
		$location.path('/vacationTypes/list');
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
		field : "type",
		direction : "asc"
	};

	$scope.goToCreateVacationType = function() {
		$location.path('/vacationTypes/create');
	};

	$scope.getAll();

});
